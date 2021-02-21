package level_22.task2202;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string==null) throw new TooShortStringException();
        String[] strSplit = string.split(" ");
        if (strSplit.length<5) throw new TooShortStringException();
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<5;i++){
            sb.append(strSplit[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
