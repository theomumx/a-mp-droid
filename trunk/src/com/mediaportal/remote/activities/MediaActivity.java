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

        /** tid1 is firstTabSpec Id. Its used to access outside. */
        TabSpec sharesTabSpec = tabHost.newTabSpec("tid0");
        TabSpec videosTabSpec = tabHost.newTabSpec("tid1");
        TabSpec seriesTabSpec = tabHost.newTabSpec("tid2");
        TabSpec moviesTabSpec = tabHost.newTabSpec("tid3");
        
        /** TabSpec setIndicator() is used to set name for the tab. */
        /** TabSpec setContent() is used to set content for a particular tab. */
        sharesTabSpec.setIndicator("Shares").setContent(new Intent(this,TabMoviesActivity.class));
        videosTabSpec.setIndicator("Videos").setContent(new Intent(this,TabMoviesActivity.class));
        seriesTabSpec.setIndicator("Series").setContent(new Intent(this,TabSeriesActivity.class));
        moviesTabSpec.setIndicator("Movies").setContent(new Intent(this,TabMoviesActivity.class));

        /** Add tabSpec to the TabHost to display. */
        tabHost.addTab(sharesTabSpec);
        tabHost.addTab(videosTabSpec);
        tabHost.addTab(seriesTabSpec);
        tabHost.addTab(moviesTabSpec);
    }
}
