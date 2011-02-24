package com.mediaportal.ampdroid.activities.settings;

import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.api.gmawebservice.GmaJsonWebserviceApi;
import com.mediaportal.ampdroid.api.tv4home.Tv4HomeJsonApi;
import com.mediaportal.ampdroid.api.wifiremote.WifiRemoteMpController;
import com.mediaportal.ampdroid.data.RemoteClient;
import com.mediaportal.ampdroid.database.RemoteClientsDatabaseHandler;
public class ClientPreference extends DialogPreference {
   private Context mContext;
   private RemoteClient mClient;
   private RemoteClientsDatabaseHandler mDbHandler;

   private EditText mNameView;
   private EditText mHostView;
   private EditText mUserView;
   private EditText mPassView;
   private EditText mAccPointView;

   private CheckBox mWifiOnlyView;

   public ClientPreference(Context context, AttributeSet attrs) {
      super(context, attrs);

      mContext = context;

      setDialogLayoutResource(R.layout.preference_host_simple);
      setDialogTitle("Add new host");
      setDialogIcon(R.drawable.bubble_add);
   }

   public ClientPreference(Context context) {
      this(context, null);
   }

   public void create(PreferenceManager preferenceManager) {
      onAttachedToHierarchy(preferenceManager);
      showDialog(null);
   }

   public void setClient(RemoteClient _client) {
      mClient = _client;
      setTitle(_client.getClientName());
      setSummary(_client.getClientDescription());
      setDialogTitle(_client.getClientName());
      setDialogIcon(null);
   }

   public RemoteClient getHost() {
      return mClient;
   }
   
   public void setDbHandler(RemoteClientsDatabaseHandler _dbHandler) {
      mDbHandler = _dbHandler;
   }

   @Override
   protected View onCreateView(final ViewGroup parent) {
      final ViewGroup view = (ViewGroup) super.onCreateView(parent);
      ImageView btn = new ImageView(getContext());
      btn.setImageResource(R.drawable.bubble_del_up);
      btn.setClickable(true);
      btn.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Are you sure you want to delete the client \"" + "client_name"
                  + "\"?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                   mDbHandler.removeRemoteClient(mClient);
                   ((PreferenceActivity)view.getContext()).getPreferenceScreen().removePreference(ClientPreference.this);
               }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                  dialog.cancel();
               }
            });
            builder.create().show();
         }
      });
      view.addView(btn);
      return view;
   }

   @Override
   protected View onCreateDialogView() {
      final ViewGroup parent = (ViewGroup) super.onCreateDialogView();
      mNameView = (EditText) parent.findViewById(R.id.pref_name);
      mHostView = (EditText) parent.findViewById(R.id.pref_host);

      mUserView = (EditText) parent.findViewById(R.id.pref_user);
      mPassView = (EditText) parent.findViewById(R.id.pref_pass);
      mAccPointView = (EditText) parent.findViewById(R.id.pref_access_point);
      mWifiOnlyView = (CheckBox) parent.findViewById(R.id.pref_wifi_only);
      return parent;
   }

   @Override
   protected void onBindDialogView(View view) {
      super.onBindDialogView(view);
      if (mClient != null) {
         mNameView.setText(mClient.getClientName());
         
         
         mHostView.setText(mClient.getClientAddress());
         mUserView.setText("");
         mPassView.setText("");

         mAccPointView.setText("");
         mWifiOnlyView.setChecked(true);
      } else {
         // set defaults:
      }
   }
   
   @Override
   protected void onDialogClosed(boolean positiveResult) {
      super.onDialogClosed(positiveResult);
      if (positiveResult) {
         boolean create = false;
         if (mClient == null) {
            Random rnd = new Random();
            mClient = new RemoteClient(rnd.nextInt());
            create = true;
         }         
         mClient.setClientName(mNameView.getText().toString());
         String addr = mHostView.getText().toString();
         GmaJsonWebserviceApi api = new GmaJsonWebserviceApi(addr, 44321);
         mClient.setRemoteAccessApi(api);
         
         Tv4HomeJsonApi tvApi = new Tv4HomeJsonApi(addr, 4321);
         mClient.setTvControlApi(tvApi);
         
         WifiRemoteMpController clientApi = new WifiRemoteMpController(addr, 8017);
         mClient.setClientControlApi(clientApi);
         
         if(create){
            mDbHandler.addRemoteClient(mClient);
         }
         else{
            mDbHandler.updateRemoteClient(mClient);
         }

         DataHandler.updateRemoteClient(mClient);
         //host.user = mUserView.getText().toString();
         //host.pass = mPassView.getText().toString();

         //host.access_point = mAccPointView.getText().toString();
         //host.wifi_only = mWifiOnlyView.isChecked();
         
         setClient(mClient);
         
         
      }
   }



}
