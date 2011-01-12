package com.mediaportal.ampdroid.activities.tvserver;

import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvRecording;
import com.mediaportal.ampdroid.R;
public class TvServerRecordingsActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setTitle(R.string.title_tvserver_recordings);
      setHome(false);
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
