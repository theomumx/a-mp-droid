package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class TvSchedule {
   int BitRateMode;
   Date Canceled;
   String Directory;
   boolean DoesUseEpisodeManagement;
   Date EndTime;
   int IdChannel;
   int IdParentSchedule;
   int IdSchedule;
   boolean IsChanged;
   boolean IsManual;
   Date KeepDate;
   int KeepMethod;
   int MaxAirings;
   int PostRecordInterval;
   int PreRecordInterval;
   int Priority;
   String ProgramName;
   int Quality;
   int QualityType;
   int RecommendedCard;
   int ScheduleType;
   boolean Series;
   Date StartTime;

   @Override
   public String toString() {
      return ProgramName;
   }

   @JsonProperty("BitRateMode")
   public int getBitRateMode() {
      return BitRateMode;
   }

   @JsonProperty("BitRateMode")
   public void setBitRateMode(int bitRateMode) {
      BitRateMode = bitRateMode;
   }

   @JsonProperty("Canceled")
   public Date getCanceled() {
      return Canceled;
   }

   @JsonProperty("Canceled")
   public void setCanceled(Date canceled) {
      Canceled = canceled;
   }

   @JsonProperty("Directory")
   public String getDirectory() {
      return Directory;
   }

   @JsonProperty("Directory")
   public void setDirectory(String directory) {
      Directory = directory;
   }

   @JsonProperty("DoesUseEpisodeManagement")
   public boolean isDoesUseEpisodeManagement() {
      return DoesUseEpisodeManagement;
   }

   @JsonProperty("DoesUseEpisodeManagement")
   public void setDoesUseEpisodeManagement(boolean doesUseEpisodeManagement) {
      DoesUseEpisodeManagement = doesUseEpisodeManagement;
   }

   @JsonProperty("EndTime")
   public Date getEndTime() {
      return EndTime;
   }

   @JsonProperty("EndTime")
   public void setEndTime(Date endTime) {
      EndTime = endTime;
   }

   @JsonProperty("IdChannel")
   public int getIdChannel() {
      return IdChannel;
   }

   @JsonProperty("IdChannel")
   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }

   @JsonProperty("IdParentSchedule")
   public int getIdParentSchedule() {
      return IdParentSchedule;
   }

   @JsonProperty("IdParentSchedule")
   public void setIdParentSchedule(int idParentSchedule) {
      IdParentSchedule = idParentSchedule;
   }

   @JsonProperty("IdSchedule")
   public int getIdSchedule() {
      return IdSchedule;
   }

   @JsonProperty("IdSchedule")
   public void setIdSchedule(int idSchedule) {
      IdSchedule = idSchedule;
   }

   @JsonProperty("IsChanged")
   public boolean isIsChanged() {
      return IsChanged;
   }

   @JsonProperty("IsChanged")
   public void setIsChanged(boolean isChanged) {
      IsChanged = isChanged;
   }

   @JsonProperty("IsManual")
   public boolean isIsManual() {
      return IsManual;
   }

   @JsonProperty("IsManual")
   public void setIsManual(boolean isManual) {
      IsManual = isManual;
   }

   @JsonProperty("KeepDate")
   public Date getKeepDate() {
      return KeepDate;
   }

   @JsonProperty("KeepDate")
   public void setKeepDate(Date keepDate) {
      KeepDate = keepDate;
   }

   @JsonProperty("KeepMethod")
   public int getKeepMethod() {
      return KeepMethod;
   }

   @JsonProperty("KeepMethod")
   public void setKeepMethod(int keepMethod) {
      KeepMethod = keepMethod;
   }

   @JsonProperty("MaxAirings")
   public int getMaxAirings() {
      return MaxAirings;
   }

   @JsonProperty("MaxAirings")
   public void setMaxAirings(int maxAirings) {
      MaxAirings = maxAirings;
   }

   @JsonProperty("PostRecordInterval")
   public int getPostRecordInterval() {
      return PostRecordInterval;
   }

   @JsonProperty("PostRecordInterval")
   public void setPostRecordInterval(int postRecordInterval) {
      PostRecordInterval = postRecordInterval;
   }

   @JsonProperty("PreRecordInterval")
   public int getPreRecordInterval() {
      return PreRecordInterval;
   }

   @JsonProperty("PreRecordInterval")
   public void setPreRecordInterval(int preRecordInterval) {
      PreRecordInterval = preRecordInterval;
   }

   @JsonProperty("Priority")
   public int getPriority() {
      return Priority;
   }

   @JsonProperty("Priority")
   public void setPriority(int priority) {
      Priority = priority;
   }

   @JsonProperty("ProgramName")
   public String getProgramName() {
      return ProgramName;
   }

   @JsonProperty("ProgramName")
   public void setProgramName(String programName) {
      ProgramName = programName;
   }

   @JsonProperty("Quality")
   public int getQuality() {
      return Quality;
   }

   @JsonProperty("Quality")
   public void setQuality(int quality) {
      Quality = quality;
   }

   @JsonProperty("QualityType")
   public int getQualityType() {
      return QualityType;
   }

   @JsonProperty("QualityType")
   public void setQualityType(int qualityType) {
      QualityType = qualityType;
   }

   @JsonProperty("RecommendedCard")
   public int getRecommendedCard() {
      return RecommendedCard;
   }

   @JsonProperty("RecommendedCard")
   public void setRecommendedCard(int recommendedCard) {
      RecommendedCard = recommendedCard;
   }

   @JsonProperty("ScheduleType")
   public int getScheduleType() {
      return ScheduleType;
   }

   @JsonProperty("ScheduleType")
   public void setScheduleType(int scheduleType) {
      ScheduleType = scheduleType;
   }

   @JsonProperty("Series")
   public boolean isSeries() {
      return Series;
   }

   @JsonProperty("Series")
   public void setSeries(boolean series) {
      Series = series;
   }

   @JsonProperty("StartTime")
   public Date getStartTime() {
      return StartTime;
   }

   @JsonProperty("StartTime")
   public void setStartTime(Date startTime) {
      StartTime = startTime;
   }
}
