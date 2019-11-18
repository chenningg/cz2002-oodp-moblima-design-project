package managers;

import utils.IDHelper;
import utils.ProjectRootPathFinder;
import utils.SerializerHelper;
import movie_entities.Review;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
 
public class ReviewManager {
	// Attributes
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
    public void printReviews(List<String> reviewIDs) {
    	int i=0;
    	
    	for (String reviewID : reviewIDs) {
    		i++;
    		Review review = this.reviews.get(reviewID);
    		
    		System.out.println("================ REVIEW " + i + " ================");
            System.out.println("Name: " + review.getReviewerName());
            System.out.println("Title: " + review.getReviewTitle());
            System.out.println("Score: " + review.getScore() + "/5");
            System.out.println("Review body: " + review.getReviewBody());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh.mma");
            System.out.println("Review date: "+review.getreviewDateTime().format(formatter));
            System.out.println("==================================================");
            System.out.println("");
    	}
    	
    	if (i==0) {
    		System.out.println("No reviews found");
    	}
    }
    
  
    public void addReview(String movieID) {
    	Review review = new Review();
    	
    	////////////// INPUT VALIDATION NEEDED
 	
        System.out.println("Enter your name: ");
        review.setReviewerName(sc.nextLine());
        
        System.out.println("Enter title of review: ");
        review.setReviewTitle(sc.nextLine());
        
        System.out.println("Enter review: ");
        review.setReviewBody(sc.nextLine());
        
        System.out.println("Enter a movie score between 0-5: ");
        review.setScore(sc.nextDouble());
        
        review.setReviewDateTime(LocalDateTime.now());
        
        int choice;
        
        do {
            System.out.println(	"========================= ADD REVIEW ====================\n" +
			                    " 1. Submit review	   						    	 	 \n" +
			                    " 2. Edit review	   						    	 	 \n" +
				                " 0. Discard review, back to Movie Choices              \n"+
                                "=========================================================");
            
            System.out.println("Your current review: ");
            System.out.println("Name: " + review.getReviewerName());
            System.out.println("Title: " + review.getReviewTitle());
            System.out.println("Review body: " + review.getReviewBody());
            System.out.println("Score: " + review.getScore());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh.mma");
            System.out.println("DateTime: " + review.getreviewDateTime().format(formatter));
            System.out.println("");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
        
            switch (choice) {
            case 1:
            	String reviewID = IDHelper.getLatestID("review");
            	review.setReviewID(reviewID);
            	this.save(review);
            	MovieManager.getInstance().updateReview(movieID, reviewID, review.getScore(), "add");
            	
            	this.reviews.put(review.getReviewID(), review);
            	
            	System.out.println("Review created! Back to ReviewPortal......");
            	choice = 0;
            	break;
            case 2:
            	this.editReview(review);
            	break;
            case 0:
            	System.out.println("Review discarded. Back to MovieChoices......");
            	break;
        	default:
        		System.out.println("Invalid choice. Please enter a number between 0-2");
        		break;
            }
        
        } while (choice != 0);
    }
    
    
    public void deleteReview(List<String> reviewIDs) {
    	this.printReviews(reviewIDs);
    	System.out.println("");
    	
    	int choice;
    	
    	do {
        	System.out.println("Which review would you like to delete? Input 0 to go back to MovieChoices");
        	
        	choice = sc.nextInt();
        	
        	if (choice == 0) {
        		System.out.println("Back to MovieChoices......");
        		return;
        	} else if (choice <= reviewIDs.size()) {
            	String reviewID = reviewIDs.get(choice-1);       		
        		MovieManager.getInstance().updateReview(this.reviews.get(reviewID).getMovieID(), reviewID, this.reviews.get(reviewID).getScore(), "remove");

        		String root = ProjectRootPathFinder.findProjectRootPath();
        		File file = new File(root + "/data/reviews/review_" + reviewID + ".dat");
        		file.delete();
        		this.reviews = this.load();        		
        		choice = 0;
        	} else {
        		System.out.println("Invalid input. Please give a number between 0-" + reviewIDs.size());
        	}    		
    	} while (choice != 0);
    }
    
    
	// Private CRUD methods  
    private void editReview(Review review) {
    	int choice;
    	
    	do {
            System.out.println(	"======================== EDIT REVIEW ====================\n" +
				                " 1. Edit Name		   						    	 	 \n" +
				                " 2. Edit Title	   						    	 	 \n" +
				                " 3. Edit Review Body	   						    	\n" +
				                " 4. Edit Score	   						    	 	 \n" +
				                " 0. Finish Editing Review 				            \n"+
                                "=========================================================");

            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
            case 1:
                System.out.println("Enter your name: ");
                review.setReviewerName(sc.nextLine());
            	break;
            case 2:
                System.out.println("Enter title of review: ");
                review.setReviewTitle(sc.nextLine());
            	break;
            case 3:
                System.out.println("Enter review: ");
                review.setReviewBody(sc.nextLine());
            	break;
            case 4: 
                System.out.println("Enter a movie score between 0-5: ");
                review.setScore(sc.nextDouble());   
            	break;
            case 0:
            	System.out.println("Review discarded. Back to AddReview......");
            	break;
        	default:
        		System.out.println("Invalid choice. Please enter a number between 0-4");
        		break;
            } 
    	} while (choice != 0);       
    }
    
    

	// Private Serialization and Deserialization
	private void save(Review review) {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/reviews/review_" + review.getReviewID() + ".dat";
		SerializerHelper.serializeObject(review, filePath);
	}
    
    public HashMap<String, Review> load() {
        HashMap<String, Review> loadedReviews = new HashMap<String, Review>();
        File folder = new File(ProjectRootPathFinder.findProjectRootPath() + "/data/reviews");

        File[] listOfFiles = folder.listFiles();
        
        if(listOfFiles != null){
          for(int i=0;i<listOfFiles.length;i++){
            String filepath = listOfFiles[i].getPath(); // Returns full path incl file name and type
            Review newReview = (Review) SerializerHelper.deSerializeObject(filepath);
            String fileID = listOfFiles[i].getName().split("\\.(?=[^\\.]+$)")[0].split("_")[1];
                loadedReviews.put(fileID, newReview);
            }
        }
        return loadedReviews;
    }    
}
