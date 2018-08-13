package dao.def;

import dao.base.TableBase;

public class T_USER extends TableBase<T_USER_COL<?>> {

    @Override
    public String tableName() {
        return this.getClass().getSimpleName();
    }

//    public <T> T_USER where(T_USER_COL<T> col, T value) {
//        // System.out.println(col.name() + "=" + value);
//        super.where(col.name(), value);
//        return this;
//    }

}
