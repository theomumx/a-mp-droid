package com.mediaportal.remote.data;

import java.util.Date;

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
   
   public String[] getGroupNames() {
      return GroupNames;
   }

   public void setGroupNames(String[] groupNames) {
      GroupNames = groupNames;
   }
   
   public TvProgram getCurrentProgram() {
      return CurrentProgram;
   }
   public void setCurrentProgram(TvProgram currentProgram) {
      CurrentProgram = currentProgram;
   }
   public boolean isEpgHasGaps() {
      return EpgHasGaps;
   }
   public void setEpgHasGaps(boolean epgHasGaps) {
      EpgHasGaps = epgHasGaps;
   }
   public String getExternalId() {
      return ExternalId;
   }
   public void setExternalId(String externalId) {
      ExternalId = externalId;
   }
   public boolean isFreeToAir() {
      return FreeToAir;
   }
   public void setFreeToAir(boolean freeToAir) {
      FreeToAir = freeToAir;
   }
   public boolean isGrabEpg() {
      return GrabEpg;
   }
   public void setGrabEpg(boolean grabEpg) {
      GrabEpg = grabEpg;
   }
   public boolean isIsChanged() {
      return IsChanged;
   }
   public void setIsChanged(boolean isChanged) {
      IsChanged = isChanged;
   }
   public boolean isIsRadio() {
      return IsRadio;
   }
   public void setIsRadio(boolean isRadio) {
      IsRadio = isRadio;
   }
   public boolean isIsTv() {
      return IsTv;
   }
   public void setIsTv(boolean isTv) {
      IsTv = isTv;
   }
   public Date getLastGrabTime() {
      return LastGrabTime;
   }
   public void setLastGrabTime(Date lastGrabTime) {
      LastGrabTime = lastGrabTime;
   }
   public TvProgram getNextProgram() {
      return NextProgram;
   }
   public void setNextProgram(TvProgram nextProgram) {
      NextProgram = nextProgram;
   }
   public int getSortOrder() {
      return SortOrder;
   }
   public void setSortOrder(int sortOrder) {
      SortOrder = sortOrder;
   }
   public int getTimesWatched() {
      return TimesWatched;
   }
   public void setTimesWatched(int timesWatched) {
      TimesWatched = timesWatched;
   }
   public Date getTotalTimeWatched() {
      return TotalTimeWatched;
   }
   public void setTotalTimeWatched(Date totalTimeWatched) {
      TotalTimeWatched = totalTimeWatched;
   }
   public boolean isVisibleInGuide() {
      return VisibleInGuide;
   }
   public void setVisibleInGuide(boolean visibleInGuide) {
      VisibleInGuide = visibleInGuide;
   }
   
   
}
