package dao2.base.enumerated;

public enum SqlSet {
    now("now()");

    final private String value;

    private SqlSet(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
