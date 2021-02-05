package Level_32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/*
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(args[0],"rw");
        long number = Long.parseLong(args[1]);
        raf.seek(Math.min(raf.length(),number));
        raf.write(args[2].getBytes(StandardCharsets.UTF_8));
        raf.close();
    }
}


