package level_37.task3702.female;

import level_37.task3702.AbstractFactory;
import level_37.task3702.Human;

public class FemaleFactory implements AbstractFactory {
    public Human getPerson (int age){
        if (age<= KidGirl.MAX_AGE) return new KidGirl();
        if (age<= TeenGirl.MAX_AGE) return new TeenGirl();
        return new Woman();
    }
}
