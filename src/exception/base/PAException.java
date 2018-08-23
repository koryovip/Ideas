package exception.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.util.StackLocatorUtil;

import exception.base.ano.PAExceptionMessage;
import exception.base.ano.PAExceptionMessage2;

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

    abstract protected String id();

    @Override
    final public String getMessage() {
        return String.format("[%s_%s]%s", id(), this.code, this.message);
    }

    protected <E extends PAException> E build(Class<E> expCls, Object... arguments) {
        Map<String, Method> methods = methodMap.get(expCls);
        if (methods == null) {
            Map<String, Method> list = new HashMap<String, Method>();
            for (Method m : expCls.getDeclaredMethods()) {
                list.put(m.getName(), m);
            }
            methodMap.put(expCls, list);
        }
        Method m = methodMap.get(expCls).get(StackLocatorUtil.getStackTraceElement(2).getMethodName());
        if (m == null) {
            return null; // TODO ログ
        }
        PAExceptionMessage2 msg = m.getAnnotation(PAExceptionMessage2.class);
        if (msg == null) {
            return null; // TODO ログ
        }
        try {
            return expCls.getConstructor(String.class, String.class).newInstance(m.getName(), MessageFormat.format(msg.log(), arguments));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String message(Class<? extends PAException> expCls, Object... arguments) {
        Map<String, Method> methods = methodMap.get(expCls);
        if (methods == null) {
            Map<String, Method> list = new HashMap<String, Method>();
            for (Method m : expCls.getDeclaredMethods()) {
                list.put(m.getName(), m);
            }
            methodMap.put(expCls, list);
        }
        Method m = methodMap.get(expCls).get(StackLocatorUtil.getStackTraceElement(2).getMethodName());
        if (m == null) {
            return null; // TODO ログ
        }
        PAExceptionMessage msg = m.getAnnotation(PAExceptionMessage.class);
        if (msg == null) {
            return null; // TODO ログ
        }
        return String.format("[%s_%s]%s", id(), m.getName(), MessageFormat.format(msg.log(), arguments));
    }

}
