package ${PACKAGE};

import ${PACKAGE_TBL}.${TABLE_NAME};
import dao2.base.ColBase;
import dao2.base.ano.SqlColInfo;

public class ${TABLE_NAME}_COL<V> extends ColBase<${TABLE_NAME}, V> {

    public ${TABLE_NAME}_COL(String name) {
        super(name);
    }
<%for(colInfo in COL_INFO_LIST) {%>
    @SqlColInfo(name = "${colInfo.COLUMN_NAME}", type = "${colInfo.TYPE_NAME}", size = ${colInfo.COLUMN_SIZE}, nullable = "${colInfo.IS_NULLABLE}")
    public static final ${TABLE_NAME}_COL<${colInfo.JAVA_TYPE}> ${colInfo.COLUMN_NAME} = new ${TABLE_NAME}_COL<${colInfo.JAVA_TYPE}>("${colInfo.COLUMN_NAME}") {
    };
<%}%>
}