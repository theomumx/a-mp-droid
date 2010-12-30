package com.mediaportal.remote.activities.lists.views;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.data.SeriesEpisode;

public class EpisodePosterViewAdapter implements ILoadingAdapterItem {

   private SeriesEpisode episode;
   public EpisodePosterViewAdapter(SeriesEpisode _episode){
      episode = _episode;
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