package com.mediaportal.ampdroid.activities.tvserver;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvChannel;
import com.mediaportal.ampdroid.data.TvChannelGroup;

public class TvServerChannelsActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   private Spinner mGroupsSpinner;

   ArrayAdapter<TvChannel> mChannelItems;
   ArrayAdapter<TvChannelGroup> mGroupsItems;
   UpdateGroupsTask mGroupsUpdater;
   UpdateChannelsTask mChannelUpdater;
   ProgressDialog mLoadingDialog;

   boolean mShowAllGroup = false;

   private class UpdateGroupsTask extends AsyncTask<Integer, Integer, List<TvChannelGroup>> {
      @Override
      protected List<TvChannelGroup> doInBackground(Integer... _group) {
         ArrayList<TvChannelGroup> groups = mService.getTvChannelGroups();
         return groups;
      }

      @Override
      protected void onPostExecute(List<TvChannelGroup> _result) {
         mGroupsItems.clear();
         if (_result != null) {
            for (TvChannelGroup g : _result) {
               if (mShowAllGroup || g.getIdGroup() != 1) {
                  mGroupsItems.add(g);
               }
            }
         }
      }
   }

   private class UpdateChannelsTask extends AsyncTask<TvChannelGroup, Integer, List<TvChannel>> {
      @Override
      protected List<TvChannel> doInBackground(TvChannelGroup... _group) {
         List<TvChannel> channels = mService.getTvChannelsForGroup(_group[0].getIdGroup()); // get
         return channels;
      }

      @Override
      protected void onPostExecute(List<TvChannel> _result) {
         if (_result != null) {
            mChannelItems.clear();

            for (TvChannel c : _result) {
               mChannelItems.add(c);
            }
         }
         mLoadingDialog.cancel();
      }
   }

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setHome(false);
      setTitle(R.string.title_tvserver_channels);

      setContentView(R.layout.tvserverchannelsactivity);
      mListView = (ListView) findViewById(R.id.ListViewChannels);
      mChannelItems = new ArrayAdapter<TvChannel>(this, android.R.layout.simple_list_item_1);
      mListView.setAdapter(mChannelItems);
      
      mGroupsSpinner = (Spinner) findViewById(R.id.SpinnerGroups);
      mGroupsItems = new ArrayAdapter<TvChannelGroup>(this, android.R.layout.simple_spinner_item);
      mGroupsItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      mGroupsSpinner.setAdapter(mGroupsItems);

      mService = DataHandler.getCurrentRemoteInstance();

      mGroupsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> _adapter, View _view, int _position, long _id) {
            TvChannelGroup group = (TvChannelGroup) mGroupsSpinner.getItemAtPosition(_position);

            refreshChannelList(group);
         }

         @Override
         public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
         }
      });
      
      mListView.setOnItemClickListener(new OnItemClickListener(){
         @Override
         public void onItemClick(AdapterView<?> _adapter, View _view, int _pos, long _id) {
            TvChannel channel = mChannelItems.getItem(_pos);
            
            Intent myIntent = new Intent(_view.getContext(), TvServerChannelDetailsActivity.class);
            myIntent.putExtra("channel_id", channel.getIdChannel());
            myIntent.putExtra("channel_name", channel.getDisplayName());
            startActivity(myIntent);
         }
      });
      
      refreshGroups();
   }

   private void refreshGroups() {
      mLoadingDialog = ProgressDialog.show(TvServerChannelsActivity.this, " Loading Groups ",
            " Loading. Please wait ... ", true);
      mLoadingDialog.setCancelable(true);

      mGroupsUpdater = new UpdateGroupsTask();
      mGroupsUpdater.execute(0);
   }

   private void refreshChannelList(TvChannelGroup _group) {
      mLoadingDialog.cancel();
      mLoadingDialog = ProgressDialog.show(TvServerChannelsActivity.this, " Loading Channels ",
            " Loading. Please wait ... ", true);
      mLoadingDialog.setCancelable(true);

      mChannelUpdater = new UpdateChannelsTask();
      mChannelUpdater.execute(_group);
   }
}
