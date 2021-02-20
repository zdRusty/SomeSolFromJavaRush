package Level_35.task3507;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;

/*
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();

        File file = new File(pathToAnimals);
        List<File> fileList = Arrays.asList(file.listFiles());
        List<Class<?>> classes = new ArrayList<>();

        for (File x : fileList) {
            classes.add(fileToClass(x));
        }

        for(Class<?> x: classes){
            if (isImplementInterface(x) && isConstructorMatch(x)) {
                try {
                    set.add((Animal) x.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return set;
    }

    private static boolean isImplementInterface(Class<?> clazz){
        Class<?>[] interfaces = clazz.getInterfaces();
        return Arrays.asList(interfaces).contains(Animal.class);
    }

    private static boolean isConstructorMatch (Class<?> clazz){
        Constructor<?>[] constructorsArr = clazz.getConstructors();
        for(Constructor<?> x: constructorsArr){
            if(x.getParameterCount() == 0) return true;
        }
        return false;
    }

    private static Class<?> fileToClass (File file){

        byte[] buffer = new byte[0];
        try (FileInputStream fis = new FileInputStream(file)) {
            buffer = new byte[fis.available()];
            fis.read(buffer, 0, fis.available());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = Solution.class.getPackage().getName()+".data."+file.getName().substring(0,file.getName().length()-6);

        return new MyLoader().myDefineClass(name,buffer,0,buffer.length);
    }

    public static class MyLoader extends ClassLoader{

        public Class myDefineClass(String name, byte[] b, int off, int len) throws ClassFormatError{
            return super.defineClass(name,b,off,len);
        }
    }
}