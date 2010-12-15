package com.mediaportal.remote.activities;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.media.TabMoviesActivity;
import com.mediaportal.remote.activities.media.TabSeriesActivity;
import com.mediaportal.remote.api.RemoteHandler;
import com.mediaportal.remote.data.SupportedFunctions;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MediaActivity extends TabActivity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.mediaactivity);

      RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
      SupportedFunctions functions = service.getSupportedFunctions();

      TabHost tabHost = getTabHost();

      if (functions.supportsVideo()) {
         /** tid1 is firstTabSpec Id. Its used to access outside. */
         TabSpec sharesTabSpec = tabHost.newTabSpec("tid0");
         sharesTabSpec.setIndicator("Shares");
         sharesTabSpec.setContent(new Intent(this, TabMoviesActivity.class));
         tabHost.addTab(sharesTabSpec);
         
         TabSpec videosTabSpec = tabHost.newTabSpec("tid1");
         videosTabSpec.setIndicator("Videos");
         videosTabSpec.setContent(new Intent(this, TabMoviesActivity.class));
         tabHost.addTab(videosTabSpec);
      }
      
      
      if(functions.supportsTvSeries()){
         TabSpec seriesTabSpec = tabHost.newTabSpec("tid2");
         seriesTabSpec.setIndicator("Series");
         seriesTabSpec.setContent(new Intent(this, TabSeriesActivity.class));
         tabHost.addTab(seriesTabSpec);
      }
      
      if(functions.supportsMovies()){
         TabSpec moviesTabSpec = tabHost.newTabSpec("tid3");
         moviesTabSpec.setIndicator("Movies");
         moviesTabSpec.setContent(new Intent(this, TabMoviesActivity.class));
         tabHost.addTab(moviesTabSpec);
      }
   }
}
