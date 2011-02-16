package com.mediaportal.ampdroid.activities.media;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseTabActivity;
import com.mediaportal.ampdroid.activities.StatusBarActivityHandler;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ILoadingListener;
import com.mediaportal.ampdroid.lists.views.MoviePosterViewAdapterItem;

public class TabMoviesActivity extends Activity implements ILoadingListener {
   private ListView mListView;
   private LazyLoadingAdapter mAdapter;
   DataHandler mService;
   private LoadMoviesTask mSeriesLoaderTask;
   private int mSeriesLoaded = 0;
   private BaseTabActivity mBaseActivity;
   private StatusBarActivityHandler mStatusBarHandler;

   private class LoadMoviesTask extends AsyncTask<Integer, List<Movie>, Boolean> {
      @Override
      protected Boolean doInBackground(Integer... _params) {
         int loadItems = mSeriesLoaded + _params[0];
         int seriesCount = mService.getMovieCount();

         while (mSeriesLoaded < loadItems) {
            List<Movie> series = mService.getMovies(mSeriesLoaded, mSeriesLoaded + 4);
            publishProgress(series);
            mSeriesLoaded += 5;
         }

         if (mSeriesLoaded < seriesCount) {
            return false;// not yet finished;
         } else {
            return true;// finished
         }
      }

      @Override
      protected void onProgressUpdate(List<Movie>... values) {
         if (values != null) {
            List<Movie> series = values[0];
            for (Movie s : series) {
               mAdapter.addItem(new MoviePosterViewAdapterItem(s));
            }
         }
         mAdapter.notifyDataSetChanged();
         super.onProgressUpdate(values);
      }

      @Override
      protected void onPostExecute(Boolean _result) {
         if (_result) {
            mAdapter.showLoadingItem(false);
         }
         mSeriesLoaderTask = null;
      }
   }

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tabmoviesactivity);
      
      mBaseActivity = (BaseTabActivity) getParent().getParent();

      mService = DataHandler.getCurrentRemoteInstance();
      mStatusBarHandler = new StatusBarActivityHandler(mBaseActivity, mService);
      mStatusBarHandler.setHome(false);
      
      mAdapter = new LazyLoadingAdapter(this);
      mAdapter.setLoadingListener(this);

      mListView = (ListView) findViewById(R.id.ListViewVideos);
      mListView.setAdapter(mAdapter);

      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            Movie selectedMovie = (Movie) ((ILoadingAdapterItem) mListView
                  .getItemAtPosition(position)).getItem();

            Intent myIntent = new Intent(v.getContext(), TabMovieDetailsActivity.class);
            myIntent.putExtra("movie_id", selectedMovie.getId());

            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Create the view using FirstGroup's LocalActivityManager
            View view = TabMoviesActivityGroup.getGroup().getLocalActivityManager()
                  .startActivity("movie_details", myIntent).getDecorView();

            // Again, replace the view
            TabMoviesActivityGroup.getGroup().replaceView(view);

         }
      });

      mAdapter.setLoadingText("Loading Movies ...");
      mAdapter.showLoadingItem(true);
      loadFurtherMovieItems();
   }

   @Override
   public void EndOfListReached() {
      loadFurtherMovieItems();
   }

   private void loadFurtherMovieItems() {
      if (mSeriesLoaderTask == null) {
         mSeriesLoaderTask = new LoadMoviesTask();
         mSeriesLoaderTask.execute(20);
      }
   }
}
