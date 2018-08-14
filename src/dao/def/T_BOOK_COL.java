package dao.def;

import dao.base.ColBase;

public class T_BOOK_COL<T> extends ColBase<T> {

    public T_BOOK_COL(String name) {
        super(name);
    }

    public static final T_BOOK_COL<String> ID = new T_BOOK_COL<String>("ID") {
    };

    //    @Override
    //    public T_BOOK_COL<T> eq(T value) {
    //        return this;
    //    }

}
