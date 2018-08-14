package dao.def;

import dao.base.TableBase;

public class T_USER extends TableBase<T_USER, T_USER_COL<?>> {

    @Override
    public String tableName() {
        return this.getClass().getSimpleName();
    }

    public String selectByPK(String userid) {
        super.clearWhere() //
                .where(T_USER_COL.ID, userid);
        return super.select();
    }

}
