package Asignment2;

import java.time.Year;

public abstract class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String model, int year, double basePrice, int doors) {
        super(model, year, basePrice);
        calculateInsuranceFee(numberOfDoors);
    }

    public int getNumberOfDoors() { return numberOfDoors; }

    public void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors < 2 || numberOfDoors > 6) {
            throw new IllegalArgumentException("Number of doors must be between 2 and 6");
        } else {
            this.numberOfDoors = numberOfDoors;
        }
    }

    @Override
    public double calculateInsuranceFee() {
        int currentYear = Year.now().getValue();
        int age = getAge(currentYear); // from vehicle

        // Car insurance formula: base + age factor + door factor
        double baseRate = getPrice() * 0.05;  // 5% of base price
        double ageFactor = age * 150;  // $150 per year old
        double doorFactor = numberOfDoors * 25;  // $25 per door

        return baseRate + ageFactor + doorFactor;
    }

    @Override
    public String toString() {
        return "Car{" +
                "numberOfDoors=" + numberOfDoors +
                '}';
    }
}

