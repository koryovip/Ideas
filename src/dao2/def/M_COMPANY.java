package dao2.def;

import java.util.List;

import dao2.base.TblBase;

public class M_COMPANY extends TblBase<M_COMPANY_COL<?>> {

    public M_COMPANY() {
        super("M_COMPANY");
    }

    @Override
    public List<M_COMPANY_COL<?>> columnAll() {
        return null;
    }

}
