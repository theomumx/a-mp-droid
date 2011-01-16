package com.mediaportal.ampdroid.lists.views;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.data.TvVirtualCard;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;
import com.mediaportal.ampdroid.lists.SubtextViewHolder;

public class VirtualCardStateAdapter implements ILoadingAdapterItem {

   TvVirtualCard mCard;
   public VirtualCardStateAdapter(TvVirtualCard _card){
      mCard = _card;
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
      return 0;
   }

   @Override
   public int getXml() {
      return R.layout.listitem_virtualcard;
   }

   @Override
   public Object getItem() {
      return mCard;
   }

   @Override
   public ViewHolder createViewHolder(View _view) {
      SubtextViewHolder holder = new SubtextViewHolder();
      holder.title = (TextView) _view.findViewById(R.id.TextViewChannelName);
      holder.text = (TextView) _view.findViewById(R.id.TextViewState);
      holder.subtext = (TextView) _view.findViewById(R.id.TextViewCardUser);
      return holder;
   }

   @Override
   public void fillViewFromViewHolder(ViewHolder _holder) {
      SubtextViewHolder holder = (SubtextViewHolder) _holder;
      if (holder.title != null) {
         holder.title.setTypeface(null, Typeface.BOLD);
         holder.title.setTextColor(Color.WHITE);
         holder.title.setText(mCard.getChannelName());
      }

      if (holder.text != null) {
         String stateString = null;
         if(mCard.isIsRecording()){
            stateString = "Recording";
         }
         else if(mCard.isIsTimeShifting()){
            stateString = "Timeshifting";
         }
         
         holder.text.setText(stateString);
         holder.text.setTextColor(Color.WHITE);
      }

      if (holder.subtext != null) {
         String subtextString = mCard.getName() + " (" + mCard.getUser().getName() + ")";
         holder.subtext.setText(subtextString);
         holder.subtext.setTextColor(Color.WHITE);
      }
   }

}
