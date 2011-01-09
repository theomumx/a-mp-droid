package com.mediaportal.remote.data;

import java.util.Date;

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

   public String getFileName() {
      return FileName;
   }

   public void setFileName(String fileName) {
      FileName = fileName;
   }

   public int getIdRecording() {
      return IdRecording;
   }

   public void setIdRecording(int idRecording) {
      IdRecording = idRecording;
   }

   public int getIdschedule() {
      return Idschedule;
   }

   public void setIdschedule(int idschedule) {
      Idschedule = idschedule;
   }

   public int getIdServer() {
      return IdServer;
   }

   public void setIdServer(int idServer) {
      IdServer = idServer;
   }

   public boolean isIsManual() {
      return IsManual;
   }

   public void setIsManual(boolean isManual) {
      IsManual = isManual;
   }

   public boolean isIsRecording() {
      return IsRecording;
   }

   public void setIsRecording(boolean isRecording) {
      IsRecording = isRecording;
   }

   public int getKeepUntil() {
      return KeepUntil;
   }

   public void setKeepUntil(int keepUntil) {
      KeepUntil = keepUntil;
   }

   public Date getKeepUntilDate() {
      return KeepUntilDate;
   }

   public void setKeepUntilDate(Date keepUntilDate) {
      KeepUntilDate = keepUntilDate;
   }

   public boolean isShouldBeDeleted() {
      return ShouldBeDeleted;
   }

   public void setShouldBeDeleted(boolean shouldBeDeleted) {
      ShouldBeDeleted = shouldBeDeleted;
   }

   public int getStopTime() {
      return StopTime;
   }

   public void setStopTime(int stopTime) {
      StopTime = stopTime;
   }

   public int getTimesWatched() {
      return TimesWatched;
   }

   public void setTimesWatched(int timesWatched) {
      TimesWatched = timesWatched;
   }
   
   

}
