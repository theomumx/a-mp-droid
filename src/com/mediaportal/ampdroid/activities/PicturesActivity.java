package com.mediaportal.remote.activities;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.ItemDownloaderService;

public class PicturesActivity extends Activity {
   private ItemDownloaderReceiver mReceiver;
   
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.picturesactivity);
      
      IntentFilter filter = new IntentFilter(ItemDownloaderService.ITEM_DOWNLOAD_PROGRESSED);
      mReceiver = new ItemDownloaderReceiver();
      registerReceiver(mReceiver, filter);
      
      
      Intent service = new Intent(this, ItemDownloaderService.class);
      
      //DataHandler service = DataHandler.getCurrentRemoteInstance();
      
      
      try {
         service.putExtra("url", "http://10.1.0.247:4322/json/FS_GetMediaItem/?path=" + URLEncoder.encode("\\\\Bagga-server\\e\\[- Series -]\\Bionic Woman\\Season.01\\Bionic.Woman - S01E02 - HDTV.XviD-XOR.avi", "UTF-8"));
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      startService(service);
   }
   
   public class ItemDownloaderReceiver extends BroadcastReceiver {

      @Override
      public void onReceive(Context context, Intent intent) {
         Log.d("ItemDownloader", "xx %");

      }

   }
}
