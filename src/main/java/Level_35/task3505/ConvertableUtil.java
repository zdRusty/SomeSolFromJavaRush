package Level_35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

        public static <K,V> Map<K,V> convert(List<? extends Convertable<K>> list) {
        Map<K,V> result = new HashMap<>();
        for(Convertable<K> x: list){
            result.put(x.getKey(), (V) x);
        }
        return result;
    }
}