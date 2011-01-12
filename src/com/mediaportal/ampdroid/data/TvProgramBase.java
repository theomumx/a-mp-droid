package com.mediaportal.ampdroid.data;

import java.util.Date;

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
   
   public int getIdChannel() {
      return IdChannel;
   }
   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }
   public String getTitle() {
      return Title;
   }
   public void setTitle(String title) {
      Title = title;
   }
   public boolean isIsChanged() {
      return IsChanged;
   }
   public void setIsChanged(boolean isChanged) {
      IsChanged = isChanged;
   }
   public String getDescription() {
      return Description;
   }
   public void setDescription(String description) {
      Description = description;
   }
   public Date getEndTime() {
      return EndTime;
   }
   public void setEndTime(Date endTime) {
      EndTime = endTime;
   }
   public String getEpisodeName() {
      return EpisodeName;
   }
   public void setEpisodeName(String episodeName) {
      EpisodeName = episodeName;
   }
   public String getEpisodeNum() {
      return EpisodeNum;
   }
   public void setEpisodeNum(String episodeNum) {
      EpisodeNum = episodeNum;
   }
   public String getEpisodeNumber() {
      return EpisodeNumber;
   }
   public void setEpisodeNumber(String episodeNumber) {
      EpisodeNumber = episodeNumber;
   }
   public String getEpisodePart() {
      return EpisodePart;
   }
   public void setEpisodePart(String episodePart) {
      EpisodePart = episodePart;
   }
   public String getGenre() {
      return Genre;
   }
   public void setGenre(String genre) {
      Genre = genre;
   }
   public Date getStartTime() {
      return StartTime;
   }
   public void setStartTime(Date startTime) {
      StartTime = startTime;
   }
   public String getSeriesNum() {
      return SeriesNum;
   }
   public void setSeriesNum(String seriesNum) {
      SeriesNum = seriesNum;
   }
   
   
}
