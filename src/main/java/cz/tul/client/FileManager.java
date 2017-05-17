package cz.tul.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by vaclavlangr on 17.05.2017.
 */
public class FileManager {
    public static FileManager get() throws IOException {
        return new FileManager();
    }

    private Path targetDir = Paths.get("images");

    private FileManager() throws IOException {
        if(!Files.exists(targetDir)) {
            Files.createDirectory(targetDir);
        }
    }

    private Path getImagePath(String filename) {
        assert (filename != null);
        return targetDir.resolve(filename + ".jpg");
    }

    public boolean imageExists(String filename) {
        Path source = getImagePath(filename);
        return Files.exists(source);
    }

    public void retrieveImage(String filename, OutputStream outputStream) throws IOException {
        Path source = getImagePath(filename);
        if(!imageExists(filename)) {
            throw new FileNotFoundException("Unable to find file:" + filename);
        }
        Files.copy(source, outputStream);
    }

    public void saveImage(String filename, InputStream inputStream) throws IOException {
        assert (inputStream != null);
        Path target = getImagePath(filename);
        Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
    }
}
