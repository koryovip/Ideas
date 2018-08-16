package dao2.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao2.base.enumerated.SqlOrder;
import dao2.base.enumerated.SqlWhereCondition;

public abstract class TblBase<C> {

    private final String name;

    public TblBase(String name) {
        this.name = name;
    }

    final public String name() {
        return this.name;
    }

    // final private Map<String, String> setCols = new LinkedHashMap<String, String>();
    final private Map<String, Object> setKV = new LinkedHashMap<String, Object>();

    final protected <T> T set(ColBase<?, ?> col, T val) {
        setKV.put(col.name(), val);
        return null;
    }

    final private List<String> whereCols = new ArrayList<String>();
    final private List<SqlWhereCondition> whereCond = new ArrayList<SqlWhereCondition>(); // 条件：=,<>,>=,<=
    final private List<Object> whereVals = new ArrayList<Object>();

    final protected <T> T where(ColBase<?, ?> col, T val, SqlWhereCondition cond) {
        whereCols.add(col.name());
        whereCond.add(cond);
        whereVals.add(val);
        return null;
    }

    final protected <T> T whereIn(ColBase<?, ?> col, T val, @SuppressWarnings("unchecked") T... vals) {
        whereCond.add(SqlWhereCondition.in);
        whereCols.add(col.name() + " IN " + buildInStr(new StringBuilder(), vals.length + 1));
        whereVals.add(val);
        for (T v : vals) {
            whereVals.add(v);
        }
        return null;
    }

    final protected <T> T isNull(ColBase<?, ?> col, T val) {
        whereCond.add(SqlWhereCondition.isnull);
        whereCols.add(col.name());
        return null;
    }

    final protected <T> T isNOTNull(ColBase<?, ?> col, T val) {
        whereCond.add(SqlWhereCondition.isNOTnull);
        whereCols.add(col.name());
        return null;
    }

    /**
     * (?,?,?,?)みたいな文字列を作る。<br>
     * size > 0を保証して下さい。
     * @param size
     * @return
     */
    final private String buildInStr(StringBuilder sb, int size) {
        sb.append("(?");
        for (int ii = 1; ii < size; ii++) {
            sb.append(", ?");
        }
        sb.append(")");
        return sb.toString();
    }

    final private List<String> orderCols = new ArrayList<String>();
    final private List<SqlOrder> orderDire = new ArrayList<SqlOrder>(); // ソート方向。asc or desc

    final protected <T> T orderBy(ColBase<?, ?> col, T val, SqlOrder order) {
        orderCols.add(col.name());
        orderDire.add(order);
        return null;
    }

    final public Object[] getParams1() {
        return this.setKV.values().toArray();
    }

    final public List<Object> getParams2() {
        return this.whereVals;
    }

    final public List<Object> getParams3() {
        List<Object> result = new ArrayList<Object>();
        result.addAll(this.setKV.values());
        result.addAll(this.whereVals);
        return result;
    }

    final public void showParam() {
        for (Object obj : setKV.values()) {
            System.out.println(obj);
        }
        for (Object obj : whereVals) {
            System.out.println(obj);
        }
    }

    final public String selectCount() {
        return select("SELECT COUNT(*", ") FROM ", false, null);
    }

    final public String selectCount(C col1) {
        return select("SELECT COUNT(", ") FROM ", true, col1);
    }

    @SafeVarargs
    final public String select(C col1, C... col2) {
        return select("SELECT ", " FROM ", true, col1, col2);
    }

    @SafeVarargs
    final private String select(final String SELECT, final String FROM, final boolean all, final C col1, final C... col2) {
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

    final public String selectColumnAll() {
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

    final public String insert() {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(this.name);
        this.buildKVStr(sb, this.setKV, " (", ", ", ")");
        sb.append(" VALUES ");
        this.buildInStr(sb, setKV.size());
        return sb.toString();
    }

    private String buildKVStr(StringBuilder sb, Map<String, Object> kv, String prefix, String mid, String suffix) {
        // StringBuilder sb = new StringBuilder(prefix);
        sb.append(prefix);
        int index = 0;
        for (Entry<String, Object> entry : kv.entrySet()) {
            sb.append(index++ > 0 ? mid : "").append(entry.getKey());
        }
        sb.append(suffix);
        return sb.toString();
    }

    final public String update() {
        StringBuilder sb = new StringBuilder("UPDATE ").append(this.name).append(" SET ");
        this.buildKVStr(sb, this.setKV, "", " = ?, ", " = ?");
        this.whereSql(sb);
        return sb.toString();
    }

    final public String delete() {
        StringBuilder sb = new StringBuilder("DELETE FROM ").append(this.name);
        this.whereSql(sb);
        return sb.toString();
    }

    private void whereSql(StringBuilder sb) {
        sb.append(" WHERE 1=1");
        int index = 0;
        for (String whereCol : whereCols) {
            sb.append(" AND ").append(whereCol);
            final SqlWhereCondition condition = whereCond.get(index++);
            switch (condition) {
            case in:
                // 何もしない
                break;
            case isnull:
            case isNOTnull:
                sb.append(" ").append(condition.value());
                break;
            default:
                sb.append(" ").append(condition.value()).append(" ?");
                break;
            }
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
