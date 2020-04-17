package Level_22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/*
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fi = new FileInputStream(args[0]);
        FileOutputStream fo = new FileOutputStream(args[1]);
        Charset chsW1251 = Charset.forName("Windows-1251");
        Charset chsUTF8 = Charset.forName("UTF-8");
        while (fi.available()>0){
            byte[] data = new byte[1000];
            fi.read(data);
            String s = new String(data,chsW1251);
            data = s.getBytes(chsUTF8);
            fo.write(data);
        }
        fi.close();
        fo.close();
    }
}
