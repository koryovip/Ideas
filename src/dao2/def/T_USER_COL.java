package dao2.def;

import java.util.Date;

import dao2.base.ColBase;
import dao2.base.ano.SqlColInfo;

public class T_USER_COL<V> extends ColBase<T_USER, V> {

    public T_USER_COL(String name) {
        super(name);
    }

    @SqlColInfo(name = "USER_ID", type = "char", size = 10, nullable = "NO")
    public static final T_USER_COL<String> USER_ID = new T_USER_COL<String>("USER_ID") {
    };

    public static final T_USER_COL<Date> REG_DT = new T_USER_COL<Date>("REG_DT") {
    };

    public static final T_USER_COL<Integer> SCORE = new T_USER_COL<Integer>("SCORE") {
    };

}
