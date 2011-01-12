package com.mediaportal.remote.data;

import java.util.Date;

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

   public int getId() {
      return Id;
   }



   public void setId(int id) {
      Id = id;
   }



   public String getName() {
      return Name;
   }



   public void setName(String name) {
      Name = name;
   }



   public int getSeasonNumber() {
      return SeasonNumber;
   }



   public void setSeasonNumber(int seasonNumber) {
      SeasonNumber = seasonNumber;
   }



   public int getEpisodeNumber() {
      return EpisodeNumber;
   }



   public void setEpisodeNumber(int episodeNumber) {
      EpisodeNumber = episodeNumber;
   }



   public int getWatched() {
      return Watched;
   }



   public void setWatched(int watched) {
      Watched = watched;
   }



   public Date getFirstAired() {
      return FirstAired;
   }



   public void setFirstAired(Date firstAired) {
      FirstAired = firstAired;
   }



   public String getBannerUrl() {
      return BannerUrl;
   }



   public void setBannerUrl(String bannerUrl) {
      BannerUrl = bannerUrl;
   }



   public float getRating() {
      return Rating;
   }



   public void setRating(float rating) {
      Rating = rating;
   }



   public int getRatingCount() {
      return RatingCount;
   }



   public void setRatingCount(int ratingCount) {
      RatingCount = ratingCount;
   }



   public boolean isHasLocalFile() {
      return HasLocalFile;
   }



   public void setHasLocalFile(boolean hasLocalFile) {
      HasLocalFile = hasLocalFile;
   }
	
	
	
}
