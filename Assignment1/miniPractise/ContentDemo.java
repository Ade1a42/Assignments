package miniPractise;

import java.util.ArrayList;
import java.time.Year;

public class ContentDemo {
    public static void main(String[] args) {
        // Create ArrayList to store different content items
        ArrayList<ContentItem> items = new ArrayList<>();

        // Add miniPractise.VideoLecture objects
        items.add(new VideoLecture("Java OOP Basics", 2023, 45, "HD"));
        items.add(new VideoLecture("Data Structures", 2021, 90, "4K"));

        // Add miniPractise.PodcastEpisode objects
        items.add(new PodcastEpisode("Tech Talk", 2022, 60, "Alex Johnson"));
        items.add(new PodcastEpisode("Programming Insights", 2024, 30, "Sam Rivera"));

        // Get current year
        int currentYear = Year.now().getValue();

        System.out.println("=== Content Library ===");
        System.out.println("Current Year: " + currentYear + "\n");

        // Loop through items and demonstrate polymorphism
        for (ContentItem item : items) {
            System.out.println(item.toString() +
                    " | License Cost: $" +
                    String.format("%.2f", item.getLicenseCost(currentYear)));

            // Check if item is miniPractise.Downloadable
            if (item instanceof Downloadable) {
                Downloadable downloadableItem = (Downloadable) item;
                downloadableItem.download();
                System.out.println("Max downloads/day: " +
                        downloadableItem.getMaxDownloadsPerDay());
            }
            System.out.println("---");
        }

        // Example of validation (will throw exception if uncommented)
        // items.add(new miniPractise.VideoLecture("", 2023, 45, "HD")); // Invalid title
        // items.add(new miniPractise.VideoLecture("Test", 1800, 45, "HD")); // Invalid year
        // items.add(new miniPractise.VideoLecture("Test", 2023, -5, "HD")); // Invalid duration
    }
}