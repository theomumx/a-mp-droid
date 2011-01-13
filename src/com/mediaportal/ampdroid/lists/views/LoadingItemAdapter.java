package com.mediaportal.ampdroid.lists.views;

import android.view.View;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;

public class LoadingItemAdapter implements ILoadingAdapterItem {

   private String mLoadingText;

   public LoadingItemAdapter(String _loadingText){
      setLoadingText(_loadingText);
   }
   
   @Override
   public String getImage() {
      return null;
   }

   @Override
   public String getImageCacheName() {
      return null;
   }

   @Override
   public int getType() {
      return -99;
   }

   @Override
   public int getXml() {
      return R.layout.listitem_loadingindicator;
   }

   @Override
   public Object getItem() {
      return null;
   }

   @Override
   public ViewHolder createViewHolder(View _view) {
      ViewHolder holder = new ViewHolder();
      holder.text = (TextView) _view.findViewById(R.id.TextViewLoadingText);
      return holder;
   }

   @Override
   public void fillViewFromViewHolder(ViewHolder _holder) {
      if(_holder.text != null){
         _holder.text.setText(mLoadingText);
      }
   }

   public void setLoadingText(String mLoadingText) {
      this.mLoadingText = mLoadingText;
   }

   public String getLoadingText() {
      return mLoadingText;
   }

}
