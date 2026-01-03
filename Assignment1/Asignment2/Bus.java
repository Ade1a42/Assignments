package Asignment2;

import java.time.Year;

public abstract class Bus extends Vehicle{
    private int passengerCapacity;

    public Bus(String model, int year, double basePrice, int capacity) {
        super(model, year, basePrice);
        calculateInsuranceFee();
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity){
        if (passengerCapacity < 10 || passengerCapacity > 100) {
            throw new IllegalArgumentException("Passenger capacity must be between 10 and 100");
        } else {
        this.passengerCapacity = passengerCapacity; }
    }

    @Override
    public double calculateInsuranceFee() {
        int currentYear = Year.now().getValue();
        int age = getAge(currentYear);

        // Bus insurance formula: base + age factor + capacity factor
        double baseRate = getPrice() * 0.08;  // 8% of base price (higher than car)
        double ageFactor = age * 250;  // $250 per year old (higher than car)
        double capacityFactor = passengerCapacity * 10;  // $10 per passenger

        return baseRate + ageFactor + capacityFactor;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "passengerCapacity=" + passengerCapacity +
                '}';
    }
}



