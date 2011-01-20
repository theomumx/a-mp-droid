package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

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
   boolean SupportSubChannels;
   String TimeShiftFolder;

   @Override
   public String toString() {
      if (Name != null) {
         return Name;
      } else
         return "[Unknown Card]";
   }

   @JsonProperty("CAM")
   public boolean isCAM() {
      return CAM;
   }

   @JsonProperty("CAM")
   public void setCAM(boolean cAM) {
      CAM = cAM;
   }

   @JsonProperty("CamType")
   public int getCamType() {
      return CamType;
   }
   
   @JsonProperty("CamType")
   public void setCamType(int camType) {
      CamType = camType;
   }

   @JsonProperty("DecryptLimit")
   public int getDecryptLimit() {
      return DecryptLimit;
   }
   
   @JsonProperty("DecryptLimit")
   public void setDecryptLimit(int decryptLimit) {
      DecryptLimit = decryptLimit;
   }

   @JsonProperty("DevicePath")
   public String getDevicePath() {
      return DevicePath;
   }
   
   @JsonProperty("DevicePath")
   public void setDevicePath(String devicePath) {
      DevicePath = devicePath;
   }

   @JsonProperty("Enabled")
   public boolean isEnabled() {
      return Enabled;
   }
   
   @JsonProperty("Enabled")
   public void setEnabled(boolean enabled) {
      Enabled = enabled;
   }

   @JsonProperty("GrabEPG")
   public boolean isGrabEPG() {
      return GrabEPG;
   }
   
   @JsonProperty("GrabEPG")
   public void setGrabEPG(boolean grabEPG) {
      GrabEPG = grabEPG;
   }

   @JsonProperty("IdCard")
   public int getIdCard() {
      return IdCard;
   }
   
   @JsonProperty("IdCard")
   public void setIdCard(int idCard) {
      IdCard = idCard;
   }

   @JsonProperty("IdServer")
   public int getIdServer() {
      return IdServer;
   }
   
   @JsonProperty("IdServer")
   public void setIdServer(int idServer) {
      IdServer = idServer;
   }

   @JsonProperty("IsChanged")
   public boolean isIsChanged() {
      return IsChanged;
   }
   
   @JsonProperty("IsChanged")
   public void setIsChanged(boolean isChanged) {
      IsChanged = isChanged;
   }

   @JsonProperty("LastEpgGrab")
   public Date getLastEpgGrab() {
      return LastEpgGrab;
   }
   
   @JsonProperty("LastEpgGrab")
   public void setLastEpgGrab(Date lastEpgGrab) {
      LastEpgGrab = lastEpgGrab;
   }

   @JsonProperty("Name")
   public String getName() {
      return Name;
   }
   
   @JsonProperty("Name")
   public void setName(String name) {
      Name = name;
   }

   @JsonProperty("CardName")
   public int getNetProvider() {
      return netProvider;
   }
   
   @JsonProperty("CardName")
   public void setNetProvider(int netProvider) {
      this.netProvider = netProvider;
   }

   @JsonProperty("PreloadCard")
   public boolean isPreloadCard() {
      return PreloadCard;
   }
   
   @JsonProperty("PreloadCard")
   public void setPreloadCard(boolean preloadCard) {
      PreloadCard = preloadCard;
   }

   @JsonProperty("Priority")
   public int getPriority() {
      return Priority;
   }
   
   @JsonProperty("Priority")
   public void setPriority(int priority) {
      Priority = priority;
   }

   @JsonProperty("RecordingFolder")
   public String getRecordingFolder() {
      return RecordingFolder;
   }
   
   @JsonProperty("RecordingFolder")
   public void setRecordingFolder(String recordingFolder) {
      RecordingFolder = recordingFolder;
   }

   @JsonProperty("RecordingFormat")
   public int getRecordingFormat() {
      return RecordingFormat;
   }
   
   @JsonProperty("RecordingFormat")
   public void setRecordingFormat(int recordingFormat) {
      RecordingFormat = recordingFormat;
   }
   
   @JsonProperty("SupportSubChannels")
   public boolean isSupportSubChannels() {
      return SupportSubChannels;
   }
   
   @JsonProperty("SupportSubChannels")
   public void setSupportSubChannels(boolean supportSubChannels) {
      this.SupportSubChannels = supportSubChannels;
   }

   @JsonProperty("TimeShiftFolder")
   public String getTimeShiftFolder() {
      return TimeShiftFolder;
   }
   
   @JsonProperty("TimeShiftFolder")
   public void setTimeShiftFolder(String timeShiftFolder) {
      TimeShiftFolder = timeShiftFolder;
   }

}
