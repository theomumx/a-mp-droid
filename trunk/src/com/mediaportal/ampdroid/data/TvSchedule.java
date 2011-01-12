package com.mediaportal.ampdroid.data;

import java.util.Date;

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
    public String toString(){
       return ProgramName;
    }

   public int getBitRateMode() {
      return BitRateMode;
   }

   public void setBitRateMode(int bitRateMode) {
      BitRateMode = bitRateMode;
   }

   public Date getCanceled() {
      return Canceled;
   }

   public void setCanceled(Date canceled) {
      Canceled = canceled;
   }

   public String getDirectory() {
      return Directory;
   }

   public void setDirectory(String directory) {
      Directory = directory;
   }

   public boolean isDoesUseEpisodeManagement() {
      return DoesUseEpisodeManagement;
   }

   public void setDoesUseEpisodeManagement(boolean doesUseEpisodeManagement) {
      DoesUseEpisodeManagement = doesUseEpisodeManagement;
   }

   public Date getEndTime() {
      return EndTime;
   }

   public void setEndTime(Date endTime) {
      EndTime = endTime;
   }

   public int getIdChannel() {
      return IdChannel;
   }

   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }

   public int getIdParentSchedule() {
      return IdParentSchedule;
   }

   public void setIdParentSchedule(int idParentSchedule) {
      IdParentSchedule = idParentSchedule;
   }

   public int getIdSchedule() {
      return IdSchedule;
   }

   public void setIdSchedule(int idSchedule) {
      IdSchedule = idSchedule;
   }

   public boolean isIsChanged() {
      return IsChanged;
   }

   public void setIsChanged(boolean isChanged) {
      IsChanged = isChanged;
   }

   public boolean isIsManual() {
      return IsManual;
   }

   public void setIsManual(boolean isManual) {
      IsManual = isManual;
   }

   public Date getKeepDate() {
      return KeepDate;
   }

   public void setKeepDate(Date keepDate) {
      KeepDate = keepDate;
   }

   public int getKeepMethod() {
      return KeepMethod;
   }

   public void setKeepMethod(int keepMethod) {
      KeepMethod = keepMethod;
   }

   public int getMaxAirings() {
      return MaxAirings;
   }

   public void setMaxAirings(int maxAirings) {
      MaxAirings = maxAirings;
   }

   public int getPostRecordInterval() {
      return PostRecordInterval;
   }

   public void setPostRecordInterval(int postRecordInterval) {
      PostRecordInterval = postRecordInterval;
   }

   public int getPreRecordInterval() {
      return PreRecordInterval;
   }

   public void setPreRecordInterval(int preRecordInterval) {
      PreRecordInterval = preRecordInterval;
   }

   public int getPriority() {
      return Priority;
   }

   public void setPriority(int priority) {
      Priority = priority;
   }

   public String getProgramName() {
      return ProgramName;
   }

   public void setProgramName(String programName) {
      ProgramName = programName;
   }

   public int getQuality() {
      return Quality;
   }

   public void setQuality(int quality) {
      Quality = quality;
   }

   public int getQualityType() {
      return QualityType;
   }

   public void setQualityType(int qualityType) {
      QualityType = qualityType;
   }

   public int getRecommendedCard() {
      return RecommendedCard;
   }

   public void setRecommendedCard(int recommendedCard) {
      RecommendedCard = recommendedCard;
   }

   public int getScheduleType() {
      return ScheduleType;
   }

   public void setScheduleType(int scheduleType) {
      ScheduleType = scheduleType;
   }

   public boolean isSeries() {
      return Series;
   }

   public void setSeries(boolean series) {
      Series = series;
   }

   public Date getStartTime() {
      return StartTime;
   }

   public void setStartTime(Date startTime) {
      StartTime = startTime;
   }
}
