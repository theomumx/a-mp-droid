package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class TvChannelDetails extends TvChannel{
   private TvProgram CurrentProgram;
   private boolean EpgHasGaps;
   private String ExternalId;
   private boolean FreeToAir;
   private boolean GrabEpg;
   private boolean IsChanged;
   private boolean IsRadio;
   private boolean IsTv;
   private Date LastGrabTime;
   private TvProgram NextProgram;
   private int SortOrder;
   private int TimesWatched;
   private Date TotalTimeWatched;
   private boolean VisibleInGuide;
   private String[] GroupNames;
   
   @JsonProperty("GroupNames")
   public String[] getGroupNames() {
      return GroupNames;
   }

   @JsonProperty("GroupNames")
   public void setGroupNames(String[] groupNames) {
      GroupNames = groupNames;
   }
   
   @JsonProperty("CurrentProgram")
   public TvProgram getCurrentProgram() {
      return CurrentProgram;
   }
   
   @JsonProperty("CurrentProgram")
   public void setCurrentProgram(TvProgram currentProgram) {
      CurrentProgram = currentProgram;
   }
   
   @JsonProperty("EpgHasGaps")
   public boolean isEpgHasGaps() {
      return EpgHasGaps;
   }
   
   @JsonProperty("EpgHasGaps")
   public void setEpgHasGaps(boolean epgHasGaps) {
      EpgHasGaps = epgHasGaps;
   }
   
   @JsonProperty("ExternalId")
   public String getExternalId() {
      return ExternalId;
   }
   
   @JsonProperty("ExternalId")
   public void setExternalId(String externalId) {
      ExternalId = externalId;
   }
   
   @JsonProperty("FreeToAir")
   public boolean isFreeToAir() {
      return FreeToAir;
   }
   
   @JsonProperty("FreeToAir")
   public void setFreeToAir(boolean freeToAir) {
      FreeToAir = freeToAir;
   }
   
   @JsonProperty("GrabEpg")
   public boolean isGrabEpg() {
      return GrabEpg;
   }
   
   @JsonProperty("GrabEpg")
   public void setGrabEpg(boolean grabEpg) {
      GrabEpg = grabEpg;
   }
   
   @JsonProperty("IsChanged")
   public boolean isIsChanged() {
      return IsChanged;
   }
   
   @JsonProperty("IsChanged")
   public void setIsChanged(boolean isChanged) {
      IsChanged = isChanged;
   }
   
   @JsonProperty("IsRadio")
   public boolean isIsRadio() {
      return IsRadio;
   }
   
   @JsonProperty("IsRadio")
   public void setIsRadio(boolean isRadio) {
      IsRadio = isRadio;
   }
   
   @JsonProperty("IsTv")
   public boolean isIsTv() {
      return IsTv;
   }
   
   @JsonProperty("IsTv")
   public void setIsTv(boolean isTv) {
      IsTv = isTv;
   }
   
   @JsonProperty("LastGrabTime")
   public Date getLastGrabTime() {
      return LastGrabTime;
   }
   
   @JsonProperty("LastGrabTime")
   public void setLastGrabTime(Date lastGrabTime) {
      LastGrabTime = lastGrabTime;
   }
   
   @JsonProperty("NextProgram")
   public TvProgram getNextProgram() {
      return NextProgram;
   }
   
   @JsonProperty("NextProgram")
   public void setNextProgram(TvProgram nextProgram) {
      NextProgram = nextProgram;
   }
   
   @JsonProperty("SortOrder")
   public int getSortOrder() {
      return SortOrder;
   }
   
   @JsonProperty("SortOrder")
   public void setSortOrder(int sortOrder) {
      SortOrder = sortOrder;
   }
   
   @JsonProperty("TimesWatched")
   public int getTimesWatched() {
      return TimesWatched;
   }
   
   @JsonProperty("TimesWatched")
   public void setTimesWatched(int timesWatched) {
      TimesWatched = timesWatched;
   }
   
   @JsonProperty("TotalTimeWatched")
   public Date getTotalTimeWatched() {
      return TotalTimeWatched;
   }
   
   @JsonProperty("TotalTimeWatched")
   public void setTotalTimeWatched(Date totalTimeWatched) {
      TotalTimeWatched = totalTimeWatched;
   }
   
   @JsonProperty("VisibleInGuide")
   public boolean isVisibleInGuide() {
      return VisibleInGuide;
   }
   
   @JsonProperty("VisibleInGuide")
   public void setVisibleInGuide(boolean visibleInGuide) {
      VisibleInGuide = visibleInGuide;
   }
   
   
}
