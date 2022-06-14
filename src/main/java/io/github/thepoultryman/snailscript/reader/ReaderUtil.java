package io.github.thepoultryman.snailscript.reader;

import io.github.thepoultryman.snailscript.SuperSnailScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReaderUtil {
    public static final Logger USER_LOGGER = LoggerFactory.getLogger("script-output");

    public static Integer tryIntegerParse(String stringInteger) {
        try {
            return Integer.valueOf(stringInteger);
        } catch (NumberFormatException e) {
            SuperSnailScript.LOGGER.error("The specified integer couldn't be parsed. Your script is broken and will not continue to run.");
            return null;
        }
    }
}
