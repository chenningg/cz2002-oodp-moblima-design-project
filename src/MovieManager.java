import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


class MovieManager {
	// Attributes 

    private Map<String,Movie> movies;
    private Scanner sc = new Scanner(System.in);

    private static MovieManager single_instance = null;

    public static MovieManager getInstance()
    {
        if (single_instance == null)
            single_instance = new MovieManager();
        return single_instance;
    }
    
    
    // Constructor
    private MovieManager() {
    	this.movies= new HashMap<String,Movie>();
    	this.loadObject();
    }
    

	// Public exposed methods to app
    public void movieMenuStaff() {
        int choice;
        
        do {
            System.out.println("=================== MOVIE MENU (STAFF) ==================\n" +
                               "| 1. View/ Edit Movies 						    		|\n" +
                               "| 2. Add Movies		                                 	|\n" +
                               "| 3. Search Movies (By Title)	                        |\n" +
                               "| 0. Back to StaffApp......                             |\n" +
                               "=========================================================");

            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    this.viewMovies("Staff");
                    break;
                case 2:
                    this.addMovies();
                    break;
                case 3:
                    this.searchMovies("Staff");
                    break;
                case 0:
                	System.out.println("Back to StaffApp......");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1-3.");
                    break;
            }
        } while (choice != 0);
    }

    public void viewMovies(String appType) {
        int choice;

        if (appType.equals("Staff")) {
            do {
                System.out.println("=================== MOVIE MENU (STAFF) ==================\n" +
                                   "| 1. List all movies	                                |\n" +
                                   "| 2. Coming Soon 						       			|\n" +
                                   "| 3. Preview		                                    |\n" +
                                   "| 4. Now Showing	                                    |\n" +
                                   "| 5. End of Showing                                     |\n" +
                                   "| 0. Back to Staff Movie Menu......                     |\n" +
                                   "=========================================================");

                System.out.println("Enter choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        List<Movie> movieList = new ArrayList<>(movies.values());
                        this.selectMovie(movieList,appType);
                        break;
                    case 2:
                        List<Movie> comingSoon = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("COMING_SOON")){
                                comingSoon.add(entry.getValue());
                            }
                        }
                        this.selectMovie(comingSoon,appType);
                        break;
                    case 3:
                        List<Movie> preview = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("PREVIEW")){
                                preview.add(entry.getValue());
                            }
                        }
                        this.selectMovie(preview,appType);
                        break;
                    case 4:
                        List<Movie> nowShowing = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                                nowShowing.add(entry.getValue());
                            }
                        }
                        this.selectMovie(nowShowing,appType);
                        break;
                    case 5:
                        List<Movie> endShowing = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("END_OF_SHOWING")){
                                endShowing.add(entry.getValue());
                            }
                        }
                        this.selectMovie(endShowing,appType);
                        break;
                    case 0:
                        System.out.println("Back to StaffApp...");
                        break;
                }
            } while (choice != 0);

        } else if (appType.equals("Customer")) {
            do {
                System.out.println("=================== MOVIE MENU (CUSTOMER) ================\n" +
                                   "| 1. List all movies	                                 |\n" +
                                   "| 2. Coming Soon 						       			 |\n" +
                                   "| 3. Preview		                                     |\n" +
                                   "| 4. Now Showing	                                     |\n" +
                                   "| 5. Search Movies (By Title)                            |\n" +
                                   "| 0. Back to Customer Movie Menu......                   |\n" +
                                   "==========================================================");
                System.out.println("Enter choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        List<Movie> allMovies = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("COMING_SOON")||
                                entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                                allMovies.add(entry.getValue());
                            }
                        }
                        this.selectMovie(allMovies,appType);
                        break;
                    case 2:
                        List<Movie> comingSoon = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("COMING_SOON")){
                                comingSoon.add(entry.getValue());
                            }
                        }
                        this.selectMovie(comingSoon,appType);
                        break;
                    case 3:
                        List<Movie> preview = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("PREVIEW")){
                                preview.add(entry.getValue());
                            }
                        }
                        this.selectMovie(preview,appType);
                        break;
                    case 4:
                        List<Movie> nowShowing = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                                nowShowing.add(entry.getValue());
                            }
                        }
                        this.selectMovie(nowShowing,appType);
                        break;
                    case 5:
                        this.searchMovies(appType);
                        break;
                    case 0:
                        System.out.println("Back to CustomerApp...");
                        break;
                }
            } while (choice != 0);
        }
    }

    public void subMovieMenu(Movie movie,String appType){
        if(appType.equals("Staff")) {
            int choice;
            do{
                System.out.println(	"====================== MOVIE CHOICES =====================\n" +
			                        "| 1. Display/Edit Showtimes                              |\n" +
			                        "| 2. Edit Movie 						       		      |\n" +
			                        "| 3. Remove Movie		                                  |\n" +
			                        "| 4. View Reviews	                                      |\n" +
			                        "| 0. Back to Movie Listings			                  |\n" +
			                        "==========================================================");            	

                System.out.println("Enter choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        ShowtimeManager.getInstance().getMovieShowtimes(movie.getMovieID(), appType);
                        break;
                    case 2:
                        this.editMovies(movie);
                        break;
                    case 3:
                        this.removeMovie(movie);
                        break;
                    case 4:
                        ReviewManager.getInstance().printReviews(movie.getReviews());
                        break;
                    case 0:
                    	System.out.println("Back to Movie Listings......");
                        break;
                    default:
                        System.out.println("Please enter a number between 0-4");
                }
            }while(choice != 0);
        }
        else if(appType.equals("Customer")){
            int choice;
            do{
                System.out.println(	"====================== MOVIE CHOICES =====================\n" +
			                        "| 1. Display Showtimes                                   |\n" +
			                        "| 2. View Reviews 						       		      |\n" +
			                        "| 0. Back to Movie Listings			                  |\n" +
			                        "==========================================================");       

                System.out.println("Enter your choice: ");
                choice = sc.nextInt();
                
                switch (choice) {
                    case 1:
                        ShowtimeManager.getInstance().getMovieShowtimes(movie.getMovieID(),appType);
                        break;
                    case 2:
                        ReviewManager.getInstance().printReviews(movie.getReviews());
                        break;
                    case 0:
                    	System.out.println("Back to Movie Listings......");
                        break;
                    default:
                        System.out.println("Please enter a number between 0-2");
                }
            } while(choice != 0);

        }
    }


    public List<Movie> searchMovies(String appType){
        System.out.println("Please enter a search term: ");
        String movieTitle = sc.next();
        List<Movie> foundMovies = new ArrayList<>();
        String lowerCaseName = movieTitle.toLowerCase();

        // for-each loop
        for (Movie movie : movies.values()) {
            if (movie.getTitle().toLowerCase().contains(lowerCaseName)) {
                foundMovies.add(movie);
            }
        }
        if(foundMovies.size() == 0){
            System.out.println("No such movie found!");
            return null;
        }
        selectMovie(foundMovies,appType);
        return foundMovies;
    }

    public Movie selectMovie(List<Movie> movieSelect,String appType) {
        int choice,subChoice;
        do{
            for (int i = 0; i < movieSelect.size(); i++) {
                System.out.println(i + 1 + ". " + movieSelect.get(i).getTitle());
            }
            do {
                System.out.println("Choose a movie (Enter 0 to exit): ");
                choice = sc.nextInt()-1;
                             
                
                if(choice==-1) {
                    return null;
                } else if (choice < 0 || choice >= movieSelect.size()) {
                	System.out.println("Invalid choice. Please enter a number between 0 and " + movieSelect.size());
                } 
                
            }while(choice  < 0 || choice >= movieSelect.size());
            displayMovieDetails(movieSelect.get(choice));
            subMovieMenu(movieSelect.get(choice),appType);
            System.out.println("Enter 0 to return to Movie Menu \t\n" +
                    "Enter 1-9 to return to list of movies:");
            subChoice = sc.nextInt();
            if(subChoice == 0){
                break;
            }
        }while(subChoice != 0);
        return movieSelect.get(choice);
    }

    public void displayMovieDetails(Movie movie) {
        System.out.print("Movie Title: ");
        System.out.println(movie.getTitle());
        System.out.print("Genres: ");
        for (int i=0; i<movie.getGenres().size();i++) 
        	System.out.print(movie.getGenres().get(i)+ ", ");
        System.out.println();
        System.out.print("Rating:");
        System.out.println(movie.getMovieRating());
        System.out.print("Duration:");
        System.out.println(movie.getMovieDuration()+ " minutes");
        System.out.print("Movie Formats:");
        for (int i=0; i<movie.getMovieFormats().size();i++) 
        	System.out.print(movie.getMovieFormats().get(i)+ ", ");
        System.out.println();
        System.out.print("Showing Status: ");
        System.out.println(movie.getShowingStatus());
        System.out.print("Synopsis: ");
        System.out.println(movie.getSynopsis());
        System.out.print("Director: ");
        System.out.println(movie.getDirector());
        System.out.print("Cast: ");
        for(int i=0;i<movie.getCast().size();i++){
            System.out.print(movie.getCast().get(i)+ ", ");
        }

        System.out.println();
    }




//CRUD - CREATE READ UPDATE DELETE MOVIE

    private void addMovies() {
        Movie newMovie = new Movie();
        ArrayList<Genre> genreList = new ArrayList<>();
        ArrayList<String> castList = new ArrayList<>();
        ArrayList<MovieFormat> formatList = new ArrayList<>();

        newMovie.setMovieID(IDHelper.getLatestID("movie"));
        System.out.println("Enter movie title: ");
        newMovie.setTitle(sc.next());

        System.out.println("Enter number of genres: ");
        int numGenres = sc.nextInt();
        System.out.println("Enter the genres: ");
        for (int i=0;i<numGenres;i++)
        {
            System.out.println("Enter the genre: ");
            String userGenre = sc.next();
            genreList.add(Genre.valueOf(userGenre));
        }
        newMovie.setGenres(genreList);

        System.out.println("Enter director name: ");
        newMovie.setDirector(sc.next());

        System.out.println("Enter length of cast: ");
        int castLength = sc.nextInt();
        for (int i=0;i<castLength;i++)
        {
            System.out.println("Enter cast member: ");
            String castName = sc.next();
            castList.add(castName);
        }
        newMovie.setCast(castList);

        System.out.println("Enter synopsis: ");
        newMovie.setSynopsis(sc.next());

        System.out.println("Enter movie rating: ");
        String movieRating = sc.next();
        newMovie.setMovieRating(MovieRating.valueOf(movieRating));

        System.out.println("Enter number of movie formats: ");
        int formatLength = sc.nextInt();
        for (int i=0;i<formatLength;i++)
        {
            System.out.println("Enter movie format: ");
            String format = sc.next();
            formatList.add(MovieFormat.valueOf(format));
        }
        newMovie.setMovieFormats(formatList);

        System.out.println("Enter movie duration: ");
        newMovie.setMovieDuration(sc.nextInt());

        System.out.println("Enter showing status: ");
        String showStatus = sc.next();
        newMovie.setShowingStatus(ShowingStatus.valueOf(showStatus));

        System.out.println("Enter release date (format DD/MM/YYYY): ");
        String releaseDate = sc.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(releaseDate, dateFormat);
        newMovie.setReleaseDate(date);

        movies.put(newMovie.getMovieID(),newMovie);
    }


    private void editMovies(Movie movie) {
        int choice;
        do {
        	System.out.println("Currently selected movie details:");
        	displayMovieDetails(movie);
            System.out.println("=================== EDIT MOVIES (STAFF) ==================\n" +
                    "| 1. Edit Title      	                                 |\n" +
                    "| 2. Edit Genres (Overwritten)       	        		 |\n" +
                    "| 3. Edit Director	                                     |\n" +
                    "| 4. Edit Cast (Overwritten)                            |\n" +
                    "| 5. Edit Synopsis                                      |\n" +
                    "| 6. Edit Rating                                        |\n" +
                    "| 7. Edit Formats (Overwritten)                         |\n" +
                    "| 8. Edit Duration                                      |\n" +
                    "| 9. Edit Showing Status                                |\n" +
                    "| 10. Edit Release Date                                 |\n" +
                    "| 0. Back to Customer Movie Menu......                  |\n" +
                    "=========================================================");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                	System.out.println("Enter new title");
                    String title = sc.nextLine();
                    movie.setTitle(title);
                    break;
                case 2:
                    System.out.println("Enter number of genres: ");
                
                    for (Genre genre : Genre.values()) {
                    	System.out.println(genre.toString());
                    }
                
                    ArrayList<Genre> Genres = new ArrayList<>();
                
                    System.out.println("Enter number of genres: ");                              
                    int numGenres = sc.nextInt();
                
                    for (int i = 0; i < numGenres; i++) {
                        System.out.println("Enter the genre, press 'ENTER' after each entry: ");
                        String userGenre = sc.next().toUpperCase();
                        Genres.add(Genre.valueOf(userGenre));
                    }
                    movie.setGenres(Genres);
                    break;
                case 3:
                	System.out.println("Enter revised director name");
                    movie.setDirector(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Enter number of cast members: ");
                    int castSize = sc.nextInt();
                    sc.nextLine();
                    ArrayList<String> newCastList = new ArrayList<>();
                    for (int i = 0; i < castSize; i++) {
                        System.out.println("Enter cast member: ");
                        String newCast = sc.nextLine();
                        newCastList.add(newCast);
                    }
                    movie.setCast(newCastList);
                    break;
                case 5:
                    System.out.println("Enter new synopsis: ");
                    String newSynopsis = sc.nextLine();
                    movie.setSynopsis(newSynopsis);
                    break;
                case 6:
                    System.out.println("Enter new rating: ");
                    String newRating = sc.next();
                    movie.setMovieRating(MovieRating.valueOf(newRating));
                    break;
                case 7:
                    System.out.println("Enter number of formats: ");
                    ArrayList<MovieFormat> newFormats = new ArrayList<>();
                    int newFormatLength = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < newFormatLength; i++) {
                        System.out.println("Enter movie format: ");
                        String newFormat = sc.next();
                        newFormats.add(MovieFormat.valueOf(newFormat));
                    }
                    movie.setMovieFormats(newFormats);
                    break;
                case 8:
                    System.out.println("Enter new duration: ");
                    int newDuration = sc.nextInt();
                    movie.setMovieDuration(newDuration);
                    break;
                case 9:
                    System.out.println("Enter new showing status: ");
                    String newShowStatus = sc.next();
                    movie.setShowingStatus(ShowingStatus.valueOf(newShowStatus));
                    break;
                case 10:
                    System.out.println("Enter new release date: ");
                    String newReleaseDate = sc.next();
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(newReleaseDate, dateFormat);
                    movie.setReleaseDate(date);
                    break;
                case 0:
                    System.out.println("End of edit\n" +
                            "Back to Staff Sub Menu");
                    break;
                default:
                    System.out.println("Please enter a number from 1-11: ");
            }
        } while (choice != 0);
//
        this.saveObject(movie);
    }

    private void removeMovie(Movie movie) {
        movie.setShowingStatus(ShowingStatus.END_OF_SHOWING);
        this.saveObject(movie);
    }

    /***
     * Displays Top 5 Movies menu for Customers
     */
    public void viewTop5Cust() {
        int choice;
        String apptype = "Customer";
        do{
            System.out.println(	"==================== View Top 5 Movies =====================\n" +
			                    "| 1. By Sales                                              |\n" +
			                    "| 2. By Tickets Sold                                       |\n" +
			                    "| 3. By Reviews                                            |\n" +
			                    "| 0. Back to CustomerApp                                   |\n" +
			                    "===========================================================");
            System.out.println("Enter choice:");
            choice= sc.nextInt();
            switch (choice){
                case 1:
                    ArrayList<Movie> top5Sales = new ArrayList<Movie>(movies.values());
                    top5Sales.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
                    if(top5Sales.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    }
                    int input1;
                   do{
                       for(int i=0;i<5;i++) {
                           System.out.println(i+1 +". "+top5Sales.get(i).getTitle() +" \t\t\t(Sales:  "+ top5Sales.get(i).getGrossProfit()+")");
                       }
                       System.out.println("Choose a movie (Press 0 to exit): ");
                       input1 = sc.nextInt()-1;
                       if(input1 == -1){
                           break;
                       }
                       displayMovieDetails(top5Sales.get(input1));
                       subMovieMenu(top5Sales.get(input1),apptype);
                   }while(input1 != -1);
                    break;
                case 2:
                    ArrayList<Movie> top5Tickets = new ArrayList<Movie>(movies.values());
                    top5Tickets.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
                    if(top5Tickets.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    }
                    int input2;
                    do{
                        for(int i=0;i<5;i++) {
                            System.out.println(i+1 +". "+top5Tickets.get(i).getTitle() +" \t\t\t(Tickets Sold:  "+ top5Tickets.get(i).getTicketsSold()+")");
                        }
                        System.out.println("Choose a movie (Press 0 to exit): ");
                        input2 = sc.nextInt()-1;
                        if(input2 == -1){
                            break;
                        }
                        displayMovieDetails(top5Tickets.get(input2));
                        subMovieMenu(top5Tickets.get(input2),apptype);
                    }while(input2 != -1);
                    break;
                case 3:
                    ArrayList<Movie> top5Reviews = new ArrayList<Movie>(movies.values());
                    for(int i=top5Reviews.size()-1;i>=0;i--){
                        if(top5Reviews.get(i).getReviews().size() <= 1){
                            top5Reviews.remove(i);
                        }
                    }
                    top5Reviews.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                    if(top5Reviews.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    }
                    int input3;
                    do{
                        for(int i=0;i<5;i++) {
                            System.out.println(i+1 +". "+top5Reviews.get(i).getTitle() +" \t\t\t(Review Score:  "+ top5Reviews.get(i).getAverageReviewScore()+")");
                        }
                        System.out.println("Choose a movie (Press 0 to exit): ");
                        input3 = sc.nextInt()-1;
                        if(input3 == -1){
                            break;
                        }
                        displayMovieDetails(top5Reviews.get(input3));
                        subMovieMenu(top5Reviews.get(input3),apptype);
                    }while(input3 != -1);
                    break;
                case 0:
                    System.out.println("Back to Customer App...");
                    break;
                default:
                    System.out.println("Please enter a number between 1-3.");
            }
        }while(choice!=0);

    }

    /***
     * Displays Top 5 Movies menu for Staff
     */
    public void viewTop5Staff(){

        int choice;
        do{
            System.out.println(	"==================== View Top 5 Movies =====================\n" +
			                    "| 1. By Sales                                              |\n" +
			                    "| 2. By Tickets Sold                                       |\n" +
			                    "| 3. By Reviews                                            |\n" +
			                    "| 0. Back to StaffApp                                      |\n" +
			                    "===========================================================");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ArrayList<Movie> top5Sales = new ArrayList<Movie>(movies.values());
                    top5Sales.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Sales.get(i).getTitle()+" \t\t\t(Sales:  "+ top5Sales.get(i).getGrossProfit()+")");
                    }
                    break;
                case 2:
                    ArrayList<Movie> top5Tickets = new ArrayList<Movie>(movies.values());
                    top5Tickets.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Tickets.get(i).getTitle()+" \t\t\t(Tickets Sold:  "+ top5Tickets.get(i).getTicketsSold()+")");
                    }
                    break;
                case 3:
                    ArrayList<Movie> top5Reviews = new ArrayList<Movie>(movies.values());
                    for(int i=top5Reviews.size()-1;i>=0;i--){
                        if(top5Reviews.get(i).getReviews().size() <= 1){
                            top5Reviews.remove(i);
                        }
                    }
                    top5Reviews.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                    if(top5Reviews.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    }
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Reviews.get(i).getTitle()+" \t\t\t(Review Score:  "+ top5Reviews.get(i).getAverageReviewScore()+")");
                    }
                    break;
                case 0:
                    System.out.println("Back to StaffApp...");
                    break;
                default:
                    System.out.println("Please enter a number between 1-3.");
            }
        }while(choice!=0);
    }

    public List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<>(movies.values());
        return movieList;
    }

    public void loadObject() {
        File folder = new File(ProjectRootPathFinder.findProjectRootPath() + "/data/movies");

        File[] listOfFiles = folder.listFiles();
        
        if(listOfFiles != null){
        	for(int i=0;i<listOfFiles.length;i++){
        		String filepath = listOfFiles[i].getPath(); // Returns full path incl file name and type
        		Movie newMovie = (Movie)SerializerHelper.deSerializeObject(filepath);
                movies.put(newMovie.getMovieID(), newMovie);
            }
        }
    }

    public void saveObject(Movie movie) {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/movies/movie_"+movie.getMovieID()+".dat";
        SerializerHelper.serializeObject(movie, filepath);
        System.out.println("Movies Saved!");
    }

    public Movie getMoviebyID(String movieID){
        return movies.get(movieID);
    }

    public void updateGrossProfit(String movieID,double grossProfit){
        movies.get(movieID).setGrossProfit(movies.get(movieID).getGrossProfit() + grossProfit);
    }

    public void updateTicketsSold(String movieID,long ticketsSold){
        movies.get(movieID).setTicketsSold(movies.get(movieID).getTicketsSold() + ticketsSold);
    }

    public void updateReview(String movieID, String reviewID, double reviewScore){
        Movie movie = movies.get(movieID);
        movie.setTotalReviewNo(movie.getTotalReviewNo()+1);
        movie.setTotalReviewScore(movie.getTotalReviewScore()+reviewScore);
        movie.addMovieReview(reviewID);
        movie.setAverageReviewScore(movie.getTotalReviewScore()/movie.getTotalReviewNo());
    }

    public void updateShowtimes(String movieID, String showtimeID) {
        this.movies.get(movieID).addShowtimeID(showtimeID);
    }
}
