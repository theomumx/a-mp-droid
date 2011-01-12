package com.mediaportal.remote.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.actionbar.ActionBar;
import com.mediaportal.remote.activities.actionbar.ActionBar.IntentAction;
import com.mediaportal.remote.activities.tvserver.TvServerChannelsActivity;
import com.mediaportal.remote.activities.tvserver.TvServerEpgActivity;
import com.mediaportal.remote.activities.tvserver.TvServerRecordingsActivity;
import com.mediaportal.remote.activities.tvserver.TvServerSchedulesActivity;
import com.mediaportal.remote.activities.tvserver.TvServerStateActivity;
import com.mediaportal.remote.lists.TvServerFeature;
import com.mediaportal.remote.lists.TvServerFeaturesAdapter;

public class TvServerOverviewActivity extends BaseActivity {
   private ListView mListView;
   private TvServerFeaturesAdapter mFeaturesAdapter;

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setHome(false);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserveractivity);
      
      mListView = (ListView) findViewById(R.id.ListViewItems);
      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> _adapter, View _view, int _position, long _id) {
            if(_position == 0){
               Intent myIntent = new Intent(_view.getContext(), TvServerStateActivity.class);
               startActivity(myIntent);
            }
            
            if(_position == 1){
               Intent myIntent = new Intent(_view.getContext(), TvServerEpgActivity.class);
               startActivity(myIntent);
            }
            
            if(_position == 2){
               Intent myIntent = new Intent(_view.getContext(), TvServerChannelsActivity.class);
               startActivity(myIntent);
            }
            
            if(_position == 3){
               Intent myIntent = new Intent(_view.getContext(), TvServerSchedulesActivity.class);
               startActivity(myIntent);
            }
            
            if(_position == 4){
               Intent myIntent = new Intent(_view.getContext(), TvServerRecordingsActivity.class);
               startActivity(myIntent);
            }
         }
      });
      
      mFeaturesAdapter = new TvServerFeaturesAdapter(this);
      mFeaturesAdapter.addFeature(new TvServerFeature("Manual Control", "Current state of the tv service and the tv cards.", R.drawable.tvserver_manual));
      mFeaturesAdapter.addFeature(new TvServerFeature("EPG", "Electronic Program Guide", R.drawable.tvserver_tv));
      mFeaturesAdapter.addFeature(new TvServerFeature("Channels", "All available Channels on the server", R.drawable.tvserver_tv));
      mFeaturesAdapter.addFeature(new TvServerFeature("Schedules", "All scheduled recordings on the server", R.drawable.tvserver_schedules));
      mFeaturesAdapter.addFeature(new TvServerFeature("Recordings", "All recordings on the server", R.drawable.tvserver_recordings));
      
      mListView.setAdapter(mFeaturesAdapter);
   }

}
