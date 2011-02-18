package com.mediaportal.ampdroid.activities.tvserver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.activities.StatusBarActivityHandler;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvChannel;
import com.mediaportal.ampdroid.data.TvChannelGroup;
import com.mediaportal.ampdroid.data.TvProgramBase;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.views.TvServerChannelAdapterItem;
import com.mediaportal.ampdroid.lists.views.TvServerProgramsBaseViewItem;

public class TvServerEpgActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mEpgView;
   private LoadEpgTask mEpgLoaderTask;
   private StatusBarActivityHandler mStatusBarHandler;
   private LazyLoadingAdapter mEpgAdapter;
   ProgressDialog mLoadingDialog;
   
   boolean mShowAllGroup = false;
   ArrayAdapter<TvChannelGroup> mGroupsItems;
   UpdateGroupsTask mGroupsUpdater;
   private Spinner mGroups;

   private class UpdateGroupsTask extends AsyncTask<Integer, Integer, List<TvChannelGroup>> {
      @Override
      protected List<TvChannelGroup> doInBackground(Integer... _group) {
         ArrayList<TvChannelGroup> groups = mService.getTvChannelGroups();
         return groups;
      }

      @Override
      protected void onPostExecute(List<TvChannelGroup> _result) {
         mGroupsItems.clear();
         if (_result != null) {
            for (TvChannelGroup g : _result) {
               if (mShowAllGroup || g.getIdGroup() != 1) {
                  mGroupsItems.add(g);
               }
            }
         }
      }
   }
   
   private class LoadEpgTask extends AsyncTask<Integer, ILoadingAdapterItem, List<TvProgramBase>> {
      private Context mContext;

      private LoadEpgTask(Context _context) {
         mContext = _context;
      }

      @Override
      protected List<TvProgramBase> doInBackground(Integer... _params) {
         Date begin = new Date();

         Calendar cal = Calendar.getInstance();
         cal.add(Calendar.HOUR, 3);
         Date end = cal.getTime();
         
         List<TvChannel> channels = mService.getTvChannelsForGroup(_params[0]);
         if (channels != null) {
            
            for (TvChannel c : channels) {

               List<TvProgramBase> programs = mService.getTvBaseEpgForChannel(c.getIdChannel(),
                     begin, end);
               ILoadingAdapterItem[] channelList = new ILoadingAdapterItem[programs.size() + 1];
               publishProgress(new TvServerChannelAdapterItem(c));
               for (TvProgramBase p : programs) {
                  publishProgress(new TvServerProgramsBaseViewItem(p));
               }
            }
         }
         return null;
      }

      @Override
      protected void onProgressUpdate(ILoadingAdapterItem... values) {
         super.onProgressUpdate(values);
         if (values != null) {
            for (ILoadingAdapterItem i : values) {
               mEpgAdapter.addItem(i);
            }
         }
         mEpgAdapter.showLoadingItem(false);
         mEpgAdapter.notifyDataSetChanged();
      }

      @Override
      protected void onPostExecute(List<TvProgramBase> _result) {
         mLoadingDialog.cancel();
      }
   }

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setTitle(R.string.title_tvserver_epg);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverepgactivity);
      mEpgView = (ListView) findViewById(R.id.ListViewEpg);
      mGroups = (Spinner) findViewById(R.id.SpinnerGroups);
      
      mGroups.setOnItemSelectedListener(new OnItemSelectedListener() {

         @Override
         public void onItemSelected(AdapterView<?> _adapter, View _view, int _position, long _id) {
            Object selected = mGroups.getItemAtPosition(_position);
            refreshEpg((TvChannelGroup) selected);
         }

         @Override
         public void onNothingSelected(AdapterView<?> _adapter) {

         }
      });
      
      mEpgAdapter = new LazyLoadingAdapter(this);
      mEpgView.setAdapter(mEpgAdapter);

      mGroupsItems = new ArrayAdapter<TvChannelGroup>(this, android.R.layout.simple_spinner_item);
      mGroupsItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      mGroups.setAdapter(mGroupsItems);

      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
      mShowAllGroup = prefs.getBoolean("tvserver_showall", true);
      
      mService = DataHandler.getCurrentRemoteInstance();
      mStatusBarHandler = new StatusBarActivityHandler(this, mService);
      mStatusBarHandler.setHome(false);

      refreshGroups();
   }
   
   private void refreshGroups(){
      mLoadingDialog = ProgressDialog.show(TvServerEpgActivity.this, " Loading Groups ",
            " Loading. Please wait ... ", true);
      mLoadingDialog.setCancelable(true);
      
      mGroupsUpdater = new UpdateGroupsTask();
      mGroupsUpdater.execute(0);
   }

   private void refreshEpg(TvChannelGroup _group) {
      mLoadingDialog.cancel();
      mLoadingDialog = ProgressDialog.show(TvServerEpgActivity.this, " Loading EPG ",
            " Loading. Please wait ... ", true);
      mLoadingDialog.setCancelable(true);
      mEpgAdapter.clear();
      mEpgAdapter.showLoadingItem(true);
      mEpgAdapter.notifyDataSetChanged();
      mEpgLoaderTask = new LoadEpgTask(this);
      mEpgLoaderTask.execute(_group.getIdGroup());
   }
}
