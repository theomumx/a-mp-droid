package com.mediaportal.remote.activities.lists.views;

import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.data.Series;

public class SeriesBannerViewAdapter implements ILoadingAdapterItem {
   private Series series;

   public SeriesBannerViewAdapter(Series _series) {
      super();
      this.series = _series;
   }

   @Override
   public String getImage() {
      return series.getCurrentBannerUrl();
   }

   @Override
   public String getSubText() {
      return series.getGenreString();
   }

   @Override
   public String getText() {
      return series.getPrettyName();
   }

   @Override
   public int getTextColor() {
      return 0;
   }

   @Override
   public String getTitle() {
      return series.getPrettyName();
   }

   @Override
   public int getSubTextColor() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getTitleColor() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getType() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getXml() {
      // TODO Auto-generated method stub
      return 0;
   }
   
   @Override
   public Object getItem() {
      return series;
   }

   @Override
   public String getImageCacheName() {
      // TODO Auto-generated method stub
      return null;
   }

}
