package com.mediaportal.remote.activities.media;

import java.util.List;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.Series;
import com.mediaportal.remote.data.VideoShare;
import com.mediaportal.remote.utils.Util;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TabSharesActivity extends Activity {
   private ListView listView;
   private ArrayAdapter<VideoShare> listItems;
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.tabsharesactivity);

      listView = (ListView) findViewById(R.id.ListViewShares);
      listView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            VideoShare selected = (VideoShare)listView.getItemAtPosition(position);
            Util.showToast(v.getContext(), selected.Path);
            //handleListClick(v, position, selected);
         }
      });
      
      listItems = new ArrayAdapter<VideoShare>(this, android.R.layout.simple_list_item_1);
      listView.setAdapter(listItems);
      
      
      DataHandler service = DataHandler.getCurrentRemoteInstance();
      List<VideoShare> shares = service.getAllVideoShares();
      
      for(VideoShare s : shares){
         listItems.add(s);
      }
   }
}
