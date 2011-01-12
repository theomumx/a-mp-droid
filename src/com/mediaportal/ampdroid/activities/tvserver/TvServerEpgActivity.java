package com.mediaportal.remote.activities.tvserver;

import android.os.Bundle;
import android.widget.ListView;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.BaseActivity;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.utils.Util;

public class TvServerEpgActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setHome(false);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverschedulesactivity);
      mListView = (ListView) findViewById(R.id.ListViewSchedules);
      
      mService = DataHandler.getCurrentRemoteInstance();
      
      Util.showToast(this, "not implemented yet");
   }
}
