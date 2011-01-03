package com.mediaportal.remote.activities.lists.views;

import java.io.File;

import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.activities.lists.Utils;

public class PosterGalleryViewAdapter implements ILoadingAdapterItem {

   private String mPosterUrl;
   public PosterGalleryViewAdapter(String _posterUrl){
      mPosterUrl = _posterUrl;
   }
   @Override
   public String getImage() {
      return mPosterUrl;
   }
   
   @Override
   public String getImageCacheName() {
      String fileName = Utils.getFileNameWithExtension(mPosterUrl, "\\");
      return "MoviePosters" + File.separator + fileName;
   }

   @Override
   public Object getItem() {
      return mPosterUrl;
   }

   @Override
   public String getSubText() {
      return null;
   }

   @Override
   public int getSubTextColor() {
      return 0;
   }

   @Override
   public String getText() {
      return null;
   }

   @Override
   public int getTextColor() {
      return 0;
   }

   @Override
   public String getTitle() {
      return null;
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


}
