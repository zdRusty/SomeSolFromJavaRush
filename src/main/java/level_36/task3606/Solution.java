package level_36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/*
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");

        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        MyLoader classLoader = new MyLoader();
        String separator = File.separator;
        File pack = new File(packageName);
        File[] files = pack.listFiles();
        for(File x: files){
            if (x.getName().endsWith(".class")){
                Class<?> clazz = classLoader.loadClazz(x);
                if(clazz!=null) hiddenClasses.add(clazz);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key){
        for(Class<?> x: hiddenClasses){
            if(x.getSimpleName().toLowerCase().startsWith(key.toLowerCase())){
                HiddenClass hiddenClass = null;
                try {
                    Constructor<?>[] constructors = x.getDeclaredConstructors();
                    for(Constructor<?> c: constructors){
                        c.setAccessible(true);
                        if(c.getParameterCount()==0){
                            hiddenClass = (HiddenClass) c.newInstance();
                        }
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return hiddenClass;
            }
        }
        return null;
    }

    public static class MyLoader extends ClassLoader {
        public Class<?> loadClazz (File file) {
            try {
                byte[] b = Files.readAllBytes(file.toPath());
                return defineClass(null, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}