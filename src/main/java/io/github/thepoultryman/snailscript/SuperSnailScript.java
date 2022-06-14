package io.github.thepoultryman.snailscript;

import io.github.thepoultryman.snailscript.reader.BasicReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SuperSnailScript {
    public static final Logger LOGGER = LoggerFactory.getLogger("snailscript");

    public static void main(String[] args) {
        if (args.length >= 1) {
            File file = new File(args[0]);
            if (!file.exists()) {
                LOGGER.error("The specified file does not exist! Please check to make sure that the path that you entered is correct.");
            } else {
                if (!isAdvanced(file)) {
                    BasicReader scriptReader = new BasicReader(file);
                }
            }
        } else {
            LOGGER.error("No arguments were provided, and therefore no file could be read.");
        }
    }

    public static boolean isAdvanced(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String firstLine = reader.readLine();
            if (firstLine != null && firstLine.equals("script.advancedFeatures;")) {
                LOGGER.info("The specified file is using 'advanced features'");
                return true;
            }
            LOGGER.info("The specified file is not using 'advanced features'");
            return false;
        } catch (FileNotFoundException e) {
            LOGGER.error("Your specified file could not be found while trying to read the first line.", e);
            LOGGER.warn("Your file couldn't be found, so it is being deemed 'not advanced'");
            return false;
        } catch (IOException e) {
            LOGGER.error("The first line of your file could not be read.", e);
            LOGGER.warn("Your file couldn't be read, so it is being deemed 'not advanced'");
            return false;
        }
    }
}
