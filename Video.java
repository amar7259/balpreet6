public class Video {
    private int videoId;
    private String title;
    private String genre;
    private String rating;
    private int releaseYear;
    private int rentalDuration;
    private boolean availabilityStatus;

    public Video(int videoId, String title, String genre, String rating, int releaseYear, int rentalDuration, boolean availabilityStatus) {
        this.videoId = videoId;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.rentalDuration = rentalDuration;
        this.availabilityStatus = availabilityStatus;
    }

    // Getters and setters
}
