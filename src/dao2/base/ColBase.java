package dao2.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ColBase<T extends TblBase<?>, V> {

    final private String name;

    public ColBase(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    public Class<T> getType() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        for (Type type : actualTypeArguments) {
            type.getTypeName();
            System.out.println(type);
        }
        @SuppressWarnings("unchecked")
        Class<T> tClass = (Class<T>) actualTypeArguments[0];
        return tClass;
    }

    /**
     * わざとwhereとパラメータの順番と違う。
     * @param val
     * @param tbl
     */
    public void set(V val, T tbl) {
        tbl.set(this, val);
    }

    public void where(T tbl, V val) {
        tbl.where(this, val);
    }

}
