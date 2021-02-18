package Level_35.task3509;

import java.util.*;

/*
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        return new ArrayList<>(Arrays.asList(elements));
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        HashSet<T> hashSet = new HashSet<>();
        Collections.addAll(hashSet,elements);
        //напишите тут ваш код
        return hashSet;
    }

    public static <K,V> HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        HashMap<K,V> hashMap = new HashMap<>();
        if(keys.size()==values.size()) {
            for(int i=0;i<keys.size();i++){
                hashMap.put(keys.get(i),values.get(i));
            }
            //напишите тут ваш код
        }
        else throw new IllegalArgumentException();
        return hashMap;
    }
}



