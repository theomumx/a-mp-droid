package com.mediaportal.ampdroid.activities.media;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.MovieFull;
public class TabMovieDetailsActivity extends Activity {
   private DataHandler mService;
   MovieFull mMovie;
	@Override
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tabmoviedetailsactivity);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int movieId = extras.getInt("movie_id");

			mService = DataHandler.getCurrentRemoteInstance();
			mMovie = mService.getMovieDetails(movieId);

			if (mMovie != null) {
				TextView mMovieName = (TextView) findViewById(R.id.TextViewMovieName);
				mMovieName.setText(mMovie.getName());

				TextView mMovieOverview = (TextView) findViewById(R.id.TextViewOverview);
				mMovieOverview.setText(mMovie.getSummary());
				
				ImageView moviePoster = (ImageView) findViewById(R.id.ImageViewMoviePoster);
				
				moviePoster.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                  mService.playFileOnClient(mMovie.getFilename());
                  
               }
            });
				
				Bitmap bmImg = mService.getBitmap(mMovie.getCoverThumbPath());
				moviePoster.setImageBitmap(bmImg);
			}
		} else {// activity called without movie id (shouldn't happen ;))

		}
	}
}
