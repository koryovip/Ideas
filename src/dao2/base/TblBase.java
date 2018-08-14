package dao2.base;

public abstract class TblBase {

    private final String name;

    public TblBase(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    protected <T> T where(ColBase<?, ?> col, T val) {
        System.out.println(col.name() + "=" + val.toString());
        return null;
    }

}
