package dao2.base;

public enum SqlOrder {

    ASC("ASC") //
    , DESC("DESC");

    final private String value;

    private SqlOrder(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
