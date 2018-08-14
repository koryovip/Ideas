package dao2.base;

import java.util.ArrayList;
import java.util.List;

public abstract class TblBase {

    private final String name;

    public TblBase(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    private List<String> selectColumnList = new ArrayList<String>();

    protected <T> T col(ColBase<?, ?> col, T val) {
        selectColumnList.add(col.name());
        return null;
    }

    private List<String> whereList = new ArrayList<String>();
    private List<Object> whereValList = new ArrayList<Object>();

    protected <T> T where(ColBase<?, ?> col, T val) {
        // System.out.println(col.name() + "=" + val.toString());
        whereList.add(col.name() + " = ?");
        whereValList.add(val);
        return null;
    }

    public final String select(ColBase<?, ?>... col) {
        StringBuilder sb = new StringBuilder("SELECT ");
        if (selectColumnList.size() <= 0) {
            sb.append("*");
        } else {
            sb.append(selectColumnList.get(0));
            for (int ii = 1; ii < selectColumnList.size(); ii++) {
                sb.append(", ").append(selectColumnList.get(ii));
            }
        }
        sb.append(" FROM ") //
                .append(this.name) //
                .append(" WHERE 1=1");
        for (String where : whereList) {
            sb.append(" AND ").append(where);
        }
        return sb.toString();
    }

    public void showParam() {
        for (Object obj : whereValList) {
            System.out.println(obj);
        }
    }

    public final int insert() {
        return 0;
    }

    public final int update() {
        return 0;
    }

    public final String delete() {
        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append(this.name);
        sb.append(" WHERE 1=1");
        for (String where : whereList) {
            sb.append(" AND ");
            sb.append(where);
        }
        return sb.toString();
    }

}
