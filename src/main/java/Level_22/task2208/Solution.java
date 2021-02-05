package Level_22.task2208;

import java.util.Map;

/*
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String> x: params.entrySet()){
            if (x.getValue()!=null) sb.append(x.getKey()+" = '"+x.getValue()+"'").append(" and ");
        }
        if (params.size()>0&&sb.length()>0) return sb.substring(0, sb.length()-5);
        else return "";
    }
}