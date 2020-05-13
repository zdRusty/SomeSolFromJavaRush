package Level_25.task2909.car;

public class Sedan extends Car{
    public Sedan(int numberOfPassengers) {
        super(Car.SEDAN, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return Car.MAX_SEDAN_SPEED;
    }
}
