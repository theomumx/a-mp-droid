package com.mediaportal.ampdroid.data;

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
   
   public boolean supportsVideo() {
      return SupportsVideos;
   }
   public void setSupportsVideo(boolean supportsVideo) {
      SupportsVideos = supportsVideo;
   }
   public boolean supportsMusic() {
      return SupportsMusic;
   }
   public void setSupportsMusic(boolean supportsMusic) {
      SupportsMusic = supportsMusic;
   }
   public boolean supportsPictures() {
      return SupportsPictures;
   }
   public void setSupportsPictures(boolean supportsPictures) {
      SupportsPictures = supportsPictures;
   }
   public boolean supportsTvSeries() {
      return SupportsTvSeries;
   }
   public void setSupportsTvSeries(boolean supportsTvSeries) {
      SupportsTvSeries = supportsTvSeries;
   }
   public boolean supportsMovies() {
      return SupportsMovies;
   }
   public void setSupportsMovies(boolean supportsMovies) {
      SupportsMovies = supportsMovies;
   }
   public RemoteFunction getVideosFunction() {
      return VideosFunction;
   }
   public void setVideosFunction(RemoteFunction videosFunction) {
      VideosFunction = videosFunction;
   }
   public RemoteFunction getMusicFunction() {
      return MusicFunction;
   }
   public void setMusicFunction(RemoteFunction musicFunction) {
      MusicFunction = musicFunction;
   }
   public RemoteFunction getPicturesFunction() {
      return PicturesFunction;
   }
   public void setPicturesFunction(RemoteFunction picturesFunction) {
      PicturesFunction = picturesFunction;
   }
   public RemoteFunction getTvSeriesFunction() {
      return TvSeriesFunction;
   }
   public void setTvSeriesFunction(RemoteFunction tvSeriesFunction) {
      TvSeriesFunction = tvSeriesFunction;
   }
   public RemoteFunction getMoviesFunction() {
      return MoviesFunction;
   }
   public void setMoviesFunction(RemoteFunction moviesFunction) {
      MoviesFunction = moviesFunction;
   }
   
   
}
