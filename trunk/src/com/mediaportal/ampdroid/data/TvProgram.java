package com.mediaportal.ampdroid.data;

import java.util.Date;

public class TvProgram extends TvProgramBase {
	String Classification;
	boolean HasConflict;
	int IdProgram;
	boolean IsPartialRecordingSeriesPending;
	boolean IsRecording;
	boolean IsRecordingManual;
	boolean IsRecordingOnce;
	boolean IsRecordingOncePending;
	boolean IsRecordingSeries;
	boolean IsRecordingSeriesPending;
	boolean Notify;
	Date OriginalAirDate;
	int ParentalRating;
	int StarRating;
	
	@Override
	public String toString(){
	   return Title;
	}
	
   public String getClassification() {
      return Classification;
   }
   public void setClassification(String classification) {
      Classification = classification;
   }
   public boolean isHasConflict() {
      return HasConflict;
   }
   public void setHasConflict(boolean hasConflict) {
      HasConflict = hasConflict;
   }
   public int getIdProgram() {
      return IdProgram;
   }
   public void setIdProgram(int idProgram) {
      IdProgram = idProgram;
   }
   public boolean isIsPartialRecordingSeriesPending() {
      return IsPartialRecordingSeriesPending;
   }
   public void setIsPartialRecordingSeriesPending(boolean isPartialRecordingSeriesPending) {
      IsPartialRecordingSeriesPending = isPartialRecordingSeriesPending;
   }
   public boolean isIsRecording() {
      return IsRecording;
   }
   public void setIsRecording(boolean isRecording) {
      IsRecording = isRecording;
   }
   public boolean isIsRecordingManual() {
      return IsRecordingManual;
   }
   public void setIsRecordingManual(boolean isRecordingManual) {
      IsRecordingManual = isRecordingManual;
   }
   public boolean isIsRecordingOnce() {
      return IsRecordingOnce;
   }
   public void setIsRecordingOnce(boolean isRecordingOnce) {
      IsRecordingOnce = isRecordingOnce;
   }
   public boolean isIsRecordingOncePending() {
      return IsRecordingOncePending;
   }
   public void setIsRecordingOncePending(boolean isRecordingOncePending) {
      IsRecordingOncePending = isRecordingOncePending;
   }
   public boolean isIsRecordingSeries() {
      return IsRecordingSeries;
   }
   public void setIsRecordingSeries(boolean isRecordingSeries) {
      IsRecordingSeries = isRecordingSeries;
   }
   public boolean isIsRecordingSeriesPending() {
      return IsRecordingSeriesPending;
   }
   public void setIsRecordingSeriesPending(boolean isRecordingSeriesPending) {
      IsRecordingSeriesPending = isRecordingSeriesPending;
   }
   public boolean isNotify() {
      return Notify;
   }
   public void setNotify(boolean notify) {
      Notify = notify;
   }
   public Date getOriginalAirDate() {
      return OriginalAirDate;
   }
   public void setOriginalAirDate(Date originalAirDate) {
      OriginalAirDate = originalAirDate;
   }
   public int getParentalRating() {
      return ParentalRating;
   }
   public void setParentalRating(int parentalRating) {
      ParentalRating = parentalRating;
   }
   public int getStarRating() {
      return StarRating;
   }
   public void setStarRating(int starRating) {
      StarRating = starRating;
   }
	
	

}
