package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class TvRecording extends TvProgramBase {
   String FileName;
   int IdRecording;
   int Idschedule;
   int IdServer;
   boolean IsManual;
   boolean IsRecording;
   int KeepUntil;
   Date KeepUntilDate;
   boolean ShouldBeDeleted;
   int StopTime;
   int TimesWatched;
   
   @Override
   public String toString(){
      return this.Title;
   }

   
   @JsonProperty("FileName")
   public String getFileName() {
      return FileName;
   }
   
   @JsonProperty("FileName")
   public void setFileName(String fileName) {
      FileName = fileName;
   }
   
   @JsonProperty("IdRecording")
   public int getIdRecording() {
      return IdRecording;
   }
   
   @JsonProperty("IdRecording")
   public void setIdRecording(int idRecording) {
      IdRecording = idRecording;
   }
   
   @JsonProperty("Idschedule")
   public int getIdschedule() {
      return Idschedule;
   }
   
   @JsonProperty("Idschedule")
   public void setIdschedule(int idschedule) {
      Idschedule = idschedule;
   }
   
   @JsonProperty("IdServer")
   public int getIdServer() {
      return IdServer;
   }
   
   @JsonProperty("IdServer")
   public void setIdServer(int idServer) {
      IdServer = idServer;
   }
   
   @JsonProperty("IsManual")
   public boolean isIsManual() {
      return IsManual;
   }
   
   @JsonProperty("IsManual")
   public void setIsManual(boolean isManual) {
      IsManual = isManual;
   }
   
   @JsonProperty("IsRecording")
   public boolean isIsRecording() {
      return IsRecording;
   }
   
   @JsonProperty("IsRecording")
   public void setIsRecording(boolean isRecording) {
      IsRecording = isRecording;
   }
   
   @JsonProperty("KeepUntil")
   public int getKeepUntil() {
      return KeepUntil;
   }
   
   @JsonProperty("KeepUntil")
   public void setKeepUntil(int keepUntil) {
      KeepUntil = keepUntil;
   }
   
   @JsonProperty("KeepUntilDate")
   public Date getKeepUntilDate() {
      return KeepUntilDate;
   }
   
   @JsonProperty("KeepUntilDate")
   public void setKeepUntilDate(Date keepUntilDate) {
      KeepUntilDate = keepUntilDate;
   }
   
   @JsonProperty("ShouldBeDeleted")
   public boolean isShouldBeDeleted() {
      return ShouldBeDeleted;
   }
   
   @JsonProperty("ShouldBeDeleted")
   public void setShouldBeDeleted(boolean shouldBeDeleted) {
      ShouldBeDeleted = shouldBeDeleted;
   }
   
   @JsonProperty("StopTime")
   public int getStopTime() {
      return StopTime;
   }
   
   @JsonProperty("StopTime")
   public void setStopTime(int stopTime) {
      StopTime = stopTime;
   }
   
   @JsonProperty("TimesWatched")
   public int getTimesWatched() {
      return TimesWatched;
   }
   
   @JsonProperty("TimesWatched")
   public void setTimesWatched(int timesWatched) {
      TimesWatched = timesWatched;
   }
   
   

}
