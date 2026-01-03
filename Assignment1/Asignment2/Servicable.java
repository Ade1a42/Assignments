package Asignment2;

public abstract class Servicable extends Vehicle {
    public Servicable(String model, int year, double basePrice) {
        super(model, year, basePrice);
    }

    public interface Serviceable {
        void performService();      // What the object can DO
        int getServiceIntervalKm(); // How often to do it
    }
}