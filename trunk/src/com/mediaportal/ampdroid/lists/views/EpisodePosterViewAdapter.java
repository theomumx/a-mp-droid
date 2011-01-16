package com.mediaportal.ampdroid.lists.views;

import java.io.File;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.data.SeriesEpisode;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;
import com.mediaportal.ampdroid.lists.SubtextViewHolder;
import com.mediaportal.ampdroid.lists.Utils;

public class EpisodePosterViewAdapter implements ILoadingAdapterItem {
   private int mSeriesId;
   private SeriesEpisode mEpisode;

   public EpisodePosterViewAdapter(int _seriesId, SeriesEpisode _episode) {
      mEpisode = _episode;
      mSeriesId = _seriesId;
   }

   /**
    * Returns the cache name for this image.
    * 
    * CACHE_DIR\Series\{SERIES_ID}\Season.{SEASON_NR}\Ep{EP_NR}.{Extension}
    */
   @Override
   public String getImageCacheName() {
      String ext = Utils.getExtension(mEpisode.getBannerUrl());
      return "Series" + File.separator + mSeriesId + File.separator + "Season." + mEpisode.getSeasonNumber()
            + File.separator + "Ep" + mEpisode.getEpisodeNumber() + "." + ext;
   }

   @Override
   public String getImage() {
      return mEpisode.getBannerUrl();
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
      return mEpisode;
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
         holder.title.setText(mEpisode.getName());
      }

      if (holder.text != null) {
         holder.text.setText(mEpisode.getSeasonNumber() + "x" + mEpisode.getEpisodeNumber());
         holder.text.setTextColor(Color.WHITE);
      }

      if (holder.subtext != null) {
         holder.subtext.setText("");
      }
   }
}