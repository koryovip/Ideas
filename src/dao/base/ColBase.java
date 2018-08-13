package dao.base;

public abstract class ColBase<T> {

    public abstract String name();

    //    abstract public ColBase<T> eq(T value);

    //    @SuppressWarnings("unchecked")
    //    public Class<T> getType() {
    //        // 実行時の型が取れる。ここではHogeDaoなど
    //        Class<?> clazz = this.getClass();
    //        // ここではBaseDao<Hoge>がとれる
    //        Type type = clazz.getGenericSuperclass();
    //        ParameterizedType pt = (ParameterizedType) type;
    //        // BaseDaoの型変数に対するバインドされた型がとれる
    //        Type[] actualTypeArguments = pt.getActualTypeArguments();
    //        /*Class<?> entityClass =*/ return (Class<T>) actualTypeArguments[0];
    //        // リフレクションでインスタンスを生成
    //        //  return (T) entityClass.newInstance();
    //    }
    //
    //    @SuppressWarnings("unchecked")
    //    public T getType2() throws InstantiationException, IllegalAccessException {
    //        // 実行時の型が取れる。ここではHogeDaoなど
    //        Class<?> clazz = this.getClass();
    //        // ここではBaseDao<Hoge>がとれる
    //        Type type = clazz.getGenericSuperclass();
    //        ParameterizedType pt = (ParameterizedType) type;
    //        // BaseDaoの型変数に対するバインドされた型がとれる
    //        Type[] actualTypeArguments = pt.getActualTypeArguments();
    //        Class<?> entityClass = (Class<T>) actualTypeArguments[0];
    //        // リフレクションでインスタンスを生成
    //        return (T) entityClass.newInstance();
    //    }

}
