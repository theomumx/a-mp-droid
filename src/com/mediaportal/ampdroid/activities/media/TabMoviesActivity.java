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
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.views.MoviePosterViewAdapter;
public class TabMoviesActivity extends Activity {
	private ListView mListView;
	private LazyLoadingAdapter mAdapter;
   DataHandler mService;
   private LoadSeriesTask mSeriesLoaderTask;

   private class LoadSeriesTask extends AsyncTask<Integer, List<Movie>, Boolean> {
      @Override
      protected Boolean doInBackground(Integer... _params) {
         int seriesCount = mService.getMovieCount();

         int cursor = 0;
         while (cursor < seriesCount) {
            List<Movie> series = mService.getMovies(cursor, cursor + 19);

            publishProgress(series);

            cursor += 20;
         }

         return true;
      }

      @Override
      protected void onProgressUpdate(List<Movie>... values) {
         if (values != null) {
            List<Movie> series = values[0];
            for (Movie s : series) {
               mAdapter.AddItem(new MoviePosterViewAdapter(s));
            }
         }
         mAdapter.notifyDataSetChanged();
         super.onProgressUpdate(values);
      }

      @Override
      protected void onPostExecute(Boolean _result) {
         mAdapter.showLoadingItem(false);
      }
   }

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tabmoviesactivity);

		mService = DataHandler.getCurrentRemoteInstance();
		mAdapter = new LazyLoadingAdapter(this);

		mListView = (ListView) findViewById(R.id.ListViewVideos);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Movie selectedMovie = (Movie) ((ILoadingAdapterItem) mListView
						.getItemAtPosition(position)).getItem();

				Intent myIntent = new Intent(v.getContext(),
						TabMovieDetailsActivity.class);
				myIntent.putExtra("movie_id", selectedMovie.getId());
				startActivity(myIntent);

			}
		});
      refreshMovies();
   }

   private void refreshMovies() {
      mAdapter.setLoadingText("Loading Series ...");
      mAdapter.showLoadingItem(true);
      mSeriesLoaderTask = new LoadSeriesTask();
      mSeriesLoaderTask.execute(0);

   }
}
