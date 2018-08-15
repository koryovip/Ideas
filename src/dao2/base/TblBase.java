package dao2.base;

import java.util.ArrayList;
import java.util.List;

import dao2.base.enumerated.SqlOrder;
import dao2.base.enumerated.SqlWhereCondition;

public abstract class TblBase<C> {

    private final String name;

    public TblBase(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    final private List<String> setCols = new ArrayList<String>();
    final private List<Object> setVals = new ArrayList<Object>();

    protected <T> T set(ColBase<?, ?> col, T val) {
        setCols.add(col.name());
        setVals.add(val);
        return null;
    }

    private List<String> whereCols = new ArrayList<String>();
    private List<SqlWhereCondition> whereCond = new ArrayList<SqlWhereCondition>(); // 条件：=,<>,>=,<=
    private List<Object> whereVals = new ArrayList<Object>();

    protected <T> T where(ColBase<?, ?> col, T val, SqlWhereCondition cond) {
        whereCols.add(col.name());
        whereCond.add(cond);
        whereVals.add(val);
        return null;
    }

    private List<String> orderCols = new ArrayList<String>();
    private List<SqlOrder> orderDire = new ArrayList<SqlOrder>(); // ソート方向。asc or desc

    protected <T> T orderBy(ColBase<?, ?> col, T val, SqlOrder order) {
        orderCols.add(col.name());
        orderDire.add(order);
        return null;
    }

    public List<Object> getParams1() {
        return this.setVals;
    }

    public List<Object> getParams2() {
        return this.whereVals;
    }

    public List<Object> getParams3() {
        List<Object> result = new ArrayList<Object>();
        result.addAll(this.setVals);
        result.addAll(this.whereVals);
        return result;
    }

    public void showParam() {
        for (Object obj : setVals) {
            System.out.println(obj);
        }
        for (Object obj : whereVals) {
            System.out.println(obj);
        }
    }

    public final String selectCount() {
        return select("SELECT COUNT(*", ") FROM ", false, null);
    }

    public final String selectCount(C col1) {
        return select("SELECT COUNT(", ") FROM ", true, col1);
    }

    @SafeVarargs
    public final String select(C col1, C... col2) {
        return select("SELECT ", " FROM ", true, col1, col2);
    }

    @SafeVarargs
    private final String select(final String SELECT, final String FROM, final boolean all, final C col1, final C... col2) {
        StringBuilder sb = new StringBuilder(SELECT);
        if (all) {
            sb.append(((ColBase<?, ?>) col1).name());
            for (C c : col2) {
                sb.append(", ").append(((ColBase<?, ?>) c).name());
            }
        }
        sb.append(FROM).append(this.name);
        this.whereSql(sb);
        return sb.toString();
    }

    abstract public List<C> columnAll();

    public final String selectColumnAll() {
        StringBuilder sb = new StringBuilder("SELECT ");
        List<C> cols = columnAll();
        sb.append(((ColBase<?, ?>) cols.get(0)).name());
        for (int ii = 1; ii < cols.size(); ii++) {
            sb.append(", ").append(((ColBase<?, ?>) cols.get(ii)).name());
        }
        sb.append(" FROM ").append(this.name);
        this.whereSql(sb);
        this.orderBySql(sb);
        return sb.toString();
    }

    public final String insert() {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(this.name);
        sb.append(" (").append(setCols.get(0));
        for (int ii = 1; ii < setCols.size(); ii++) {
            sb.append(", ").append(setCols.get(ii));
        }
        sb.append(") VALUES (?");
        for (int ii = 1; ii < setCols.size(); ii++) {
            sb.append(", ?");
        }
        sb.append(")");
        return sb.toString();
    }

    public final String update() {
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(this.name).append(" SET ");
        sb.append(setCols.get(0)).append("= ?");
        for (int ii = 1; ii < setCols.size(); ii++) {
            sb.append(", ").append(setCols.get(ii)).append(" = ?");
        }
        this.whereSql(sb);
        return sb.toString();
    }

    public final String delete() {
        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append(this.name);
        this.whereSql(sb);
        return sb.toString();
    }

    private void whereSql(StringBuilder sb) {
        sb.append(" WHERE 1=1");
        int index = 0;
        for (String where : whereCols) {
            sb.append(" AND ").append(where).append(" ").append(whereCond.get(index++).value()).append(" ?");
        }
    }

    private void orderBySql(StringBuilder sb) {
        if (orderCols.size() <= 0) {
            return;
        }
        sb.append(" ORDER BY ");
        sb.append(orderCols.get(0)).append(" ").append(orderDire.get(0).value());
        for (int ii = 1; ii < orderCols.size(); ii++) {
            sb.append(", ").append(orderCols.get(ii)).append(" ").append(orderDire.get(ii).value());
        }
    }
}
