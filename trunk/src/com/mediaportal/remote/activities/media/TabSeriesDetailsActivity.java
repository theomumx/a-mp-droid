package com.mediaportal.remote.activities.media;

import java.security.spec.MGF1ParameterSpec;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.ImageHandler;
import com.mediaportal.remote.activities.lists.LazyLoadingAdapter;
import com.mediaportal.remote.activities.lists.LazyLoadingGalleryAdapter;
import com.mediaportal.remote.activities.lists.views.PosterGalleryViewAdapter;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.SeriesFull;
import com.mediaportal.remote.data.SeriesSeason;

public class TabSeriesDetailsActivity extends Activity {
   private LazyLoadingGalleryAdapter adapter;
   private LinearLayout mSeasonLayout;
   private ImageView mSeriesPoster;
   private TextView mSeriesName;
   private TextView mSeriesOverview;
   private Gallery mPosterGallery;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.tabseriesdetailsactivity);
      mSeasonLayout = (LinearLayout) findViewById(R.id.LinearLayoutSeasons);
      mSeriesPoster = (ImageView) findViewById(R.id.ImageViewSeriesPoster);
      mSeriesName = (TextView) findViewById(R.id.TextViewSeriesName);
      mSeriesOverview = (TextView) findViewById(R.id.TextViewOverview);
      mPosterGallery = (Gallery) findViewById(R.id.GalleryAllMoviePosters);

      Bundle extras = getIntent().getExtras();
      if (extras != null) {
         int seriesId = extras.getInt("series_id");

         DataHandler service = DataHandler.getCurrentRemoteInstance();

         SeriesFull fullSeries = service.getFullSeries(seriesId);

         String seriesPoster = fullSeries.getCurrentPosterUrl();
         if (seriesPoster != null && !seriesPoster.equals("")) {
            Bitmap seriesPosterThumb = service.getImage(seriesPoster, 200, 400);
            if (seriesPosterThumb != null) {
               mSeriesPoster.setImageBitmap(seriesPosterThumb);
            }
         }

         mSeriesName.setText(fullSeries.getPrettyName());

         adapter = new LazyLoadingGalleryAdapter(this, service);
         
         String[] posterUrls = fullSeries.getPosterUrls();
         if (posterUrls != null) {
            for (int i = 0; i < posterUrls.length; i++) {
               adapter.AddItem(new PosterGalleryViewAdapter(posterUrls[i]));
            }
         }
/*
         String[] fanartUrls = fullSeries.getFanartUrls();
         if (fanartUrls != null) {
            for (int i = 0; i < fanartUrls.length; i++) {
               adapter.AddItem(new PosterGalleryViewAdapter(fanartUrls[i]));
            }
         }

         String[] bannerUrls = fullSeries.getBannerUrls();
         if (bannerUrls != null) {
            for (int i = 0; i < bannerUrls.length; i++) {
               adapter.AddItem(new PosterGalleryViewAdapter(bannerUrls[i]));
            }
         }
*/
         // mPosterGallery.setSpacing(-10);
         mPosterGallery.setAdapter(adapter);

         mSeriesOverview.setText(fullSeries.getSummary());

         ArrayList<SeriesSeason> seasons = service.getAllSeasons(seriesId);

         for (int i = 0; i < seasons.size(); i++) {
            View view = Button.inflate(this, R.layout.listitem_poster, null);
            TextView text = (TextView) view.findViewById(R.id.TextViewTitle);
            ImageView image = (ImageView) view.findViewById(R.id.ImageViewEventImage);
            TextView subtext = (TextView) view.findViewById(R.id.TextViewText);
            ProgressBar progress = (ProgressBar) view.findViewById(R.id.ProgressBarLoading);
            progress.setVisibility(View.GONE);

            SeriesSeason s = seasons.get(i);
            String seasonBanner = s.getSeasonBanner();
            if (seasonBanner != null && !seasonBanner.equals("")) {
               Bitmap seasonImage = service.getImage(seasonBanner, 150, 300);

               image.setImageBitmap(seasonImage);
            }

            view.setOnTouchListener(new OnTouchListener() {

               @Override
               public boolean onTouch(View v, MotionEvent event) {
                  if (event.getAction() == MotionEvent.ACTION_DOWN) {
                     v.setBackgroundColor(Color.rgb(255, 165, 0));
                  } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                     v.setBackgroundColor(Color.TRANSPARENT);
                  } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

                  } else {
                     v.setBackgroundColor(Color.TRANSPARENT);
                  }

                  return false;
               }
            });

            view.setOnFocusChangeListener(new OnFocusChangeListener() {

               @Override
               public void onFocusChange(View v, boolean hasFocus) {
                  if (hasFocus) {

                  } else {

                  }
               }
            });

            view.setOnClickListener(new OnClickListener() {

               @Override
               public void onClick(View v) {
                  Intent myIntent = new Intent(v.getContext(), TabEpisodesActivity.class);
                  SeriesSeason s = (SeriesSeason) v.getTag();
                  myIntent.putExtra("series_id", s.getSeriesId());
                  myIntent.putExtra("season_number", s.getSeasonNumber());

                  myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                  // Create the view using FirstGroup's LocalActivityManager
                  View view = TabSeriesActivityGroup.group.getLocalActivityManager().startActivity(
                        "season_episodes", myIntent).getDecorView();

                  // Again, replace the view
                  TabSeriesActivityGroup.group.replaceView(view);
               }
            });

            text.setText("Season " + s.getSeasonNumber());
            subtext.setText(s.getEpisodesUnwatchedCount() + "/" + s.getEpisodesCount());
            view.setTag(s);

            mSeasonLayout.addView(view);
         }

         // m_listView.setAdapter(adapter);

      } else {// activity called without movie id (shouldn't happen ;))

      }

      /*
       * m_listView.setOnItemClickListener(new OnItemClickListener() {
       * 
       * @Override public void onItemClick(AdapterView<?> a, View v, int
       * position, long id) {
       * 
       * MPSeason selectedSeason = (MPSeason) m_listView
       * .getItemAtPosition(position); // MPWebServiceProvider provider = new //
       * MPWebServiceProvider("10.1.0.154", // 8090);
       * 
       * // ArrayList<MPSeason> seasons = //
       * provider.getAllSeasons(selectedSeries.getId());
       * 
       * 
       * 
       * } });
       */

   }

   @Override
   public void onDestroy() {
      // adapter.imageLoader.stopThread();
      // m_listView.setAdapter(null);
      super.onDestroy();
   }
}
