package Level_35.task3504;

/*
Простой generics
*/

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Solution<K,V> {
    private HashMap<K,V> map;

    public Solution(HashMap<K,V> map) {
        this.map = map;
    }

    public HashMap<K,V> getMap() {
        return map;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("string", 4);
        Solution<String, Integer> solution = new Solution<>(hashMap);
        HashMap<String, Integer> mapFromSolution = solution.getMap();
        System.out.println(mapFromSolution.getClass());


        LinkedHashMap<Solution, Solution> hashMap2 = new LinkedHashMap<>();
        hashMap2.put(solution, solution);
        Solution<Solution, Solution> solution2 = new Solution<>(hashMap2);
        LinkedHashMap<Solution, Solution> mapFromSolution2 = (LinkedHashMap) solution2.getMap();   //need to cast  :(
        System.out.println(mapFromSolution2.getClass());
    }
}



