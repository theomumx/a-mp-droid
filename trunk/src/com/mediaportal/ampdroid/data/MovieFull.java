package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class MovieFull extends Movie {
   private String DiscId;
   private String Hash;
   private int Part;
   private int Duration;
   private int VideoWidth;
   private int VideoHeight;
   private String VideoResolution;
   private String VideoCodec;
   private String AudioCodec;
   private String AudioChannels;
   private boolean HasSubtitles;
   private String VideoFormat;
   private String AlternateTitlesString;
   private String SortBy;
   private String Directors;
   private String Actors;
   private String Writers;
   private String Certification;
   private String Language;
   private String Summary;
   private double Score;
   private int Popularity;
   private Date DateAdded;
   private int Runtime;
   private String ImdbId;
   private String CoverPathAlternate;
   private String CoverPath;
   private String BackdropPath;

   @JsonProperty("DiscId")
   public String getDiscId() {
      return DiscId;
   }

   @JsonProperty("DiscId")
   public void setDiscId(String discId) {
      DiscId = discId;
   }

   @JsonProperty("Hash")
   public String getHash() {
      return Hash;
   }

   @JsonProperty("Hash")
   public void setHash(String hash) {
      Hash = hash;
   }

   @JsonProperty("Part")
   public int getPart() {
      return Part;
   }

   @JsonProperty("Part")
   public void setPart(int part) {
      Part = part;
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

   @JsonProperty("VideoResolution")
   public String getVideoResolution() {
      return VideoResolution;
   }

   @JsonProperty("VideoResolution")
   public void setVideoResolution(String videoResolution) {
      VideoResolution = videoResolution;
   }

   @JsonProperty("VideoCodec")
   public String getVideoCodec() {
      return VideoCodec;
   }

   @JsonProperty("VideoCodec")
   public void setVideoCodec(String videoCodec) {
      VideoCodec = videoCodec;
   }

   @JsonProperty("AudioCodec")
   public String getAudioCodec() {
      return AudioCodec;
   }

   @JsonProperty("AudioCodec")
   public void setAudioCodec(String audioCodec) {
      AudioCodec = audioCodec;
   }

   @JsonProperty("AudioChannels")
   public String getAudioChannels() {
      return AudioChannels;
   }

   @JsonProperty("AudioChannels")
   public void setAudioChannels(String audioChannels) {
      AudioChannels = audioChannels;
   }

   @JsonProperty("HasSubtitles")
   public boolean isHasSubtitles() {
      return HasSubtitles;
   }

   @JsonProperty("HasSubtitles")
   public void setHasSubtitles(boolean hasSubtitles) {
      HasSubtitles = hasSubtitles;
   }

   @JsonProperty("VideoFormat")
   public String getVideoFormat() {
      return VideoFormat;
   }

   @JsonProperty("VideoFormat")
   public void setVideoFormat(String videoFormat) {
      VideoFormat = videoFormat;
   }

   @JsonProperty("AlternateTitlesString")
   public String getAlternateTitlesString() {
      return AlternateTitlesString;
   }

   @JsonProperty("AlternateTitlesString")
   public void setAlternateTitlesString(String alternateTitlesString) {
      AlternateTitlesString = alternateTitlesString;
   }

   @JsonProperty("SortBy")
   public String getSortBy() {
      return SortBy;
   }

   @JsonProperty("SortBy")
   public void setSortBy(String sortBy) {
      SortBy = sortBy;
   }

   @JsonProperty("Directors")
   public String getDirectorsString() {
      return Directors;
   }

   @JsonProperty("Directors")
   public void setDirectorsString(String directorsString) {
      Directors = directorsString;
   }

   @JsonProperty("Actors")
   public String getActorsString() {
      return Actors;
   }

   @JsonProperty("Actors")
   public void setActorsString(String actorsString) {
      Actors = actorsString;
   }

   @JsonProperty("Writers")
   public String getWritersString() {
      return Writers;
   }

   @JsonProperty("Writers")
   public void setWritersString(String writersString) {
      Writers = writersString;
   }

   @JsonProperty("Certification")
   public String getCertification() {
      return Certification;
   }

   @JsonProperty("Certification")
   public void setCertification(String certification) {
      Certification = certification;
   }

   @JsonProperty("Language")
   public String getLanguage() {
      return Language;
   }

   @JsonProperty("Language")
   public void setLanguage(String language) {
      Language = language;
   }

   @JsonProperty("Summary")
   public String getSummary() {
      return Summary;
   }

   @JsonProperty("Summary")
   public void setSummary(String summary) {
      Summary = summary;
   }

   @JsonProperty("Score")
   public double getScore() {
      return Score;
   }

   @JsonProperty("Score")
   public void setScore(double score) {
      Score = score;
   }

   @JsonProperty("Popularity")
   public int getPopularity() {
      return Popularity;
   }

   @JsonProperty("Popularity")
   public void setPopularity(int popularity) {
      Popularity = popularity;
   }

   @JsonProperty("DateAdded")
   public Date getDateAdded() {
      return DateAdded;
   }

   @JsonProperty("DateAdded")
   public void setDateAdded(Date dateAdded) {
      DateAdded = dateAdded;
   }

   @JsonProperty("Runtime")
   public int getRuntime() {
      return Runtime;
   }

   @JsonProperty("Runtime")
   public void setRuntime(int runtime) {
      Runtime = runtime;
   }

   @JsonProperty("ImdbId")
   public String getImdbId() {
      return ImdbId;
   }

   @JsonProperty("ImdbId")
   public void setImdbId(String imdbId) {
      ImdbId = imdbId;
   }

   @JsonProperty("CoverPathAlternate")
   public String getCoverPathAlternate() {
      return CoverPathAlternate;
   }

   @JsonProperty("CoverPathAlternate")
   public void setCoverPathAlternate(String coverPathAlternate) {
      CoverPathAlternate = coverPathAlternate;
   }

   @JsonProperty("CoverPath")
   public String getCoverPath() {
      return CoverPath;
   }

   @JsonProperty("CoverPath")
   public void setCoverPath(String coverPath) {
      CoverPath = coverPath;
   }

   @JsonProperty("BackdropPath")
   public String getBackdropPath() {
      return BackdropPath;
   }

   @JsonProperty("BackdropPath")
   public void setBackdropPath(String backdropPath) {
      BackdropPath = backdropPath;
   }
}
