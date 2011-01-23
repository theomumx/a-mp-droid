package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mediaportal.ampdroid.database.ColumnProperty;

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
   
   @ColumnProperty(value="Classification", type="text")
	@JsonProperty("Classification")
   public String getClassification() {
      return Classification;
   }
   
   @ColumnProperty(value="Classification", type="text")
   @JsonProperty("Classification")
   public void setClassification(String classification) {
      Classification = classification;
   }
   
   @ColumnProperty(value="HasConflict", type="boolean")
   @JsonProperty("HasConflict")
   public boolean isHasConflict() {
      return HasConflict;
   }
   
   @ColumnProperty(value="HasConflict", type="boolean")
   @JsonProperty("HasConflict")
   public void setHasConflict(boolean hasConflict) {
      HasConflict = hasConflict;
   }
   
   @ColumnProperty(value="IdProgram", type="integer")
   @JsonProperty("IdProgram")
   public int getIdProgram() {
      return IdProgram;
   }
   
   @ColumnProperty(value="IdProgram", type="integer")
   @JsonProperty("IdProgram")
   public void setIdProgram(int idProgram) {
      IdProgram = idProgram;
   }
   
   @ColumnProperty(value="IsPartialRecordingSeriesPending", type="boolean")
   @JsonProperty("IsPartialRecordingSeriesPending")
   public boolean isIsPartialRecordingSeriesPending() {
      return IsPartialRecordingSeriesPending;
   }
   
   @ColumnProperty(value="IsPartialRecordingSeriesPending", type="boolean")
   @JsonProperty("IsPartialRecordingSeriesPending")
   public void setIsPartialRecordingSeriesPending(boolean isPartialRecordingSeriesPending) {
      IsPartialRecordingSeriesPending = isPartialRecordingSeriesPending;
   }
   
   @ColumnProperty(value="IsRecording", type="boolean")
   @JsonProperty("IsRecording")
   public boolean isIsRecording() {
      return IsRecording;
   }
   
   @ColumnProperty(value="IsRecording", type="boolean")
   @JsonProperty("IsRecording")
   public void setIsRecording(boolean isRecording) {
      IsRecording = isRecording;
   }
   
   @ColumnProperty(value="IsRecordingManual", type="boolean")
   @JsonProperty("IsRecordingManual")
   public boolean isIsRecordingManual() {
      return IsRecordingManual;
   }
   
   @ColumnProperty(value="IsRecordingManual", type="boolean")
   @JsonProperty("IsRecordingManual")
   public void setIsRecordingManual(boolean isRecordingManual) {
      IsRecordingManual = isRecordingManual;
   }
   
   @ColumnProperty(value="IsRecordingOnce", type="boolean")
   @JsonProperty("IsRecordingOnce")
   public boolean isIsRecordingOnce() {
      return IsRecordingOnce;
   }
   
   @ColumnProperty(value="IsRecordingOnce", type="boolean")
   @JsonProperty("IsRecordingOnce")
   public void setIsRecordingOnce(boolean isRecordingOnce) {
      IsRecordingOnce = isRecordingOnce;
   }
   
   @ColumnProperty(value="IsRecordingOncePending", type="boolean")
   @JsonProperty("IsRecordingOncePending")
   public boolean isIsRecordingOncePending() {
      return IsRecordingOncePending;
   }
   
   @ColumnProperty(value="IsRecordingOncePending", type="boolean")
   @JsonProperty("IsRecordingOncePending")
   public void setIsRecordingOncePending(boolean isRecordingOncePending) {
      IsRecordingOncePending = isRecordingOncePending;
   }
   
   @ColumnProperty(value="IsRecordingSeries", type="boolean")
   @JsonProperty("IsRecordingSeries")
   public boolean isIsRecordingSeries() {
      return IsRecordingSeries;
   }
   
   @ColumnProperty(value="IsRecordingSeries", type="boolean")
   @JsonProperty("IsRecordingSeries")
   public void setIsRecordingSeries(boolean isRecordingSeries) {
      IsRecordingSeries = isRecordingSeries;
   }
   
   @ColumnProperty(value="IsRecordingSeriesPending", type="boolean")
   @JsonProperty("IsRecordingSeriesPending")
   public boolean isIsRecordingSeriesPending() {
      return IsRecordingSeriesPending;
   }
   
   @ColumnProperty(value="IsRecordingSeriesPending", type="boolean")
   @JsonProperty("IsRecordingSeriesPending")
   public void setIsRecordingSeriesPending(boolean isRecordingSeriesPending) {
      IsRecordingSeriesPending = isRecordingSeriesPending;
   }
   
   @ColumnProperty(value="Notify", type="boolean")
   @JsonProperty("Notify")
   public boolean isNotify() {
      return Notify;
   }
   
   @ColumnProperty(value="Notify", type="boolean")
   @JsonProperty("Notify")
   public void setNotify(boolean notify) {
      Notify = notify;
   }
   
   @ColumnProperty(value="OriginalAirDate", type="date")
   @JsonProperty("OriginalAirDate")
   public Date getOriginalAirDate() {
      return OriginalAirDate;
   }
   
   @ColumnProperty(value="OriginalAirDate", type="date")
   @JsonProperty("OriginalAirDate")
   public void setOriginalAirDate(Date originalAirDate) {
      OriginalAirDate = originalAirDate;
   }
   
   @ColumnProperty(value="ParentalRating", type="integer")
   @JsonProperty("ParentalRating")
   public int getParentalRating() {
      return ParentalRating;
   }
   
   @ColumnProperty(value="ParentalRating", type="integer")
   @JsonProperty("ParentalRating")
   public void setParentalRating(int parentalRating) {
      ParentalRating = parentalRating;
   }
   
   @ColumnProperty(value="StarRating", type="integer")
   @JsonProperty("StarRating")
   public int getStarRating() {
      return StarRating;
   }
   
   @ColumnProperty(value="StarRating", type="integer")
   @JsonProperty("StarRating")
   public void setStarRating(int starRating) {
      StarRating = starRating;
   }
	
	

}
