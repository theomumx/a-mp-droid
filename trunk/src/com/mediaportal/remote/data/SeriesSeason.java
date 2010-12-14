package com.mediaportal.remote.data;

import java.util.ArrayList;

import com.mediaportal.remote.activities.lists.ILoadingAdapter;

public class SeriesSeason implements ILoadingAdapter{
	private String id;
	private int seriesId;
	private int seasonNumber;
	private int episodesCount;
	private int episodesUnwatchedCount;
	private String seasonBanner;
	private String seasonBannerThumb;
	private ArrayList<String> alternateSeasonBanners;
	private ArrayList<String> alternateSeasonBannerThumbs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodesCount() {
		return episodesCount;
	}

	public void setEpisodesCount(int episodesCount) {
		this.episodesCount = episodesCount;
	}

	public int getEpisodesUnwatchedCount() {
		return episodesUnwatchedCount;
	}

	public void setEpisodesUnwatchedCount(int episodesUnwatchedCount) {
		this.episodesUnwatchedCount = episodesUnwatchedCount;
	}

	public String getSeasonBanner() {
		return seasonBanner;
	}

	public void setSeasonBanner(String seasonBanner) {
		this.seasonBanner = seasonBanner;
	}

	public String getSeasonBannerThumb() {
		return seasonBannerThumb;
	}

	public void setSeasonBannerThumb(String seasonBannerThumb) {
		this.seasonBannerThumb = seasonBannerThumb;
	}

	public ArrayList<String> getAlternateSeasonBanners() {
		return alternateSeasonBanners;
	}

	public void setAlternateSeasonBanners(
			ArrayList<String> alternateSeasonBanners) {
		this.alternateSeasonBanners = alternateSeasonBanners;
	}

	public ArrayList<String> getAlternateSeasonBannerThumbs() {
		return alternateSeasonBannerThumbs;
	}

	public void setAlternateSeasonBannerThumbs(
			ArrayList<String> alternateSeasonBannerThumbs) {
		this.alternateSeasonBannerThumbs = alternateSeasonBannerThumbs;
	}



	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return seasonBannerThumb;
		//return (seasonBannerThumb != null ? seasonBannerThumb : seasonBanner);
	}

	@Override
	public String getSubText() {
		// TODO Auto-generated method stub
		return episodesUnwatchedCount + "/" + episodesCount;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return "Season " + seasonNumber;
	}

	@Override
	public int getTextColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
