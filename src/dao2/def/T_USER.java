package dao2.def;

import java.util.Date;

import dao2.base.TblBase;

public class T_USER extends TblBase<T_USER_COL<?>> {

    public T_USER() {
        super("T_USER");
    }

    final public String selectByPrimaryKey(String userId, Date registeDate) {
        T_USER_COL.USER_ID.where(this, userId);
        T_USER_COL.REG_DT.where(this, registeDate);
        return super.select(T_USER_COL.USER_ID);
    }

}
