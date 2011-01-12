package com.mediaportal.ampdroid.data;

import java.util.Date;

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

   public int getBitRateMode() {
      return BitRateMode;
   }

   public void setBitRateMode(int bitRateMode) {
      BitRateMode = bitRateMode;
   }

   public String getChannelName() {
      return ChannelName;
   }

   public void setChannelName(String channelName) {
      ChannelName = channelName;
   }

   public String getDevice() {
      return Device;
   }

   public void setDevice(String device) {
      Device = device;
   }

   public boolean isEnabled() {
      return Enabled;
   }

   public void setEnabled(boolean enabled) {
      Enabled = enabled;
   }

   public int getGetTimeshiftStoppedReason() {
      return GetTimeshiftStoppedReason;
   }

   public void setGetTimeshiftStoppedReason(int getTimeshiftStoppedReason) {
      GetTimeshiftStoppedReason = getTimeshiftStoppedReason;
   }

   public boolean isGrabTeletext() {
      return GrabTeletext;
   }

   public void setGrabTeletext(boolean grabTeletext) {
      GrabTeletext = grabTeletext;
   }

   public boolean isHasTeletext() {
      return HasTeletext;
   }

   public void setHasTeletext(boolean hasTeletext) {
      HasTeletext = hasTeletext;
   }

   public int getId() {
      return Id;
   }

   public void setId(int id) {
      Id = id;
   }

   public int getIdChannel() {
      return IdChannel;
   }

   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }

   public boolean isIsGrabbingEpg() {
      return IsGrabbingEpg;
   }

   public void setIsGrabbingEpg(boolean isGrabbingEpg) {
      IsGrabbingEpg = isGrabbingEpg;
   }

   public boolean isIsRecording() {
      return IsRecording;
   }

   public void setIsRecording(boolean isRecording) {
      IsRecording = isRecording;
   }

   public boolean isIsScanning() {
      return IsScanning;
   }

   public void setIsScanning(boolean isScanning) {
      IsScanning = isScanning;
   }

   public boolean isIsScrambled() {
      return IsScrambled;
   }

   public void setIsScrambled(boolean isScrambled) {
      IsScrambled = isScrambled;
   }

   public boolean isIsTimeShifting() {
      return IsTimeShifting;
   }

   public void setIsTimeShifting(boolean isTimeShifting) {
      IsTimeShifting = isTimeShifting;
   }

   public boolean isIsTunerLocked() {
      return IsTunerLocked;
   }

   public void setIsTunerLocked(boolean isTunerLocked) {
      IsTunerLocked = isTunerLocked;
   }

   public int getMaxChannel() {
      return MaxChannel;
   }

   public void setMaxChannel(int maxChannel) {
      MaxChannel = maxChannel;
   }

   public int getMinChannel() {
      return MinChannel;
   }

   public void setMinChannel(int minChannel) {
      MinChannel = minChannel;
   }

   public String getName() {
      return Name;
   }

   public void setName(String name) {
      Name = name;
   }

   public int getQualityType() {
      return QualityType;
   }

   public void setQualityType(int qualityType) {
      QualityType = qualityType;
   }

   public String getRecordingFileName() {
      return RecordingFileName;
   }

   public void setRecordingFileName(String recordingFileName) {
      RecordingFileName = recordingFileName;
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

   public int getRecordingScheduleId() {
      return RecordingScheduleId;
   }

   public void setRecordingScheduleId(int recordingScheduleId) {
      RecordingScheduleId = recordingScheduleId;
   }

   public Date getRecordingStarted() {
      return RecordingStarted;
   }

   public void setRecordingStarted(Date recordingStarted) {
      RecordingStarted = recordingStarted;
   }

   public String getRemoteServer() {
      return RemoteServer;
   }

   public void setRemoteServer(String remoteServer) {
      RemoteServer = remoteServer;
   }

   public String getRTSPUrl() {
      return RTSPUrl;
   }

   public void setRTSPUrl(String rTSPUrl) {
      RTSPUrl = rTSPUrl;
   }

   public int getSignalLevel() {
      return SignalLevel;
   }

   public void setSignalLevel(int signalLevel) {
      SignalLevel = signalLevel;
   }

   public int getSignalQuality() {
      return SignalQuality;
   }

   public void setSignalQuality(int signalQuality) {
      SignalQuality = signalQuality;
   }

   public String getTimeShiftFileName() {
      return TimeShiftFileName;
   }

   public void setTimeShiftFileName(String timeShiftFileName) {
      TimeShiftFileName = timeShiftFileName;
   }

   public String getTimeshiftFolder() {
      return TimeshiftFolder;
   }

   public void setTimeshiftFolder(String timeshiftFolder) {
      TimeshiftFolder = timeshiftFolder;
   }

   public Date getTimeShiftStarted() {
      return TimeShiftStarted;
   }

   public void setTimeShiftStarted(Date timeShiftStarted) {
      TimeShiftStarted = timeShiftStarted;
   }

   public int getType() {
      return Type;
   }

   public void setType(int type) {
      Type = type;
   }

   public TvUser getUser() {
      return User;
   }

   public void setUser(TvUser user) {
      User = user;
   }
}
