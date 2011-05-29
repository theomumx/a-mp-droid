package com.mediaportal.ampdroid.activities.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.mediaportal.ampdroid.R;
public class SettingsActivity extends PreferenceActivity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       addPreferencesFromResource(R.xml.preferences);
   }
}
