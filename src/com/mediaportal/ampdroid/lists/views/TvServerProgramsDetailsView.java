package com.mediaportal.ampdroid.lists.views;

import java.util.Date;

import android.view.View;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.data.TvProgram;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;
import com.mediaportal.ampdroid.lists.SubtextViewHolder;

public class TvServerProgramsDetailsView implements ILoadingAdapterItem {

   private TvProgram mProgram;
   private String mDateString;
   private String mOverviewString;

   public TvServerProgramsDetailsView(TvProgram _program) {
      mProgram = _program;

      Date begin = mProgram.getStartTime();
      Date end = mProgram.getEndTime();
      if (begin != null && end != null) {
         String startString = (String) android.text.format.DateFormat.format("hh:mm a", begin);
         // String endString = (String)
         // android.text.format.DateFormat.format("hh:mm a", end);

         mDateString = startString;
      } else {
         mDateString = "";
      }

      cropDescription();
   }

   private void cropDescription() {
      String overview = mProgram.getDescription();

      if (overview == null) {
         mOverviewString = "";
      } else if (overview.length() > 50) {
         mOverviewString = overview.substring(0, 50);
      } else {
         mOverviewString = overview;
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

   @Override
   public int getType() {
      return 0;
   }

   @Override
   public int getXml() {
      return R.layout.listitem_epg;
   }

   @Override
   public Object getItem() {
      return mProgram;
   }

   @Override
   public ViewHolder createViewHolder(View _view) {
      SubtextViewHolder holder = new SubtextViewHolder();
      holder.title = (TextView) _view.findViewById(R.id.TextViewProgramName);
      holder.text = (TextView) _view.findViewById(R.id.TextViewProgramDescription);
      holder.subtext = (TextView) _view.findViewById(R.id.TextViewProgramDate);
      return holder;
   }

   @Override
   public void fillViewFromViewHolder(ViewHolder _holder) {
      SubtextViewHolder holder = (SubtextViewHolder) _holder;
      if (holder.title != null) {
         holder.title.setText(mProgram.getTitle() + (mProgram.isIsRecordingOncePending() ? " - Rec" : ""));
      }

      if (holder.text != null) {
         holder.text.setText(mOverviewString);
      }

      if (holder.subtext != null) {
         holder.subtext.setText(mDateString);
      }
   }
}
