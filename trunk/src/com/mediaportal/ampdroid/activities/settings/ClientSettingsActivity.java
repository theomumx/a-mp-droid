package com.mediaportal.ampdroid.activities.settings;

import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.Menu;
import android.view.MenuItem;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.data.RemoteClient;
import com.mediaportal.ampdroid.database.RemoteClientsDatabaseHandler;
import com.mediaportal.ampdroid.utils.Util;

public class ClientSettingsActivity extends PreferenceActivity {
   public static final int MENU_ADD_HOST = 1;
   public static final int MENU_EXIT = 2;
   public static final int MENU_ADD_HOST_WIZARD = 3;
   private RemoteClientsDatabaseHandler mDbHandler;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setTitle("MediaPortal Clients");

   }

   @Override
   protected void onResume() {
      PreferenceScreen root = getPreferenceManager().createPreferenceScreen(this);

      mDbHandler = new RemoteClientsDatabaseHandler(this);
      mDbHandler.open();
      List<RemoteClient> clients = mDbHandler.getClients();

      if (clients != null) {
         for (RemoteClient c : clients) {
            ClientPreference pref = new ClientPreference(this);
            pref.setTitle(c.getClientName());
            pref.setSummary("Id: " + c.getClientId());
            pref.setClient(c);
            pref.setDbHandler(mDbHandler);

            root.addPreference(pref);
         }
      }
      if(clients == null || clients.size() == 0){
         Util.showToast(this, "Use menu to add host");
      }

      setPreferenceScreen(root);
      super.onResume();
   }

   @Override
   protected void onPause() {
      mDbHandler.close();
      super.onPause();
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      menu.addSubMenu(0, MENU_ADD_HOST, 0, getString(R.string.menu_addhost));// .setIcon(R.drawable.menu_add_host);
      //menu.addSubMenu(0, MENU_ADD_HOST_WIZARD, 0, "Host Wizard");// .setIcon(R.drawable.menu_add_host);
      //menu.addSubMenu(0, MENU_EXIT, 0, "Exit");// .setIcon(R.drawable.menu_exit);

      return true;
   }

   @Override
   public boolean onMenuItemSelected(int featureId, MenuItem item) {
      switch (item.getItemId()) {
      case MENU_ADD_HOST:
         ClientPreference pref = new ClientPreference(this);
         pref.setTitle("New Client");
         pref.create(getPreferenceManager());
         getPreferenceScreen().addPreference(pref);
         pref.setDbHandler(mDbHandler);
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
