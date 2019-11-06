import java.time.LocalDateTime;

public class Movie {
    private String movieID;
    private String title;
    private Genre[] genres;
    private String director;
    private String[] cast;
    private String synopsis;
    private MovieRating movieRating;
    private MovieFormat[] movieFormats;
    private float movieDuration;
    private Review[] movieReviews = null;
    private float averageReviewScore;
    private int totalReviewNo;
    private float totalReviewScore;
    private ShowingStatus showingStatus = ShowingStatus.COMING_SOON;
    private LocalDateTime releaseDate;
    private long ticketsSold;
    private double grossProfit;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public MovieRating getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    public MovieFormat[] getMovieFormats() {
        return movieFormats;
    }

    public void setMovieFormats(MovieFormat[] movieFormats) {
        this.movieFormats = movieFormats;
    }

    public float getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(float movieDuration) {
        this.movieDuration = movieDuration;
    }

    public Review[] getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(Review[] movieReviews) {
        this.movieReviews = movieReviews;
    }

    public float getAverageReviewScore() {
        return averageReviewScore;
    }

    public void setAverageReviewScore(float averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    public int getTotalReviewNo() {
        return totalReviewNo;
    }

    public void setTotalReviewNo(int totalReviewNo) {
        this.totalReviewNo = totalReviewNo;
    }

    public float getTotalReviewScore() {
        return totalReviewScore;
    }

    public void setTotalReviewScore(float totalReviewScore) {
        this.totalReviewScore = totalReviewScore;
    }

    public ShowingStatus getShowingStatus() {
        return showingStatus;
    }

    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(long ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(double grossProfit) {
        this.grossProfit = grossProfit;
    }
}
