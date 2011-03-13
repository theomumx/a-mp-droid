package com.mediaportal.ampdroid.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class PreferencesManager {
   static SharedPreferences Preferences;

   public static void intitialisePreferencesManager(Context _context) {
      Preferences = PreferenceManager.getDefaultSharedPreferences(_context);
   }

   public static boolean isFullscreen() {
      if (Preferences != null) {
         return Preferences.getBoolean("general_fullscreen", true);
      } else {
         Log.w("aMPdroid", "PreferencesManager not initialised");
         return false;
      }
   }

   public static boolean isVibrating() {
      if (Preferences != null) {
         return Preferences.getBoolean("general_vibrate", true);
      } else {
         Log.w("aMPdroid", "PreferencesManager not initialised");
         return false;
      }
   }

}
