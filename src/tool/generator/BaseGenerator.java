package tool.generator;

public abstract class BaseGenerator {

    public final String underlineToHump(String para, boolean up) {
        StringBuilder result = new StringBuilder();
        String a[] = para.toUpperCase().split("_");
        for (String s : a) {
            if (up && result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /** MYSQL */
    public final Class<?> toJavaType(String columnType) {
        System.out.println(columnType);
        switch (columnType.toLowerCase()) {
        case "char":
        case "varchar":
        case "nvarchar":
            return String.class;
        case "int":
        case "int unsigned":
            return Integer.class;
        case "bigint":
            return Long.class;
        case "float":
            return Double.class;
        case "numeric":
            return java.math.BigDecimal.class;
        case "time":
            return java.sql.Time.class;
        case "date":
            return java.sql.Date.class;
        case "datetime":
            return java.sql.Timestamp.class;
        case "varbinary":
        case "image":
            return byte[].class;
        default:
            return Void.class;
        }
    }

    public final Class<?> toJavaTypeMSSQL(String columnType) {
        switch (columnType) {
        case "char":
        case "varchar":
        case "nvarchar":
            return String.class;
        case "int":
            return Integer.class;
        case "bigint":
            return Long.class;
        case "float":
            return Double.class;
        case "numeric":
            return java.math.BigDecimal.class;
        case "time":
            return java.sql.Time.class;
        case "date":
            return java.sql.Date.class;
        case "datetime":
            return java.sql.Timestamp.class;
        case "varbinary":
        case "image":
            return byte[].class;
        default:
            return Void.class;
        }
    }

    public final String typeToStr(Class<?> clazz) {
        if (clazz.isArray()) {
            return clazz.getComponentType() + "[]";
        }
        return clazz.getName();
    }

    protected void append(StringBuilder sb) {
        append(sb, null);
    }

    protected void append(StringBuilder sb, String value) {
        if (value != null) {
            sb.append(value);
        }
        sb.append("\r\n");
    }
}
