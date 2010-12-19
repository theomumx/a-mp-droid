package com.mediaportal.remote.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.RemoteHandler;
import com.mediaportal.remote.utils.Util;

public class HomeActivity extends Activity {
   private StatusBarActivityHandler statusBarHandler;
   
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.homescreen);
      
      RemoteHandler remoteController = RemoteHandler.getCurrentRemoteInstance();
      statusBarHandler = new StatusBarActivityHandler(this, remoteController);

      final ImageButton buttonRemote = (ImageButton) findViewById(R.id.ImageButtonRemote);
      buttonRemote.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Util.Vibrate(v.getContext(), 50);
            Intent myIntent = new Intent(v.getContext(), RemoteControlActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonMusic = (ImageButton) findViewById(R.id.ImageButtonMusic);
      buttonMusic.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Util.Vibrate(v.getContext(), 50);
            Toast toast = Toast.makeText(v.getContext(), "Music not implemented yet",
                  Toast.LENGTH_SHORT);
            toast.show();
            Intent myIntent = new Intent(v.getContext(), MusicActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonTv = (ImageButton) findViewById(R.id.ImageButtonTv);
      buttonTv.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Util.Vibrate(v.getContext(), 50);
            Intent myIntent = new Intent(v.getContext(), TvServerActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonVideos = (ImageButton) findViewById(R.id.ImageButtonVideos);
      buttonVideos.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Util.Vibrate(v.getContext(), 50);
            Intent myIntent = new Intent(v.getContext(), MediaActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonPictures = (ImageButton) findViewById(R.id.ImageButtonPictures);
      buttonPictures.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Util.Vibrate(v.getContext(), 50);
            Toast toast = Toast.makeText(v.getContext(), "Pictures not implemented yet",
                  Toast.LENGTH_SHORT);
            toast.show();
            Intent myIntent = new Intent(v.getContext(), PicturesActivity.class);
            startActivity(myIntent);
         }
      });
   }
}
