package tool.generator;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import utils.IOUtils;
import utils.Prop;

public class PADBGenerator extends BaseGenerator {

    public static void main(String[] args) throws Exception {
        String dbURL = Prop.me().get("db.url");
        String user = Prop.me().get("db.user");
        String pass = Prop.me().get("db.pass");
        String schema = Prop.me().get("db.schema");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbURL, user, pass);
            conn.setAutoCommit(false);

            PADBGenerator g = new PADBGenerator();
            g.execute(conn, schema);

        } finally {
            conn.close();
        }
    }

    public void execute(final Connection conn, String schema) throws SQLException, Exception {
        DatabaseMetaData dmd = conn.getMetaData();

        // ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("src/pa/tmpl");
        FileResourceLoader resourceLoader = new FileResourceLoader("template", "utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template tblTempl = gt.getTemplate("/T_USER.java");
        Template colTempl = gt.getTemplate("/T_USER_COL.java");
        Template dtoTempl = gt.getTemplate("/T_USER_DTO.java");
        {
            String version = "1.0";
            tblTempl.binding("version", version);
            colTempl.binding("version", version);
            dtoTempl.binding("version", version);
        }
        {
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timeStr = time.format(formatter);
            tblTempl.binding("create_time", timeStr);
            colTempl.binding("create_time", timeStr);
            dtoTempl.binding("create_time", timeStr);
        }
        final String dirBase = Prop.me().get("root.dir");

        this.execute9(dmd, schema, dirBase, tblTempl, colTempl, dtoTempl);
    }

    private void execute9(final DatabaseMetaData dmd, final String schema, final String dirBase //
            , final Template tblTempl, final Template colTempl, final Template dtoTempl) throws SQLException {
        final String basePackageStr = "pa.db." + schema;
        final String tblPackageStr = basePackageStr + ".tbl";
        final String colPackageStr = basePackageStr + ".col";
        final String dtoPackageStr = basePackageStr + ".dto";

        // final String dirBase = "src/";
        final String tblFileDir = dirBase + tblPackageStr.replaceAll("\\.", "/") + "/";
        final String colFileDir = dirBase + colPackageStr.replaceAll("\\.", "/") + "/";
        final String dtoFileDir = dirBase + dtoPackageStr.replaceAll("\\.", "/") + "/";

        new File(tblFileDir).mkdirs();
        new File(colFileDir).mkdirs();
        new File(dtoFileDir).mkdirs();

        tblTempl.binding("PACKAGE", tblPackageStr);
        tblTempl.binding("PACKAGE_COL", colPackageStr);
        colTempl.binding("PACKAGE", colPackageStr);
        colTempl.binding("PACKAGE_TBL", tblPackageStr);
        dtoTempl.binding("PACKAGE", dtoPackageStr);

        int tblCount = 1;
        ResultSet tblRs = dmd.getTables(null, schema, "%", new String[] { "TABLE"/*, "VIEW"*/ });
        while (tblRs.next()) {
            String TABLE_NAME = tblRs.getString("TABLE_NAME");
            System.out.println(String.format("[%03d]%s", tblCount++, TABLE_NAME));
            tblTempl.binding("TABLE_NAME", TABLE_NAME);
            colTempl.binding("TABLE_NAME", TABLE_NAME);
            dtoTempl.binding("TABLE_NAME", TABLE_NAME);

            int colCount = 0;
            final List<COL_INFO> colInfoList = new ArrayList<COL_INFO>();
            ResultSet colRs = dmd.getColumns(null, schema, TABLE_NAME, "%");
            while (colRs.next()) {
                String COLUMN_NAME = colRs.getString("COLUMN_NAME");
                String TYPE_NAME = colRs.getString("TYPE_NAME");
                String IS_NULLABLE = colRs.getString("IS_NULLABLE");
                String JAVA_TYPE = typeToStr(toJavaType(TYPE_NAME));
                int COLUMN_SIZE = colRs.getInt("COLUMN_SIZE");

                colInfoList.add(new COL_INFO(COLUMN_NAME, TYPE_NAME, IS_NULLABLE, JAVA_TYPE, COLUMN_SIZE));
                colCount++;
            }
            colRs.close();
            tblTempl.binding("COL_INFO_LIST", colInfoList);
            colTempl.binding("COL_INFO_LIST", colInfoList);
            dtoTempl.binding("COL_INFO_LIST", colInfoList);

            tblTempl.binding("COL_COUNT", colCount);
            colTempl.binding("COL_COUNT", colCount);
            dtoTempl.binding("COL_COUNT", colCount);

            {
                IOUtils.me().write(tblTempl.render(), tblFileDir + TABLE_NAME + ".java");
            }
            {
                IOUtils.me().write(colTempl.render(), colFileDir + TABLE_NAME + "_COL" + ".java");
            }
            {
                IOUtils.me().write(dtoTempl.render(), dtoFileDir + TABLE_NAME + "_DTO" + ".java");
            }
        }
        tblRs.close();
    }

    class COL_INFO {
        public String COLUMN_NAME;
        public String TYPE_NAME;
        public String IS_NULLABLE;
        public String JAVA_TYPE;
        public int COLUMN_SIZE;

        private String COLUMN_NAME_LOW = null;
        private String COLUMN_NAME_UP = null;

        public COL_INFO(String COLUMN_NAME, String TYPE_NAME, String IS_NULLABLE, String JAVA_TYPE, int COLUMN_SIZE) {
            super();
            this.COLUMN_NAME = COLUMN_NAME;
            this.TYPE_NAME = TYPE_NAME;
            this.IS_NULLABLE = IS_NULLABLE;
            this.JAVA_TYPE = JAVA_TYPE;
            this.COLUMN_SIZE = COLUMN_SIZE;
        }

        public String getCOLUMN_NAME() {
            return COLUMN_NAME;
        }

        public String getTYPE_NAME() {
            return TYPE_NAME;
        }

        public String getIS_NULLABLE() {
            return IS_NULLABLE;
        }

        public String getJAVA_TYPE() {
            return JAVA_TYPE;
        }

        public int getCOLUMN_SIZE() {
            return COLUMN_SIZE;
        }

        public String getCOLUMN_NAME_LOW() {
            if (COLUMN_NAME_LOW == null) {
                COLUMN_NAME_LOW = underlineToHump(COLUMN_NAME, true);
            }
            return COLUMN_NAME_LOW;
        }

        public String getCOLUMN_NAME_UP() {
            if (COLUMN_NAME_UP == null) {
                COLUMN_NAME_UP = underlineToHump(COLUMN_NAME, false);
            }
            return COLUMN_NAME_UP;
        }

    }
}
