package com.mediaportal.remote.activities.lists.views;

import java.io.File;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.activities.lists.Utils;
import com.mediaportal.remote.data.SeriesEpisode;

public class EpisodePosterViewAdapter implements ILoadingAdapterItem {
   private int mSeriesId;
   private SeriesEpisode episode;

   public EpisodePosterViewAdapter(int _seriesId, SeriesEpisode _episode) {
      episode = _episode;
      mSeriesId = _seriesId;
   }

   /**
    * Returns the cache name for this image.
    * 
    * CACHE_DIR\Series\{SERIES_ID}\Season.{SEASON_NR}\Ep{EP_NR}.{Extension}
    */
   @Override
   public String getImageCacheName() {
      String ext = Utils.getExtension(episode.getBannerUrl());
      return "Series" + File.separator + mSeriesId + File.separator + "Season." + episode.getSeasonNumber()
            + File.separator + "Ep" + episode.getEpisodeNumber() + "." + ext;
   }

   @Override
   public String getImage() {
      return episode.getBannerUrl();
   }

   @Override
   public String getSubText() {
      return "";
   }

   @Override
   public String getText() {
      return episode.getSeasonNumber() + "x" + episode.getEpisodeNumber();
   }

   @Override
   public int getTextColor() {
      return -99;
   }

   @Override
   public String getTitle() {
      return episode.getName();
   }

   @Override
   public int getSubTextColor() {
      return -99;
   }

   @Override
   public int getTitleColor() {
      return -99;
   }

   @Override
   public int getType() {
      return ViewTypes.PosterView.ordinal();
   }

   @Override
   public int getXml() {
      return R.layout.listitem_poster;
   }

   @Override
   public Object getItem() {
      return episode;
   }

}