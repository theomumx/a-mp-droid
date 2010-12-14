package com.mediaportal.remote.data;

import java.util.ArrayList;
import java.util.Date;

public class SeriesFull extends Series {
	private String sortName;
	private String origName;
	private String status;
	private ArrayList<String> bannerUrls;
	private ArrayList<String> fanartUrls;
	private ArrayList<String> posterUrls;
	private String contentRating;
	private String network;
	private String overview;
	private String airsDay;
	private String airsTime;
	private ArrayList<String> actors;
	private int episodesUnwatchedCount;
	private int runtime;
	
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	private Date firstAired;
	private String episodeOrder;
	
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getOrigName() {
		return origName;
	}
	public void setOrigName(String origName) {
		this.origName = origName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<String> getBannerUrls() {
		return bannerUrls;
	}
	public void setBannerUrls(ArrayList<String> bannerUrls) {
		this.bannerUrls = bannerUrls;
	}
	public ArrayList<String> getFanartUrls() {
		return fanartUrls;
	}
	public void setFanartUrls(ArrayList<String> fanartUrls) {
		this.fanartUrls = fanartUrls;
	}
	public ArrayList<String> getPosterUrls() {
		return posterUrls;
	}
	public void setPosterUrls(ArrayList<String> posterUrls) {
		this.posterUrls = posterUrls;
	}
	public String getContentRating() {
		return contentRating;
	}
	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getAirsDay() {
		return airsDay;
	}
	public void setAirsDay(String airsDay) {
		this.airsDay = airsDay;
	}
	public String getAirsTime() {
		return airsTime;
	}
	public void setAirsTime(String airsTime) {
		this.airsTime = airsTime;
	}
	public ArrayList<String> getActors() {
		return actors;
	}
	public void setActors(ArrayList<String> actors) {
		this.actors = actors;
	}
	public int getEpisodesUnwatchedCount() {
		return episodesUnwatchedCount;
	}
	public void setEpisodesUnwatchedCount(int episodesUnwatchedCount) {
		this.episodesUnwatchedCount = episodesUnwatchedCount;
	}
	public Date getFirstAired() {
		return firstAired;
	}
	public void setFirstAired(Date firstAired) {
		this.firstAired = firstAired;
	}
	public String getEpisodeOrder() {
		return episodeOrder;
	}
	public void setEpisodeOrder(String episodeOrder) {
		this.episodeOrder = episodeOrder;
	}
}
