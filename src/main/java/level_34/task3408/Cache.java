package level_34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V object = cache.get(key);
        if (!cache.containsValue(object)) {
            Constructor<V> constructor = clazz.getConstructor(key.getClass());
            cache.put(key, constructor.newInstance(key));
            object = cache.get(key);
        }//TODO add your code here
        return object;
    }

    public boolean put(V obj) {
        try {
            Method get = obj.getClass().getDeclaredMethod("getKey");//TODO add your code here
            get.setAccessible(true);
            cache.put((K) get.invoke(obj),obj);
            return true;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}