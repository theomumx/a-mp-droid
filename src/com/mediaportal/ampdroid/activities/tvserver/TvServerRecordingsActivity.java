package com.mediaportal.ampdroid.activities.tvserver;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvRecording;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.views.TvServerRecordingsThumbsView;
public class TvServerRecordingsActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   private LazyLoadingAdapter mAdapter;
   private UpdateRecordingsTask mRecordingsUpdater;
   
   private class UpdateRecordingsTask extends AsyncTask<Integer, Integer, List<TvRecording>> {
      @Override
      protected List<TvRecording> doInBackground(Integer... _params) {
         List<TvRecording> recordings = mService.getTvRecordings();
         return recordings;
      }

      @Override
      protected void onPostExecute(List<TvRecording> _result) {
         if (_result != null) {
            for(TvRecording r : _result){
               mAdapter.AddItem(new TvServerRecordingsThumbsView(r));
            }

            mListView.setAdapter(mAdapter);
         }
         mAdapter.showLoadingItem(false);
      }
   }
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setTitle(R.string.title_tvserver_recordings);
      setHome(false);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverrecordingsactivity);
      mListView = (ListView) findViewById(R.id.ListViewRecordings);
      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            mAdapter.showLoadingItem(false);
            mAdapter.notifyDataSetChanged();
            
         }
      });
      mAdapter = new LazyLoadingAdapter(this);
      mListView.setAdapter(mAdapter);
      mService = DataHandler.getCurrentRemoteInstance();
      
      refreshRecordings();
   }
   
   private void refreshRecordings(){
      mAdapter.showLoadingItem(true);
      mAdapter.setLoadingText("Loading Recordings ...");
      mRecordingsUpdater = new UpdateRecordingsTask();
      mRecordingsUpdater.execute(0);
   }
}
