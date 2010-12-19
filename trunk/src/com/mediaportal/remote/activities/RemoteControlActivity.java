package com.mediaportal.remote.activities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.IClientControlListener;
import com.mediaportal.remote.api.RemoteCommands;
import com.mediaportal.remote.api.RemoteHandler;
import com.mediaportal.remote.data.commands.RemoteKey;
import com.mediaportal.remote.utils.Util;

public class RemoteControlActivity extends Activity implements IClientControlListener {
   protected class SendKeyTask extends AsyncTask<RemoteKey, String, String> {
      private RemoteHandler controller;
      private boolean repeat;
      private Context context;
      protected SendKeyTask(Context _parent){
         context = _parent;
      }

      private SendKeyTask(Context _parent, RemoteHandler _controller) {
         this(_parent);
         controller = _controller;
      }

      private SendKeyTask(Context _parent, RemoteHandler _controller, boolean _repeat) {
         this(_parent, _controller);
         controller = _controller;
         repeat = _repeat;
      }

      @Override
      protected String doInBackground(RemoteKey... arg0) {
         if (controller.isClientControlConnected()) {
            controller.sendRemoteButton((RemoteKey) arg0[0]);

            int repCount = 200;
            while (repeat) {

               try {
                  Thread.sleep(repCount);
                  if (repCount > 50)
                     repCount -= 20;// make it fasteeeeeer
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
               if (repeat)
                  controller.sendRemoteButton((RemoteKey) arg0[0]);
            }
            return null;
         }
         else{
            return "Remote not connected";
         }
      }
      
      @Override
      protected void onPostExecute(String result) {
         if(result != null){
            Toast toast = Toast.makeText(context, result, Toast.LENGTH_SHORT);
            toast.show();
         }
      }

      public void setRepeat(boolean repeat) {
         this.repeat = repeat;
      }

      public boolean getRepeat() {
         return repeat;
      }
   }

   private StatusBarActivityHandler statusBarHandler;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.remotecontrolactivity);

      final RemoteHandler remoteController = RemoteHandler.getCurrentRemoteInstance();
      remoteController.addClientControlListener(this);

      statusBarHandler = new StatusBarActivityHandler(this, remoteController);

      if (!remoteController.isClientControlConnected() && !remoteController.connectClientControl()) {
         Util.showToast(this, "Remote not connected");
      }

      final ImageButton backButton = (ImageButton) findViewById(R.id.ImageButtonBack);
      backButton.setOnTouchListener(new OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
               Util.Vibrate(v.getContext(), 30);
               new SendKeyTask(v.getContext(), remoteController).execute(RemoteCommands.backButton);
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
               Util.Vibrate(v.getContext(), 30);
               new SendKeyTask(v.getContext(), remoteController).execute(RemoteCommands.infoButton);
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

      remote.setOnTouchListener(new OnTouchListener() {
         SendKeyTask task = null;

         @Override
         public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
               Util.Vibrate(v.getContext(), 30);
               float x = event.getX();
               float y = event.getY();
               int index = getTouchPart(x, y);
               switch (index) {
               case 0:
                  remote.setImageResource(R.drawable.remote_left);
                  new SendKeyTask(v.getContext(), remoteController).execute(RemoteCommands.leftButton);
                  break;
               case 1:
                  remote.setImageResource(R.drawable.remote_right);

                  new SendKeyTask(v.getContext(), remoteController).execute(RemoteCommands.rightButton);
                  break;
               case 2:
                  remote.setImageResource(R.drawable.remote_up);
                  task = (SendKeyTask) new SendKeyTask(v.getContext(), remoteController, true)
                        .execute(RemoteCommands.upButton);
                  break;
               case 3:
                  remote.setImageResource(R.drawable.remote_down);
                  task = (SendKeyTask) new SendKeyTask(v.getContext(), remoteController, true)
                        .execute(RemoteCommands.downButton);
                  break;
               case 4:
                  remote.setImageResource(R.drawable.remote_enter);
                  new SendKeyTask(v.getContext(), remoteController).execute(RemoteCommands.okButton);
                  break;
               }

            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
               if (task != null)
                  task.setRepeat(false);
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

   @Override
   public void messageReceived(String _message) {
      //String test = _message;

   }

   @Override
   public void stateChanged(String _state) {
      statusBarHandler.setStatusText(_state);
   }
}
