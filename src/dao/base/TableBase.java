package dao.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TableBase<T extends ColBase<?>> {

    public abstract String tableName();

    private List<String> selectCols = new ArrayList<String>(0);

    public TableBase<T> col(T col) {
        if (!selectCols.contains(col.name())) {
            selectCols.add(col.name());
        }
        return this;
    }

    private List<WhereKV> where = new ArrayList<WhereKV>(0);

    public TableBase<T> where(T col, String value) {
        this.where(col.name(), value);
        return this;
    }

    public TableBase<T> where(T col, Long value) {
        this.where(col.name(), value);
        return this;
    }

    public TableBase<T> where(T col, Date value) {
        this.where(col.name(), value);
        return this;
    }

    public TableBase<T> where(T col, BigDecimal value) {
        this.where(col.name(), value);
        return this;
    }

    private void where(String col, Object value) {
        where.add(WhereKV.$(col, value));
    }

    public String select() {
        return this.select(false);
    }

    public String select(boolean showParam) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(selectCols.get(0));
        for (int ii = 1; ii < selectCols.size(); ii++) {
            sb.append(",").append(selectCols.get(ii));
        }
        sb.append(" FROM ").append(tableName()) //
                .append(" where 1=1");
        for (WhereKV kv : where) {
            if (showParam) {
                sb.append(String.format(" and %s = %s", kv.key, kv.val));
            } else {
                sb.append(String.format(" and %s = ?", kv.key));
            }
        }
        return sb.toString();
    }

    public void showParam() {
        for (WhereKV kv : where) {
            System.out.println(kv);
        }
    }
}
