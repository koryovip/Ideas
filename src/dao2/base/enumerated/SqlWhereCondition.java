package dao2.base.enumerated;

/**
-lt  Less than                   <
-gt  Greater than                >
-le  Less than or Equal to       <=
-ge  Greater than or Equal to    >=
-eq  Equal to                    =
-ne  Not equal to                !=
 * @author kou
 *
 */
public enum SqlWhereCondition {

    $00("=") //
    , $AB("<>") //
    , $01("<") //
    , $10(">") //
    , $0eq1("<=") //
    , $1eq0(">=") //
    , in("IN") //
    ;

    final private String value;

    private SqlWhereCondition(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
