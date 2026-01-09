package miniPractise;

import java.time.Year;

public abstract class ContentItem {
    private int id;
    private static int idGen = 1;
    private String title;
    private int year;
    private int durationMinutes;

    public ContentItem(String title, int year, int durationMinutes){
        this.id = idGen++;

        setTitle(title);
        setYear(year);
        setMinute(durationMinutes);
    }

    public void setTitle(String title){
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        this.title = title;
    }

    public void setYear(int year){
        // !!!
        int currentYear = Year.now().getValue();
        if(year >= 1990 && year <= currentYear){
            this.year = year;
        } else {
            throw new IllegalArgumentException("Year must be between 1990 and " + currentYear);
        }
    }

    public void setMinute(int durationMinutes){
        if(durationMinutes > 0){
            this.durationMinutes = durationMinutes;
        } else {
            throw new IllegalArgumentException("Duration must be > 0");
        }
    }


    public int getAge(int currentYear){
        return currentYear - this.year;
    }

    // !!!
    public abstract double getLicenseCost(int currentYear);

    public int getDurationMinutes(){ return durationMinutes; }

    public String getTitle(){ return title; }

    public int getYear(){ return year; }

    @Override
    public String toString() {
        return "ID: " + id +
                " Title: " + title +
                " Year: " + year +
                " Duration minutes: " + durationMinutes + " minutes";
    }
}
