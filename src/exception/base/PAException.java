package exception.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import exception.base.ano.PAExceptionMessage;

public abstract class PAException extends Throwable {

    private static final long serialVersionUID = 955466739582102010L;

    private Map<Class<? extends PAException>, Map<String, Method>> methodMap = new HashMap<Class<? extends PAException>, Map<String, Method>>();

    private final String code;
    private final String message;

    public PAException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    protected <E> E build(Class<E> expCls, Object... params) {
        Map<String, Method> methods = methodMap.get(expCls);
        if (methods == null) {
            Map<String, Method> list = new HashMap<String, Method>();
            for (Method m : expCls.getDeclaredMethods()) {
                list.put(m.getName(), m);
            }
            methodMap.put((Class<? extends PAException>) expCls, list);
        }
        Method m = methodMap.get(expCls).get(Thread.currentThread().getStackTrace()[2].getMethodName());
        if (m == null) {
            return null;
        }
        PAExceptionMessage msg = m.getAnnotation(PAExceptionMessage.class);
        if (msg == null) {
            return null;
        }
        try {
            return expCls.getConstructor(String.class, String.class).newInstance("", msg.log());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
