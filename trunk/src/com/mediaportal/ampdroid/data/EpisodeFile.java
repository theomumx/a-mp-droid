package com.mediaportal.remote.data;

public class EpisodeFile {
   private String FileName;//EpisodeFileName
   private int EpisodeIndex;//EpisodeIndex
   private int SeasonIndex;//SeasonIndex
   private boolean IsAvailable;//IsAvailable
   private boolean IsRemovable;//Removable
   private int Duration;//localPlaytime
   private int VideoWidth;//videoWidth
   private int VideoHeight;//videoHeight

   private String VideoCodec;//VideoCodec
   private int VideoBitrate;//VideoBitrate
   private float VideoFrameRate;//VideoFrameRate
   private String AudioCodec;//AudioCodec
   private int AudioBitrate;//AudioBitrate
   private int AudioChannels;//AudioChannels
   private int AudioTracks;//AudioTracks
   private boolean HasSubtitles; //AvailableSubtitles
   
   
   public String getFileName() {
      return FileName;
   }
   public void setFileName(String fileName) {
      FileName = fileName;
   }
   public int getEpisodeIndex() {
      return EpisodeIndex;
   }
   public void setEpisodeIndex(int episodeIndex) {
      EpisodeIndex = episodeIndex;
   }
   public int getSeasonIndex() {
      return SeasonIndex;
   }
   public void setSeasonIndex(int seasonIndex) {
      SeasonIndex = seasonIndex;
   }
   public boolean isIsAvailable() {
      return IsAvailable;
   }
   public void setIsAvailable(boolean isAvailable) {
      IsAvailable = isAvailable;
   }
   public boolean isIsRemovable() {
      return IsRemovable;
   }
   public void setIsRemovable(boolean isRemovable) {
      IsRemovable = isRemovable;
   }
   public int getDuration() {
      return Duration;
   }
   public void setDuration(int duration) {
      Duration = duration;
   }
   public int getVideoWidth() {
      return VideoWidth;
   }
   public void setVideoWidth(int videoWidth) {
      VideoWidth = videoWidth;
   }
   public int getVideoHeight() {
      return VideoHeight;
   }
   public void setVideoHeight(int videoHeight) {
      VideoHeight = videoHeight;
   }
   public String getVideoCodec() {
      return VideoCodec;
   }
   public void setVideoCodec(String videoCodec) {
      VideoCodec = videoCodec;
   }
   public int getVideoBitrate() {
      return VideoBitrate;
   }
   public void setVideoBitrate(int videoBitrate) {
      VideoBitrate = videoBitrate;
   }
   public float getVideoFrameRate() {
      return VideoFrameRate;
   }
   public void setVideoFrameRate(float videoFrameRate) {
      VideoFrameRate = videoFrameRate;
   }
   public String getAudioCodec() {
      return AudioCodec;
   }
   public void setAudioCodec(String audioCodec) {
      AudioCodec = audioCodec;
   }
   public int getAudioBitrate() {
      return AudioBitrate;
   }
   public void setAudioBitrate(int audioBitrate) {
      AudioBitrate = audioBitrate;
   }
   public int getAudioChannels() {
      return AudioChannels;
   }
   public void setAudioChannels(int audioChannels) {
      AudioChannels = audioChannels;
   }
   public int getAudioTracks() {
      return AudioTracks;
   }
   public void setAudioTracks(int audioTracks) {
      AudioTracks = audioTracks;
   }
   public boolean isHasSubtitles() {
      return HasSubtitles;
   }
   public void setHasSubtitles(boolean hasSubtitles) {
      HasSubtitles = hasSubtitles;
   }
   
   
}
