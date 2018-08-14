package dao.def;

import java.util.Date;

import dao.base.ColBase;

public class T_USER_COL<T> extends ColBase<T> {

    public T_USER_COL(String name) {
        super(name);
    }

    public static final T_USER_COL<String> ID = new T_USER_COL<String>("ID") {
    };
    public static final T_USER_COL<String> PASS = new T_USER_COL<String>("PASS") {
    };
    public static final T_USER_COL<Long> REG_DT = new T_USER_COL<Long>("REG_DT") {
    };
    public static final T_USER_COL<Date> LAST_UPD = new T_USER_COL<Date>("LAST_UPD") {
    };

    //    @Override
    //    public T_USER_COL<T> eq(T value) {
    //        return this;
    //    }

    public static void main(String[] args) throws Exception {
        //        System.out.println(ID.getType2());
        //        System.out.println(REG_DT.getType2());
        //        System.out.println(LAST_UPD.getType2());
        //        ID.eq("");
        //        REG_DT.eq(100L);
        //        LAST_UPD.eq(new Date());
    }
}
