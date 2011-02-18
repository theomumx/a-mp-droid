package com.mediaportal.ampdroid.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.tvserver.TvServerChannelsActivity;
import com.mediaportal.ampdroid.activities.tvserver.TvServerEpgActivity;
import com.mediaportal.ampdroid.activities.tvserver.TvServerRecordingsActivity;
import com.mediaportal.ampdroid.activities.tvserver.TvServerSchedulesActivity;
import com.mediaportal.ampdroid.activities.tvserver.TvServerStateActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.lists.TvServerFeature;
import com.mediaportal.ampdroid.lists.TvServerFeaturesAdapter;

public class TvServerOverviewActivity extends BaseActivity {
   private ListView mListView;
   private TvServerFeaturesAdapter mFeaturesAdapter;
   private DataHandler mService;
   private StatusBarActivityHandler statusBarHandler;

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserveractivity);

      mService = DataHandler.getCurrentRemoteInstance();
      statusBarHandler = new StatusBarActivityHandler(this, mService);
      statusBarHandler.setHome(false);

      if (mService.isTvServiceActive()) {
         mListView = (ListView) findViewById(R.id.ListViewItems);
         mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> _adapter, View _view, int _position, long _id) {
               if (_position == 0) {
                  Intent myIntent = new Intent(_view.getContext(), TvServerStateActivity.class);
                  startActivity(myIntent);
               }

               if (_position == 1) {
                  Intent myIntent = new Intent(_view.getContext(), TvServerEpgActivity.class);
                  startActivity(myIntent);
               }

               if (_position == 2) {
                  Intent myIntent = new Intent(_view.getContext(), TvServerChannelsActivity.class);
                  startActivity(myIntent);
               }

               if (_position == 3) {
                  Intent myIntent = new Intent(_view.getContext(), TvServerSchedulesActivity.class);
                  startActivity(myIntent);
               }

               if (_position == 4) {
                  Intent myIntent = new Intent(_view.getContext(), TvServerRecordingsActivity.class);
                  startActivity(myIntent);
               }
            }
         });

         mFeaturesAdapter = new TvServerFeaturesAdapter(this);
         mFeaturesAdapter.addFeature(new TvServerFeature("Manual Control",
               "Current state of the tv service and the tv cards.", R.drawable.tvserver_manual));
         mFeaturesAdapter.addFeature(new TvServerFeature("EPG", "Electronic Program Guide",
               R.drawable.tvserver_tv));
         mFeaturesAdapter.addFeature(new TvServerFeature("Channels",
               "All available Channels on the server", R.drawable.tvserver_tv));
         mFeaturesAdapter.addFeature(new TvServerFeature("Schedules",
               "All scheduled recordings on the server", R.drawable.tvserver_schedules));
         mFeaturesAdapter.addFeature(new TvServerFeature("Recordings",
               "All recordings on the server", R.drawable.tvserver_recordings));

         mListView.setAdapter(mFeaturesAdapter);
      } else {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("Couldn't connect to tv server!");
         builder.setMessage("Couldn't connect to tv server!");
         builder.setCancelable(false);
         builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                         TvServerOverviewActivity.this.finish();
                    }
                });

         AlertDialog alert = builder.create();
         alert.show();
      }
   }

}
