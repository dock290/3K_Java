import java.util.ArrayList;

public class Phone {
    private String name, manufacturer, operatingsystem;

    private ArrayList<Review> reviews;
    private int average;

    public Phone(String name, String manufacturer, String operatingsystem) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.operatingsystem = operatingsystem;

        reviews = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setOperatingsystem(String operatingsystem) {
        this.operatingsystem = operatingsystem;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public void addSubject(Review review) {
        reviews.add(review);
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getOperatingsystem() {
        return operatingsystem;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public int getAverage() {
        return average;
    }
}
