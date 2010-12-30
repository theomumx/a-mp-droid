package com.mediaportal.remote.activities;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.RemoteCommands;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.commands.RemoteKey;
import com.mediaportal.remote.utils.Util;

public class StatusBarActivityHandler {
   Activity parent;
   DataHandler remote;
   TextView statusText;
   ImageButton pauseButton;
   ImageButton prevButton;
   ImageButton nextButton;
   ImageButton volumeButton;
   ImageButton homeButton;
   SeekBar seekBar;
   SlidingDrawer slider;
   
   private static String statusString;

   public StatusBarActivityHandler(Activity _parent, DataHandler _remote) {
      parent = _parent;
      remote = _remote;

      slider = (SlidingDrawer) parent.findViewById(R.id.SlidingDrawerStatus);
      pauseButton = (ImageButton) parent.findViewById(R.id.ImageButtonPause);
      prevButton = (ImageButton) parent.findViewById(R.id.ImageButtonRewind);
      nextButton = (ImageButton) parent.findViewById(R.id.ImageButtonNext);
      homeButton = (ImageButton) parent.findViewById(R.id.ImageButtonHome);
      volumeButton = (ImageButton) parent.findViewById(R.id.ImageButtonVolume);

      if (pauseButton != null) {
         pauseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               sendRemoteKey(RemoteCommands.pauseButton);
            }

         });
      }

      if (prevButton != null) {
         prevButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               sendRemoteKey(RemoteCommands.prevButton);
            }
         });
      }

      if (nextButton != null) {
         nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               sendRemoteKey(RemoteCommands.nextButton);
            }
         });
      }

      if (homeButton != null) {
         homeButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                  Util.Vibrate(parent, 70);
               }
               return false;
            }
         });
         homeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

               if (!parent.getClass().equals(HomeActivity.class)) {
                  slider.close();
                  parent.finish();
               } else {
                  slider.animateClose();
               }
            }
         });
      }

      if (volumeButton != null) {
         volumeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               if (seekBar.getVisibility() == View.VISIBLE) {
                  setControlsVisibility(true);
               } else {
                  setControlsVisibility(false);
               }
            }

         });
      }

      statusText = (TextView) parent.findViewById(R.id.TextViewSliderSatusText);
      seekBar = (SeekBar) parent.findViewById(R.id.SeekBarVolume);

      if (seekBar != null) {
         seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
               if (!remote.isClientControlConnected()) {
                  Util.showToast(parent, "Remote not connected");
               }
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if (fromUser) {
                  int seekValue = seekBar.getProgress();// between 0 - 20
                  Util.Vibrate(parent, seekValue * 2);
                  if (remote.isClientControlConnected()) {
                     remote.sendClientVolume((int) seekValue * 5);
                  }
               }
            }
         });
      }
   }

   private void sendRemoteKey(RemoteKey _button) {
      Util.Vibrate(parent, 50);
      if (remote.isClientControlConnected()) {
         remote.sendRemoteButton(_button);
      } else {
         Util.showToast(parent, "Remote not connected");
      }

   }

   private void setControlsVisibility(boolean _visibility) {
      if (_visibility) {
         if (prevButton != null)
            prevButton.setVisibility(View.VISIBLE);
         if (nextButton != null)
            nextButton.setVisibility(View.VISIBLE);
         if (pauseButton != null)
            pauseButton.setVisibility(View.VISIBLE);
         if (homeButton != null)
            homeButton.setVisibility(View.VISIBLE);

         if (seekBar != null)
            seekBar.setVisibility(View.INVISIBLE);
      } else {
         if (prevButton != null)
            prevButton.setVisibility(View.INVISIBLE);
         if (nextButton != null)
            nextButton.setVisibility(View.INVISIBLE);
         if (pauseButton != null)
            pauseButton.setVisibility(View.INVISIBLE);
         if (homeButton != null)
            homeButton.setVisibility(View.INVISIBLE);

         if (seekBar != null)
            seekBar.setVisibility(View.VISIBLE);
      }
   }

   protected void setStatusText(String _text) {
      if (statusText != null) {
         StatusBarActivityHandler.statusString = _text;
      }
      statusText.setText(StatusBarActivityHandler.statusString);
   }

   public void setupRemoteStatus() {
      if (statusText != null) {
         //TODO: use async-task for this, also handle nowplaying here
         if (remote.isClientControlConnected() || remote.connectClientControl()) {
            //statusText.setText("Remote connected...");
         } else {
            Util.showToast(parent, "Remote not connected");
            StatusBarActivityHandler.statusString = "Remote not connected...";
         }
         statusText.setText(StatusBarActivityHandler.statusString);
      }
   }
}
