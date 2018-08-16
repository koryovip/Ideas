package ${PACKAGE};

public final class ${TABLE_NAME}_DTO {
<%for(colInfo in COL_INFO_LIST) {%>
    private ${colInfo.JAVA_TYPE} ${colInfo.COLUMN_NAME_LOW};
<%}%>
<%for(colInfo in COL_INFO_LIST) {%>
    final public void set${colInfo.COLUMN_NAME_UP}(${colInfo.JAVA_TYPE} ${colInfo.COLUMN_NAME_LOW}) {
        this.${colInfo.COLUMN_NAME_LOW} = ${colInfo.COLUMN_NAME_LOW};
    }
<%}%>
<%for(colInfo in COL_INFO_LIST) {%>
    final public ${colInfo.JAVA_TYPE} get${colInfo.COLUMN_NAME_UP}() {
        return this.${colInfo.COLUMN_NAME_LOW};
    }
<%}%>
    @Override
    public String toString() {
        return "${TABLE_NAME}_DTO ${COL_INFO_LIST.~size} columns other.";
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
<%for(colInfo in COL_INFO_LIST) {%>
        sb.append("[${colInfo.COLUMN_NAME_LOW}=").append(this.${colInfo.COLUMN_NAME_LOW}).append("]");
<%}%>
        return sb.toString();
    }
}