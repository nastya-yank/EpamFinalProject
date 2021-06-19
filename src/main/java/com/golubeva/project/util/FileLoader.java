package com.golubeva.project.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * The {@code FileLoader} class represents file loader.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class FileLoader {
    private static final Logger LOGGER = LogManager.getLogger(FileLoader.class);
    private static final String FILE_EXTENSION = ".jpg";

    /**
     * Load image.
     *
     * @param parts the parts of photo
     * @param directory the directory
     * @return the optional of new generated photo name
     */
    public Optional<String> load(Collection<Part> parts, String directory) {
        File fileSaveDir = new File(directory);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        Optional<String> fileOptional = Optional.empty();
        for (Part part : parts) {
            String fileName = part.getSubmittedFileName();
            try {
                if (fileName != null) {
                    if (fileName.endsWith(FILE_EXTENSION)) {
                        String uniqueFileName = UUID.randomUUID().toString();
                        part.write(directory + uniqueFileName + FILE_EXTENSION);
                        fileOptional = Optional.of(uniqueFileName);
                        LOGGER.log(Level.INFO, "File was uploaded successfully {}", fileName);
                    } else {
                        LOGGER.log(Level.ERROR, "File was not uploaded. Incorrect extension {}", fileName);
                    }
                }
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "File was not uploaded {}", fileName, e);
            }
        }
        return fileOptional;
    }
}
