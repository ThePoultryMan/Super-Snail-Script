package io.github.thepoultryman.snailscript.reader;

import io.github.thepoultryman.snailscript.SuperSnailScript;
import io.github.thepoultryman.snailscript.reader.util.ReaderUtil;
import io.github.thepoultryman.snailscript.reader.util.VariableUtil;
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
    private final Map<String, Float> Floats = new HashMap<>();

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
            int variableType = VariableUtil.checkForVariable(line);
            if (variableType != 0) {
                // 0 means no variable 1 is Integer, 2 is float
                String variableString = line.substring(line.indexOf("=") + 2, line.indexOf(";"));
                switch (variableType) {
                    case 1 -> {
                        Integer integerVariable = VariableUtil.tryIntegerParse(variableString);
                        if (integerVariable != null)
                            this.Integers.put(line.substring(8, line.indexOf("=") - 1), integerVariable);
                        else return;
                    }
                    case 2 -> {
                        Float floatVariable = VariableUtil.tryFloatParse(variableString);
                        if (floatVariable != null)
                            this.Floats.put(line.substring(6, line.indexOf("=") - 1), floatVariable);
                        else return;
                    }
                }
            } else if (line.startsWith("print(")) { // Check if the line specifies a print function;
                // Will check all maps to properly print variable.
                String printString = this.printVariable(line.substring(line.indexOf("(") + 1, line.indexOf(")")));
                if (printString != null) {
                    ReaderUtil.USER_LOGGER.info(printString); // Writes to "latest-script-output.log"
                } else {
                    SuperSnailScript.LOGGER.debug("The script is being stopped because printing a variable failed.");
                    return; // Stop the script from running
                }
            }
        }
    }

    private String printVariable(String variableName) {
        if (this.Integers.containsKey(variableName)) {
            return Integers.get(variableName).toString();
        } else if (this.Floats.containsKey(variableName)) {
            return this.Floats.get(variableName).toString();
        } else {
            ReaderUtil.USER_LOGGER.error("'" + variableName + "' does not exist as a variable, of any type.");
            return null;
        }
    }
}
