package Level_31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Добавление файла в архив
*/

public class Solution {

    public static void main(String[] args) {

        String fileName = args[0];
        File addingFile = new File(fileName);
        Map<String, ByteArrayOutputStream> map = new HashMap<>();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(args[1]))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                ByteArrayOutputStream array = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int b;
                while ((b = zis.read(buffer)) != -1) {
                    array.write(buffer, 0, b);
                }
                map.put(entry.getName(), array);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String searchResult = "";
        for (Map.Entry<String, ByteArrayOutputStream> x : map.entrySet()) {
            if (x.getKey().substring(x.getKey().lastIndexOf("/") + 1).equals(addingFile.getName())) {
                searchResult = x.getKey();
            }
        }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]))) {
            if (searchResult.equals("")) {
                zos.putNextEntry(new ZipEntry("new/" + addingFile.getName()));
            } else {
                zos.putNextEntry(new ZipEntry(searchResult));
            }
            Files.copy(Paths.get(fileName), zos);
            for (Map.Entry<String, ByteArrayOutputStream> x : map.entrySet()) {
                if (x.getKey().substring(x.getKey().lastIndexOf("/") + 1).equals(addingFile.getName())) {
                    zos.closeEntry();
                } else {
                    zos.putNextEntry(new ZipEntry(x.getKey()));
                    zos.write(x.getValue().toByteArray());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

