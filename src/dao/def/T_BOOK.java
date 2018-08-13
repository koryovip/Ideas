package dao.def;

import dao.base.TableBase;

public class T_BOOK extends TableBase<T_BOOK_COL<?>> {

    @Override
    public String tableName() {
        return this.getClass().getSimpleName();
    }

}
