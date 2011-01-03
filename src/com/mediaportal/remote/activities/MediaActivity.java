package com.mediaportal.remote.activities;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.actionbar.ActionBar;
import com.mediaportal.remote.activities.actionbar.ActionBar.IntentAction;
import com.mediaportal.remote.activities.media.TabMoviesActivity;
import com.mediaportal.remote.activities.media.TabSeriesActivityGroup;
import com.mediaportal.remote.activities.media.TabSharesActivity;
import com.mediaportal.remote.activities.media.TabVideosActivity;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.SupportedFunctions;

public class MediaActivity extends TabActivity {
   TabHost mTabHost;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.mediaactivity);
      
      ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
      actionBar.setTitle("aMPdroid");
      actionBar.setHomeAction(new IntentAction(this, null, R.drawable.actionbar_home));
      
      actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_remote));
      actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_search));

      DataHandler service = DataHandler.getCurrentRemoteInstance();
      SupportedFunctions functions = service.getSupportedFunctions();

      
      
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
            moviesTabSpec.setContent(new Intent(this, TabMoviesActivity.class));
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
