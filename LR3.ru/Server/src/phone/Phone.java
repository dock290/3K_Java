package phone;

import java.io.Serializable;
import java.util.ArrayList;

public class Phone implements Serializable {
    private int modelIndex;
    private String name, manufacturer, operatingSystem, releaseYear;

    private ArrayList<Review> reviews;
    private int average;

    public Phone(int modelIndex, String name, String manufacturer, String operatingSystem, String releaseYear) {
        this.modelIndex = modelIndex;
        this.name = name;
        this.manufacturer = manufacturer;
        this.operatingSystem = operatingSystem;
        this.releaseYear = releaseYear;

        reviews = new ArrayList<>();
    }

    public void setModelIndex(int modelIndex) {
        this.modelIndex = modelIndex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
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

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getModelIndex() {
        return modelIndex;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public int getAverage() {
        return average;
    }

    public String getReleaseYear() {
        return releaseYear;
    }
}
