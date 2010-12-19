package com.mediaportal.remote.data;

import java.util.Date;

public class SeriesFull extends Series {
	private String SortName;
	private String OrigName;
	private String Status;
	private String[] BannerUrls;
	private String[] FanartUrls;
	private String[] PosterUrls;
	private String ContentRating;
	private String Network;
	private String Summary;
	private String AirsDay;
	private String AirsTime;
	private String[] Actors;
	private int EpisodesUnwatchedCount;
	private int Runtime;
	private Date FirstAired;
	private String EpisodeOrder;
   public String getSortName() {
      return SortName;
   }
   public void setSortName(String sortName) {
      SortName = sortName;
   }
   public String getOrigName() {
      return OrigName;
   }
   public void setOrigName(String origName) {
      OrigName = origName;
   }
   public String getStatus() {
      return Status;
   }
   public void setStatus(String status) {
      Status = status;
   }
   public String[] getBannerUrls() {
      return BannerUrls;
   }
   public void setBannerUrls(String[] bannerUrls) {
      BannerUrls = bannerUrls;
   }
   public String[] getFanartUrls() {
      return FanartUrls;
   }
   public void setFanartUrls(String[] fanartUrls) {
      FanartUrls = fanartUrls;
   }
   public String[] getPosterUrls() {
      return PosterUrls;
   }
   public void setPosterUrls(String[] posterUrls) {
      PosterUrls = posterUrls;
   }
   public String getContentRating() {
      return ContentRating;
   }
   public void setContentRating(String contentRating) {
      ContentRating = contentRating;
   }
   public String getNetwork() {
      return Network;
   }
   public void setNetwork(String network) {
      Network = network;
   }
   public String getSummary() {
      return Summary;
   }
   public void setSummary(String summary) {
      Summary = summary;
   }
   public String getAirsDay() {
      return AirsDay;
   }
   public void setAirsDay(String airsDay) {
      AirsDay = airsDay;
   }
   public String getAirsTime() {
      return AirsTime;
   }
   public void setAirsTime(String airsTime) {
      AirsTime = airsTime;
   }
   public String[] getActors() {
      return Actors;
   }
   public void setActors(String[] actors) {
      Actors = actors;
   }
   public int getEpisodesUnwatchedCount() {
      return EpisodesUnwatchedCount;
   }
   public void setEpisodesUnwatchedCount(int episodesUnwatchedCount) {
      EpisodesUnwatchedCount = episodesUnwatchedCount;
   }
   public int getRuntime() {
      return Runtime;
   }
   public void setRuntime(int runtime) {
      Runtime = runtime;
   }
   public Date getFirstAired() {
      return FirstAired;
   }
   public void setFirstAired(Date firstAired) {
      FirstAired = firstAired;
   }
   public String getEpisodeOrder() {
      return EpisodeOrder;
   }
   public void setEpisodeOrder(String episodeOrder) {
      EpisodeOrder = episodeOrder;
   }
	
	
}
