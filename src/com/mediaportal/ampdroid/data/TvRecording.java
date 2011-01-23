package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mediaportal.ampdroid.database.ColumnProperty;

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

   
   @ColumnProperty(value="FileName", type="text")
   @JsonProperty("FileName")
   public String getFileName() {
      return FileName;
   }
   
   @ColumnProperty(value="FileName", type="text")
   @JsonProperty("FileName")
   public void setFileName(String fileName) {
      FileName = fileName;
   }
   
   @ColumnProperty(value="IdRecording", type="integer")
   @JsonProperty("IdRecording")
   public int getIdRecording() {
      return IdRecording;
   }
   
   @ColumnProperty(value="IdRecording", type="integer")
   @JsonProperty("IdRecording")
   public void setIdRecording(int idRecording) {
      IdRecording = idRecording;
   }
   
   @ColumnProperty(value="Idschedule", type="integer")
   @JsonProperty("Idschedule")
   public int getIdschedule() {
      return Idschedule;
   }
   
   @ColumnProperty(value="Idschedule", type="integer")
   @JsonProperty("Idschedule")
   public void setIdschedule(int idschedule) {
      Idschedule = idschedule;
   }
   
   @ColumnProperty(value="IdServer", type="integer")
   @JsonProperty("IdServer")
   public int getIdServer() {
      return IdServer;
   }
   
   @ColumnProperty(value="IdServer", type="integer")
   @JsonProperty("IdServer")
   public void setIdServer(int idServer) {
      IdServer = idServer;
   }
   
   @ColumnProperty(value="IsManual", type="boolean")
   @JsonProperty("IsManual")
   public boolean isIsManual() {
      return IsManual;
   }
   
   @ColumnProperty(value="IsManual", type="boolean")
   @JsonProperty("IsManual")
   public void setIsManual(boolean isManual) {
      IsManual = isManual;
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
   
   @ColumnProperty(value="KeepUntil", type="integer")
   @JsonProperty("KeepUntil")
   public int getKeepUntil() {
      return KeepUntil;
   }
   
   @ColumnProperty(value="KeepUntil", type="integer")
   @JsonProperty("KeepUntil")
   public void setKeepUntil(int keepUntil) {
      KeepUntil = keepUntil;
   }
   
   @ColumnProperty(value="KeepUntilDate", type="date")
   @JsonProperty("KeepUntilDate")
   public Date getKeepUntilDate() {
      return KeepUntilDate;
   }
   
   @ColumnProperty(value="KeepUntilDate", type="date")
   @JsonProperty("KeepUntilDate")
   public void setKeepUntilDate(Date keepUntilDate) {
      KeepUntilDate = keepUntilDate;
   }
   
   @ColumnProperty(value="ShouldBeDeleted", type="boolean")
   @JsonProperty("ShouldBeDeleted")
   public boolean isShouldBeDeleted() {
      return ShouldBeDeleted;
   }
   
   @ColumnProperty(value="ShouldBeDeleted", type="boolean")
   @JsonProperty("ShouldBeDeleted")
   public void setShouldBeDeleted(boolean shouldBeDeleted) {
      ShouldBeDeleted = shouldBeDeleted;
   }
   
   @ColumnProperty(value="StopTime", type="integer")
   @JsonProperty("StopTime")
   public int getStopTime() {
      return StopTime;
   }
   
   @ColumnProperty(value="StopTime", type="integer")
   @JsonProperty("StopTime")
   public void setStopTime(int stopTime) {
      StopTime = stopTime;
   }
   
   @ColumnProperty(value="TimesWatched", type="integer")
   @JsonProperty("TimesWatched")
   public int getTimesWatched() {
      return TimesWatched;
   }
   
   @ColumnProperty(value="TimesWatched", type="integer")
   @JsonProperty("TimesWatched")
   public void setTimesWatched(int timesWatched) {
      TimesWatched = timesWatched;
   }
   
   

}
