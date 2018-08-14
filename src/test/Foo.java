package test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class Foo<T> {

    public Class<T> getTClass() {
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