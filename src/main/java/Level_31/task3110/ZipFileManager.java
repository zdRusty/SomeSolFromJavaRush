package Level_31.task3110;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception{

        try (ZipOutputStream zop = new ZipOutputStream(Files.newOutputStream(zipFile));
                InputStream is = Files.newInputStream(source)){
            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            zop.putNextEntry(zipEntry);
            zop.write(Files.readAllBytes(source));
            zop.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
