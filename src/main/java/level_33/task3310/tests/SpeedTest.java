package level_33.task3310.tests;

import level_33.task3310.Helper;
import level_33.task3310.Shortener;
import level_33.task3310.strategy.HashBiMapStorageStrategy;
import level_33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        long start = new Date().getTime();
        ids = strings.stream().map(shortener::getId).collect(Collectors.toSet());
        long end = new Date().getTime();
        return end-start;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        long start = new Date().getTime();
        strings = ids.stream().map(shortener::getString).collect(Collectors.toSet());
        long end = new Date().getTime();
        return end-start;
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for(int i=0;i<10000;i++){
            origStrings.add(Helper.generateRandomString());
        }
        Assert.assertTrue(getTimeToGetIds(shortener1, origStrings, new HashSet<>()) > getTimeToGetIds(shortener2,origStrings, new HashSet<>()));

        Set<Long> ids1 = origStrings.stream().map(shortener1::getId).collect(Collectors.toSet());
        Set<Long> ids2 = origStrings.stream().map(shortener2::getId).collect(Collectors.toSet());
        Assert.assertEquals(getTimeToGetStrings(shortener1, ids1, new HashSet<>()),getTimeToGetStrings(shortener2, ids2, new HashSet<>()),30);

    }
}
