package com.mediaportal.remote.activities.media;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.LazyLoadingAdapter;
import com.mediaportal.remote.api.RemoteHandler;
import com.mediaportal.remote.data.Movie;

public class TabMoviesActivity extends Activity {
	private ListView m_listView;
	private LazyLoadingAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabmoviesactivity);

		RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
		List<Movie> movies = service.getAllMovies();

		adapter = new LazyLoadingAdapter(this);

		if (movies != null) {
			for (Movie m : movies) {
				adapter.AddItem(m);
			}
		}

		m_listView = (ListView) findViewById(R.id.ListViewVideos);
		m_listView.setAdapter(adapter);

		m_listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				/*
				 * AlertDialog.Builder adb=new
				 * AlertDialog.Builder(MoviesActivity.this);
				 * adb.setTitle("LVSelectedItemExample");
				 * adb.setMessage("Selected Item is = "
				 * +m_listView.getItemAtPosition(position));
				 * adb.setPositiveButton("Ok", null); adb.show();
				 */
				Movie selectedMovie = (Movie) m_listView
						.getItemAtPosition(position);

				Intent myIntent = new Intent(v.getContext(),
						TabMovieDetailsActivity.class);
				myIntent.putExtra("movie_id", selectedMovie.getId());
				startActivity(myIntent);

			}
		});
	}
}
