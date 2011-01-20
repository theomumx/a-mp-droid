package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class EpisodeDetails extends SeriesEpisode {

   // Div Info
   private String Summary; // Summary
   private String GuestStarsString; // GuestStars
   private String[] GuestStars;
   private String DirectorsString; // Director
   private String[] Directors;
   private String WritersString; // Writer
   private String[] Writers;
   private Date LastUpdated; // lastupdated
   private String ImdbId;// IMDB_ID
   private String ProductionCode;// ProductionCode

   // Combined
   private int CombinedEpisodeNumber; // Combined_episodenumber
   private int CombinedSeasonNumber; // Combined_season

   // DVD
   private int DvdChapter; // DVD_chapter
   private int DvdDiscid; // DVD_discid
   private int DvdEpisodenumber; // DVD_episodenumber
   private int DvdSeason; // DVD_season

   // absolute ordering
   private int AbsoluteEpisodeNumber;// absolute_number

   // Specials
   private int AirsAfterSeason;// airsafter_season
   private int AirsBeforeEpisode;// airsbefore_episode
   private int AirsBeforeSesaon;// airsbefore_season

   // Physical files
   private EpisodeFile EpisodeFile;// The physical file on disk
   private EpisodeFile EpisodeFile2;// Episode consists of a second file

   @JsonProperty("Summary")
   public String getSummary() {
      return Summary;
   }

   @JsonProperty("Summary")
   public void setSummary(String summary) {
      Summary = summary;
   }

   @JsonProperty("GuestStarsString")
   public String getGuestStarsString() {
      return GuestStarsString;
   }

   @JsonProperty("GuestStarsString")
   public void setGuestStarsString(String guestStarsString) {
      GuestStarsString = guestStarsString;
   }

   @JsonProperty("GuestStars")
   public String[] getGuestStars() {
      return GuestStars;
   }

   @JsonProperty("GuestStars")
   public void setGuestStars(String[] guestStars) {
      GuestStars = guestStars;
   }

   @JsonProperty("DirectorsString")
   public String getDirectorsString() {
      return DirectorsString;
   }

   @JsonProperty("DirectorsString")
   public void setDirectorsString(String directorsString) {
      DirectorsString = directorsString;
   }

   @JsonProperty("Directors")
   public String[] getDirectors() {
      return Directors;
   }

   @JsonProperty("Directors")
   public void setDirectors(String[] directors) {
      Directors = directors;
   }

   @JsonProperty("WritersString")
   public String getWritersString() {
      return WritersString;
   }

   @JsonProperty("WritersString")
   public void setWritersString(String writersString) {
      WritersString = writersString;
   }

   @JsonProperty("Writers")
   public String[] getWriters() {
      return Writers;
   }

   @JsonProperty("Writers")
   public void setWriters(String[] writers) {
      Writers = writers;
   }

   @JsonProperty("LastUpdated")
   public Date getLastUpdated() {
      return LastUpdated;
   }

   @JsonProperty("LastUpdated")
   public void setLastUpdated(Date lastUpdated) {
      LastUpdated = lastUpdated;
   }

   @JsonProperty("ImdbId")
   public String getImdbId() {
      return ImdbId;
   }

   @JsonProperty("ImdbId")
   public void setImdbId(String imdbId) {
      ImdbId = imdbId;
   }

   @JsonProperty("ProductionCode")
   public String getProductionCode() {
      return ProductionCode;
   }

   @JsonProperty("ProductionCode")
   public void setProductionCode(String productionCode) {
      ProductionCode = productionCode;
   }

   @JsonProperty("CombinedEpisodeNumber")
   public int getCombinedEpisodeNumber() {
      return CombinedEpisodeNumber;
   }

   @JsonProperty("CombinedEpisodeNumber")
   public void setCombinedEpisodeNumber(int combinedEpisodeNumber) {
      CombinedEpisodeNumber = combinedEpisodeNumber;
   }

   @JsonProperty("CombinedSeasonNumber")
   public int getCombinedSeasonNumber() {
      return CombinedSeasonNumber;
   }

   @JsonProperty("CombinedSeasonNumber")
   public void setCombinedSeasonNumber(int combinedSeasonNumber) {
      CombinedSeasonNumber = combinedSeasonNumber;
   }

   @JsonProperty("DvdChapter")
   public int getDvdChapter() {
      return DvdChapter;
   }

   @JsonProperty("DvdChapter")
   public void setDvdChapter(int dvdChapter) {
      DvdChapter = dvdChapter;
   }

   @JsonProperty("DvdDiscid")
   public int getDvdDiscid() {
      return DvdDiscid;
   }

   @JsonProperty("DvdDiscid")
   public void setDvdDiscid(int dvdDiscid) {
      DvdDiscid = dvdDiscid;
   }

   @JsonProperty("DvdEpisodenumber")
   public int getDvdEpisodenumber() {
      return DvdEpisodenumber;
   }

   @JsonProperty("DvdEpisodenumber")
   public void setDvdEpisodenumber(int dvdEpisodenumber) {
      DvdEpisodenumber = dvdEpisodenumber;
   }

   @JsonProperty("DvdSeason")
   public int getDvdSeason() {
      return DvdSeason;
   }

   @JsonProperty("DvdSeason")
   public void setDvdSeason(int dvdSeason) {
      DvdSeason = dvdSeason;
   }

   @JsonProperty("AbsoluteEpisodeNumber")
   public int getAbsoluteEpisodeNumber() {
      return AbsoluteEpisodeNumber;
   }

   @JsonProperty("AbsoluteEpisodeNumber")
   public void setAbsoluteEpisodeNumber(int absoluteEpisodeNumber) {
      AbsoluteEpisodeNumber = absoluteEpisodeNumber;
   }

   @JsonProperty("AirsAfterSeason")
   public int getAirsAfterSeason() {
      return AirsAfterSeason;
   }

   @JsonProperty("AirsAfterSeason")
   public void setAirsAfterSeason(int airsAfterSeason) {
      AirsAfterSeason = airsAfterSeason;
   }

   @JsonProperty("AirsBeforeEpisode")
   public int getAirsBeforeEpisode() {
      return AirsBeforeEpisode;
   }

   @JsonProperty("AirsBeforeEpisode")
   public void setAirsBeforeEpisode(int airsBeforeEpisode) {
      AirsBeforeEpisode = airsBeforeEpisode;
   }

   @JsonProperty("AirsBeforeSesaon")
   public int getAirsBeforeSesaon() {
      return AirsBeforeSesaon;
   }

   @JsonProperty("AirsBeforeSesaon")
   public void setAirsBeforeSesaon(int airsBeforeSesaon) {
      AirsBeforeSesaon = airsBeforeSesaon;
   }

   @JsonProperty("EpisodeFile")
   public EpisodeFile getEpisodeFile() {
      return EpisodeFile;
   }

   @JsonProperty("EpisodeFile")
   public void setEpisodeFile(EpisodeFile episodeFile) {
      EpisodeFile = episodeFile;
   }

   @JsonProperty("EpisodeFile2")
   public EpisodeFile getEpisodeFile2() {
      return EpisodeFile2;
   }

   @JsonProperty("EpisodeFile2")
   public void setEpisodeFile2(EpisodeFile episodeFile2) {
      EpisodeFile2 = episodeFile2;
   }

}
