package Level_25.task2909.car;

public class Truck extends Car{
    public Truck(int numberOfPassengers) {
        super(Car.TRUCK, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return Car.MAX_TRUCK_SPEED;
    }
}
