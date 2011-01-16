package com.mediaportal.ampdroid.lists.views;

import java.util.Date;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.data.TvSchedule;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;
import com.mediaportal.ampdroid.lists.SubtextViewHolder;

public class TvServerSchedulesDetailsView implements ILoadingAdapterItem {
   TvSchedule mSchedule;

   public TvServerSchedulesDetailsView(TvSchedule _schedule) {
      mSchedule = _schedule;
   }

   private String getText() {
      //TODO -> get channel name
      return "Channel: " + mSchedule.getIdChannel();
   }

   private String getSubText() {
      Date begin = mSchedule.getStartTime();
      Date end = mSchedule.getEndTime();
      if (begin != null && end != null) {
         String startString = (String) android.text.format.DateFormat.format("yyyy-MM-dd kk:mm", begin);
         String endString = (String) android.text.format.DateFormat.format("kk:mm", end);
         return startString + " - " + endString;
      } else {
         return "Unknown time";
      }
   }

   @Override
   public String getImage() {
      return null;
   }

   @Override
   public String getImageCacheName() {
      return null;
   }

   public String getTitle() {
      return mSchedule.getProgramName();
   }

   @Override
   public int getType() {
      return 0;
   }

   @Override
   public int getXml() {
      return com.mediaportal.ampdroid.R.layout.listitem_recordings_thumbs;
   }

   @Override
   public Object getItem() {
      return mSchedule;
   }

   @Override
   public ViewHolder createViewHolder(View _view) {
      SubtextViewHolder holder = new SubtextViewHolder();
      holder.text = (TextView) _view.findViewById(R.id.TextViewText);
      holder.image = (ImageView) _view.findViewById(R.id.ImageViewEventImage);
      holder.subtext = (TextView) _view.findViewById(R.id.TextViewSubtext);
      holder.title = (TextView) _view.findViewById(R.id.TextViewTitle);
      return holder;
   }

   @Override
   public void fillViewFromViewHolder(ViewHolder _holder) {
      SubtextViewHolder holder = (SubtextViewHolder)_holder;
      if (holder.title != null) {
         holder.title.setTypeface(null, Typeface.BOLD);
         holder.title.setTextColor(Color.WHITE);
         holder.title.setText(getTitle());
      }

      if (holder.text != null) {
         holder.text.setText(getText());
         holder.text.setTextColor(Color.WHITE);
      }

      if (holder.subtext != null) {
         holder.subtext.setText(getSubText());
         holder.subtext.setTextColor(Color.WHITE);
      }
   }
}
