import java.util.Date;

public class Rental {
    private int rentalId;
    private int customerId;
    private int videoId;
    private Date rentalDate;
    private Date returnDate;
    private int rentalDuration;
    private double rentalCost;

    // Constructors

    public Rental(int rentalId, int customerId, int videoId, Date rentalDate, Date returnDate, int rentalDuration, double rentalCost) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.videoId = videoId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.rentalDuration = rentalDuration;
        this.rentalCost = rentalCost;
    }


    // Rental Id
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    // Customer Id
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Video Id
    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    // Rental Date
    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    // Return Date
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    // Rental Duration
    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    // Rental Cost
    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }
}
