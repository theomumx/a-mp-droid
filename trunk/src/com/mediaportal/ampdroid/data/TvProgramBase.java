package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class TvProgramBase {
   int IdChannel;
   String Title;
   boolean IsChanged;
   String Description;
   Date EndTime;
   String EpisodeName;
   String EpisodeNum;
   String EpisodeNumber;
   String EpisodePart;
   String Genre;
   Date StartTime;
   String SeriesNum;
   
   
   @JsonProperty("IdChannel")
   public int getIdChannel() {
      return IdChannel;
   }   
   
   @JsonProperty("IdChannel")
   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }   
   
   @JsonProperty("Title")
   public String getTitle() {
      return Title;
   }   
   
   @JsonProperty("Title")
   public void setTitle(String title) {
      Title = title;
   }   
   
   @JsonProperty("IsChanged")
   public boolean isIsChanged() {
      return IsChanged;
   }   
   
   @JsonProperty("IsChanged")
   public void setIsChanged(boolean isChanged) {
      IsChanged = isChanged;
   }   
   
   @JsonProperty("Description")
   public String getDescription() {
      return Description;
   }   
   
   @JsonProperty("Description")
   public void setDescription(String description) {
      Description = description;
   }   
   
   @JsonProperty("EndTime")
   public Date getEndTime() {
      return EndTime;
   }   
   
   @JsonProperty("EndTime")
   public void setEndTime(Date endTime) {
      EndTime = endTime;
   }   
   
   @JsonProperty("EpisodeName")
   public String getEpisodeName() {
      return EpisodeName;
   }   
   
   @JsonProperty("EpisodeName")
   public void setEpisodeName(String episodeName) {
      EpisodeName = episodeName;
   }   
   
   @JsonProperty("EpisodeNum")
   public String getEpisodeNum() {
      return EpisodeNum;
   }   
   
   @JsonProperty("EpisodeNum")
   public void setEpisodeNum(String episodeNum) {
      EpisodeNum = episodeNum;
   }   
   
   @JsonProperty("EpisodeNumber")
   public String getEpisodeNumber() {
      return EpisodeNumber;
   }   
   
   @JsonProperty("EpisodeNumber")
   public void setEpisodeNumber(String episodeNumber) {
      EpisodeNumber = episodeNumber;
   }   
   
   @JsonProperty("EpisodePart")
   public String getEpisodePart() {
      return EpisodePart;
   }   
   
   @JsonProperty("EpisodePart")
   public void setEpisodePart(String episodePart) {
      EpisodePart = episodePart;
   }   
   
   @JsonProperty("Genre")
   public String getGenre() {
      return Genre;
   }   
   
   @JsonProperty("Genre")
   public void setGenre(String genre) {
      Genre = genre;
   }   
   
   @JsonProperty("StartTime")
   public Date getStartTime() {
      return StartTime;
   }   
   
   @JsonProperty("StartTime")
   public void setStartTime(Date startTime) {
      StartTime = startTime;
   }   
   
   @JsonProperty("SeriesNum")
   public String getSeriesNum() {
      return SeriesNum;
   }   
   
   @JsonProperty("SeriesNum")
   public void setSeriesNum(String seriesNum) {
      SeriesNum = seriesNum;
   }
   
   
}
