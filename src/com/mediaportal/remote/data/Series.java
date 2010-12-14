package com.mediaportal.remote.data;

import java.util.ArrayList;

import com.mediaportal.remote.activities.lists.ILoadingAdapter;

public class Series implements ILoadingAdapter {
	private int id;
	private String prettyName;
	private int episodeCount;
	private String imdbId;
	private double rating;
	private int ratingCount;
	private String fanartUrl;
	private String fanartThumbUrl;
	private String bannerUrl;
	private String bannerThumbUrl;
	private String posterUrl;
	private String posterThumbUrl;
	private String genreString;
	private ArrayList<String> genres;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrettyName() {
		return prettyName;
	}
	public void setPrettyName(String prettyName) {
		this.prettyName = prettyName;
	}
	public int getEpisodeCount() {
		return episodeCount;
	}
	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int myRating) {
		this.ratingCount = myRating;
	}
	public String getFanartUrl() {
		return fanartUrl;
	}
	public void setFanartUrl(String fanartUrl) {
		this.fanartUrl = fanartUrl;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public String getPosterUrl() {
		return posterUrl;
	}
	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	public void setFanartThumbUrl(String fanartThumbUrl) {
		this.fanartThumbUrl = fanartThumbUrl;
	}
	public String getFanartThumbUrl() {
		return fanartThumbUrl;
	}
	public void setBannerThumbUrl(String bannerThumbUrl) {
		this.bannerThumbUrl = bannerThumbUrl;
	}
	public String getBannerThumbUrl() {
		return bannerThumbUrl;
	}
	public void setPosterThumbUrl(String posterThumbUrl) {
		this.posterThumbUrl = posterThumbUrl;
	}
	public String getPosterThumbUrl() {
		return posterThumbUrl;
	}

	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}
	public ArrayList<String> getGenres() {
		return genres;
	}
	public void setGenreString(String genreString) {
		this.genreString = genreString;
	}
	public String getGenreString() {
		return genreString;
	}
	@Override
	public String getImage() {
		return posterUrl;
	}
	@Override
	public String getSubText() {
		return genreString;
	}
	@Override
	public String getText() {
		return prettyName;
	}
	@Override
	public int getTextColor() {
		return 0;
	}
	@Override
	public String getTitle() {
		return prettyName;
	}


	
}
