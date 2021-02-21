package level_32.task3204;

import java.io.ByteArrayOutputStream;

/*
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for(int i=0;i<8;i++){
            bos.write((char) randomize());
        }

        boolean isSmallLetters = false;
        boolean isBigLetters = false;
        boolean isDigit = false;

        for(byte x: bos.toByteArray()){
            if (x>=48&&x<58) isDigit = true;
            if (x>=65&&x<91) isBigLetters = true;
            if (x>=97&&x<123) isSmallLetters = true;
        }

        if (isDigit&isBigLetters&isSmallLetters) return bos;
        else return getPassword();
    }

    private static int randomize(){
        int result=(int)(Math.random() * 100);
        if ((result>=48&&result<58)||
        (result>=65&&result<91)||
        (result>=97&&result<123)){
            return result;
        }
        return randomize();
    }
}