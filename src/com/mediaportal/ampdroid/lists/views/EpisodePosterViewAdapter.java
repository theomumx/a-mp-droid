package com.mediaportal.remote.lists.views;

import java.io.File;

import com.mediaportal.remote.R;
import com.mediaportal.remote.data.SeriesEpisode;
import com.mediaportal.remote.lists.ILoadingAdapterItem;
import com.mediaportal.remote.lists.Utils;

public class EpisodePosterViewAdapter implements ILoadingAdapterItem {
   private int mSeriesId;
   private SeriesEpisode mEpisode;

   public EpisodePosterViewAdapter(int _seriesId, SeriesEpisode _episode) {
      mEpisode = _episode;
      mSeriesId = _seriesId;
   }

   /**
    * Returns the cache name for this image.
    * 
    * CACHE_DIR\Series\{SERIES_ID}\Season.{SEASON_NR}\Ep{EP_NR}.{Extension}
    */
   @Override
   public String getImageCacheName() {
      String ext = Utils.getExtension(mEpisode.getBannerUrl());
      return "Series" + File.separator + mSeriesId + File.separator + "Season." + mEpisode.getSeasonNumber()
            + File.separator + "Ep" + mEpisode.getEpisodeNumber() + "." + ext;
   }

   @Override
   public String getImage() {
      return mEpisode.getBannerUrl();
   }

   @Override
   public String getSubText() {
      return "";
   }

   @Override
   public String getText() {
      return mEpisode.getSeasonNumber() + "x" + mEpisode.getEpisodeNumber();
   }

   @Override
   public int getTextColor() {
      return -99;
   }

   @Override
   public String getTitle() {
      return mEpisode.getName();
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
      return mEpisode;
   }

}