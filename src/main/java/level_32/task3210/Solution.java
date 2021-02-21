package level_32.task3210;

/*
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(args[0],"rw");
        byte[] buffer = new byte[args[2].toCharArray().length];
        raf.seek(Long.parseLong(args[1]));
        raf.read(buffer,0,buffer.length);
        raf.seek(raf.length());
        String result = args[2].equals(new String(buffer, StandardCharsets.UTF_8)) ? "true" : "false";
        raf.write(result.getBytes(StandardCharsets.UTF_8));
        raf.close();
    }
}


