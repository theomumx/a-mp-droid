package com.mediaportal.remote.data;

import java.sql.Date;

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
   public String getDiscId() {
      return DiscId;
   }
   public void setDiscId(String discId) {
      DiscId = discId;
   }
   public String getHash() {
      return Hash;
   }
   public void setHash(String hash) {
      Hash = hash;
   }
   public int getPart() {
      return Part;
   }
   public void setPart(int part) {
      Part = part;
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
   public String getVideoResolution() {
      return VideoResolution;
   }
   public void setVideoResolution(String videoResolution) {
      VideoResolution = videoResolution;
   }
   public String getVideoCodec() {
      return VideoCodec;
   }
   public void setVideoCodec(String videoCodec) {
      VideoCodec = videoCodec;
   }
   public String getAudioCodec() {
      return AudioCodec;
   }
   public void setAudioCodec(String audioCodec) {
      AudioCodec = audioCodec;
   }
   public String getAudioChannels() {
      return AudioChannels;
   }
   public void setAudioChannels(String audioChannels) {
      AudioChannels = audioChannels;
   }
   public boolean isHasSubtitles() {
      return HasSubtitles;
   }
   public void setHasSubtitles(boolean hasSubtitles) {
      HasSubtitles = hasSubtitles;
   }
   public String getVideoFormat() {
      return VideoFormat;
   }
   public void setVideoFormat(String videoFormat) {
      VideoFormat = videoFormat;
   }
   public String getAlternateTitlesString() {
      return AlternateTitlesString;
   }
   public void setAlternateTitlesString(String alternateTitlesString) {
      AlternateTitlesString = alternateTitlesString;
   }
   public String getSortBy() {
      return SortBy;
   }
   public void setSortBy(String sortBy) {
      SortBy = sortBy;
   }
   public String getDirectorsString() {
      return Directors;
   }
   public void setDirectorsString(String directorsString) {
      Directors = directorsString;
   }
   public String getActorsString() {
      return Actors;
   }
   public void setActorsString(String actorsString) {
      Actors = actorsString;
   }
   public String getWritersString() {
      return Writers;
   }
   public void setWritersString(String writersString) {
      Writers = writersString;
   }
   public String getCertification() {
      return Certification;
   }
   public void setCertification(String certification) {
      Certification = certification;
   }
   public String getLanguage() {
      return Language;
   }
   public void setLanguage(String language) {
      Language = language;
   }
   public String getSummary() {
      return Summary;
   }
   public void setSummary(String summary) {
      Summary = summary;
   }
   public double getScore() {
      return Score;
   }
   public void setScore(double score) {
      Score = score;
   }
   public int getPopularity() {
      return Popularity;
   }
   public void setPopularity(int popularity) {
      Popularity = popularity;
   }
   public Date getDateAdded() {
      return DateAdded;
   }
   public void setDateAdded(Date dateAdded) {
      DateAdded = dateAdded;
   }
   public int getRuntime() {
      return Runtime;
   }
   public void setRuntime(int runtime) {
      Runtime = runtime;
   }
   public String getImdbId() {
      return ImdbId;
   }
   public void setImdbId(String imdbId) {
      ImdbId = imdbId;
   }
   public String getCoverPathAlternate() {
      return CoverPathAlternate;
   }
   public void setCoverPathAlternate(String coverPathAlternate) {
      CoverPathAlternate = coverPathAlternate;
   }
   public String getCoverPath() {
      return CoverPath;
   }
   public void setCoverPath(String coverPath) {
      CoverPath = coverPath;
   }
   public String getBackdropPath() {
      return BackdropPath;
   }
   public void setBackdropPath(String backdropPath) {
      BackdropPath = backdropPath;
   }
}
