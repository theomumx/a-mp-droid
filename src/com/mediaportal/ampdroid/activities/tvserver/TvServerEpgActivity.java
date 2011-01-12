package com.mediaportal.ampdroid.activities.tvserver;

import android.os.Bundle;
import android.widget.ListView;

import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.utils.Util;
import com.mediaportal.ampdroid.R;
public class TvServerEpgActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setHome(false);
      setTitle(R.string.title_tvserver_epg);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverschedulesactivity);
      mListView = (ListView) findViewById(R.id.ListViewSchedules);
      
      mService = DataHandler.getCurrentRemoteInstance();
      
      Util.showToast(this, "not implemented yet");
   }
}
