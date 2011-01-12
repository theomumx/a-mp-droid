package com.mediaportal.ampdroid.data;


public class Series{
	private int Id;
	private String PrettyName;
	private int EpisodeCount;
	private String ImdbId;
	private double Rating;
	private int RatingCount;
	private String CurrentFanartUrl;
	private String CurrentBannerUrl;
	private String CurrentPosterUrl;
	private String GenreString;
	private String[] Genres;
	
	public int getId() {
      return Id;
   }
   public void setId(int id) {
      Id = id;
   }
   public String getPrettyName() {
      return PrettyName;
   }
   public void setPrettyName(String prettyName) {
      PrettyName = prettyName;
   }
   public int getEpisodeCount() {
      return EpisodeCount;
   }
   public void setEpisodeCount(int episodeCount) {
      EpisodeCount = episodeCount;
   }
   public String getImdbId() {
      return ImdbId;
   }
   public void setImdbId(String imdbId) {
      ImdbId = imdbId;
   }
   public double getRating() {
      return Rating;
   }
   public void setRating(double rating) {
      Rating = rating;
   }
   public int getRatingCount() {
      return RatingCount;
   }
   public void setRatingCount(int ratingCount) {
      RatingCount = ratingCount;
   }
   public String getCurrentFanartUrl() {
      return CurrentFanartUrl;
   }
   public void setCurrentFanartUrl(String currentFanartUrl) {
      CurrentFanartUrl = currentFanartUrl;
   }
   public String getCurrentBannerUrl() {
      return CurrentBannerUrl;
   }
   public void setCurrentBannerUrl(String currentBannerUrl) {
      CurrentBannerUrl = currentBannerUrl;
   }
   public String getCurrentPosterUrl() {
      return CurrentPosterUrl;
   }
   public void setCurrentPosterUrl(String currentPosterUrl) {
      CurrentPosterUrl = currentPosterUrl;
   }
   public String getGenreString() {
      return GenreString;
   }
   public void setGenreString(String genreString) {
      GenreString = genreString;
   }
   public String[] getGenres() {
      return Genres;
   }
   public void setGenres(String[] genres) {
      Genres = genres;
   }
}
