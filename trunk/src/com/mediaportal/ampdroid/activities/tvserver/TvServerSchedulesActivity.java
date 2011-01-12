package com.mediaportal.ampdroid.activities.tvserver;

import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvSchedule;
import com.mediaportal.ampdroid.R;
public class TvServerSchedulesActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setHome(false);
      setTitle(R.string.title_tvserver_schedules);
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
