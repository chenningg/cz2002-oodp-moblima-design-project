import java.io.Serializable;
import java.time.LocalDate;

public class Review implements Serializable {
    //Attributes
    private String reviewID;
    private String reviewerName;
    private String reviewTitle;
    private String reviewBody;
    private double score;
    private LocalDate reviewDate;


    //Methods
    //Getters
    public String getReviewID() {
        return reviewID;
    }
    public String getReviewerName() { return reviewerName; }
    public String getReviewTitle() {
        return reviewTitle;
    }
    public String getReviewBody() {
        return reviewBody;
    }
    public double getScore() {
        return score;
    }
    public LocalDate getReviewDate() {
        return reviewDate;
    }
    //Setters
    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }
    public void setReviewerName(String reviewName) { this.reviewerName = reviewName; }
    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }
    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
