package exception.base;

import exception.base.ano.PAExceptionMessage;

public class BL001Exception extends PAException {

    private static final long serialVersionUID = -5724163822802960268L;

    private BL001Exception() {
        super("", "");
    }

    public BL001Exception(String code, String message) {
        super(code, message);
    }

    private static final BL001Exception me = new BL001Exception();

    public static final BL001Exception $() {
        return me;
    }

    @PAExceptionMessage(//
            log = "ユーザー存在しない。ユーザーID:{0}"//
            , jp = ""//
            , en = "")
    final public BL001Exception $E_0001(String userId) {
        return super.build(this.getClass(), userId);
    }

    @PAExceptionMessage(//
            log = "ユーザー存在しない。ユーザーID:{0}"//
            , jp = ""//
            , en = "")
    final public BL001Exception $I_0001(String userId) {
        return super.build(this.getClass(), userId);
    }

}
