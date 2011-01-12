package com.mediaportal.remote.data;

public class MusicTrack {
   //MPDatabase Values
   private int TrackId;
   private String ArtistsString;
   private String[] Artists;
   private String AlbumArtistsString;
   private String[] AlbumArtists;
   private String Url;
   private int Duration;

   //FileSystem Values
   private int TrackNum;
   private int Year;
   private String Album;
   private String Genre;
   private String AlbumArtist;
   private String Title;
   private String TrackNumber;
   private String FilePath;
   
   
   
   public int getTrackId() {
      return TrackId;
   }
   public void setTrackId(int trackId) {
      TrackId = trackId;
   }
   public String getArtistsString() {
      return ArtistsString;
   }
   public void setArtistsString(String artistsString) {
      ArtistsString = artistsString;
   }
   public String[] getArtists() {
      return Artists;
   }
   public void setArtists(String[] artists) {
      Artists = artists;
   }
   public String getAlbumArtistsString() {
      return AlbumArtistsString;
   }
   public void setAlbumArtistsString(String albumArtistsString) {
      AlbumArtistsString = albumArtistsString;
   }
   public String[] getAlbumArtists() {
      return AlbumArtists;
   }
   public void setAlbumArtists(String[] albumArtists) {
      AlbumArtists = albumArtists;
   }
   public String getUrl() {
      return Url;
   }
   public void setUrl(String url) {
      Url = url;
   }
   public int getDuration() {
      return Duration;
   }
   public void setDuration(int duration) {
      Duration = duration;
   }
   public int getTrackNum() {
      return TrackNum;
   }
   public void setTrackNum(int trackNum) {
      TrackNum = trackNum;
   }
   public int getYear() {
      return Year;
   }
   public void setYear(int year) {
      Year = year;
   }
   public String getAlbum() {
      return Album;
   }
   public void setAlbum(String album) {
      Album = album;
   }
   public String getGenre() {
      return Genre;
   }
   public void setGenre(String genre) {
      Genre = genre;
   }
   public String getAlbumArtist() {
      return AlbumArtist;
   }
   public void setAlbumArtist(String albumArtist) {
      AlbumArtist = albumArtist;
   }
   public String getTitle() {
      return Title;
   }
   public void setTitle(String title) {
      Title = title;
   }
   public String getTrackNumber() {
      return TrackNumber;
   }
   public void setTrackNumber(String trackNumber) {
      TrackNumber = trackNumber;
   }
   public String getFilePath() {
      return FilePath;
   }
   public void setFilePath(String filePath) {
      FilePath = filePath;
   }
   
   
   
}
