package level_36.task3602;

/*
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        System.out.println(getExpectedClass());

    }

    public static Class getExpectedClass() {
        Class<?> [] classes = Collections.class.getDeclaredClasses();
        List<Class<?>> list = new ArrayList<>(Arrays.asList(classes));
        List<Class<?>> newlist = list.stream()
                .filter(Solution::isPrivate)
                .filter(Solution::isImplList)
                .filter(Solution::isNotIndexAccess)
                .collect(Collectors.toList());
        return newlist.get(0);
    }

    private static boolean isImplList (Class<?> clazz){

        List<Class<?>> parents = new ArrayList<>();
        parents.add(clazz);
        getAllParents(clazz,parents);

        for(Class<?> p: parents){
            Class<?>[] iterfaces = p.getInterfaces();
            for(Class<?> x: iterfaces){
                if (x.getSimpleName().equals("List")) return true;
            }
        }

        return false;
    }

    private static boolean isPrivate (Class<?> clazz){
        return (clazz.getModifiers() & Modifier.PRIVATE)>0;
    }

    private static boolean isNotIndexAccess(Class<?> clazz){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            List<Integer> list = (List<Integer>) constructor.newInstance();
            list.get(0);
        } catch (IndexOutOfBoundsException e) {
            return true;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static List<Class<?>> getAllParents (Class<?> clazz, List<Class<?>> list){
        Class<?> result = clazz.getSuperclass();
        if (result==null||result.getSimpleName().equals("Object")) return list;
        else {
            list.add(result);
            return getAllParents(result,list);
        }
    }
}