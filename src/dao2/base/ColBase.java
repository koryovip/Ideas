package dao2.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import dao2.base.enumerated.SqlOrder;
import dao2.base.enumerated.SqlWhereCondition;

public abstract class ColBase<T extends TblBase<?>, V> {

    final private String name;

    public ColBase(String name) {
        this.name = name;
    }

    final public String name() {
        return this.name;
    }

    @SuppressWarnings("unchecked")
    final public Class<T> getType() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        /*for (Type type : actualTypeArguments) {
            type.getTypeName();
        }*/
        return (Class<T>) actualTypeArguments[0];
    }

    /**
     * わざとwhereとパラメータの順番と違う。
     * @param val
     * @param tbl
     */
    final public void set(V val, T tbl) {
        tbl.set(this, val);
    }

    final public void where(T tbl, V val) {
        tbl.where(this, val, SqlWhereCondition.$00);
    }

    /**
     * Greater than
     * @param tbl
     * @param val
     */
    final public void where$10(T tbl, V val) {
        tbl.where(this, val, SqlWhereCondition.$10);
    }

    /**
     * Less than
     * @param tbl
     * @param val
     */
    final public void where$01(T tbl, V val) {
        tbl.where(this, val, SqlWhereCondition.$01);
    }

    /**
     * Not equal to
     * @param tbl
     * @param val
     */
    final public void where$AB(T tbl, V val) {
        tbl.where(this, val, SqlWhereCondition.$AB);
    }

    /**
     * Less than or Equal to
     * @param tbl
     * @param val
     */
    final public void where$0eq1(T tbl, V val) {
        tbl.where(this, val, SqlWhereCondition.$0eq1);
    }

    /**
     * Greater than or Equal to
     * @param tbl
     * @param val
     */
    final public void where$1eq0(T tbl, V val) {
        tbl.where(this, val, SqlWhereCondition.$1eq0);
    }

    final public void whereAsc(T tbl, V val) {
        this.where(tbl, val);
        this.asc(tbl);
    }

    final public void whereDesc(T tbl, V val) {
        this.where(tbl, val);
        this.desc(tbl);
    }

    /**
     * select * from table where col1 in (?,?,?)<br>
     * @param tbl
     * @param val1
     * @param vals
     */
    final public void whereIn(T tbl, V val1, @SuppressWarnings("unchecked") V... vals) {
        tbl.whereIn(this, val1, vals);
    }

    /**
     * select * from table where col1 like ?<br>
     * select * from table where col1 like 'abc%'<br>
     * select * from table where col1 like '%123%'<br>
     * @param tbl
     * @param val
     */
    final public void whereLike(T tbl, V val) {
        tbl.where(this, val, SqlWhereCondition.like);
    }

    final public void asc(T tbl) {
        tbl.orderBy(this, null, SqlOrder.ASC);
    }

    final public void desc(T tbl) {
        tbl.orderBy(this, null, SqlOrder.DESC);
    }

    final public void isNull(T tbl) {
        tbl.isNull(this, null);
    }

    final public void isNOTNull(T tbl) {
        tbl.isNOTNull(this, null);
    }
}
