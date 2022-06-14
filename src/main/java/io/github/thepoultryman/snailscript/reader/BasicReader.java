package io.github.thepoultryman.snailscript.reader;

import io.github.thepoultryman.snailscript.SuperSnailScript;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BasicReader {
    private List<String> lines = null;

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
            SuperSnailScript.LOGGER.info(line);
        }
    }
}
