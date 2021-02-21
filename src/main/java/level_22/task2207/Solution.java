package level_22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        BufferedReader fr = new BufferedReader(new FileReader(fileName));

        String[] array = fr.lines()
                .map(x-> x + " ")
                .collect(Collectors.joining())
                .split(" ");

        for(int i=0;i<array.length;i++){
            for(int j=array.length-1;j>i;j--){
                String mirror = new StringBuilder(array[j])
                        .reverse()
                        .toString();
                if (array[i].equals(mirror)){
                    Pair pair = new Pair();
                    pair.first = array[i];
                    pair.second = array[j];
                    result.add(pair);
                    i++;
                }
            }
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}