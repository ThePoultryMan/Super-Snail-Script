package io.github.thepoultryman.snailscript.reader.util;

import io.github.thepoultryman.snailscript.SuperSnailScript;

public class VariableUtil {
    public static int checkForVariable(String line) {
        if (line.startsWith("integer")) {
            return 1;
        } else if (line.startsWith("float")) {
            return 2;
        } else {
            return 0;
        }
    }

    public static Integer tryIntegerParse(String stringInteger) {
        try {
            return Integer.valueOf(stringInteger);
        } catch (NumberFormatException e) {
            SuperSnailScript.LOGGER.error("The specified integer couldn't be parsed. Your script is broken and will not continue to run.");
            return null;
        }
    }


}
