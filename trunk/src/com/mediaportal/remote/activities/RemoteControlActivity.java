package com.mediaportal.remote.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.mpclient.MPClientController;
import com.mediaportal.remote.api.mpclient.RemoteCommands;
import com.mediaportal.remote.data.commands.RemoteKey;

public class RemoteControlActivity extends Activity {
   private class DownloadImageTask extends AsyncTask {
      MPClientController contr = new MPClientController();
      protected void onPostExecute(Object result) {
         //mImageView.setImageBitmap(result);
      }

      @Override
      protected Object doInBackground(Object... arg0) {
         contr.SendKey((RemoteKey)arg0[0]);
         return null;
      }
   }

   
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.remotecontrolactivity);
      final AsyncTask execute = new DownloadImageTask();
      final ImageButton backButton = (ImageButton) findViewById(R.id.ImageButtonBack);
      backButton.setOnTouchListener(new OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
               new DownloadImageTask().execute(RemoteCommands.backButton);
               backButton.setImageResource(R.drawable.remote_back_sel);
               return true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
               backButton.setImageResource(R.drawable.remote_back);
               return true;
            }
            return false;
         }
      });

      final ImageButton infoButton = (ImageButton) findViewById(R.id.ImageButtonInfo);
      infoButton.setOnTouchListener(new OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
               new DownloadImageTask().execute(RemoteCommands.infoButton);
               infoButton.setImageResource(R.drawable.remote_info_sel);
               return true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
               infoButton.setImageResource(R.drawable.remote_info);
               return true;
            }
            return false;
         }
      });

      final ImageButton remote = (ImageButton) findViewById(R.id.ImageButtonArrows);

      remote.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            // remote.setimage
         }
      });

      remote.setOnTouchListener(new OnTouchListener() {

         @Override
         public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
               float x = event.getX();
               float y = event.getY();
               int index = getTouchPart(x, y);
               switch (index) {
               case 0:
                  remote.setImageResource(R.drawable.remote_left);
                  new DownloadImageTask().execute(RemoteCommands.leftButton);
                  break;
               case 1:
                  remote.setImageResource(R.drawable.remote_right);

                  new DownloadImageTask().execute(RemoteCommands.rightButton);
                  break;
               case 2:
                  remote.setImageResource(R.drawable.remote_up);
                  new DownloadImageTask().execute(RemoteCommands.upButton);
                  break;
               case 3:
                  remote.setImageResource(R.drawable.remote_down);
                  new DownloadImageTask().execute(RemoteCommands.downButton);
                  break;
               case 4:
                  remote.setImageResource(R.drawable.remote_enter);
                  new DownloadImageTask().execute(RemoteCommands.okButton);
                  break;
               }

            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
               remote.setImageResource(R.drawable.remote_default);
            }
            return false;
         }

         private int getTouchPart(float x, float y) {
            int width = remote.getWidth();
            int height = remote.getHeight();

            if (x < width / 3 && y > height / 6 && y < height - height / 6) {
               return 0;
            }

            if (x > width * 0.66 && y > height / 6 && y < height - height / 6) {
               return 1;
            }

            if (y < height / 3 && x > width / 6 && x < width - width / 6) {
               return 2;
            }

            if (y > height * 0.66 && x > width / 6 && x < width - width / 6) {
               return 3;
            }

            if (x > width / 3 && x < width * 0.66 && y > height / 3 && y < height * 0.66) {
               return 4;// middle
            }
            return -99;
         }

      });

   }

}
