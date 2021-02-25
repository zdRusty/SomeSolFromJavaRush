package level_33.task3310;

import level_33.task3310.strategy.HashMapStorageStrategy;
import level_33.task3310.strategy.OurHashMapStorageStrategy;
import level_33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
         StorageStrategy strategy = new HashMapStorageStrategy();
         StorageStrategy our = new OurHashMapStorageStrategy();
         testStrategy(strategy,20000);
         testStrategy(our,20000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new HashSet<>();
        for(String x: strings){
            result.add(shortener.getId(x));
        }
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> result = new HashSet<>();
        for(Long x: keys){
            result.add(shortener.getString(x));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strGen = new HashSet<>();
        for(int i=0;i<elementsNumber;i++){
            strGen.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date start = new Date();
        Set<Long> ids = getIds(shortener,strGen);
        Date end = new Date();
        long timeToWork_getIds = end.getTime()-start.getTime();
        Helper.printMessage(Long.toString(timeToWork_getIds));

        Date start2 = new Date();
        Set<String> stringSet = getStrings(shortener,ids);
        Date end2 = new Date();
        long timeToWork_getStrings = end2.getTime()-start2.getTime();
        Helper.printMessage(Long.toString(timeToWork_getStrings));
        if (strGen.equals(stringSet)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
