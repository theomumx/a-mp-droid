package com.mediaportal.remote.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mediaportal.remote.R;

public class HomeActivity extends Activity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.homescreen);

      final ImageButton buttonRemote = (ImageButton) findViewById(R.id.ImageButtonRemote);
      buttonRemote.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Vibrate(50);
            Toast toast = Toast.makeText(v.getContext(), "Remote not implemented yet", Toast.LENGTH_SHORT);
            toast.show();
         }
      });

      final ImageButton buttonMusic = (ImageButton) findViewById(R.id.ImageButtonMusic);
      buttonMusic.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Vibrate(50);
            Toast toast = Toast.makeText(v.getContext(), "Music not implemented yet", Toast.LENGTH_SHORT);
            toast.show();
         }
      });

      final ImageButton buttonTv = (ImageButton) findViewById(R.id.ImageButtonTv);
      buttonTv.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Vibrate(50);
            Vibrate(50);
            Intent myIntent = new Intent(v.getContext(), TvServerActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonVideos = (ImageButton) findViewById(R.id.ImageButtonVideos);
      buttonVideos.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Vibrate(50);
            Intent myIntent = new Intent(v.getContext(), MediaActivity.class);
            startActivity(myIntent);
         }
      });

      final ImageButton buttonPictures = (ImageButton) findViewById(R.id.ImageButtonPictures);
      buttonPictures.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Vibrate(50);
            Toast toast = Toast.makeText(v.getContext(), "Pictures not implemented yet", Toast.LENGTH_SHORT);
            toast.show();
         }
      });

      // ArrayList<SupportedFunction> functions =
      // service.getSupportedFunctions();
   }
   public void Vibrate(int _time) {
      if (true) {
         // Get instance of Vibrator from current Context
         Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
         v.vibrate(_time);
      }
   }
}
