package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mediaportal.ampdroid.database.ColumnProperty;

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
   
   
   @ColumnProperty(value="IdChannel", type="integer")
   @JsonProperty("IdChannel")
   public int getIdChannel() {
      return IdChannel;
   }   
   
   @ColumnProperty(value="IdChannel", type="integer")
   @JsonProperty("IdChannel")
   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }   
   
   @ColumnProperty(value="Title", type="text")
   @JsonProperty("Title")
   public String getTitle() {
      return Title;
   }   
   
   @ColumnProperty(value="Title", type="text")
   @JsonProperty("Title")
   public void setTitle(String title) {
      Title = title;
   }   
   
   @ColumnProperty(value="IsChanged", type="boolean")
   @JsonProperty("IsChanged")
   public boolean isIsChanged() {
      return IsChanged;
   }   
   
   @ColumnProperty(value="IsChanged", type="boolean")
   @JsonProperty("IsChanged")
   public void setIsChanged(boolean isChanged) {
      IsChanged = isChanged;
   }   
   
   @ColumnProperty(value="Description", type="text")
   @JsonProperty("Description")
   public String getDescription() {
      return Description;
   }   
   
   @ColumnProperty(value="Description", type="text")
   @JsonProperty("Description")
   public void setDescription(String description) {
      Description = description;
   }   
   
   @ColumnProperty(value="EndTime", type="date")
   @JsonProperty("EndTime")
   public Date getEndTime() {
      return EndTime;
   }   
   
   @ColumnProperty(value="EndTime", type="date")
   @JsonProperty("EndTime")
   public void setEndTime(Date endTime) {
      EndTime = endTime;
   }   
   
   @ColumnProperty(value="EpisodeName", type="text")
   @JsonProperty("EpisodeName")
   public String getEpisodeName() {
      return EpisodeName;
   }   
   
   @ColumnProperty(value="EpisodeName", type="text")
   @JsonProperty("EpisodeName")
   public void setEpisodeName(String episodeName) {
      EpisodeName = episodeName;
   }   
   
   @ColumnProperty(value="EpisodeNum", type="integer")
   @JsonProperty("EpisodeNum")
   public String getEpisodeNum() {
      return EpisodeNum;
   }   
   
   @ColumnProperty(value="EpisodeNum", type="integer")
   @JsonProperty("EpisodeNum")
   public void setEpisodeNum(String episodeNum) {
      EpisodeNum = episodeNum;
   }   
   
   @ColumnProperty(value="EpisodeNumber", type="text")
   @JsonProperty("EpisodeNumber")
   public String getEpisodeNumber() {
      return EpisodeNumber;
   }   
   
   @ColumnProperty(value="EpisodeNumber", type="text")
   @JsonProperty("EpisodeNumber")
   public void setEpisodeNumber(String episodeNumber) {
      EpisodeNumber = episodeNumber;
   }   
   
   @ColumnProperty(value="EpisodePart", type="text")
   @JsonProperty("EpisodePart")
   public String getEpisodePart() {
      return EpisodePart;
   }   
   
   @ColumnProperty(value="EpisodePart", type="text")
   @JsonProperty("EpisodePart")
   public void setEpisodePart(String episodePart) {
      EpisodePart = episodePart;
   }   
   
   @ColumnProperty(value="Genre", type="text")
   @JsonProperty("Genre")
   public String getGenre() {
      return Genre;
   }   
   
   @ColumnProperty(value="Genre", type="text")
   @JsonProperty("Genre")
   public void setGenre(String genre) {
      Genre = genre;
   }   
   
   @ColumnProperty(value="StartTime", type="date")
   @JsonProperty("StartTime")
   public Date getStartTime() {
      return StartTime;
   }   
   
   @ColumnProperty(value="StartTime", type="date")
   @JsonProperty("StartTime")
   public void setStartTime(Date startTime) {
      StartTime = startTime;
   }   
   
   @ColumnProperty(value="IdChannel", type="text")
   @JsonProperty("SeriesNum")
   public String getSeriesNum() {
      return SeriesNum;
   }   
   
   @ColumnProperty(value="IdChannel", type="text")
   @JsonProperty("SeriesNum")
   public void setSeriesNum(String seriesNum) {
      SeriesNum = seriesNum;
   }
   
   
}
