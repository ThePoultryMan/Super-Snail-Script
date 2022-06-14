package io.github.thepoultryman.snailscript.reader;

import io.github.thepoultryman.snailscript.SuperSnailScript;

public class ReaderUtil {
    public static Integer tryIntegerParse(String stringInteger) {
        try {
            return Integer.valueOf(stringInteger);
        } catch (NumberFormatException e) {
            SuperSnailScript.LOGGER.error("The specified integer couldn't be parsed. Your script is broken and will not continue to run.");
            return null;
        }
    }
}
