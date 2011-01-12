package com.mediaportal.remote.activities.tvserver;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.BaseActivity;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.TvChannel;
import com.mediaportal.remote.data.TvChannelGroup;

public class TvServerChannelsActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   private Spinner mGroupsSpinner;
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setHome(false);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverchannelsactivity);
      mListView = (ListView) findViewById(R.id.ListViewChannels);
      mGroupsSpinner = (Spinner)findViewById(R.id.SpinnerGroups);
      mService = DataHandler.getCurrentRemoteInstance();
      
      if (mService.isTvServiceActive()) {
         List<TvChannelGroup> groups = mService.getTvChannelGroups();
         if (groups != null) {
            ArrayAdapter<TvChannelGroup> adapter = new ArrayAdapter<TvChannelGroup>(this,
                  android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mGroupsSpinner.setAdapter(adapter);
         }
      }
      
      mGroupsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

         @Override
         public void onItemSelected(AdapterView<?> _adapter, View _view, int _position, long _id) {
            TvChannelGroup group = (TvChannelGroup) mGroupsSpinner.getItemAtPosition(_position);
            
            fillChannelListView(group);
         }

         @Override
         public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
         }
      });
   }
   

   private void fillChannelListView(TvChannelGroup _group) {
     List<TvChannel> channels = mService.getTvChannelsForGroup(_group.getIdGroup());
     
     if (channels != null) {
        ArrayAdapter<TvChannel> adapter = new ArrayAdapter<TvChannel>(this,
              android.R.layout.simple_list_item_1);
        
        for(TvChannel c : channels){
           adapter.add(c);
        }

        mListView.setAdapter(adapter);
     }
      
   }
}
