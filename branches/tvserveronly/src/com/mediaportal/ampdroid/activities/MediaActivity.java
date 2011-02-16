package com.mediaportal.ampdroid.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.actionbar.ActionBar;
import com.mediaportal.ampdroid.activities.media.TabMoviesActivityGroup;
import com.mediaportal.ampdroid.activities.media.TabSeriesActivityGroup;
import com.mediaportal.ampdroid.activities.media.TabSharesActivity;
import com.mediaportal.ampdroid.activities.media.TabVideosActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.SupportedFunctions;

public class MediaActivity extends BaseTabActivity {
   TabHost mTabHost;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.mediaactivity);
      
      ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
      actionBar.setTitle("Media Browsing");

      DataHandler service = DataHandler.getCurrentRemoteInstance();
      //SupportedFunctions functions = service.getSupportedFunctions();
      SupportedFunctions functions = new SupportedFunctions();
      functions.setSupportsMovies(true);
      functions.setSupportsTvSeries(true);
      functions.setSupportsVideo(true);

      
      
      if (functions != null) {
         mTabHost = getTabHost();
         mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
         if (functions.supportsVideo()) {
            /** tid1 is firstTabSpec Id. Its used to access outside. */
            TabSpec sharesTabSpec = mTabHost.newTabSpec("tid0");
            View tab = createTabView(this, "Shares");
            sharesTabSpec.setIndicator(tab);
            sharesTabSpec.setContent(new Intent(this, TabSharesActivity.class));
            mTabHost.addTab(sharesTabSpec);

            TabSpec videosTabSpec = mTabHost.newTabSpec("tid1");
            videosTabSpec.setIndicator(createTabView(this, "Videos"));
            videosTabSpec.setContent(new Intent(this, TabVideosActivity.class));
            mTabHost.addTab(videosTabSpec);
         }

         if (functions.supportsTvSeries()) {
            TabSpec seriesTabSpec = mTabHost.newTabSpec("tid2");
            seriesTabSpec.setIndicator(createTabView(this, "Series"));
            // TabActivityGroup seriesActivityGroup = new
            // TabActivityGroup(TabSeriesActivity.class);
            seriesTabSpec.setContent(new Intent(this, TabSeriesActivityGroup.class));
            mTabHost.addTab(seriesTabSpec);
         }

         if (functions.supportsMovies()) {
            TabSpec moviesTabSpec = mTabHost.newTabSpec("tid3");
            moviesTabSpec.setIndicator(createTabView(this, "Movies"));
            moviesTabSpec.setContent(new Intent(this, TabMoviesActivityGroup.class));
            mTabHost.addTab(moviesTabSpec);
         }
      } else {
         Toast toast = Toast.makeText(this, "No connection to service", Toast.LENGTH_SHORT);
         toast.show();
      }
   }

   private static View createTabView(final Context _context, final String _text) {
      try {
         View view = LayoutInflater.from(_context).inflate(R.layout.tabs_bg, null);
         TextView tv = (TextView) view.findViewById(R.id.tabsText);
         tv.setText(_text);
         return view;
      } catch (Exception ex) {
         Log.d("Tab:", ex.toString());
         return null;
      }
   }
}
