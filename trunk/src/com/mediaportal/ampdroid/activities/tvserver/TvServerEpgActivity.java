package com.mediaportal.ampdroid.activities.tvserver;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.activities.StatusBarActivityHandler;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvChannel;
import com.mediaportal.ampdroid.data.TvProgram;
import com.mediaportal.ampdroid.data.TvProgramBase;
import com.mediaportal.ampdroid.utils.Util;

public class TvServerEpgActivity extends BaseActivity {
   private DataHandler mService;
   private LinearLayout mEpgView;
   private LoadEpgTask mEpgLoaderTask;
   private StatusBarActivityHandler mStatusBarHandler;

   private class LoadEpgTask extends AsyncTask<Integer, LinearLayout, Boolean> {
      private Context mContext;
      private LoadEpgTask(Context _context){
         mContext = _context;
      }
      
      @Override
      protected Boolean doInBackground(Integer... _params) {
         List<TvChannel> channels = mService.getTvChannelsForGroup(2);
         Date begin = new Date();
         
         Calendar cal = Calendar.getInstance();
         cal.add(Calendar.DATE, 1);
         Date end = cal.getTime();
         
         
         for(TvChannel c : channels){
            List<TvProgramBase> programs = mService.getTvBaseEpgForChannel(c.getIdChannel(), begin, end);
            LinearLayout layout = new LinearLayout(mContext);
            
            for (TvProgramBase p : programs) {
               Button epgButton = new Button(mContext);
               epgButton.setText(p.getTitle());
               layout.addView(epgButton);
            }
            
            publishProgress(layout);
         }

         return true;
      }

      @Override
      protected void onProgressUpdate(LinearLayout... values) {
         if (values != null) {
            LinearLayout epgForChannel = values[0];
            mEpgView.addView(epgForChannel);
         }
         super.onProgressUpdate(values);
      }

      @Override
      protected void onPostExecute(Boolean _result) {

      }
   }
   
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setTitle(R.string.title_tvserver_epg);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverepgactivity);
      mEpgView = (LinearLayout) findViewById(R.id.LinearLayoutEpgView);
      
      mService = DataHandler.getCurrentRemoteInstance();
      mStatusBarHandler = new StatusBarActivityHandler(this, mService);
      mStatusBarHandler.setHome(false);
      
      Util.showToast(this, "not implemented yet");
      
      /*
      LinearLayout layout1 = new LinearLayout(this);
      
      Button button = new Button(this);
      button.setWidth(120);
      button.setText("Button1");
      
      Button button2 = new Button(this);
      button2.setWidth(200);
      button2.setText("Button2");
      
      Button button3 = new Button(this);
      button2.setWidth(100);
      button3.setText("Button3");
      
      layout1.addView(button);
      layout1.addView(button2);
      layout1.addView(button3);
      
      LinearLayout layout2 = new LinearLayout(this);
      
      Button button4 = new Button(this);
      button4.setWidth(100);
      button4.setText("Button4");
      
      Button button5 = new Button(this);
      button5.setWidth(200);
      button5.setText("Button5");
      
      Button button6 = new Button(this);
      button6.setWidth(300);
      button6.setText("Button6");
      
      layout2.addView(button4);
      layout2.addView(button5);
      layout2.addView(button6);
      
      mEpgView.addView(layout1);
      mEpgView.addView(layout2);
      */
      
      refreshEpg();
   }

   private void refreshEpg() {
      mEpgLoaderTask = new LoadEpgTask(this);
      mEpgLoaderTask.execute(0);
      
   }
}
