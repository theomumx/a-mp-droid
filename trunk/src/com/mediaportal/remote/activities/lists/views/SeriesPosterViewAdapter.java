package com.mediaportal.remote.activities.lists.views;

import java.io.File;

import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.activities.lists.Utils;
import com.mediaportal.remote.data.Series;

public class SeriesPosterViewAdapter implements ILoadingAdapterItem {
   private Series series;

   public SeriesPosterViewAdapter(Series _series) {
      super();
      this.series = _series;
   }

   @Override
   public String getImage() {
      return series.getCurrentPosterUrl();
   }
   
   @Override
   public String getImageCacheName() {
      String fileName = Utils.getFileNameWithExtension(series.getCurrentPosterUrl(), "\\");
      return "Series" + File.separator + series.getId() + File.separator + fileName;
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
