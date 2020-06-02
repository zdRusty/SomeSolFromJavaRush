package Level_31.task3110;

import Level_31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Введите путь к архиву");
            Path archivePath = Paths.get(br.readLine());
            ZipFileManager zipArchive = new ZipFileManager(archivePath);

            System.out.println("Введите путь к файлу, который будет архивирован");
            Path filePath = Paths.get(br.readLine());
            zipArchive.createZip(filePath);
            new ExitCommand().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
