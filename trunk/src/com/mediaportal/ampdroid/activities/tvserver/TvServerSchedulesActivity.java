package com.mediaportal.ampdroid.activities.tvserver;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvSchedule;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.views.TvServerSchedulesDetailsView;
public class TvServerSchedulesActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   private LazyLoadingAdapter mAdapter;
   private UpdateSchedulesTask mRecordingsUpdater;
   
   private class UpdateSchedulesTask extends AsyncTask<Integer, Integer, List<TvSchedule>> {
      @Override
      protected List<TvSchedule> doInBackground(Integer... _params) {
         List<TvSchedule> recordings = mService.getTvSchedules();
         return recordings;
      }

      @Override
      protected void onPostExecute(List<TvSchedule> _result) {
         if (_result != null) {
            for(TvSchedule s : _result){
               mAdapter.AddItem(new TvServerSchedulesDetailsView(s));
            }

            mListView.setAdapter(mAdapter);
         }
         mAdapter.showLoadingItem(false);
      }
   }
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setHome(false);
      setTitle(R.string.title_tvserver_schedules);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverschedulesactivity);
      
      mListView = (ListView) findViewById(R.id.ListViewSchedules);
      
      mService = DataHandler.getCurrentRemoteInstance();
      mAdapter = new LazyLoadingAdapter(this);
      mListView.setAdapter(mAdapter);
      mService = DataHandler.getCurrentRemoteInstance();
      
      refreshSchedules();
   }
   
   private void refreshSchedules(){
      mAdapter.showLoadingItem(true);
      mAdapter.setLoadingText("Loading Schedules ...");
      mRecordingsUpdater = new UpdateSchedulesTask();
      mRecordingsUpdater.execute(0);
   }
}
