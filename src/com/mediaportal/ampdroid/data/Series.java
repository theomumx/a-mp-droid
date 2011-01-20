package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

public class Series {
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

   @JsonProperty("Id")
   public int getId() {
      return Id;
   }

   @JsonProperty("Id")
   public void setId(int id) {
      Id = id;
   }

   @JsonProperty("PrettyName")
   public String getPrettyName() {
      return PrettyName;
   }

   @JsonProperty("PrettyName")
   public void setPrettyName(String prettyName) {
      PrettyName = prettyName;
   }

   @JsonProperty("EpisodeCount")
   public int getEpisodeCount() {
      return EpisodeCount;
   }

   @JsonProperty("EpisodeCount")
   public void setEpisodeCount(int episodeCount) {
      EpisodeCount = episodeCount;
   }

   @JsonProperty("ImdbId")
   public String getImdbId() {
      return ImdbId;
   }

   @JsonProperty("ImdbId")
   public void setImdbId(String imdbId) {
      ImdbId = imdbId;
   }

   @JsonProperty("Rating")
   public double getRating() {
      return Rating;
   }

   @JsonProperty("Rating")
   public void setRating(double rating) {
      Rating = rating;
   }

   @JsonProperty("RatingCount")
   public int getRatingCount() {
      return RatingCount;
   }

   @JsonProperty("RatingCount")
   public void setRatingCount(int ratingCount) {
      RatingCount = ratingCount;
   }

   @JsonProperty("CurrentFanartUrl")
   public String getCurrentFanartUrl() {
      return CurrentFanartUrl;
   }

   @JsonProperty("CurrentFanartUrl")
   public void setCurrentFanartUrl(String currentFanartUrl) {
      CurrentFanartUrl = currentFanartUrl;
   }

   @JsonProperty("CurrentBannerUrl")
   public String getCurrentBannerUrl() {
      return CurrentBannerUrl;
   }

   @JsonProperty("CurrentBannerUrl")
   public void setCurrentBannerUrl(String currentBannerUrl) {
      CurrentBannerUrl = currentBannerUrl;
   }

   @JsonProperty("CurrentPosterUrl")
   public String getCurrentPosterUrl() {
      return CurrentPosterUrl;
   }

   @JsonProperty("CurrentPosterUrl")
   public void setCurrentPosterUrl(String currentPosterUrl) {
      CurrentPosterUrl = currentPosterUrl;
   }

   @JsonProperty("GenreString")
   public String getGenreString() {
      return GenreString;
   }

   @JsonProperty("GenreString")
   public void setGenreString(String genreString) {
      GenreString = genreString;
   }

   @JsonProperty("Genres")
   public String[] getGenres() {
      return Genres;
   }

   @JsonProperty("Genres")
   public void setGenres(String[] genres) {
      Genres = genres;
   }
}
