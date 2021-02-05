package Level_31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {

        String[] parts = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            parts[i - 1] = args[i];
        }
        Arrays.sort(parts);

        List<FileInputStream> list = new ArrayList<>();
        for (String x : parts) {
            list.add(new FileInputStream(x));
        }

        Enumeration<FileInputStream> input = Collections.enumeration(list);
        SequenceInputStream sis = new SequenceInputStream(input);

        try (ZipInputStream zis = new ZipInputStream(sis);
            FileOutputStream fos = new FileOutputStream(args[0])){
            while (zis.getNextEntry()!=null){
                byte[] buffer = new byte[1024];
                int res;
                while ((res= zis.read(buffer))!=-1){
                    fos.write(buffer,0,res);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}