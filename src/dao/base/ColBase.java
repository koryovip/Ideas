package dao.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ColBase<T> {

    private final String name;

    public ColBase(String name) {
        this.name = name;
    }

    final public String name() {
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

}
