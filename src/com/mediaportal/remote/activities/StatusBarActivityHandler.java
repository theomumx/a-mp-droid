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
   Activity mParent;
   DataHandler mRemote;
   TextView mStatusText;
   ImageButton mPauseButton;
   ImageButton mPrevButton;
   ImageButton mNextButton;
   ImageButton mVolumeButton;
   ImageButton mHomeButton;
   SeekBar mSeekBar;
   SlidingDrawer mSlider;
   
   private static String statusString;

   public StatusBarActivityHandler(Activity _parent, DataHandler _remote) {
      mParent = _parent;
      mRemote = _remote;

      mSlider = (SlidingDrawer) mParent.findViewById(R.id.SlidingDrawerStatus);
      mPauseButton = (ImageButton) mParent.findViewById(R.id.ImageButtonPause);
      mPrevButton = (ImageButton) mParent.findViewById(R.id.ImageButtonRewind);
      mNextButton = (ImageButton) mParent.findViewById(R.id.ImageButtonNext);
      mHomeButton = (ImageButton) mParent.findViewById(R.id.ImageButtonHome);
      mVolumeButton = (ImageButton) mParent.findViewById(R.id.ImageButtonVolume);

      if (mPauseButton != null) {
         mPauseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               sendRemoteKey(RemoteCommands.pauseButton);
            }

         });
      }

      if (mPrevButton != null) {
         mPrevButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View _view) {
               sendRemoteKey(RemoteCommands.prevButton);
            }
         });
      }

      if (mNextButton != null) {
         mNextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View _view) {
               sendRemoteKey(RemoteCommands.nextButton);
            }
         });
      }

      if (mHomeButton != null) {
         mHomeButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View _view, MotionEvent _event) {
               if (_event.getAction() == MotionEvent.ACTION_DOWN) {
                  Util.Vibrate(mParent, 70);
               }
               return false;
            }
         });
         mHomeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View _view) {

               if (!mParent.getClass().equals(HomeActivity.class)) {
                  mSlider.close();
                  mParent.finish();
               } else {
                  mSlider.animateClose();
               }
            }
         });
      }

      if (mVolumeButton != null) {
         mVolumeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View _view) {
               if (mSeekBar.getVisibility() == View.VISIBLE) {
                  setControlsVisibility(true);
               } else {
                  setControlsVisibility(false);
               }
            }

         });
      }

      mStatusText = (TextView) mParent.findViewById(R.id.TextViewSliderSatusText);
      mSeekBar = (SeekBar) mParent.findViewById(R.id.SeekBarVolume);

      if (mSeekBar != null) {
         mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar _seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar _seekBar) {
               if (!mRemote.isClientControlConnected()) {
                  Util.showToast(mParent, "Remote not connected");
               }
            }

            @Override
            public void onProgressChanged(SeekBar _seekBar, int _progress, boolean _fromUser) {
               if (_fromUser) {
                  int seekValue = _seekBar.getProgress();// between 0 - 20
                  Util.Vibrate(mParent, seekValue * 2);
                  if (mRemote.isClientControlConnected()) {
                     mRemote.sendClientVolume((int) seekValue * 5);
                  }
               }
            }
         });
      }
   }

   private void sendRemoteKey(RemoteKey _button) {
      Util.Vibrate(mParent, 50);
      if (mRemote.isClientControlConnected()) {
         mRemote.sendRemoteButton(_button);
      } else {
         Util.showToast(mParent, "Remote not connected");
      }

   }

   private void setControlsVisibility(boolean _visibility) {
      if (_visibility) {
         if (mPrevButton != null)
            mPrevButton.setVisibility(View.VISIBLE);
         if (mNextButton != null)
            mNextButton.setVisibility(View.VISIBLE);
         if (mPauseButton != null)
            mPauseButton.setVisibility(View.VISIBLE);
         if (mHomeButton != null)
            mHomeButton.setVisibility(View.VISIBLE);

         if (mSeekBar != null)
            mSeekBar.setVisibility(View.INVISIBLE);
      } else {
         if (mPrevButton != null)
            mPrevButton.setVisibility(View.INVISIBLE);
         if (mNextButton != null)
            mNextButton.setVisibility(View.INVISIBLE);
         if (mPauseButton != null)
            mPauseButton.setVisibility(View.INVISIBLE);
         if (mHomeButton != null)
            mHomeButton.setVisibility(View.INVISIBLE);

         if (mSeekBar != null)
            mSeekBar.setVisibility(View.VISIBLE);
      }
   }

   protected void setStatusText(String _text) {
      if (mStatusText != null) {
         StatusBarActivityHandler.statusString = _text;
      }
      mStatusText.setText(StatusBarActivityHandler.statusString);
   }

   public void setupRemoteStatus() {
      if (mStatusText != null) {
         //TODO: use async-task for this, also handle nowplaying here
         if (mRemote.isClientControlConnected() || mRemote.connectClientControl()) {
            //statusText.setText("Remote connected...");
         } else {
            Util.showToast(mParent, "Remote not connected");
            StatusBarActivityHandler.statusString = "Remote not connected...";
         }
         mStatusText.setText(StatusBarActivityHandler.statusString);
      }
   }
}
