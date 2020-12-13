package Level_31.task3112;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();

        if (Files.notExists(downloadDirectory)) {
            Files.createDirectories(downloadDirectory);
        }

        Path temp = Files.createTempFile("temp-", ".tmp");
        Files.copy(inputStream, temp, StandardCopyOption.REPLACE_EXISTING);

        String sourceName = urlString.substring(urlString.lastIndexOf("/") + 1);

        Path dest = downloadDirectory.resolve(sourceName);

        Files.move(temp, dest, StandardCopyOption.REPLACE_EXISTING);

        return dest;        // implement this method
    }
}