package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mediaportal.ampdroid.database.ColumnProperty;
import com.mediaportal.ampdroid.database.TableProperty;

@TableProperty("SupportedFunctions")
public class SupportedFunctions {
   public static final String TABLE_NAME = "SupportedFunctions";
   
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

   @ColumnProperty(value="SupportsVideos", type="boolean")
   @JsonProperty("SupportsVideos")
   public boolean supportsVideo() {
      return SupportsVideos;
   }

   @ColumnProperty(value="SupportsVideos", type="boolean")
   @JsonProperty("SupportsVideos")
   public void setSupportsVideo(boolean supportsVideo) {
      SupportsVideos = supportsVideo;
   }

   @ColumnProperty(value="SupportsMusic", type="boolean")
   @JsonProperty("SupportsMusic")
   public boolean supportsMusic() {
      return SupportsMusic;
   }

   @ColumnProperty(value="SupportsMusic", type="boolean")
   @JsonProperty("SupportsMusic")
   public void setSupportsMusic(boolean supportsMusic) {
      SupportsMusic = supportsMusic;
   }

   @ColumnProperty(value="SupportsPictures", type="boolean")
   @JsonProperty("SupportsPictures")
   public boolean supportsPictures() {
      return SupportsPictures;
   }

   @ColumnProperty(value="SupportsPictures", type="boolean")
   @JsonProperty("SupportsPictures")
   public void setSupportsPictures(boolean supportsPictures) {
      SupportsPictures = supportsPictures;
   }
   
   @ColumnProperty(value="SupportsTvSeries", type="boolean")
   @JsonProperty("SupportsTvSeries")
   public boolean supportsTvSeries() {
      return SupportsTvSeries;
   }

   @ColumnProperty(value="SupportsTvSeries", type="boolean")
   @JsonProperty("SupportsTvSeries")
   public void setSupportsTvSeries(boolean supportsTvSeries) {
      SupportsTvSeries = supportsTvSeries;
   }
   
   @ColumnProperty(value="SupportsMovies", type="boolean")
   @JsonProperty("SupportsMovies")
   public boolean supportsMovies() {
      return SupportsMovies;
   }

   @ColumnProperty(value="SupportsMovies", type="boolean")
   @JsonProperty("SupportsMovies")
   public void setSupportsMovies(boolean supportsMovies) {
      SupportsMovies = supportsMovies;
   }

   @ColumnProperty(value="VideosFunction", type="boolean")
   @JsonProperty("VideosFunction")
   public RemoteFunction getVideosFunction() {
      return VideosFunction;
   }

   @ColumnProperty(value="VideosFunction", type="boolean")
   @JsonProperty("VideosFunction")
   public void setVideosFunction(RemoteFunction videosFunction) {
      VideosFunction = videosFunction;
   }

   @ColumnProperty(value="MusicFunction", type="boolean")
   @JsonProperty("MusicFunction")
   public RemoteFunction getMusicFunction() {
      return MusicFunction;
   }

   @ColumnProperty(value="MusicFunction", type="boolean")
   @JsonProperty("MusicFunction")
   public void setMusicFunction(RemoteFunction musicFunction) {
      MusicFunction = musicFunction;
   }

   @ColumnProperty(value="PicturesFunction", type="boolean")
   @JsonProperty("PicturesFunction")
   public RemoteFunction getPicturesFunction() {
      return PicturesFunction;
   }

   @ColumnProperty(value="PicturesFunction", type="boolean")
   @JsonProperty("PicturesFunction")
   public void setPicturesFunction(RemoteFunction picturesFunction) {
      PicturesFunction = picturesFunction;
   }

   @ColumnProperty(value="TvSeriesFunction", type="boolean")
   @JsonProperty("TvSeriesFunction")
   public RemoteFunction getTvSeriesFunction() {
      return TvSeriesFunction;
   }

   @ColumnProperty(value="TvSeriesFunction", type="boolean")
   @JsonProperty("TvSeriesFunction")
   public void setTvSeriesFunction(RemoteFunction tvSeriesFunction) {
      TvSeriesFunction = tvSeriesFunction;
   }

   @ColumnProperty(value="MoviesFunction", type="boolean")
   @JsonProperty("MoviesFunction")
   public RemoteFunction getMoviesFunction() {
      return MoviesFunction;
   }

   @ColumnProperty(value="MoviesFunction", type="boolean")
   @JsonProperty("MoviesFunction")
   public void setMoviesFunction(RemoteFunction moviesFunction) {
      MoviesFunction = moviesFunction;
   }

}
