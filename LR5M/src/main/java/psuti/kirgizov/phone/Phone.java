package psuti.kirgizov.phone;

import java.io.Serializable;

public class Phone implements Serializable {
    private int number, score, releaseYear;
    private String name, manufacturer, operatingSystem, site;

    public Phone() {
    }

    public Phone(int number, String name, String manufacturer, String operatingSystem, int releaseYear, String site, int score) {
        this.number = number;
        this.name = name;
        this.manufacturer = manufacturer;
        this.operatingSystem = operatingSystem;
        this.releaseYear = releaseYear;
        this.site = site;
        this.score = score;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getNumber() {
        return number;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
