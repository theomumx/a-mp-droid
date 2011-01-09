package com.mediaportal.remote.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.TvRecording;

public class TvServerRecordingsActivity extends Activity {
   private DataHandler mService;
   private ListView mListView;
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverrecordingsactivity);
      mListView = (ListView) findViewById(R.id.ListViewRecordings);
      
      mService = DataHandler.getCurrentRemoteInstance();
      if (mService.isTvServiceActive()) {
         List<TvRecording> recordings = mService.getTvRecordings();
         if (recordings != null) {
            ArrayAdapter<TvRecording> adapter = new ArrayAdapter<TvRecording>(this,
                  android.R.layout.simple_list_item_1);
            
            for(TvRecording r : recordings){
               adapter.add(r);
            }

            mListView.setAdapter(adapter);
         }
      }
   }
}
