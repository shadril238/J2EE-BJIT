package com.devrezaur.user.service.util;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;

/**
 * Utility class for integration tests
 */
@Component
public class TestUtil {

    private final Logger systemLogger;

    public TestUtil(@Qualifier("systemLogger") Logger systemLogger) {
        this.systemLogger = systemLogger;
    }

    /**
     * Helper method to load data from a particular file
     *
     * @param relativePath relative path of the file
     * @return data in json string format
     */
    public String loadFromFile(String relativePath) {
        try {
            Path path = Paths.get(TestUtil.class.getClassLoader().getResource(relativePath.replaceAll("/",
                    Matcher.quoteReplacement(File.separator))).toURI());
            return Files.readString(path);
        } catch (Exception ex) {
            systemLogger.error("TestUtil: Unable to read the file: " + relativePath);
        }
        return null;
    }
}
