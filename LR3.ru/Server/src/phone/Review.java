package phone;

import java.io.Serializable;

public class Review implements Serializable {
    private String title;
    private int score;

    public Review(String title, int score) {
        this.title = title;
        this.score = score;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public int getScore() {
        return score;
    }
}
