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
import com.mediaportal.remote.activities.lists.TvServerFeature;
import com.mediaportal.remote.activities.lists.TvServerFeaturesAdapter;

public class TvServerOverviewActivity extends Activity {
   private ListView mListView;
   private TvServerFeaturesAdapter mFeaturesAdapter;

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserveractivity);
      
      ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
      actionBar.setTitle("Tv Server");
      actionBar.setHomeAction(new IntentAction(this, null, R.drawable.actionbar_home));
      
      actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_remote));
      actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_search));

      
      mListView = (ListView) findViewById(R.id.ListViewItems);
      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> _adapter, View _view, int _position, long _id) {
            Object selected = mListView.getItemAtPosition(_position);
            
            if(_position == 0){
               Intent myIntent = new Intent(_view.getContext(), TvServerStateActivity.class);
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
      mFeaturesAdapter.addFeature(new TvServerFeature("Manual Control", "Program Guide", R.drawable.tvserver_manual));
      mFeaturesAdapter.addFeature(new TvServerFeature("EPG", "Program Guide", R.drawable.bubble_add));
      mFeaturesAdapter.addFeature(new TvServerFeature("Channels", "Program Guide", R.drawable.bubble_add));
      mFeaturesAdapter.addFeature(new TvServerFeature("Schedules", "Program Guide", R.drawable.tvserver_schedules));
      mFeaturesAdapter.addFeature(new TvServerFeature("Recordings", "Program Guide", R.drawable.tvserver_recordings));
      
      mListView.setAdapter(mFeaturesAdapter);
   }

}
