package io.github.thepoultryman.snailscript.reader;

import io.github.thepoultryman.snailscript.SuperSnailScript;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicReader {
    private List<String> lines = null;

    // Variable Maps
    private final Map<String, Integer> Integers = new HashMap<>();

    public BasicReader(File file) {
        try {
            this.lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
            SuperSnailScript.LOGGER.info("All of the lines were successfully read, and your script will now be run.");
            this.runScript();
        } catch (IOException e) {
            SuperSnailScript.LOGGER.error("There was an issue reading the lines of your script. This is the end of any attempt to run the script.");
        }
    }

    private void runScript() {
        for (String line : lines) {
            if (line.startsWith("integer")) { // Check if line specifies an integer variable.
                Integer parsedInteger = ReaderUtil.tryIntegerParse(line.substring(line.indexOf("=") + 2, line.indexOf(";")));
                if (parsedInteger != null) {
                    Integers.put(line.substring(8, line.indexOf("=") - 1), parsedInteger);
                } else {
                    SuperSnailScript.LOGGER.error("Stopping your script");
                    return;
                }
            } else if (line.startsWith("print(")) { // Check if the line specifies a print function;
                // Will check all maps to properly print variable.
                String printString = this.printVariable(line.substring(line.indexOf("(") + 1, line.indexOf(")")));
                if (printString != null) {
                    SuperSnailScript.LOGGER.info(printString); // Will write to custom log later.
                } else {
                    return; // Stop the script from running
                }
            }
        }
    }

    private String printVariable(String variableName) {
        if (Integers.containsKey(variableName)) {
            return Integers.get(variableName).toString();
        } else {
            SuperSnailScript.LOGGER.error("'" + variableName + "' does not exist as a variable, of any type.");
            return null;
        }
    }
}
