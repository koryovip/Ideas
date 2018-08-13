package dao.def;

import dao.base.ColBase;

public class T_BOOK_COL<T> extends ColBase<T> {

    private final String name;

    public T_BOOK_COL(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return this.name;
    }

    public static final T_BOOK_COL<String> ID = new T_BOOK_COL<String>("ID");

    //    @Override
    //    public T_BOOK_COL<T> eq(T value) {
    //        return this;
    //    }

}
