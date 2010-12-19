package com.mediaportal.remote.data;

import com.mediaportal.remote.activities.lists.ILoadingAdapter;

public class SeriesSeason implements ILoadingAdapter{
	private String Id;
	private int SeriesId;
	private int SeasonNumber;
	private int EpisodesCount;
	private int EpisodesUnwatchedCount;
	private String SeasonBanner;
	private String[] AlternateSeasonBanners;
	
	public String getId() {
      return Id;
   }

   public void setId(String id) {
      Id = id;
   }

   public int getSeriesId() {
      return SeriesId;
   }

   public void setSeriesId(int seriesId) {
      SeriesId = seriesId;
   }

   public int getSeasonNumber() {
      return SeasonNumber;
   }

   public void setSeasonNumber(int seasonNumber) {
      SeasonNumber = seasonNumber;
   }

   public int getEpisodesCount() {
      return EpisodesCount;
   }

   public void setEpisodesCount(int episodesCount) {
      EpisodesCount = episodesCount;
   }

   public int getEpisodesUnwatchedCount() {
      return EpisodesUnwatchedCount;
   }

   public void setEpisodesUnwatchedCount(int episodesUnwatchedCount) {
      EpisodesUnwatchedCount = episodesUnwatchedCount;
   }

   public String getSeasonBanner() {
      return SeasonBanner;
   }

   public void setSeasonBanner(String seasonBanner) {
      SeasonBanner = seasonBanner;
   }

   public String[] getAlternateSeasonBanners() {
      return AlternateSeasonBanners;
   }

   public void setAlternateSeasonBanners(String[] alternateSeasonBanners) {
      AlternateSeasonBanners = alternateSeasonBanners;
   }

   @Override
	public String getImage() {
		return getSeasonBanner();
	}

	@Override
	public String getSubText() {
		return getEpisodesUnwatchedCount() + "/" + getEpisodesCount();
	}

	@Override
	public String getText() {
		return "Season " + getSeasonNumber();
	}

	@Override
	public int getTextColor() {
		return 0;
	}

	@Override
	public String getTitle() {
		return "";
	}

}
