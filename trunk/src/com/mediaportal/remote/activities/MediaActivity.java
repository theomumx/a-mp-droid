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
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.actionbar.ActionBar;
import com.mediaportal.remote.activities.actionbar.ActionBar.IntentAction;
import com.mediaportal.remote.activities.media.TabSeriesActivityGroup;
import com.mediaportal.remote.activities.media.TabMoviesActivity;
import com.mediaportal.remote.activities.media.TabSeriesActivity;
import com.mediaportal.remote.activities.media.TabSharesActivity;
import com.mediaportal.remote.activities.media.TabVideosActivity;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.SupportedFunctions;

public class MediaActivity extends TabActivity {
   TabHost tabHost;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.mediaactivity);
      
      ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
      actionBar.setTitle("aMPdroid");
      actionBar.setHomeAction(new IntentAction(this, null, R.drawable.actionbar_home));
      
      actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_remote));
      actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_search));

      DataHandler service = DataHandler.getCurrentRemoteInstance();
      SupportedFunctions functions = service.getSupportedFunctions();

      
      
      if (functions != null) {
         tabHost = getTabHost();
         tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
         if (functions.supportsVideo()) {
            /** tid1 is firstTabSpec Id. Its used to access outside. */
            TabSpec sharesTabSpec = tabHost.newTabSpec("tid0");
            View tab = createTabView(this, "Shares");
            sharesTabSpec.setIndicator(tab);
            sharesTabSpec.setContent(new Intent(this, TabSharesActivity.class));
            tabHost.addTab(sharesTabSpec);

            TabSpec videosTabSpec = tabHost.newTabSpec("tid1");
            videosTabSpec.setIndicator(createTabView(this, "Videos"));
            videosTabSpec.setContent(new Intent(this, TabVideosActivity.class));
            tabHost.addTab(videosTabSpec);
         }

         if (functions.supportsTvSeries()) {
            TabSpec seriesTabSpec = tabHost.newTabSpec("tid2");
            seriesTabSpec.setIndicator(createTabView(this, "Series"));
            // TabActivityGroup seriesActivityGroup = new
            // TabActivityGroup(TabSeriesActivity.class);
            seriesTabSpec.setContent(new Intent(this, TabSeriesActivityGroup.class));
            tabHost.addTab(seriesTabSpec);
         }

         if (functions.supportsMovies()) {
            TabSpec moviesTabSpec = tabHost.newTabSpec("tid3");
            moviesTabSpec.setIndicator(createTabView(this, "Movies"));
            moviesTabSpec.setContent(new Intent(this, TabMoviesActivity.class));
            tabHost.addTab(moviesTabSpec);
         }
      } else {
         Toast toast = Toast.makeText(this, "No connection to service", Toast.LENGTH_SHORT);
         toast.show();
      }
   }

   private void setupTab(final View view, final String tag) {
      View tabview = createTabView(tabHost.getContext(), tag);
      TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabview).setContent(
            new TabContentFactory() {
               public View createTabContent(String tag) {
                  return view;
               }
            });
      tabHost.addTab(setContent);
   }

   private static View createTabView(final Context context, final String text) {
      try {
         View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
         TextView tv = (TextView) view.findViewById(R.id.tabsText);
         tv.setText(text);
         return view;
      } catch (Exception ex) {
         Log.d("Tab:", ex.toString());
         return null;
      }
   }
}
