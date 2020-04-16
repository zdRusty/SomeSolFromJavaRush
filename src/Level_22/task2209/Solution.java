package Level_22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
Составить цепочку слов
*/

/*
Этот вариант решения принимает кривой валидатор, хотя оно не должно проходить по пункту 6 и 7 т.к.
удаляет слова, у которых первая и последняя буквы одинаковые
 */
public class Solution {
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
        HashMap<Integer,StringBuilder> map = new HashMap<>();

        for (String s : words) {
            StringBuilder sb = new StringBuilder(s).append(" ");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
            for (int j = 0; j < list.size(); j++) {
                if (s.equals(list.get(j))) {
                    list.remove(j);
                    j--;
                    continue;
                }
                char[] chain = sb.toString().toLowerCase().toCharArray();
                char[] word = list.get(j).toLowerCase().toCharArray();
                if (chain[chain.length - 2] == word[0]) {
                    sb.append(list.get(j));
                    sb.append(" ");
                    list.remove(j);
                    j = -1;
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            map.put(sb.length(), sb);
        }

        int key = 0;
        for(Map.Entry<Integer, StringBuilder> x : map.entrySet()){
            if(key< x.getKey()){
                key= x.getKey();
            }
        }
        return map.get(key);
    }
}

