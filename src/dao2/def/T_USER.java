package dao2.def;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao2.base.TblBase;

public class T_USER extends TblBase<T_USER_COL<?>> {

    public T_USER() {
        super("T_USER");
    }

    final public static T_USER $() {
        return new T_USER();
    }

    private List<T_USER_COL<?>> columns = null;

    @Override
    public List<T_USER_COL<?>> columnAll() {
        if (columns == null) {
            columns = new ArrayList<T_USER_COL<?>>();
        }
        columns.add(T_USER_COL.USER_ID);
        columns.add(T_USER_COL.SCORE);
        columns.add(T_USER_COL.REG_DT);
        return columns;
    }

    final public String selectByPrimaryKey(String userId, Date registeDate) {
        T_USER_COL.USER_ID.where(this, userId);
        T_USER_COL.REG_DT.where(this, registeDate);
        return super.select(T_USER_COL.USER_ID);
    }

}
