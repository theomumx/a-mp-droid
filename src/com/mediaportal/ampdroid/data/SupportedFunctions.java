package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

public class SupportedFunctions {
   private boolean SupportsVideos;
   private boolean SupportsMusic;
   private boolean SupportsPictures;
   private boolean SupportsTvSeries;
   private boolean SupportsMovies;

   private RemoteFunction VideosFunction;
   private RemoteFunction MusicFunction;
   private RemoteFunction PicturesFunction;
   private RemoteFunction TvSeriesFunction;
   private RemoteFunction MoviesFunction;

   @JsonProperty("SupportsVideos")
   public boolean supportsVideo() {
      return SupportsVideos;
   }

   @JsonProperty("SupportsVideos")
   public void setSupportsVideo(boolean supportsVideo) {
      SupportsVideos = supportsVideo;
   }

   @JsonProperty("SupportsMusic")
   public boolean supportsMusic() {
      return SupportsMusic;
   }

   @JsonProperty("SupportsMusic")
   public void setSupportsMusic(boolean supportsMusic) {
      SupportsMusic = supportsMusic;
   }

   @JsonProperty("SupportsPictures")
   public boolean supportsPictures() {
      return SupportsPictures;
   }

   @JsonProperty("SupportsPictures")
   public void setSupportsPictures(boolean supportsPictures) {
      SupportsPictures = supportsPictures;
   }

   @JsonProperty("SupportsTvSeries")
   public boolean supportsTvSeries() {
      return SupportsTvSeries;
   }

   @JsonProperty("SupportsTvSeries")
   public void setSupportsTvSeries(boolean supportsTvSeries) {
      SupportsTvSeries = supportsTvSeries;
   }

   @JsonProperty("SupportsMovies")
   public boolean supportsMovies() {
      return SupportsMovies;
   }

   @JsonProperty("SupportsMovies")
   public void setSupportsMovies(boolean supportsMovies) {
      SupportsMovies = supportsMovies;
   }

   @JsonProperty("VideosFunction")
   public RemoteFunction getVideosFunction() {
      return VideosFunction;
   }

   @JsonProperty("VideosFunction")
   public void setVideosFunction(RemoteFunction videosFunction) {
      VideosFunction = videosFunction;
   }

   @JsonProperty("MusicFunction")
   public RemoteFunction getMusicFunction() {
      return MusicFunction;
   }

   @JsonProperty("MusicFunction")
   public void setMusicFunction(RemoteFunction musicFunction) {
      MusicFunction = musicFunction;
   }

   @JsonProperty("PicturesFunction")
   public RemoteFunction getPicturesFunction() {
      return PicturesFunction;
   }

   @JsonProperty("PicturesFunction")
   public void setPicturesFunction(RemoteFunction picturesFunction) {
      PicturesFunction = picturesFunction;
   }

   @JsonProperty("TvSeriesFunction")
   public RemoteFunction getTvSeriesFunction() {
      return TvSeriesFunction;
   }

   @JsonProperty("TvSeriesFunction")
   public void setTvSeriesFunction(RemoteFunction tvSeriesFunction) {
      TvSeriesFunction = tvSeriesFunction;
   }

   @JsonProperty("MoviesFunction")
   public RemoteFunction getMoviesFunction() {
      return MoviesFunction;
   }

   @JsonProperty("MoviesFunction")
   public void setMoviesFunction(RemoteFunction moviesFunction) {
      MoviesFunction = moviesFunction;
   }

}
