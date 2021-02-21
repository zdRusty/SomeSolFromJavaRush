package level_25.task2502;

import java.util.ArrayList;
import java.util.List;

/*
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception{
            //init wheels here
            wheels = new ArrayList<>();
            if (Wheel.values().length!=loadWheelNamesFromDB().length) throw new IllegalArgumentException();
            for(String x: loadWheelNamesFromDB()){
                wheels.add(Wheel.valueOf(x));
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}


