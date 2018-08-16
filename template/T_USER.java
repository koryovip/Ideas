package ${PACKAGE};

import java.util.ArrayList;
import java.util.List;

import ${PACKAGE_COL}.${TABLE_NAME}_COL;
import dao2.base.TblBase;

public class ${TABLE_NAME} extends TblBase<${TABLE_NAME}_COL<?>> {

    public ${TABLE_NAME}() {
        super("${TABLE_NAME}");
    }

    public final static ${TABLE_NAME} $() {
        return new ${TABLE_NAME}();
    }

    private List<${TABLE_NAME}_COL<?>> columns = null;
    private final Byte lock = 0;
    private final int colCount = ${COL_COUNT};

    @Override
    public final List<${TABLE_NAME}_COL<?>> columnAll() {
        if (columns == null) {
            synchronized (this.lock) {
                if (columns == null) {
                    List<${TABLE_NAME}_COL<?>> tmp = new ArrayList<${TABLE_NAME}_COL<?>>(this.colCount);
<%for(colInfo in COL_INFO_LIST) {%>
                    tmp.add(${TABLE_NAME}_COL.${colInfo.COLUMN_NAME});
<%}%>
                    columns = tmp;
                }
            }
        }
        return columns;
    }

}