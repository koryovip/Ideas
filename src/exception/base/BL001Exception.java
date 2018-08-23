package exception.base;

import java.io.File;

import exception.base.ano.PAExceptionMessage;
import exception.base.ano.PAExceptionMessage2;

public class BL001Exception extends PAException {

    private static final long serialVersionUID = -5724163822802960268L;

    @Override
    protected String id() {
        return "BL001";
    }

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

    @PAExceptionMessage2(//
            log = "ユーザー存在しない。ユーザーID:{0}"//
            , jp = ""//
            , en = "")
    final public BL001Exception E_0001(String userId) {
        return super.build(this.getClass(), userId);
    }

    @PAExceptionMessage(log = "ファイル存在しない。ファイル:{0}")
    final public String I_0001(File file) {
        return super.message(this.getClass(), file);
    }

}
