package com.mediaportal.ampdroid.lists.views;

import java.io.File;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.SubtextViewHolder;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;
import com.mediaportal.ampdroid.lists.Utils;

public class SeriesPosterViewAdapter implements ILoadingAdapterItem {
   private Series mSeries;

   public SeriesPosterViewAdapter(Series _series) {
      super();
      this.mSeries = _series;
   }

   @Override
   public String getImage() {
      return mSeries.getCurrentPosterUrl();
   }
   
   @Override
   public String getImageCacheName() {
      String fileName = Utils.getFileNameWithExtension(mSeries.getCurrentPosterUrl(), "\\");
      return "Series" + File.separator + mSeries.getId() + File.separator + "Poster" + File.separator + fileName;
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
      return mSeries;
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
         holder.title.setText(mSeries.getPrettyName());
      }

      if (holder.text != null) {
         holder.text.setText("");
         holder.text.setTextColor(Color.WHITE);
      }

      if (holder.subtext != null) {
         holder.subtext.setText(mSeries.getGenreString());
      }
   }



}
