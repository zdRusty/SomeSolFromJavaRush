package Level_22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
Составить цепочку слов
*/

/*
Третье решение довольно лаконичное. Багованный валидатор не принимает. Если добавить в файл слова,
не вписывающиеся в цепочку (хотя по условию такого быть НЕ может), уходит в бесконечность.
 */

public class Solution3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        BufferedReader fr = new BufferedReader(new FileReader(fileName));
        String[] str = fr.lines()
                .map(x-> x + " ")
                .collect(Collectors.joining())
                .split(" ");

        StringBuilder result = getLine(str);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words==null||words.length==0) return new StringBuilder();
        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
        while (!check(list)){
            listSort(list);
        }
        StringBuilder sb = new StringBuilder();
        for(String x: list){
            sb.append(x).append(" ");
        }
        return new StringBuilder(sb.substring(0,sb.length()-1));
    }

    public static void listSort (ArrayList<String> list){
        for(int i=1;i<list.size();i++){
            if(list.get(i).toLowerCase().charAt(0)!=list.get(i-1).toLowerCase().charAt(list.get(i-1).length()-1)){
                String temp = list.get(i);
                list.remove(i);
                list.add(i-1,temp);
            }
        }
    }

    public static boolean check (ArrayList<String> list){
        for(int i=1;i<list.size();i++){
            if(list.get(i).toLowerCase().charAt(0)!=list.get(i-1).toLowerCase().charAt(list.get(i-1).length()-1)){
                String temp = list.get(i);
                list.remove(i);
                list.add(0,temp);
                return false;
            }
        }
        return true;
    }
}