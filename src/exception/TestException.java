package exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.base.BL001Exception;

public class TestException {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");

        logger.info(BL001Exception.$().$I_0001("user-0001"));
        logger.throwing(BL001Exception.$().$E_0001("user-0001"));
    }

}
