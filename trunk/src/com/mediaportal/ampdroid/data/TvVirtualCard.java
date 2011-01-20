package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class TvVirtualCard {
   int BitRateMode;
   String ChannelName;
   String Device;
   boolean Enabled;
   int GetTimeshiftStoppedReason;
   boolean GrabTeletext;
   boolean HasTeletext;
   int Id;
   int IdChannel;
   boolean IsGrabbingEpg;
   boolean IsRecording;
   boolean IsScanning;
   boolean IsScrambled;
   boolean IsTimeShifting;
   boolean IsTunerLocked;
   int MaxChannel;
   int MinChannel;
   String Name;
   int QualityType;
   String RecordingFileName;
   String RecordingFolder;
   int RecordingFormat;
   int RecordingScheduleId;
   Date RecordingStarted;
   String RemoteServer;
   String RTSPUrl;
   int SignalLevel;
   int SignalQuality;
   String TimeShiftFileName;
   String TimeshiftFolder;
   Date TimeShiftStarted;
   int Type;

   TvUser User;

   @Override
   public String toString() {
      if (ChannelName != null) {
         if (User != null) {
            return ChannelName + " - " + User.Name;
         } else {
            return ChannelName;
         }
      } else if (Name != null) {
         return Name;
      } else
         return "[Unknown Card]";
   }

   @JsonProperty("BitRateMode")
   public int getBitRateMode() {
      return BitRateMode;
   }

   @JsonProperty("BitRateMode")
   public void setBitRateMode(int bitRateMode) {
      BitRateMode = bitRateMode;
   }

   @JsonProperty("ChannelName")
   public String getChannelName() {
      return ChannelName;
   }

   @JsonProperty("ChannelName")
   public void setChannelName(String channelName) {
      ChannelName = channelName;
   }

   @JsonProperty("Device")
   public String getDevice() {
      return Device;
   }

   @JsonProperty("Device")
   public void setDevice(String device) {
      Device = device;
   }

   @JsonProperty("Enabled")
   public boolean isEnabled() {
      return Enabled;
   }

   @JsonProperty("Enabled")
   public void setEnabled(boolean enabled) {
      Enabled = enabled;
   }

   @JsonProperty("GetTimeshiftStoppedReason")
   public int getGetTimeshiftStoppedReason() {
      return GetTimeshiftStoppedReason;
   }

   @JsonProperty("GetTimeshiftStoppedReason")
   public void setGetTimeshiftStoppedReason(int getTimeshiftStoppedReason) {
      GetTimeshiftStoppedReason = getTimeshiftStoppedReason;
   }

   @JsonProperty("GrabTeletext")
   public boolean isGrabTeletext() {
      return GrabTeletext;
   }

   @JsonProperty("GrabTeletext")
   public void setGrabTeletext(boolean grabTeletext) {
      GrabTeletext = grabTeletext;
   }

   @JsonProperty("HasTeletext")
   public boolean isHasTeletext() {
      return HasTeletext;
   }

   @JsonProperty("HasTeletext")
   public void setHasTeletext(boolean hasTeletext) {
      HasTeletext = hasTeletext;
   }

   @JsonProperty("Id")
   public int getId() {
      return Id;
   }

   @JsonProperty("Id")
   public void setId(int id) {
      Id = id;
   }

   @JsonProperty("IdChannel")
   public int getIdChannel() {
      return IdChannel;
   }

   @JsonProperty("IdChannel")
   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }

   @JsonProperty("IsGrabbingEpg")
   public boolean isIsGrabbingEpg() {
      return IsGrabbingEpg;
   }

   @JsonProperty("IsGrabbingEpg")
   public void setIsGrabbingEpg(boolean isGrabbingEpg) {
      IsGrabbingEpg = isGrabbingEpg;
   }

   @JsonProperty("IsRecording")
   public boolean isIsRecording() {
      return IsRecording;
   }

   @JsonProperty("IsRecording")
   public void setIsRecording(boolean isRecording) {
      IsRecording = isRecording;
   }

   @JsonProperty("IsScanning")
   public boolean isIsScanning() {
      return IsScanning;
   }

   @JsonProperty("IsScanning")
   public void setIsScanning(boolean isScanning) {
      IsScanning = isScanning;
   }

   @JsonProperty("IsScrambled")
   public boolean isIsScrambled() {
      return IsScrambled;
   }

   @JsonProperty("IsScrambled")
   public void setIsScrambled(boolean isScrambled) {
      IsScrambled = isScrambled;
   }

   @JsonProperty("IsTimeShifting")
   public boolean isIsTimeShifting() {
      return IsTimeShifting;
   }

   @JsonProperty("IsTimeShifting")
   public void setIsTimeShifting(boolean isTimeShifting) {
      IsTimeShifting = isTimeShifting;
   }

   @JsonProperty("IsTunerLocked")
   public boolean isIsTunerLocked() {
      return IsTunerLocked;
   }

   @JsonProperty("IsTunerLocked")
   public void setIsTunerLocked(boolean isTunerLocked) {
      IsTunerLocked = isTunerLocked;
   }

   @JsonProperty("MaxChannel")
   public int getMaxChannel() {
      return MaxChannel;
   }

   @JsonProperty("MaxChannel")
   public void setMaxChannel(int maxChannel) {
      MaxChannel = maxChannel;
   }

   @JsonProperty("MinChannel")
   public int getMinChannel() {
      return MinChannel;
   }

   @JsonProperty("MinChannel")
   public void setMinChannel(int minChannel) {
      MinChannel = minChannel;
   }

   @JsonProperty("Name")
   public String getName() {
      return Name;
   }

   @JsonProperty("Name")
   public void setName(String name) {
      Name = name;
   }

   @JsonProperty("QualityType")
   public int getQualityType() {
      return QualityType;
   }

   @JsonProperty("QualityType")
   public void setQualityType(int qualityType) {
      QualityType = qualityType;
   }

   @JsonProperty("RecordingFileName")
   public String getRecordingFileName() {
      return RecordingFileName;
   }

   @JsonProperty("RecordingFileName")
   public void setRecordingFileName(String recordingFileName) {
      RecordingFileName = recordingFileName;
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

   @JsonProperty("RecordingScheduleId")
   public int getRecordingScheduleId() {
      return RecordingScheduleId;
   }

   @JsonProperty("RecordingScheduleId")
   public void setRecordingScheduleId(int recordingScheduleId) {
      RecordingScheduleId = recordingScheduleId;
   }

   @JsonProperty("RecordingStarted")
   public Date getRecordingStarted() {
      return RecordingStarted;
   }

   @JsonProperty("RecordingStarted")
   public void setRecordingStarted(Date recordingStarted) {
      RecordingStarted = recordingStarted;
   }

   @JsonProperty("RemoteServer")
   public String getRemoteServer() {
      return RemoteServer;
   }

   @JsonProperty("RemoteServer")
   public void setRemoteServer(String remoteServer) {
      RemoteServer = remoteServer;
   }

   @JsonProperty("RTSPUrl")
   public String getRTSPUrl() {
      return RTSPUrl;
   }

   @JsonProperty("RTSPUrl")
   public void setRTSPUrl(String rTSPUrl) {
      RTSPUrl = rTSPUrl;
   }

   @JsonProperty("SignalLevel")
   public int getSignalLevel() {
      return SignalLevel;
   }

   @JsonProperty("SignalLevel")
   public void setSignalLevel(int signalLevel) {
      SignalLevel = signalLevel;
   }

   @JsonProperty("SignalQuality")
   public int getSignalQuality() {
      return SignalQuality;
   }

   @JsonProperty("SignalQuality")
   public void setSignalQuality(int signalQuality) {
      SignalQuality = signalQuality;
   }

   @JsonProperty("TimeShiftFileName")
   public String getTimeShiftFileName() {
      return TimeShiftFileName;
   }

   @JsonProperty("TimeShiftFileName")
   public void setTimeShiftFileName(String timeShiftFileName) {
      TimeShiftFileName = timeShiftFileName;
   }

   @JsonProperty("TimeshiftFolder")
   public String getTimeshiftFolder() {
      return TimeshiftFolder;
   }

   @JsonProperty("TimeshiftFolder")
   public void setTimeshiftFolder(String timeshiftFolder) {
      TimeshiftFolder = timeshiftFolder;
   }

   @JsonProperty("TimeShiftStarted")
   public Date getTimeShiftStarted() {
      return TimeShiftStarted;
   }

   @JsonProperty("TimeShiftStarted")
   public void setTimeShiftStarted(Date timeShiftStarted) {
      TimeShiftStarted = timeShiftStarted;
   }

   @JsonProperty("Type")
   public int getType() {
      return Type;
   }

   @JsonProperty("Type")
   public void setType(int type) {
      Type = type;
   }

   @JsonProperty("User")
   public TvUser getUser() {
      return User;
   }

   @JsonProperty("User")
   public void setUser(TvUser user) {
      User = user;
   }
}
