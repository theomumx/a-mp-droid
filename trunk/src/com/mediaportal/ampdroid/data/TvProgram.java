package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

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
	
	@JsonProperty("Classification")
   public String getClassification() {
      return Classification;
   }
	
   @JsonProperty("Classification")
   public void setClassification(String classification) {
      Classification = classification;
   }
   
   @JsonProperty("HasConflict")
   public boolean isHasConflict() {
      return HasConflict;
   }
   
   @JsonProperty("HasConflict")
   public void setHasConflict(boolean hasConflict) {
      HasConflict = hasConflict;
   }
   
   @JsonProperty("IdProgram")
   public int getIdProgram() {
      return IdProgram;
   }
   
   @JsonProperty("IdProgram")
   public void setIdProgram(int idProgram) {
      IdProgram = idProgram;
   }
   
   @JsonProperty("IsPartialRecordingSeriesPending")
   public boolean isIsPartialRecordingSeriesPending() {
      return IsPartialRecordingSeriesPending;
   }
   
   @JsonProperty("IsPartialRecordingSeriesPending")
   public void setIsPartialRecordingSeriesPending(boolean isPartialRecordingSeriesPending) {
      IsPartialRecordingSeriesPending = isPartialRecordingSeriesPending;
   }
   
   @JsonProperty("IsRecording")
   public boolean isIsRecording() {
      return IsRecording;
   }
   
   @JsonProperty("IsRecording")
   public void setIsRecording(boolean isRecording) {
      IsRecording = isRecording;
   }
   
   @JsonProperty("IsRecordingManual")
   public boolean isIsRecordingManual() {
      return IsRecordingManual;
   }
   
   @JsonProperty("IsRecordingManual")
   public void setIsRecordingManual(boolean isRecordingManual) {
      IsRecordingManual = isRecordingManual;
   }
   
   @JsonProperty("IsRecordingOnce")
   public boolean isIsRecordingOnce() {
      return IsRecordingOnce;
   }
   
   @JsonProperty("IsRecordingOnce")
   public void setIsRecordingOnce(boolean isRecordingOnce) {
      IsRecordingOnce = isRecordingOnce;
   }
   
   @JsonProperty("IsRecordingOncePending")
   public boolean isIsRecordingOncePending() {
      return IsRecordingOncePending;
   }
   
   @JsonProperty("IsRecordingOncePending")
   public void setIsRecordingOncePending(boolean isRecordingOncePending) {
      IsRecordingOncePending = isRecordingOncePending;
   }
   
   @JsonProperty("IsRecordingSeries")
   public boolean isIsRecordingSeries() {
      return IsRecordingSeries;
   }
   
   @JsonProperty("IsRecordingSeries")
   public void setIsRecordingSeries(boolean isRecordingSeries) {
      IsRecordingSeries = isRecordingSeries;
   }
   
   @JsonProperty("IsRecordingSeriesPending")
   public boolean isIsRecordingSeriesPending() {
      return IsRecordingSeriesPending;
   }
   
   @JsonProperty("IsRecordingSeriesPending")
   public void setIsRecordingSeriesPending(boolean isRecordingSeriesPending) {
      IsRecordingSeriesPending = isRecordingSeriesPending;
   }
   
   @JsonProperty("Notify")
   public boolean isNotify() {
      return Notify;
   }
   
   @JsonProperty("Notify")
   public void setNotify(boolean notify) {
      Notify = notify;
   }
   
   @JsonProperty("OriginalAirDate")
   public Date getOriginalAirDate() {
      return OriginalAirDate;
   }
   
   @JsonProperty("OriginalAirDate")
   public void setOriginalAirDate(Date originalAirDate) {
      OriginalAirDate = originalAirDate;
   }
   
   @JsonProperty("ParentalRating")
   public int getParentalRating() {
      return ParentalRating;
   }
   
   @JsonProperty("ParentalRating")
   public void setParentalRating(int parentalRating) {
      ParentalRating = parentalRating;
   }
   
   @JsonProperty("StarRating")
   public int getStarRating() {
      return StarRating;
   }
   
   @JsonProperty("StarRating")
   public void setStarRating(int starRating) {
      StarRating = starRating;
   }
	
	

}
