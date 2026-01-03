package Asignment2;

import java.time.Year;

public abstract class Vehicle {
    private int id;
    private static int idGen = 1;
    private String model;
    private int year;
    private double basePrice;

    public Vehicle(String model, int year, double basePrice){
        this.id = idGen++;

        setModel(model);
        setYear(year);
        setPrice(basePrice);
    }

    public void setModel(String model) {
        if( model == null || model.isBlank() ){
            throw new IllegalArgumentException("Model cannot be null or blank");
        } else {
            this.model = model;
        }
    }

    public void setYear(int year) {
        //!!!
        int currentYear = Year.now().getValue();
        if(year >= 1990 && year <= currentYear){
            this.year = year;
        } else {
            throw new IllegalArgumentException("Year must be between 1990 and " + currentYear);
        }
    }

    public void setPrice(double basePrice) {
        if(basePrice > 0){
            this.basePrice = basePrice;
        } else {
            throw new IllegalArgumentException(" base price must be > 0 ");
        }
    }

    public String getModel(){
        return model;
    }

    public int getYear(){
        return year;
    }

    public double getPrice(){
        return basePrice;
    }

    public int getId() {
        return id;
    }

    public int getAge(int currentYear){
        return currentYear - year;
    }

    public abstract double calculateInsuranceFee(int numberOfDoors);
    public abstract double calculateInsuranceFee();

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", basePrice=" + basePrice +
                '}';
    }



}
