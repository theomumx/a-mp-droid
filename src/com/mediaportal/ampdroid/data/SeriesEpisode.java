package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class SeriesEpisode {
	private int Id;
	private String Name;
	private int SeasonNumber;
	private int EpisodeNumber;
	private int Watched;
	private Date FirstAired;
	private String BannerUrl;
	private float Rating;
	private int RatingCount;
	private boolean HasLocalFile;
	
	public SeriesEpisode() {

	}

	@Override
	public String toString() {
		if (getName() != null) {
			return getName();
		} else
			return super.toString();
	}

	@JsonProperty("Id")
   public int getId() {
      return Id;
   }

   @JsonProperty("Id")
   public void setId(int id) {
      Id = id;
   }

   @JsonProperty("Name")
   public String getName() {
      return Name;
   }

   @JsonProperty("Name")
   public void setName(String name) {
      Name = name;
   }

   @JsonProperty("SeasonNumber")
   public int getSeasonNumber() {
      return SeasonNumber;
   }

   @JsonProperty("SeasonNumber")
   public void setSeasonNumber(int seasonNumber) {
      SeasonNumber = seasonNumber;
   }

   @JsonProperty("EpisodeNumber")
   public int getEpisodeNumber() {
      return EpisodeNumber;
   }

   @JsonProperty("EpisodeNumber")
   public void setEpisodeNumber(int episodeNumber) {
      EpisodeNumber = episodeNumber;
   }

   @JsonProperty("Watched")
   public int getWatched() {
      return Watched;
   }

   @JsonProperty("Watched")
   public void setWatched(int watched) {
      Watched = watched;
   }

   @JsonProperty("FirstAired")
   public Date getFirstAired() {
      return FirstAired;
   }

   @JsonProperty("FirstAired")
   public void setFirstAired(Date firstAired) {
      FirstAired = firstAired;
   }

   @JsonProperty("BannerUrl")
   public String getBannerUrl() {
      return BannerUrl;
   }

   @JsonProperty("BannerUrl")
   public void setBannerUrl(String bannerUrl) {
      BannerUrl = bannerUrl;
   }

   @JsonProperty("Rating")
   public float getRating() {
      return Rating;
   }

   @JsonProperty("Rating")
   public void setRating(float rating) {
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

   @JsonProperty("HasLocalFile")
   public boolean isHasLocalFile() {
      return HasLocalFile;
   }

   @JsonProperty("HasLocalFile")
   public void setHasLocalFile(boolean hasLocalFile) {
      HasLocalFile = hasLocalFile;
   }
	
	
	
}
