package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

public class EpisodeFile {
   private String FileName;// EpisodeFileName
   private int EpisodeIndex;// EpisodeIndex
   private int SeasonIndex;// SeasonIndex
   private boolean IsAvailable;// IsAvailable
   private boolean IsRemovable;// Removable
   private int Duration;// localPlaytime
   private int VideoWidth;// videoWidth
   private int VideoHeight;// videoHeight

   private String VideoCodec;// VideoCodec
   private int VideoBitrate;// VideoBitrate
   private float VideoFrameRate;// VideoFrameRate
   private String AudioCodec;// AudioCodec
   private int AudioBitrate;// AudioBitrate
   private int AudioChannels;// AudioChannels
   private int AudioTracks;// AudioTracks
   private boolean HasSubtitles; // AvailableSubtitles

   @JsonProperty("FileName")
   public String getFileName() {
      return FileName;
   }

   @JsonProperty("FileName")
   public void setFileName(String fileName) {
      FileName = fileName;
   }

   @JsonProperty("EpisodeIndex")
   public int getEpisodeIndex() {
      return EpisodeIndex;
   }

   @JsonProperty("EpisodeIndex")
   public void setEpisodeIndex(int episodeIndex) {
      EpisodeIndex = episodeIndex;
   }

   @JsonProperty("SeasonIndex")
   public int getSeasonIndex() {
      return SeasonIndex;
   }

   @JsonProperty("SeasonIndex")
   public void setSeasonIndex(int seasonIndex) {
      SeasonIndex = seasonIndex;
   }

   @JsonProperty("IsAvailable")
   public boolean isIsAvailable() {
      return IsAvailable;
   }

   @JsonProperty("IsAvailable")
   public void setIsAvailable(boolean isAvailable) {
      IsAvailable = isAvailable;
   }

   @JsonProperty("IsRemovable")
   public boolean isIsRemovable() {
      return IsRemovable;
   }

   @JsonProperty("IsRemovable")
   public void setIsRemovable(boolean isRemovable) {
      IsRemovable = isRemovable;
   }

   @JsonProperty("Duration")
   public int getDuration() {
      return Duration;
   }

   @JsonProperty("Duration")
   public void setDuration(int duration) {
      Duration = duration;
   }

   @JsonProperty("VideoWidth")
   public int getVideoWidth() {
      return VideoWidth;
   }

   @JsonProperty("VideoWidth")
   public void setVideoWidth(int videoWidth) {
      VideoWidth = videoWidth;
   }

   @JsonProperty("VideoHeight")
   public int getVideoHeight() {
      return VideoHeight;
   }

   @JsonProperty("VideoHeight")
   public void setVideoHeight(int videoHeight) {
      VideoHeight = videoHeight;
   }

   @JsonProperty("VideoCodec")
   public String getVideoCodec() {
      return VideoCodec;
   }

   @JsonProperty("VideoCodec")
   public void setVideoCodec(String videoCodec) {
      VideoCodec = videoCodec;
   }

   @JsonProperty("VideoBitrate")
   public int getVideoBitrate() {
      return VideoBitrate;
   }

   @JsonProperty("VideoBitrate")
   public void setVideoBitrate(int videoBitrate) {
      VideoBitrate = videoBitrate;
   }

   @JsonProperty("VideoFrameRate")
   public float getVideoFrameRate() {
      return VideoFrameRate;
   }

   @JsonProperty("VideoFrameRate")
   public void setVideoFrameRate(float videoFrameRate) {
      VideoFrameRate = videoFrameRate;
   }

   @JsonProperty("AudioCodec")
   public String getAudioCodec() {
      return AudioCodec;
   }

   @JsonProperty("AudioCodec")
   public void setAudioCodec(String audioCodec) {
      AudioCodec = audioCodec;
   }

   @JsonProperty("AudioBitrate")
   public int getAudioBitrate() {
      return AudioBitrate;
   }

   @JsonProperty("AudioBitrate")
   public void setAudioBitrate(int audioBitrate) {
      AudioBitrate = audioBitrate;
   }

   @JsonProperty("AudioChannels")
   public int getAudioChannels() {
      return AudioChannels;
   }

   @JsonProperty("AudioChannels")
   public void setAudioChannels(int audioChannels) {
      AudioChannels = audioChannels;
   }

   @JsonProperty("AudioTracks")
   public int getAudioTracks() {
      return AudioTracks;
   }

   @JsonProperty("AudioTracks")
   public void setAudioTracks(int audioTracks) {
      AudioTracks = audioTracks;
   }

   @JsonProperty("HasSubtitles")
   public boolean isHasSubtitles() {
      return HasSubtitles;
   }

   @JsonProperty("HasSubtitles")
   public void setHasSubtitles(boolean hasSubtitles) {
      HasSubtitles = hasSubtitles;
   }

}
