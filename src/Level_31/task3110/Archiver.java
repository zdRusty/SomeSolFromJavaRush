package Level_31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите путь к архиву");
        Path archivePath = null;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            archivePath = Paths.get(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ZipFileManager zip = new ZipFileManager(archivePath);

        System.out.println("Введите путь к файлу, который будет архивирован");
        Path filePath = null;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            filePath = Paths.get(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        zip.createZip(filePath);
    }
}
