package level_33.task3310.tests;

import level_33.task3310.Shortener;
import level_33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener){

        String str1 = new String("str");
        String str2 = new String("str2");
        String str3 = new String("str");

        Long idStr1 = shortener.getId(str1);
        Long idStr2 = shortener.getId(str2);
        Long idStr3 = shortener.getId(str3);

        Assert.assertNotEquals(str2,str1);
        Assert.assertNotEquals(str2,str3);
        Assert.assertEquals(str1,str3);

        String strNew1 = shortener.getString(idStr1);
        String strNew2 = shortener.getString(idStr2);
        String strNew3 = shortener.getString(idStr3);

        Assert.assertEquals(str1,strNew1);
        Assert.assertEquals(str2,strNew2);
        Assert.assertEquals(str3,strNew3);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy(){
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy(){
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy(){
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashMapStorageStrategy(){
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy(){
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy(){
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }
}
