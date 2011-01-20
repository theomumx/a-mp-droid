package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;


public class SeriesSeason{
	private String Id;
	private int SeriesId;
	private int SeasonNumber;
	private int EpisodesCount;
	private int EpisodesUnwatchedCount;
	private String SeasonBanner;
	private String[] AlternateSeasonBanners;
	
	@JsonProperty("Id")
	public String getId() {
      return Id;
   }

   @JsonProperty("Id")
   public void setId(String id) {
      Id = id;
   }

   @JsonProperty("SeriesId")
   public int getSeriesId() {
      return SeriesId;
   }

   @JsonProperty("SeriesId")
   public void setSeriesId(int seriesId) {
      SeriesId = seriesId;
   }

   @JsonProperty("SeasonNumber")
   public int getSeasonNumber() {
      return SeasonNumber;
   }

   @JsonProperty("SeasonNumber")
   public void setSeasonNumber(int seasonNumber) {
      SeasonNumber = seasonNumber;
   }

   @JsonProperty("EpisodesCount")
   public int getEpisodesCount() {
      return EpisodesCount;
   }

   @JsonProperty("EpisodesCount")
   public void setEpisodesCount(int episodesCount) {
      EpisodesCount = episodesCount;
   }

   @JsonProperty("EpisodesUnwatchedCount")
   public int getEpisodesUnwatchedCount() {
      return EpisodesUnwatchedCount;
   }

   @JsonProperty("EpisodesUnwatchedCount")
   public void setEpisodesUnwatchedCount(int episodesUnwatchedCount) {
      EpisodesUnwatchedCount = episodesUnwatchedCount;
   }

   @JsonProperty("SeasonBanner")
   public String getSeasonBanner() {
      return SeasonBanner;
   }

   @JsonProperty("SeasonBanner")
   public void setSeasonBanner(String seasonBanner) {
      SeasonBanner = seasonBanner;
   }

   @JsonProperty("AlternateSeasonBanners")
   public String[] getAlternateSeasonBanners() {
      return AlternateSeasonBanners;
   }

   @JsonProperty("AlternateSeasonBanners")
   public void setAlternateSeasonBanners(String[] alternateSeasonBanners) {
      AlternateSeasonBanners = alternateSeasonBanners;
   }
}
