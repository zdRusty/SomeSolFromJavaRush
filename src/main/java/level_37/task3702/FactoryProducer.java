package level_37.task3702;

import level_37.task3702.female.FemaleFactory;
import level_37.task3702.male.MaleFactory;

public class FactoryProducer {
    public static enum HumanFactoryType {
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory (HumanFactoryType type){
        if (type.equals(HumanFactoryType.MALE)) return new MaleFactory();
        if (type.equals(HumanFactoryType.FEMALE)) return new FemaleFactory();
        return null;
    }
}
