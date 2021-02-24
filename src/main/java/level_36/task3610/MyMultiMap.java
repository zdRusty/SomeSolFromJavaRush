package level_36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return this.values().size();//напишите тут ваш код
    }

    @Override
    public V put(K key, V value) {
        List<V> vList = new ArrayList<>();
        V lastValue = null;

        if(!map.containsKey(key)) {
            vList.add(value);
            map.put(key,vList);
            return null;
        }

        for(Entry<K,List<V>> x: map.entrySet()){
            if(x.getKey()==key) {
                if (x.getValue().size()<repeatCount&&x.getValue().size()>0){
                    lastValue = x.getValue().get(x.getValue().size()-1);
                }
                if (x.getValue().size()==repeatCount){
                    lastValue = x.getValue().get(x.getValue().size()-1);
                    x.getValue().remove(0);
                }
                x.getValue().add(value);
            }
        }
        return lastValue;//напишите тут ваш код
    }

    @Override
    public V remove(Object key) {
        V removedValue = null;
        boolean isMustBeRemoved = false;
        for(Entry<K,List<V>> x: map.entrySet()){
            if(x.getKey()==key) {
                if(x.getValue().size()>0){
                    removedValue = x.getValue().get(0);
                    x.getValue().remove(0);
                }
                if(x.getValue().size()==0){
                    isMustBeRemoved = true;
                }
            }
        }
        if(isMustBeRemoved){
            map.remove(key);
        }
        return removedValue;//напишите тут ваш код
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();//напишите тут ваш код
    }

    @Override
    public Collection<V> values() {
        List<V> list = new ArrayList<>();
        for(List<V> x: map.values()){
            list.addAll(x);
        }
        return list;//напишите тут ваш код
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);//напишите тут ваш код
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);//напишите тут ваш код
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}