package com.mediaportal.ampdroid.activities.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.RatingBar;
import android.widget.TextView;

import com.mediaportal.ampdroid.activities.tvserver.TvServerChannelsActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.SeriesFull;
import com.mediaportal.ampdroid.data.SeriesSeason;
import com.mediaportal.ampdroid.lists.ImageHandler;
import com.mediaportal.ampdroid.lists.LazyLoadingGalleryAdapter;
import com.mediaportal.ampdroid.lists.LazyLoadingImage;
import com.mediaportal.ampdroid.lists.Utils;
import com.mediaportal.ampdroid.lists.views.MoviePosterViewAdapterItem;
import com.mediaportal.ampdroid.lists.views.PosterGalleryViewAdapterItem;
import com.mediaportal.ampdroid.R;

public class TabSeriesDetailsActivity extends Activity {
   private LazyLoadingGalleryAdapter mAdapter;
   private LinearLayout mSeasonLayout;
   private ImageView mSeriesPoster;
   private TextView mSeriesName;
   private TextView mSeriesOverview;
   private TextView mSeriesReleaseDate;
   private TextView mSeriesRuntime;
   private TextView mSeriesCertification;
   private TextView mSeriesActors;
   private RatingBar mSeriesRating;
   
   private Gallery mPosterGallery;
   private int mSeriesId;
   private SeriesFull mSeries;
   private ImageHandler mImageHandler;
   private LoadSeriesDetailsTask mLoadSeriesTask;
   private LoadSeasonsDetailsTask mLoadSeasonTask;
   private DataHandler mService;
   private ProgressDialog mLoadingDialog;

   private class LoadSeriesDetailsTask extends AsyncTask<Integer, List<Movie>, SeriesFull> {
      Activity mContext;

      private LoadSeriesDetailsTask(Activity _context) {
         mContext = _context;
      }

      @Override
      protected SeriesFull doInBackground(Integer... _params) {
         mSeries = mService.getFullSeries(mSeriesId);

         return mSeries;
      }

      @Override
      protected void onPostExecute(SeriesFull _result) {
         String seriesPoster = _result.getCurrentPosterUrl();
         if (seriesPoster != null && !seriesPoster.equals("")) {
            String fileName = Utils.getFileNameWithExtension(_result.getCurrentPosterUrl(), "\\");
            String cacheName = "Series" + File.separator + mSeriesId + File.separator
                  + "LargePoster" + File.separator + fileName;

            LazyLoadingImage image = new LazyLoadingImage(seriesPoster, cacheName, 200, 400);
            mSeriesPoster.setTag(seriesPoster);
            mImageHandler.DisplayImage(image, R.drawable.listview_imageloading_poster, mContext,
                  mSeriesPoster);

         }

         mSeriesName.setText(_result.getPrettyName());

         Date firstAired = _result.getFirstAired();

         if (firstAired != null) {
            String date = (String) android.text.format.DateFormat.format("yyyy-MM-dd", firstAired);
            mSeriesReleaseDate.setText(date);
         }

         int runtime = _result.getRuntime();
         if (runtime != 0) {
            mSeriesRuntime.setText(String.valueOf(runtime));
         } else {
            mSeriesRuntime.setText("-");
         }

         String[] actors = _result.getActors();
         if (actors != null) {
            String actorsString = "| ";
            for(String a : actors){
               actorsString += a + " | ";
            }
            mSeriesActors.setText(actorsString);
         } else {
            mSeriesActors.setText("-");
         }
         
         int rating = (int) _result.getRating();
         mSeriesRating.setRating(rating);

         mSeriesOverview.setText(_result.getSummary());
         
         mLoadingDialog.cancel();
      }
   }

   private class LoadSeasonsDetailsTask extends
         AsyncTask<Integer, List<Movie>, List<SeriesSeason>> {
      Activity mContext;

      private LoadSeasonsDetailsTask(Activity _context) {
         mContext = _context;
      }

      @Override
      protected List<SeriesSeason> doInBackground(Integer... _params) {
         List<SeriesSeason> seasons = mService.getAllSeasons(mSeriesId);

         return seasons;
      }

      @Override
      protected void onPostExecute(List<SeriesSeason> _result) {
         for (int i = 0; i < _result.size(); i++) {
            View view = Button.inflate(mContext, R.layout.listitem_poster, null);
            TextView text = (TextView) view.findViewById(R.id.TextViewTitle);
            ImageView image = (ImageView) view.findViewById(R.id.ImageViewEventImage);
            TextView subtext = (TextView) view.findViewById(R.id.TextViewText);

            SeriesSeason s = _result.get(i);
            String seasonBanner = s.getSeasonBanner();
            if (seasonBanner != null && !seasonBanner.equals("")) {
               String fileName = Utils.getFileNameWithExtension(seasonBanner, "\\");
               String cacheName = "Series" + File.separator + mSeriesId + File.separator
                     + "LargePoster" + File.separator + fileName;

               LazyLoadingImage bannerImage = new LazyLoadingImage(seasonBanner, cacheName, 100,
                     150);
               image.setTag(seasonBanner);
               mImageHandler.DisplayImage(bannerImage, R.drawable.listview_imageloading_poster,
                     mContext, image);
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

            view.setOnClickListener(new OnClickListener() {

               @Override
               public void onClick(View _view) {
                  Intent myIntent = new Intent(_view.getContext(), TabEpisodesActivity.class);
                  SeriesSeason s = (SeriesSeason) _view.getTag();
                  myIntent.putExtra("series_id", s.getSeriesId());
                  myIntent.putExtra("season_number", s.getSeasonNumber());
                  myIntent.putExtra("series_name", mSeries.getPrettyName());

                  myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                  // Create the view using FirstGroup's LocalActivityManager
                  View view = TabSeriesActivityGroup.getGroup().getLocalActivityManager()
                        .startActivity("season_episodes", myIntent).getDecorView();

                  // Again, replace the view
                  TabSeriesActivityGroup.getGroup().replaceView(view);
               }
            });

            text.setText("Season " + s.getSeasonNumber());
            subtext.setText(s.getEpisodesUnwatchedCount() + "/" + s.getEpisodesCount());
            view.setTag(s);

            mSeasonLayout.addView(view);
         }
      }
   }

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tabseriesdetailsactivity);
      mSeasonLayout = (LinearLayout) findViewById(R.id.LinearLayoutSeasons);
      mSeriesPoster = (ImageView) findViewById(R.id.ImageViewSeriesPoster);
      mSeriesName = (TextView) findViewById(R.id.TextViewSeriesName);
      mSeriesOverview = (TextView) findViewById(R.id.TextViewOverview);
      mPosterGallery = (Gallery) findViewById(R.id.GalleryAllMoviePosters);
      mSeriesReleaseDate = (TextView) findViewById(R.id.TextViewSeriesRelease);
      mSeriesRuntime = (TextView) findViewById(R.id.TextViewSeriesRuntime);
      mSeriesCertification = (TextView) findViewById(R.id.TextViewSeriesCertification);
      mSeriesActors = (TextView) findViewById(R.id.TextViewSeriesActors);
      mSeriesRating = (RatingBar) findViewById(R.id.RatingBarSeriesRating);
      mSeriesRating.setNumStars(10);

      Bundle extras = getIntent().getExtras();
      if (extras != null) {
         mSeriesId = extras.getInt("series_id");

         mService = DataHandler.getCurrentRemoteInstance();
         mImageHandler = new ImageHandler(this);

         mLoadSeriesTask = new LoadSeriesDetailsTask(this);
         mLoadSeriesTask.execute(mSeriesId);

         // mPosterGallery.setSpacing(-10);
         // mPosterGallery.setAdapter(mAdapter);
         
         mLoadingDialog = ProgressDialog.show(getParent(), " Loading Series Details ",
               " Loading. Please wait ... ", true);
         mLoadingDialog.setCancelable(true);

         mLoadSeasonTask = new LoadSeasonsDetailsTask(this);
         mLoadSeasonTask.execute(mSeriesId);
      } else {// activity called without movie id (shouldn't happen ;))

      }
   }

   @Override
   public void onDestroy() {
      // adapter.imageLoader.stopThread();
      // m_listView.setAdapter(null);
      super.onDestroy();
   }
}
