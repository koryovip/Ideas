package dao.base;

public class WhereKV {

    final public String key;
    final public Object val;

    public WhereKV(String key, Object val) {
        this.key = key;
        this.val = val;
    }

    public static final WhereKV $(String key, Object val) {
        return new WhereKV(key, val);
    }

    @Override
    public String toString() {
        return String.format("%s = %s", key, val);
    }

}
