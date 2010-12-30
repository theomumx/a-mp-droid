package com.mediaportal.remote.activities.lists.views;

import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.data.Series;

public class SeriesThumbViewAdapter implements ILoadingAdapterItem {
   private Series series;

   public SeriesThumbViewAdapter(Series _series) {
      super();
      this.series = _series;
   }

   @Override
   public String getImage() {
      return series.getCurrentFanartUrl();
   }

   @Override
   public String getSubText() {
      return "";
   }

   @Override
   public String getText() {
      return "";
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
      return ViewTypes.ThumbView.ordinal();
   }

   @Override
   public int getXml() {
      return 0;
   }
   
   @Override
   public Object getItem() {
      return series;
   }

}
