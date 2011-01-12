package com.mediaportal.remote.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.api.IClientControlListener;
import com.mediaportal.remote.api.RemoteCommands;
import com.mediaportal.remote.data.commands.RemoteKey;
import com.mediaportal.remote.utils.Util;

public class RemoteControlActivity extends Activity implements IClientControlListener {
   protected class SendKeyTask extends AsyncTask<RemoteKey, String, String> {
      private DataHandler mController;
      private boolean mRepeat;
      private Context mContext;
      protected SendKeyTask(Context _parent){
         mContext = _parent;
      }

      private SendKeyTask(Context _parent, DataHandler _controller) {
         this(_parent);
         mController = _controller;
      }

      private SendKeyTask(Context _parent, DataHandler _controller, boolean _repeat) {
         this(_parent, _controller);
         mController = _controller;
         mRepeat = _repeat;
      }

      @Override
      protected String doInBackground(RemoteKey... _keys) {
         if (mController.isClientControlConnected()) {
            mController.sendRemoteButton((RemoteKey) _keys[0]);

            int repCount = 200;
            while (mRepeat) {

               try {
                  Thread.sleep(repCount);
                  if (repCount > 50)
                     repCount -= 20;// make it fasteeeeeer
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
               if (mRepeat)
                  mController.sendRemoteButton((RemoteKey) _keys[0]);
            }
            return null;
         }
         else{
            return "Remote not connected";
         }
      }
      
      @Override
      protected void onPostExecute(String _result) {
         if(_result != null){
            Util.showToast(mContext, _result);
         }
      }

      public void setRepeat(boolean _repeat) {
         this.mRepeat = _repeat;
      }

      public boolean getRepeat() {
         return mRepeat;
      }
   }

   private StatusBarActivityHandler mStatusBarHandler;

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.remotecontrolactivity);

      final DataHandler remoteController = DataHandler.getCurrentRemoteInstance();
      remoteController.addClientControlListener(this);

      mStatusBarHandler = new StatusBarActivityHandler(this, remoteController);
      mStatusBarHandler.setupRemoteStatus();

      final ImageButton backButton = (ImageButton) findViewById(R.id.ImageButtonBack);
      backButton.setOnTouchListener(new OnTouchListener() {
         @Override
         public boolean onTouch(View _view, MotionEvent _event) {
            if (_event.getAction() == MotionEvent.ACTION_DOWN) {
               Util.Vibrate(_view.getContext(), 30);
               new SendKeyTask(_view.getContext(), remoteController).execute(RemoteCommands.backButton);
               backButton.setImageResource(R.drawable.remote_back_sel);
               return true;
            }
            if (_event.getAction() == MotionEvent.ACTION_UP) {
               backButton.setImageResource(R.drawable.remote_back);
               return true;
            }
            return false;
         }
      });

      final ImageButton infoButton = (ImageButton) findViewById(R.id.ImageButtonInfo);
      infoButton.setOnTouchListener(new OnTouchListener() {
         @Override
         public boolean onTouch(View _view, MotionEvent _event) {
            if (_event.getAction() == MotionEvent.ACTION_DOWN) {
               Util.Vibrate(_view.getContext(), 30);
               new SendKeyTask(_view.getContext(), remoteController).execute(RemoteCommands.infoButton);
               infoButton.setImageResource(R.drawable.remote_info_sel);
               return true;
            }
            if (_event.getAction() == MotionEvent.ACTION_UP) {
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
         public boolean onTouch(View _view, MotionEvent _event) {
            if (_event.getAction() == MotionEvent.ACTION_DOWN) {
               Util.Vibrate(_view.getContext(), 30);
               float x = _event.getX();
               float y = _event.getY();
               int index = getTouchPart(x, y);
               switch (index) {
               case 0:
                  remote.setImageResource(R.drawable.remote_left);
                  new SendKeyTask(_view.getContext(), remoteController).execute(RemoteCommands.leftButton);
                  break;
               case 1:
                  remote.setImageResource(R.drawable.remote_right);

                  new SendKeyTask(_view.getContext(), remoteController).execute(RemoteCommands.rightButton);
                  break;
               case 2:
                  remote.setImageResource(R.drawable.remote_up);
                  task = (SendKeyTask) new SendKeyTask(_view.getContext(), remoteController, true)
                        .execute(RemoteCommands.upButton);
                  break;
               case 3:
                  remote.setImageResource(R.drawable.remote_down);
                  task = (SendKeyTask) new SendKeyTask(_view.getContext(), remoteController, true)
                        .execute(RemoteCommands.downButton);
                  break;
               case 4:
                  remote.setImageResource(R.drawable.remote_enter);
                  new SendKeyTask(_view.getContext(), remoteController).execute(RemoteCommands.okButton);
                  break;
               }

            }
            if (_event.getAction() == MotionEvent.ACTION_UP) {
               if (task != null)
                  task.setRepeat(false);
               remote.setImageResource(R.drawable.remote_default);
            }
            return false;
         }

         private int getTouchPart(float _x, float _y) {
            int width = remote.getWidth();
            int height = remote.getHeight();

            if (_x < width / 3 && _y > height / 6 && _y < height - height / 6) {
               return 0;
            }

            if (_x > width * 0.66 && _y > height / 6 && _y < height - height / 6) {
               return 1;
            }

            if (_y < height / 3 && _x > width / 6 && _x < width - width / 6) {
               return 2;
            }

            if (_y > height * 0.66 && _x > width / 6 && _x < width - width / 6) {
               return 3;
            }

            if (_x > width / 3 && _x < width * 0.66 && _y > height / 3 && _y < height * 0.66) {
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
      mStatusBarHandler.setStatusText(_state);
   }
   
   @Override
   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      Window window = getWindow();
      window.setFormat(PixelFormat.RGBA_8888);
   }
}
