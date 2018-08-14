package dao2.def;

import dao2.base.ColBase;

public class M_COMPANY_COL<V> extends ColBase<M_COMPANY, V> {

    public M_COMPANY_COL(String name) {
        super(name);
    }

    public static final M_COMPANY_COL<String> COMPANY_ID = new M_COMPANY_COL<String>("COMPANY_ID") {
    };

    public static final M_COMPANY_COL<String> COMPANY_NAME = new M_COMPANY_COL<String>("COMPANY_NAME") {
    };

    public static final M_COMPANY_COL<Long> COMPANY_XXXX = new M_COMPANY_COL<Long>("COMPANY_XXXX") {
    };

}
