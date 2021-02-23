package level_36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/*
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        List<String> list =
        br.lines()
                .collect(Collectors.joining())
                .chars()
                .mapToObj(x -> Character.toString((char) x))
                .filter(x -> x.matches("[\\w]"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        TreeSet<String> set = new TreeSet<>();
        for(String x: list){
            set.add(x);
        }
        String result = set.stream().limit(5).collect(Collectors.joining());
        System.out.println(result);
        br.close();
    }
}