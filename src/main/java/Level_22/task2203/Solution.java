package Level_22.task2203;

/*
Между табуляциями
*/

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string==null) throw new TooShortStringException();
        String[] strSplit = string.split("\t");
        char[] strCh = string.toCharArray();
        int count=0;
        for (char ch : strCh) {
            if (ch == '\t') count++;
        }
        if (count<2) throw new TooShortStringException();
        return strSplit[1];
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}

