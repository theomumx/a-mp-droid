package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

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

   @JsonProperty("SortName")
   public String getSortName() {
      return SortName;
   }

   @JsonProperty("SortName")
   public void setSortName(String sortName) {
      SortName = sortName;
   }

   @JsonProperty("OrigName")
   public String getOrigName() {
      return OrigName;
   }

   @JsonProperty("OrigName")
   public void setOrigName(String origName) {
      OrigName = origName;
   }

   @JsonProperty("Status")
   public String getStatus() {
      return Status;
   }

   @JsonProperty("Status")
   public void setStatus(String status) {
      Status = status;
   }

   @JsonProperty("BannerUrls")
   public String[] getBannerUrls() {
      return BannerUrls;
   }

   @JsonProperty("BannerUrls")
   public void setBannerUrls(String[] bannerUrls) {
      BannerUrls = bannerUrls;
   }

   @JsonProperty("FanartUrls")
   public String[] getFanartUrls() {
      return FanartUrls;
   }

   @JsonProperty("FanartUrls")
   public void setFanartUrls(String[] fanartUrls) {
      FanartUrls = fanartUrls;
   }

   @JsonProperty("PosterUrls")
   public String[] getPosterUrls() {
      return PosterUrls;
   }

   @JsonProperty("PosterUrls")
   public void setPosterUrls(String[] posterUrls) {
      PosterUrls = posterUrls;
   }

   @JsonProperty("ContentRating")
   public String getContentRating() {
      return ContentRating;
   }

   @JsonProperty("ContentRating")
   public void setContentRating(String contentRating) {
      ContentRating = contentRating;
   }

   @JsonProperty("Network")
   public String getNetwork() {
      return Network;
   }

   @JsonProperty("Network")
   public void setNetwork(String network) {
      Network = network;
   }

   @JsonProperty("Summary")
   public String getSummary() {
      return Summary;
   }

   @JsonProperty("Summary")
   public void setSummary(String summary) {
      Summary = summary;
   }

   @JsonProperty("AirsDay")
   public String getAirsDay() {
      return AirsDay;
   }

   @JsonProperty("AirsDay")
   public void setAirsDay(String airsDay) {
      AirsDay = airsDay;
   }

   @JsonProperty("AirsTime")
   public String getAirsTime() {
      return AirsTime;
   }

   @JsonProperty("AirsTime")
   public void setAirsTime(String airsTime) {
      AirsTime = airsTime;
   }

   @JsonProperty("Actors")
   public String[] getActors() {
      return Actors;
   }

   @JsonProperty("Actors")
   public void setActors(String[] actors) {
      Actors = actors;
   }

   @JsonProperty("EpisodesUnwatchedCount")
   public int getEpisodesUnwatchedCount() {
      return EpisodesUnwatchedCount;
   }

   @JsonProperty("EpisodesUnwatchedCount")
   public void setEpisodesUnwatchedCount(int episodesUnwatchedCount) {
      EpisodesUnwatchedCount = episodesUnwatchedCount;
   }

   @JsonProperty("Runtime")
   public int getRuntime() {
      return Runtime;
   }

   @JsonProperty("Runtime")
   public void setRuntime(int runtime) {
      Runtime = runtime;
   }

   @JsonProperty("FirstAired")
   public Date getFirstAired() {
      return FirstAired;
   }

   @JsonProperty("FirstAired")
   public void setFirstAired(Date firstAired) {
      FirstAired = firstAired;
   }

   @JsonProperty("EpisodeOrder")
   public String getEpisodeOrder() {
      return EpisodeOrder;
   }

   @JsonProperty("EpisodeOrder")
   public void setEpisodeOrder(String episodeOrder) {
      EpisodeOrder = episodeOrder;
   }

}
