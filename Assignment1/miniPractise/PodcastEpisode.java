package miniPractise;

public class PodcastEpisode extends ContentItem implements Downloadable {
    private String hostName;

    public PodcastEpisode(String title, int year, int durationMinutes, String hostName) {
        super(title, year, durationMinutes);
        this.hostName = hostName;
    }

    @Override
    public double getLicenseCost(int currentYear) {
        int age = getAge(currentYear);
        double ageFactor = (age <= 2) ? 3 : 1;
        return 0.03 * getDurationMinutes() + ageFactor;
    }

    // miniPractise.Downloadable interface methods
    @Override
    public void download() {
        System.out.println("Downloading podcast hosted by " + hostName + "...");
    }

    @Override
    public int getMaxDownloadsPerDay() {
        return 10;
    }

    @Override
    public String toString() {
        return super.toString() + " | Host: " + hostName;
    }
}