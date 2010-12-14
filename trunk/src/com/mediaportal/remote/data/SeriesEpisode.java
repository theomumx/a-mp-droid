package com.mediaportal.remote.data;

import java.util.Date;

import com.mediaportal.remote.activities.lists.ILoadingAdapter;

public class SeriesEpisode implements ILoadingAdapter {
	private int id;
	private String name;
	private int seasonNumber;
	private int episodeNumber;
	private int watched;
	private Date firstAired;
	private String bannerUrl;
	private String bannerThumbUrl;
	private float rating;
	private float myRating;
	private boolean hasLocalFile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public int getWatched() {
		return watched;
	}

	public void setWatched(int watched) {
		this.watched = watched;
	}

	public Date getFirstAired() {
		return firstAired;
	}

	public void setFirstAired(Date firstAired) {
		this.firstAired = firstAired;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getBannerThumbUrl() {
		return bannerThumbUrl;
	}

	public void setBannerThumbUrl(String bannerThumbUrl) {
		this.bannerThumbUrl = bannerThumbUrl;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getMyRating() {
		return myRating;
	}

	public void setMyRating(float myRating) {
		this.myRating = myRating;
	}

	public boolean isHasLocalFile() {
		return hasLocalFile;
	}

	public void setHasLocalFile(boolean hasLocalFile) {
		this.hasLocalFile = hasLocalFile;
	}

	public SeriesEpisode() {

	}

	@Override
	public String toString() {
		if (name != null) {
			return name;
		} else
			return super.toString();
	}

	@Override
	public String getImage() {
		return bannerUrl;
	}

	@Override
	public String getSubText() {
		return seasonNumber + "x" + episodeNumber;
	}

	@Override
	public String getText() {
		return name;
	}
	
	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public int getTextColor() {
		return 0;
	}
}
