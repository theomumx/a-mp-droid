package com.mediaportal.ampdroid.lists.views;

import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;

public class SeriesBannerViewAdapter implements ILoadingAdapterItem {
   private Series mSeries;

   public SeriesBannerViewAdapter(Series _series) {
      super();
      this.mSeries = _series;
   }

   @Override
   public String getImage() {
      return mSeries.getCurrentBannerUrl();
   }

   @Override
   public String getSubText() {
      return mSeries.getGenreString();
   }

   @Override
   public String getText() {
      return mSeries.getPrettyName();
   }

   @Override
   public int getTextColor() {
      return 0;
   }

   @Override
   public String getTitle() {
      return mSeries.getPrettyName();
   }

   @Override
   public int getSubTextColor() {
      return 0;
   }

   @Override
   public int getTitleColor() {
      return 0;
   }

   @Override
   public int getType() {
      return 0;
   }

   @Override
   public int getXml() {
      return 0;
   }
   
   @Override
   public Object getItem() {
      return mSeries;
   }

   @Override
   public String getImageCacheName() {
      return null;
   }

}
