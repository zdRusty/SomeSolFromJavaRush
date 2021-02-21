package level_37.task3702.male;

import level_37.task3702.AbstractFactory;
import level_37.task3702.Human;

public class MaleFactory implements AbstractFactory {
    public Human getPerson (int age){
        if (age<=KidBoy.MAX_AGE) return new KidBoy();
        if (age<=TeenBoy.MAX_AGE) return new TeenBoy();
        return new Man();
    }
}
