package com.mediaportal.ampdroid.lists.views;

import java.io.File;

import android.view.View;

import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;
import com.mediaportal.ampdroid.lists.Utils;

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
   public int getType() {
      return 0;
   }

   @Override
   public int getXml() {
      return 0;
   }
   @Override
   public ViewHolder createViewHolder(View _view) {
      // TODO Auto-generated method stub
      return null;
   }
   @Override
   public void fillViewFromViewHolder(ViewHolder _holder) {
      // TODO Auto-generated method stub
      
   }


}
