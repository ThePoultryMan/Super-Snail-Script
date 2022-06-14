package io.github.thepoultryman.snailscript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SuperSnailScript {
    public static final Logger LOGGER = LoggerFactory.getLogger("snailscript");

    public static void main(String[] args) {
        if (args.length >= 1) {
            File file = new File(args[0]);
            if (!file.exists()) {
                LOGGER.error("The specified file does not exist! Please check to make sure that the path that you entered is correct.");
            }
        } else {
            LOGGER.error("No arguments were provided, and therefore no file could be read.");
        }
    }
}
