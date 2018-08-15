package test;

public class JavaArray {

    public static void main(String[] args) {
        System.out.println(typeToStr(String.class));
        System.out.println(typeToStr(byte[].class));
    }

    public static final String typeToStr(Class<?> clazz) {
        if (clazz.isArray()) {
            return clazz.getComponentType() + "[]";
        }
        return clazz.getName();
    }
}
