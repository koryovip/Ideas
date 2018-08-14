package dao2.base;

import java.util.ArrayList;
import java.util.List;

public abstract class TblBase<C> {

    private final String name;

    public TblBase(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    @Deprecated
    private List<String> selectColumnList = new ArrayList<String>();

    @Deprecated
    protected <T> T col(ColBase<?, ?> col, T val) {
        selectColumnList.add(col.name());
        return null;
    }

    final private List<String> setCol = new ArrayList<String>();
    final private List<Object> setVal = new ArrayList<Object>();

    protected <T> T set(ColBase<?, ?> col, T val) {
        setCol.add(col.name());
        setVal.add(val);
        return null;
    }

    private List<String> whereList = new ArrayList<String>();
    private List<Object> whereValList = new ArrayList<Object>();

    protected <T> T where(ColBase<?, ?> col, T val) {
        whereList.add(col.name() + " = ?");
        whereValList.add(val);
        return null;
    }

    public final String select(C col1, C... col2) {
        StringBuilder sb = new StringBuilder("SELECT ");
        /*
        if (selectColumnList.size() <= 0) {
            sb.append("*");
        } else {
            sb.append(selectColumnList.get(0));
            for (int ii = 1; ii < selectColumnList.size(); ii++) {
                sb.append(", ").append(selectColumnList.get(ii));
            }
        }*/
        sb.append(((ColBase<?, ?>) col1).name());
        for (C c : col2) {
            sb.append(", ").append(((ColBase<?, ?>) c).name());
        }
        sb.append(" FROM ") //
                .append(this.name) //
                .append(" WHERE 1=1");
        for (String where : whereList) {
            sb.append(" AND ").append(where);
        }
        return sb.toString();
    }

    public List<Object> getParams1() {
        return this.setVal;
    }

    public List<Object> getParams2() {
        return this.whereValList;
    }

    public List<Object> getParams3() {
        List<Object> result = new ArrayList<Object>();
        result.addAll(this.setVal);
        result.addAll(this.whereValList);
        return result;
    }

    public void showParam() {
        for (Object obj : setVal) {
            System.out.println(obj);
        }
        for (Object obj : whereValList) {
            System.out.println(obj);
        }
    }

    public final String insert() {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(this.name);
        sb.append(" (").append(setCol.get(0));
        for (int ii = 1; ii < setCol.size(); ii++) {
            sb.append(", ").append(setCol.get(ii));
        }
        sb.append(") VALUES (?");
        for (int ii = 1; ii < setCol.size(); ii++) {
            sb.append(", ?");
        }
        sb.append(")");
        return sb.toString();
    }

    public final String update() {
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(this.name).append(" SET ");
        sb.append(setCol.get(0)).append("= ?");
        for (int ii = 1; ii < setCol.size(); ii++) {
            sb.append(", ").append(setCol.get(ii)).append(" = ?");
        }
        sb.append(" WHERE 1=1");
        for (String where : whereList) {
            sb.append(" AND ");
            sb.append(where);
        }
        return sb.toString();
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
