package test;

public class Main14123123 {

    public static final Foo<String> FOO = new Foo<String>() {
    };

    public static void main(String[] args) {
        //        Foo<String> foo = new Foo<String>() {
        //        };
        //        // 在类的外部这样获取
        //        Type type = ((ParameterizedType) foo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        //        System.out.println(type);
        //        // 在类的内部这样获取
        //        System.out.println(foo.getTClass());
        //
        //        Foo<Date> foo3 = new Foo<Date>() {
        //        };
        //        // 在类的内部这样获取
        //        System.out.println(foo3.getTClass());

        //        Foo<byte[]> foo5 = new Foo<byte[]>() {
        //        };
        //        // 在类的内部这样获取
        //        System.out.println(foo5.getTClass());

        System.out.println(FOO.getTClass());
    }

}
