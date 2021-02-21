package level_22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
Составить цепочку слов
*/

/*
Это решение немного громозкое. Его валидатор не принимает.
 */

public class Solution2 {
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
        priority(words);
        List<String> list = new LinkedList<>();
        for(int i=1;i<words.length;i++){
            list.add(words[i]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(words[0]);

        while (!list.isEmpty()){

            if (list.get(0).toLowerCase().charAt(0)==sb.toString().toLowerCase().charAt(sb.length()-1)){
                sb.append(" ").append(list.get(0));
                list.remove(0);
            }
            else if (list.get(0).toLowerCase().charAt(list.get(0).length()-1)==sb.toString().toLowerCase().charAt(0)){
                String tempStr = sb.toString();
                sb = new StringBuilder(list.get(0)).append(" ").append(tempStr);
                list.remove(0);
            }
            else {
                if (list.get(0).toLowerCase().charAt(0)==list.get(0).toLowerCase().charAt(list.get(0).length()-1)) {
                    String s = list.get(0);
                    list.remove(0);
                    list.add(1,s);
                }
                else {
                    String s = list.get(0);
                    list.remove(0);
                    list.add(s);
                }
            }
        }
        for(String x: list){
            sb.append(x).append(" ");
        }

        if(sb.substring(sb.length()-1).equals(" ")) return new StringBuilder(sb.substring(0,sb.length()-1));
        return sb;
    }

    public static void priority (String[] arr){
        String temp;
        for(int i=0;i<arr.length;i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j].toLowerCase().charAt(0)==arr[j].toLowerCase().charAt(arr[j].length()-1)) {
                    temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                    i++;
                }
            }
        }
    }
}
