import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReviewManager {
    // prevents errors
    private Scanner sc = new Scanner(System.in);
    private Map<String, Review> reviews;

    private static ReviewManager single_instance = null;

    private ReviewManager() {
    	this.reviews = new HashMap<String, Review>();
    	this.reviews = this.load();
    }

    public static ReviewManager getInstance() {
        if (single_instance == null)
            single_instance = new ReviewManager();
        return single_instance;
    }
    
    
	// Public exposed methods to app
    public void reviewMenu(String movieID) {
    	int choice;
    	
    	do {
            System.out.println(	"===================== REVIEW PORTAL =====================\n" +
			                    "| 1. Leave a review	 						    	 |\n" +
				                "| 0. Back to Movie Listings	                         |\n" +
				                "=========================================================");    	
    	
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
	            case 1:
	            	this.addReview(movieID);
	            	choice = 0;
	            	break;
	            case 0:
	            	System.out.println("Back to Movie Listings......");
	            	break;
            	default:
            		System.out.println("Invalid choice. Please enter a number between 0-1");
            }
            
    	} while (choice != 0);
    }
    
    public void printReviews(ArrayList<String> reviewIDs) {
    	int i=1;
    	
    	for (String reviewID : reviewIDs) {
    		Review review = this.reviews.get(reviewID);
    		
    		System.out.println("================ REVIEW " + i + " ================");
            System.out.println("Your current review: ");
            System.out.println("Name: " + review.getReviewerName());
            System.out.println("Title: " + review.getReviewTitle() + "     Score: " + review.getScore() + "/5");
            System.out.println("Review body: " + review.getReviewBody());
            System.out.println("");
            
            i++;
    	}
    	
    }
    
    
	// Private CRUD methods    
    private String addReview(String movieID) {
    	Review review = new Review();
    	
    	////////////// INPUT VALIDATION NEEDED
 	
        System.out.println("Enter your name: ");
        review.setReviewerName(sc.next());
        
        System.out.println("Enter title of review: ");
        review.setReviewTitle(sc.next());
        
        System.out.println("Enter review: ");
        review.setReviewBody(sc.next());
        
        System.out.println("Enter a movie score between 0-5: ");
        review.setScore(sc.nextDouble());
        
        int choice;
        
        do {
            System.out.println(	"========================= ADD REVIEW ====================\n" +
			                    "| 1. Submit review	   						    	 	 |\n" +
			                    "| 2. Edit review	   						    	 	 |\n" +
				                "| 0. Discard review, back to ReviewPortal               |\n" +
				                "=========================================================");            	
            
            System.out.println("Your current review: ");
            System.out.println("Name: " + review.getReviewerName());
            System.out.println("Title: " + review.getReviewTitle());
            System.out.println("Review body: " + review.getReviewBody());
            System.out.println("Score: " + review.getScore());
            
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
        
            switch (choice) {
            case 1:
            	String reviewID = IDHelper.getLatestID("review");
            	review.setReviewID(reviewID);
            	this.save(review);
            	MovieManager.getInstance().updateReview(movieID, reviewID, review.getScore());
            	
            	this.reviews.put(review.getReviewID(), review);
            	
            	System.out.println("Review created! Back to ReviewPortal......");
            	break;
            case 2:
            	this.editReview(review);
            	break;
            case 0:
            	System.out.println("Review discarded. Back to ReviewPortal......");
            	break;
        	default:
        		System.out.println("Invalid choice. Please enter a number between 0-2");
        		break;
            }
        
        } while (choice != 0);
    }

    public ArrayList<Review> getReviews(Movie movie) {
        return movie.getMovieReviews();
    }

    public float getAverageReviewScore(Movie movie) {
        ArrayList<Review> reviews = movie.getMovieReviews();
        float sum = 0;
        for (Review review : reviews) {
            sum += review.getScore();
        }
        return sum / reviews.size();
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        review = review;
    }


    public void displayReview(Movie movie) {
        ArrayList<Review> reviewList = getReviews(movie);
        for (int i = 0; i < reviewList.size(); i++) {
            System.out.println(i + 1 + ". " + reviewList.get(i));
        }
    }
   */
}



class testApp {
	public static void main(String[] args) {
		ReviewManager.getInstance();
	}
}