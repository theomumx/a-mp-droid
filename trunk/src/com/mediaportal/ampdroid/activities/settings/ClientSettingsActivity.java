package com.mediaportal.ampdroid.activities.settings;

import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.Menu;
import android.view.MenuItem;

import com.mediaportal.ampdroid.api.database.RemoteClientFactory;
import com.mediaportal.ampdroid.data.RemoteClient;

public class ClientSettingsActivity extends PreferenceActivity {
   public static final int MENU_ADD_HOST = 1;
   public static final int MENU_EXIT = 2;
   public static final int MENU_ADD_HOST_WIZARD = 3;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setTitle("MediaPortal Clients");
      PreferenceScreen root = getPreferenceManager().createPreferenceScreen(this);

      //RemoteClientFactory.openDatabase(this);

      List<RemoteClient> clients = RemoteClientFactory.getClients();

      for (RemoteClient c : clients) {
         ClientPreference pref = new ClientPreference(this);
         pref.setTitle(c.getClientName());
         pref.setSummary("Id: " + c.getClientId());
         pref.setClient(c);

         root.addPreference(pref);
      }

      setPreferenceScreen(root);
   }

   @Override
   protected void onPause() {
      //RemoteClientFactory.closeDatabase();
      super.onPause();
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      menu.addSubMenu(0, MENU_ADD_HOST, 0, "Add Host");// .setIcon(R.drawable.menu_add_host);
      menu.addSubMenu(0, MENU_ADD_HOST_WIZARD, 0, "Host Wizard");// .setIcon(R.drawable.menu_add_host);
      menu.addSubMenu(0, MENU_EXIT, 0, "Exit");// .setIcon(R.drawable.menu_exit);

      return true;
   }

   @Override
   public boolean onMenuItemSelected(int featureId, MenuItem item) {
      switch (item.getItemId()) {
      case MENU_ADD_HOST:
         ClientPreference pref = new ClientPreference(this);
         pref.setTitle("New MediaPortal Client");
         pref.create(getPreferenceManager());
         getPreferenceScreen().addPreference(pref);
         break;
      /*
       * case MENU_ADD_HOST_WIZARD: Intent i = new Intent(mPreferenceActivity,
       * SetupWizard.class); mPreferenceActivity.startActivity(i); break;
       */
      case MENU_EXIT:
         System.exit(0);
         break;
      }
      return true;
   }

}
