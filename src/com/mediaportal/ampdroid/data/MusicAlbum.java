package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

public class MusicAlbum {
   private String AlbumArtistString;
   private String[] AlbumArtists;
   private String Title;
   private int Year;
   private String Genre;
   private String Composer;
   private String Publisher;

   @JsonProperty("AlbumArtistString")
   public String getAlbumArtistString() {
      return AlbumArtistString;
   }

   @JsonProperty("AlbumArtistString")
   public void setAlbumArtistString(String albumArtistString) {
      AlbumArtistString = albumArtistString;
   }

   @JsonProperty("AlbumArtists")
   public String[] getAlbumArtists() {
      return AlbumArtists;
   }

   @JsonProperty("AlbumArtists")
   public void setAlbumArtists(String[] albumArtists) {
      AlbumArtists = albumArtists;
   }

   @JsonProperty("Title")
   public String getTitle() {
      return Title;
   }

   @JsonProperty("Title")
   public void setTitle(String title) {
      Title = title;
   }

   @JsonProperty("Year")
   public int getYear() {
      return Year;
   }

   @JsonProperty("Year")
   public void setYear(int year) {
      Year = year;
   }

   @JsonProperty("Genre")
   public String getGenre() {
      return Genre;
   }

   @JsonProperty("Genre")
   public void setGenre(String genre) {
      Genre = genre;
   }

   @JsonProperty("Composer")
   public String getComposer() {
      return Composer;
   }

   @JsonProperty("Composer")
   public void setComposer(String composer) {
      Composer = composer;
   }

   @JsonProperty("Publisher")
   public String getPublisher() {
      return Publisher;
   }

   @JsonProperty("Publisher")
   public void setPublisher(String publisher) {
      Publisher = publisher;
   }

}
