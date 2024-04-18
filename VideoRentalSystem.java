import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoRentalSystem {
    private List<Customer> customers;
    private List<Video> videos;
    private List<Rental> rentals;

    public VideoRentalSystem() {
        customers = new ArrayList<>();
        videos = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void readDataFromFile(String customerFile, String videoFile, String rentalFile) {
        readCustomers(customerFile);
        readVideos(videoFile);
        readRentals(rentalFile);
    }

    private void readCustomers(String customerFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(customerFile))) {
            String line;
            boolean isFirstLine = true; // Flag to skip the first line (header)
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                String[] parts = line.split(",");
                int customerId = Integer.parseInt(parts[0]);
                String name = parts[1];
                String contactDetails = parts[2];
                String membershipType = parts[3];
                customers.add(new Customer(customerId, name, contactDetails, membershipType));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void readVideos(String videoFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(videoFile))) {
            String line;
            boolean isFirstLine = true; // Flag to skip the first line (header)
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                String[] parts = line.split(",");
                int videoId = Integer.parseInt(parts[0]);
                String title = parts[1];
                String genre = parts[2];
                String rating = parts[3];
                int releaseYear = Integer.parseInt(parts[4]);
                int rentalDuration = Integer.parseInt(parts[5]);
                boolean availabilityStatus = Boolean.parseBoolean(parts[6]);
                videos.add(new Video(videoId, title, genre, rating, releaseYear, rentalDuration, availabilityStatus));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void readRentals(String rentalFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rentalFile))) {
            String line;
            boolean isFirstLine = true; // Flag to skip the first line (header)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                String[] parts = line.split(",");
                int rentalId = Integer.parseInt(parts[0]);
                int customerId = Integer.parseInt(parts[1]);
                int videoId = Integer.parseInt(parts[2]);
                // Parse date strings into Date objects
                Date rentalDate = dateFormat.parse(parts[3]);
                Date returnDate = dateFormat.parse(parts[4]);
                int rentalDuration = Integer.parseInt(parts[5]);
                double rentalCost = Double.parseDouble(parts[6]);
                rentals.add(new Rental(rentalId, customerId, videoId, rentalDate, returnDate, rentalDuration, rentalCost));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    

  

    public void displayRecordsPerPage(int pageNumber, int recordsPerPage) {
        int startIndex = (pageNumber - 1) * recordsPerPage;
        int endIndex = Math.min(startIndex + recordsPerPage, rentals.size());

        System.out.println("Displaying records for page " + pageNumber + ":\n");

        System.out.printf("%-15s %-25s %-15s %-15s %-15s %-15s %-15s%n", "Rental ID", "Customer ID", "Video ID", "Rental Date", "Return Date", "Rental Duration", "Rental Cost");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

        for (int i = startIndex; i < endIndex; i++) {
            Rental rental = rentals.get(i);
            System.out.printf("%-15d %-25d %-15d %-15s %-15s %-15d %-15.2f%n",
                    rental.getRentalId(), rental.getCustomerId(), rental.getVideoId(),
                    rental.getRentalDate(), rental.getReturnDate(), rental.getRentalDuration(), rental.getRentalCost());
        }

        System.out.println();
    }


    public void updateRecordDetails(int rentalId, String fieldName, String newValue) {
        for (Rental rental : rentals) {
            if (rental.getRentalId() == rentalId) {
                switch (fieldName) {
                    case "rentalDate":
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            rental.setRentalDate(dateFormat.parse(newValue));
                            System.out.println("Rental date updated successfully.");
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please provide date in yyyy-MM-dd format.");
                        }
                        break;
                    case "returnDate":
                        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            rental.setReturnDate(dateFormat.parse(newValue));
                            System.out.println("Return date updated successfully.");
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please provide date in yyyy-MM-dd format.");
                        }
                        break;
                    // Add cases for other fields if needed
                    default:
                        System.out.println("Invalid field name. Please provide a valid field name.");
                }
                return;
            }
        }
        System.out.println("Rental with ID " + rentalId + " not found.");
    }

    public void calculateAverages() {
        int totalRentalDuration = 0;
        double totalRentalCost = 0;
        int totalCustomers = customers.size();
        int totalVideos = videos.size();
        int totalRentals = rentals.size();
        for (Rental rental : rentals) {
            totalRentalDuration += rental.getRentalDuration();
            totalRentalCost += rental.getRentalCost();
        }
        double avgRentalDuration = (double) totalRentalDuration / totalRentals;
        double avgRentalCost = totalRentalCost / totalRentals;
        System.out.println("Average rental duration: " + avgRentalDuration);
        System.out.println("Average rental cost: " + avgRentalCost);
        System.out.println("Total customers: " + totalCustomers);
        System.out.println("Total videos: " + totalVideos);
        System.out.println("Total rentals: " + totalRentals);
    }

    public static void main(String[] args) {
        VideoRentalSystem system = new VideoRentalSystem();
        system.readDataFromFile("customer_data.csv", "video_data.csv", "rental_data.csv");
        system.displayRecordsPerPage(1, 10);
        // Update record example
        system.updateRecordDetails(201, "rentalDate", "2022-01-10");
        system.displayRecordsPerPage(1, 10);
        // Calculate averages
        system.calculateAverages();
    }
}
