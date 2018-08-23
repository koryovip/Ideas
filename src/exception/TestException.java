package exception;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.base.BL001Exception;
import exception.base.PAException;

public class TestException {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws PAException {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");

        TestException logic = new TestException();
        logic.execute("0");
    }

    public void execute(String id) throws PAException {
        logger.info("execute>");

        logger.info(BL001Exception.$().I_0001(new File("/home/user/set.txt")));

        if ("0".equals(id)) {
            throw logger.throwing(BL001Exception.$().E_0001(id));
        }

        logger.info("<execute");
    }

}
