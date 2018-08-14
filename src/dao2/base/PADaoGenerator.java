package dao2.base;

import java.util.List;

import dao2.def.T_USER;

public class PADaoGenerator {

    public static void main(String[] args) {
        create(new T_USER());

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void create(TblBase tbl) {
        System.out.println(String.format("public final class %s_NTT {", tbl.name()));
        System.out.println();
        List<ColBase> cols = tbl.columnAll();
        for (ColBase col : cols) {
            System.out.println(String.format("private %s %s;", col.getType().getName(), underlineToHump(col.name(), true)));
        }
        for (ColBase col : cols) {
            System.out.println();
            final String type = col.getType().getName();
            final String var1 = underlineToHump(col.name(), false);
            final String var2 = underlineToHump(col.name(), true);
            System.out.println(String.format("public void set%s(%s %s) {", var1, type, var2));
            System.out.println(String.format("this.%s = %s;", var2, var2));
            System.out.println("}");
            System.out.println();
            System.out.println(String.format("public %s get%s() {", type, var1, var2));
            System.out.println(String.format("return this.%s;", var2));
            System.out.println("}");
        }
        System.out.println("}");
    }

    public static String underlineToHump(String para, boolean up) {
        StringBuilder result = new StringBuilder();
        String a[] = para.toUpperCase().split("_");
        for (String s : a) {
            if (up && result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
