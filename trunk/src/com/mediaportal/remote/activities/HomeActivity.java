package com.mediaportal.remote.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.actionbar.ActionBar;
import com.mediaportal.remote.activities.actionbar.ActionBar.IntentAction;
import com.mediaportal.remote.activities.settings.SettingsActivity;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.utils.Util;

public class HomeActivity extends Activity {
   private StatusBarActivityHandler statusBarHandler = null;
   
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.homescreen);
      
      ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
      actionBar.setTitle("aMPdroid");
      actionBar.setHomeAction(new IntentAction(this, null, R.drawable.actionbar_home));
      
      actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_remote));
      actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_search));
      //actionBar.addAction(new ToastAction());

      
      DataHandler remoteController = DataHandler.getCurrentRemoteInstance();
      statusBarHandler = new StatusBarActivityHandler(this, remoteController);
      statusBarHandler.setupRemoteStatus();
      

      final ImageButton buttonRemote = (ImageButton) findViewById(R.id.ImageButtonRemote);
      buttonRemote.setOnClickListener(new View.OnClickListener() {
         public void onClick(View _view) {
            Util.Vibrate(_view.getContext(), 50);
            Intent myIntent = new Intent(_view.getContext(), RemoteControlActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonMusic = (ImageButton) findViewById(R.id.ImageButtonMusic);
      buttonMusic.setOnClickListener(new View.OnClickListener() {
         public void onClick(View _view) {
            Util.Vibrate(_view.getContext(), 50);
            Toast toast = Toast.makeText(_view.getContext(), "Music not implemented yet",
                  Toast.LENGTH_SHORT);
            toast.show();
            Intent myIntent = new Intent(_view.getContext(), MusicActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonTv = (ImageButton) findViewById(R.id.ImageButtonTv);
      buttonTv.setOnClickListener(new View.OnClickListener() {
         public void onClick(View _view) {
            Util.Vibrate(_view.getContext(), 50);
            Intent myIntent = new Intent(_view.getContext(), TvServerActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonVideos = (ImageButton) findViewById(R.id.ImageButtonVideos);
      buttonVideos.setOnClickListener(new View.OnClickListener() {
         public void onClick(View _view) {
            Util.Vibrate(_view.getContext(), 50);
            Intent myIntent = new Intent(_view.getContext(), MediaActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonPictures = (ImageButton) findViewById(R.id.ImageButtonPictures);
      buttonPictures.setOnClickListener(new View.OnClickListener() {
         public void onClick(View _view) {
            Util.Vibrate(_view.getContext(), 50);
            Toast toast = Toast.makeText(_view.getContext(), "Pictures not implemented yet",
                  Toast.LENGTH_SHORT);
            toast.show();
            Intent myIntent = new Intent(_view.getContext(), PicturesActivity.class);
            startActivity(myIntent);
         }
      });
      
      final ImageButton buttonPlugins = (ImageButton) findViewById(R.id.ImageButtonPlugins);
      buttonPlugins.setOnClickListener(new View.OnClickListener() {
         public void onClick(View _view) {
            Util.Vibrate(_view.getContext(), 50);
            Toast toast = Toast.makeText(_view.getContext(), "Plugins not implemented yet",
                  Toast.LENGTH_SHORT);
            toast.show();
            
            Intent settingsIntent = new Intent(_view.getContext(), SettingsActivity.class);
            startActivity(settingsIntent);
         }
      });
   }
   
   @Override
   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      Window window = getWindow();
      window.setFormat(PixelFormat.RGBA_8888);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu _menu) {
      MenuItem settingsItem = _menu.add(0, Menu.FIRST, Menu.NONE, "Settings");
      settingsItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
            startSettings();
            return true;
         }
      });

      return true;
   }
   
   private void startSettings() {
      Intent settingsIntent = new Intent(this, SettingsActivity.class);
      startActivity(settingsIntent);
      //startActivityForResult(settingsIntent, 0);
   }

}
