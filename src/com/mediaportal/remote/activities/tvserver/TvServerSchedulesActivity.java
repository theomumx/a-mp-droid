package com.mediaportal.remote.activities.tvserver;

import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.BaseActivity;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.TvSchedule;

public class TvServerSchedulesActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setHome(false);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverschedulesactivity);
      mListView = (ListView) findViewById(R.id.ListViewSchedules);
      
      mService = DataHandler.getCurrentRemoteInstance();
      if (mService.isTvServiceActive()) {
         List<TvSchedule> schedules = mService.getTvSchedules();
         if (schedules != null) {
            ArrayAdapter<TvSchedule> adapter = new ArrayAdapter<TvSchedule>(this,
                  android.R.layout.simple_list_item_1);
            
            for(TvSchedule r : schedules){
               adapter.add(r);
            }

            mListView.setAdapter(adapter);
         }
      }
   }
}
