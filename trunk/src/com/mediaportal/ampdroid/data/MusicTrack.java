package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

public class MusicTrack {
   // MPDatabase Values
   private int TrackId;
   private String ArtistsString;
   private String[] Artists;
   private String AlbumArtistsString;
   private String[] AlbumArtists;
   private String Url;
   private int Duration;

   // FileSystem Values
   private int TrackNum;
   private int Year;
   private String Album;
   private String Genre;
   private String AlbumArtist;
   private String Title;
   private String TrackNumber;
   private String FilePath;

   @JsonProperty("TrackId")
   public int getTrackId() {
      return TrackId;
   }

   @JsonProperty("TrackId")
   public void setTrackId(int trackId) {
      TrackId = trackId;
   }

   @JsonProperty("ArtistsString")
   public String getArtistsString() {
      return ArtistsString;
   }

   @JsonProperty("ArtistsString")
   public void setArtistsString(String artistsString) {
      ArtistsString = artistsString;
   }

   @JsonProperty("Artists")
   public String[] getArtists() {
      return Artists;
   }

   @JsonProperty("Artists")
   public void setArtists(String[] artists) {
      Artists = artists;
   }

   @JsonProperty("AlbumArtistsString")
   public String getAlbumArtistsString() {
      return AlbumArtistsString;
   }

   @JsonProperty("AlbumArtistsString")
   public void setAlbumArtistsString(String albumArtistsString) {
      AlbumArtistsString = albumArtistsString;
   }

   @JsonProperty("AlbumArtists")
   public String[] getAlbumArtists() {
      return AlbumArtists;
   }

   @JsonProperty("AlbumArtists")
   public void setAlbumArtists(String[] albumArtists) {
      AlbumArtists = albumArtists;
   }

   @JsonProperty("Url")
   public String getUrl() {
      return Url;
   }

   @JsonProperty("Url")
   public void setUrl(String url) {
      Url = url;
   }

   @JsonProperty("Duration")
   public int getDuration() {
      return Duration;
   }

   @JsonProperty("Duration")
   public void setDuration(int duration) {
      Duration = duration;
   }

   @JsonProperty("TrackNum")
   public int getTrackNum() {
      return TrackNum;
   }

   @JsonProperty("TrackNum")
   public void setTrackNum(int trackNum) {
      TrackNum = trackNum;
   }

   @JsonProperty("Year")
   public int getYear() {
      return Year;
   }

   @JsonProperty("Year")
   public void setYear(int year) {
      Year = year;
   }

   @JsonProperty("Album")
   public String getAlbum() {
      return Album;
   }

   @JsonProperty("Album")
   public void setAlbum(String album) {
      Album = album;
   }

   @JsonProperty("Genre")
   public String getGenre() {
      return Genre;
   }

   @JsonProperty("Genre")
   public void setGenre(String genre) {
      Genre = genre;
   }

   @JsonProperty("AlbumArtist")
   public String getAlbumArtist() {
      return AlbumArtist;
   }

   @JsonProperty("AlbumArtist")
   public void setAlbumArtist(String albumArtist) {
      AlbumArtist = albumArtist;
   }

   @JsonProperty("Title")
   public String getTitle() {
      return Title;
   }

   @JsonProperty("Title")
   public void setTitle(String title) {
      Title = title;
   }

   @JsonProperty("TrackNumber")
   public String getTrackNumber() {
      return TrackNumber;
   }

   @JsonProperty("TrackNumber")
   public void setTrackNumber(String trackNumber) {
      TrackNumber = trackNumber;
   }

   @JsonProperty("FilePath")
   public String getFilePath() {
      return FilePath;
   }

   @JsonProperty("FilePath")
   public void setFilePath(String filePath) {
      FilePath = filePath;
   }

}
