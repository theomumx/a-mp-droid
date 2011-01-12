package com.mediaportal.ampdroid.activities.media;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.MovieFull;
import com.mediaportal.ampdroid.R;
public class TabMovieDetailsActivity extends Activity {
	@Override
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tabmoviedetailsactivity);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int movieId = extras.getInt("movie_id");

			DataHandler service = DataHandler.getCurrentRemoteInstance();
			MovieFull movie = service.getMovieDetails(movieId);

			if (movie != null) {
				TextView mMovieName = (TextView) findViewById(R.id.TextViewMovieName);
				mMovieName.setText(movie.getName());

				TextView mMovieOverview = (TextView) findViewById(R.id.TextViewOverview);
				mMovieOverview.setText(movie.getSummary());
				
				ImageView moviePoster = (ImageView) findViewById(R.id.ImageViewMoviePoster);
				
				Bitmap bmImg = service.getBitmap(movie.getCoverThumbPath());
				moviePoster.setImageBitmap(bmImg);
			}
		} else {// activity called without movie id (shouldn't happen ;))

		}
	}
}
