package dao2.def;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao2.base.TblBase;

public final class T_USER extends TblBase<T_USER_COL<?>> {

    public T_USER() {
        this(null);
    }

    public T_USER(String fixedId) {
        super("T_USER", fixedId);
    }

    public final static T_USER $() {
        return new T_USER(null);
    }

    public final static T_USER $(String fixedId) {
        return new T_USER(fixedId);
    }

    private List<T_USER_COL<?>> columns = null;

    private final Byte lock = 0;

    @Override
    public final List<T_USER_COL<?>> columnAll() {
        if (columns == null) {
            synchronized (this.lock) {
                if (columns == null) {
                    List<T_USER_COL<?>> tmp = new ArrayList<T_USER_COL<?>>(this.colCount);
                    tmp.add(T_USER_COL.USER_ID);
                    tmp.add(T_USER_COL.SCORE);
                    tmp.add(T_USER_COL.REG_DT);
                    columns = tmp;
                }
            }
        }
        return columns;
    }

    public final String selectByPrimaryKey(String userId, Date registeDate) {
        T_USER_COL.USER_ID.where(this, userId);
        T_USER_COL.REG_DT.where(this, registeDate);
        return super.select(T_USER_COL.USER_ID);
    }

    private final int colCount = 3;
}
