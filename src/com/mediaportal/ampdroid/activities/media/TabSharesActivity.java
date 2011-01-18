package com.mediaportal.ampdroid.activities.media;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.VideoShare;
import com.mediaportal.ampdroid.utils.Util;
public class TabSharesActivity extends Activity {
   private ListView mListView;
   private ArrayAdapter<VideoShare> mListItems;
   private LoadSharesTask mSeriesLoaderTask;
   private DataHandler mService;

   private class LoadSharesTask extends AsyncTask<Integer, Integer, List<VideoShare>> {
      @Override
      protected List<VideoShare> doInBackground(Integer... _params) {
         List<VideoShare> shares = mService.getAllVideoShares();

         return shares;
      }

      @Override
      protected void onPostExecute(List<VideoShare> _result) {
         for(VideoShare s : _result){
            mListItems.add(s);
         }
         
         mListItems.notifyDataSetChanged();
      }
   }
   
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tabsharesactivity);

      mListView = (ListView) findViewById(R.id.ListViewShares);
      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            VideoShare selected = (VideoShare)mListView.getItemAtPosition(position);
            Util.showToast(v.getContext(), selected.Path);
            //handleListClick(v, position, selected);
         }
      });
      
      mListItems = new ArrayAdapter<VideoShare>(this, android.R.layout.simple_list_item_1);
      mListView.setAdapter(mListItems);
      
      mService = DataHandler.getCurrentRemoteInstance();
      
      mSeriesLoaderTask = new LoadSharesTask();
      mSeriesLoaderTask.execute(0);

   }
}
