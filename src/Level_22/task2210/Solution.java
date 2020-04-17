package Level_22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer st = new StringTokenizer(query,delimiter);
        ArrayList<String> list = new ArrayList<>();
        while (st.hasMoreTokens()){
            list.add(st.nextToken());
        }

        String[] arr = new String[list.size()];

        for(int i=0;i<list.size();i++){
            arr[i] = list.get(i);
        }
        return arr;
    }


    // этот вариант не прошел, выдает ошибку с "list.toArray(String[]::new)"
    /*public static String[] getTokens(String query, String delimiter) {
        StringTokenizer st = new StringTokenizer(query,delimiter);
        ArrayList<String> list = new ArrayList<>();
        while (st.hasMoreTokens()){
            list.add(st.nextToken());
        }
        return list.toArray(String[]::new);
    }*/

}

