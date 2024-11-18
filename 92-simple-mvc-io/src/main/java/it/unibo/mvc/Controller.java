package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public final class Controller {

    private static final String DEFAULT = System.getProperty("user.home")
            + File.separator
            + "output.txt";
    private File current = new File(DEFAULT);
    /**
     * Sets a new destination file.
     *
     * @param file
     *            the file where to write
     */
    public void setFile(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            current = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }
    /**
     * Sets a new destination file from a string.
     * @param file
     *              the name of the new file 
     */
    public void setFile(final String file) {
        setFile(new File(file));
    }
    /**
     * @return the current file
     */
    public File getFile() {
        return current;
    }
    /**
     * @return the path of the current file
     */
    public String getFilePath() {
        return current.getPath();
    }
    /**
     * @param text
     *              the text that will be saved in a file
     * @throws IOException
     */
    public void write(final String text) throws IOException {
        try (PrintStream out = new PrintStream(current, StandardCharsets.UTF_8)) {
            out.println(text);
        }
    }
}
