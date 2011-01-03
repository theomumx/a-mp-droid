package com.mediaportal.remote.activities.lists.views;


import java.io.File;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.activities.lists.Utils;
import com.mediaportal.remote.data.Movie;

public class MoviePosterViewAdapter implements ILoadingAdapterItem {

   private Movie movie;
   public MoviePosterViewAdapter(Movie _movie){
      movie = _movie;
   }
   
   @Override
   public String getImageCacheName() {
      String fileName = Utils.getFileNameWithExtension(movie.getCoverThumbPath(), "\\");
      return "Movies" + File.separator + movie.getId() + File.separator + fileName;
   }
   
   
   @Override
   public String getImage() {
      return movie.getCoverThumbPath();
   }

   @Override
   public String getSubText() {
      // TODO Auto-generated method stub
      return movie.getName();
   }

   @Override
   public String getText() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public int getTextColor() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public String getTitle() {
      // TODO Auto-generated method stub
      return movie.getName();
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
      return ViewTypes.PosterView.ordinal();
   }


   @Override
   public int getXml() {
      return R.layout.listitem_poster;
   }


   @Override
   public Object getItem() {
      return movie;
   }




}
