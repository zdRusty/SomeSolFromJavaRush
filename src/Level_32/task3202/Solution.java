package Level_32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/*
Читаем из потока
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        if (is==null) return new StringWriter();
        StringWriter sw = new StringWriter();
        int i;
        while ((i=is.read())>0){
            sw.write(i);
        }
        sw.close();
        return sw;
    }
}



