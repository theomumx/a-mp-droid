package com.mediaportal.ampdroid.data;

import java.util.Date;

public class TvCardDetails {
   boolean CAM;
   int CamType;
   int DecryptLimit;
   String DevicePath;
   boolean Enabled;
   boolean GrabEPG;
   int IdCard;
   int IdServer;
   boolean IsChanged;
   Date LastEpgGrab;
   String Name;
   int netProvider;
   boolean PreloadCard;
   int Priority;
   String RecordingFolder;
   int RecordingFormat;
   boolean supportSubChannels;
   String TimeShiftFolder;

   @Override
   public String toString() {
      if (Name != null) {
         return Name;
      } else
         return "[Unknown Card]";
   }

   public boolean isCAM() {
      return CAM;
   }

   public void setCAM(boolean cAM) {
      CAM = cAM;
   }

   public int getCamType() {
      return CamType;
   }

   public void setCamType(int camType) {
      CamType = camType;
   }

   public int getDecryptLimit() {
      return DecryptLimit;
   }

   public void setDecryptLimit(int decryptLimit) {
      DecryptLimit = decryptLimit;
   }

   public String getDevicePath() {
      return DevicePath;
   }

   public void setDevicePath(String devicePath) {
      DevicePath = devicePath;
   }

   public boolean isEnabled() {
      return Enabled;
   }

   public void setEnabled(boolean enabled) {
      Enabled = enabled;
   }

   public boolean isGrabEPG() {
      return GrabEPG;
   }

   public void setGrabEPG(boolean grabEPG) {
      GrabEPG = grabEPG;
   }

   public int getIdCard() {
      return IdCard;
   }

   public void setIdCard(int idCard) {
      IdCard = idCard;
   }

   public int getIdServer() {
      return IdServer;
   }

   public void setIdServer(int idServer) {
      IdServer = idServer;
   }

   public boolean isIsChanged() {
      return IsChanged;
   }

   public void setIsChanged(boolean isChanged) {
      IsChanged = isChanged;
   }

   public Date getLastEpgGrab() {
      return LastEpgGrab;
   }

   public void setLastEpgGrab(Date lastEpgGrab) {
      LastEpgGrab = lastEpgGrab;
   }

   public String getName() {
      return Name;
   }

   public void setName(String name) {
      Name = name;
   }

   public int getNetProvider() {
      return netProvider;
   }

   public void setNetProvider(int netProvider) {
      this.netProvider = netProvider;
   }

   public boolean isPreloadCard() {
      return PreloadCard;
   }

   public void setPreloadCard(boolean preloadCard) {
      PreloadCard = preloadCard;
   }

   public int getPriority() {
      return Priority;
   }

   public void setPriority(int priority) {
      Priority = priority;
   }

   public String getRecordingFolder() {
      return RecordingFolder;
   }

   public void setRecordingFolder(String recordingFolder) {
      RecordingFolder = recordingFolder;
   }

   public int getRecordingFormat() {
      return RecordingFormat;
   }

   public void setRecordingFormat(int recordingFormat) {
      RecordingFormat = recordingFormat;
   }

   public boolean isSupportSubChannels() {
      return supportSubChannels;
   }

   public void setSupportSubChannels(boolean supportSubChannels) {
      this.supportSubChannels = supportSubChannels;
   }

   public String getTimeShiftFolder() {
      return TimeShiftFolder;
   }

   public void setTimeShiftFolder(String timeShiftFolder) {
      TimeShiftFolder = timeShiftFolder;
   }

}
