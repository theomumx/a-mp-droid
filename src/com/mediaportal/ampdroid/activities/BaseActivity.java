package com.mediaportal.ampdroid.activities;

import android.app.Activity;
import android.os.Bundle;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.actionbar.ActionBar;
import com.mediaportal.ampdroid.activities.actionbar.ActionBar.IntentAction;

public class BaseActivity extends Activity {
   private boolean isHome = false;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);

   }

   @Override
   protected void onStart() {
      super.onStart();
      ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
      if (actionBar != null) {
         actionBar.setTitle(this.getTitle());

         if (!isHome) {
            actionBar.setHomeAction(new IntentAction(this, null, R.drawable.actionbar_home));
         }

         if (!actionBar.isInitialised()) {
            actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_remote));
            actionBar.addAction(new IntentAction(this, null, R.drawable.actionbar_search));
         
            actionBar.setInitialised(true);
         }
      }
   }

   public void setHome(boolean isHome) {
      this.isHome = isHome;
   }

   public boolean isHome() {
      return isHome;
   }
}
