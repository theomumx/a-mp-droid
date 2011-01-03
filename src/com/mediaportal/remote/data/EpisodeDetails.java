package com.mediaportal.remote.data;

import java.util.Date;

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
   
   
   
   public String getSummary() {
      return Summary;
   }
   public void setSummary(String summary) {
      Summary = summary;
   }
   public String getGuestStarsString() {
      return GuestStarsString;
   }
   public void setGuestStarsString(String guestStarsString) {
      GuestStarsString = guestStarsString;
   }
   public String[] getGuestStars() {
      return GuestStars;
   }
   public void setGuestStars(String[] guestStars) {
      GuestStars = guestStars;
   }
   public String getDirectorsString() {
      return DirectorsString;
   }
   public void setDirectorsString(String directorsString) {
      DirectorsString = directorsString;
   }
   public String[] getDirectors() {
      return Directors;
   }
   public void setDirectors(String[] directors) {
      Directors = directors;
   }
   public String getWritersString() {
      return WritersString;
   }
   public void setWritersString(String writersString) {
      WritersString = writersString;
   }
   public String[] getWriters() {
      return Writers;
   }
   public void setWriters(String[] writers) {
      Writers = writers;
   }
   public Date getLastUpdated() {
      return LastUpdated;
   }
   public void setLastUpdated(Date lastUpdated) {
      LastUpdated = lastUpdated;
   }
   public String getImdbId() {
      return ImdbId;
   }
   public void setImdbId(String imdbId) {
      ImdbId = imdbId;
   }
   public String getProductionCode() {
      return ProductionCode;
   }
   public void setProductionCode(String productionCode) {
      ProductionCode = productionCode;
   }
   public int getCombinedEpisodeNumber() {
      return CombinedEpisodeNumber;
   }
   public void setCombinedEpisodeNumber(int combinedEpisodeNumber) {
      CombinedEpisodeNumber = combinedEpisodeNumber;
   }
   public int getCombinedSeasonNumber() {
      return CombinedSeasonNumber;
   }
   public void setCombinedSeasonNumber(int combinedSeasonNumber) {
      CombinedSeasonNumber = combinedSeasonNumber;
   }
   public int getDvdChapter() {
      return DvdChapter;
   }
   public void setDvdChapter(int dvdChapter) {
      DvdChapter = dvdChapter;
   }
   public int getDvdDiscid() {
      return DvdDiscid;
   }
   public void setDvdDiscid(int dvdDiscid) {
      DvdDiscid = dvdDiscid;
   }
   public int getDvdEpisodenumber() {
      return DvdEpisodenumber;
   }
   public void setDvdEpisodenumber(int dvdEpisodenumber) {
      DvdEpisodenumber = dvdEpisodenumber;
   }
   public int getDvdSeason() {
      return DvdSeason;
   }
   public void setDvdSeason(int dvdSeason) {
      DvdSeason = dvdSeason;
   }
   public int getAbsoluteEpisodeNumber() {
      return AbsoluteEpisodeNumber;
   }
   public void setAbsoluteEpisodeNumber(int absoluteEpisodeNumber) {
      AbsoluteEpisodeNumber = absoluteEpisodeNumber;
   }
   public int getAirsAfterSeason() {
      return AirsAfterSeason;
   }
   public void setAirsAfterSeason(int airsAfterSeason) {
      AirsAfterSeason = airsAfterSeason;
   }
   public int getAirsBeforeEpisode() {
      return AirsBeforeEpisode;
   }
   public void setAirsBeforeEpisode(int airsBeforeEpisode) {
      AirsBeforeEpisode = airsBeforeEpisode;
   }
   public int getAirsBeforeSesaon() {
      return AirsBeforeSesaon;
   }
   public void setAirsBeforeSesaon(int airsBeforeSesaon) {
      AirsBeforeSesaon = airsBeforeSesaon;
   }
   public EpisodeFile getEpisodeFile() {
      return EpisodeFile;
   }
   public void setEpisodeFile(EpisodeFile episodeFile) {
      EpisodeFile = episodeFile;
   }
   public EpisodeFile getEpisodeFile2() {
      return EpisodeFile2;
   }
   public void setEpisodeFile2(EpisodeFile episodeFile2) {
      EpisodeFile2 = episodeFile2;
   }
   
   
}
