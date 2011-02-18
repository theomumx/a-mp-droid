package com.mediaportal.ampdroid.lists.views;

import java.util.Date;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.data.TvProgramBase;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;
import com.mediaportal.ampdroid.lists.LazyLoadingImage;
import com.mediaportal.ampdroid.lists.SubtextViewHolder;

public class TvServerProgramsBaseViewItem implements ILoadingAdapterItem {

   private TvProgramBase mProgram;
   private String mDateString;
   private String mOverviewString;
   
   public TvServerProgramsBaseViewItem(TvProgramBase _program) {
      mProgram = _program;

      Date begin = mProgram.getStartTime();
      Date end = mProgram.getEndTime();
      if (begin != null && end != null) {
         String startString = (String) android.text.format.DateFormat.format("kk:mm", begin);
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
   public LazyLoadingImage getImage() {
      return null;
   }

   @Override
   public int getType() {
      return 1;
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
      holder.text = (TextView) _view.findViewById(R.id.TextViewProgramDate);
      holder.image2 = (ImageView) _view.findViewById(R.id.ImageViewRecording);
      return holder;
   }

   @Override
   public void fillViewFromViewHolder(ViewHolder _holder) {
      SubtextViewHolder holder = (SubtextViewHolder) _holder;
      if (holder.title != null) {
         holder.title.setText(mProgram.getTitle() + (mProgram.isIsScheduled() ? " - Rec" : ""));
      }

      if (holder.text != null) {
         holder.text.setText(mDateString);
      }
      
      if(holder.image2 != null){
         if(mProgram.isIsScheduled()){
            holder.image2.setImageResource(R.drawable.tvserver_record_button);
         }
         else{
            holder.image2.setImageBitmap(null);
         }
      }
   }

   @Override
   public int getLoadingImageResource() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getDefaultImageResource() {
      // TODO Auto-generated method stub
      return 0;
   }
}
