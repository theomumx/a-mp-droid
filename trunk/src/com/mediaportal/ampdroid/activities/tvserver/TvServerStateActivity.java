package com.mediaportal.ampdroid.activities.tvserver;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvChannel;
import com.mediaportal.ampdroid.data.TvChannelGroup;
import com.mediaportal.ampdroid.data.TvVirtualCard;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.views.VirtualCardStateAdapter;
import com.mediaportal.ampdroid.quickactions.ActionItem;
import com.mediaportal.ampdroid.quickactions.QuickAction;
import com.mediaportal.ampdroid.utils.Util;

public class TvServerStateActivity extends BaseActivity {
   private ListView mListView;
   private Button mStartTimeshift;
   private Button mStartRecording;
   private Spinner mGroups;
   private Spinner mChannels;
   private LazyLoadingAdapter mListItems;
   private DataHandler mService;
   ArrayAdapter<TvChannel> mChannelItems;
   ArrayAdapter<TvChannelGroup> mGroupsItems;

   UpdateGroupsTask mGroupsUpdater;
   UpdateChannelsTask mChannelUpdater;
   UpdateCardsTask mCardsUpdater;
   StartTimeshiftTask mTimeshiftStarter;
   StopTimeshiftTask mTimeshiftStopper;
   ProgressDialog mLoadingDialog;

   boolean mShowAllGroup = false;

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

   private class UpdateChannelsTask extends AsyncTask<TvChannelGroup, Integer, List<TvChannel>> {
      @Override
      protected List<TvChannel> doInBackground(TvChannelGroup... _group) {
         List<TvChannel> channels = mService.getTvChannelsForGroup(_group[0].getIdGroup()); // get
         return channels;
      }

      @Override
      protected void onPostExecute(List<TvChannel> _result) {
         if (_result != null) {
            mChannelItems.clear();

            for (TvChannel c : _result) {
               mChannelItems.add(c);
            }
         }
         mLoadingDialog.cancel();
      }
   }

   private class UpdateCardsTask extends AsyncTask<Integer, Integer, List<TvVirtualCard>> {
      @Override
      protected List<TvVirtualCard> doInBackground(Integer... _params) {
         List<TvVirtualCard> cards = mService.getTvCardsActive();
         return cards;
      }

      @Override
      protected void onPostExecute(List<TvVirtualCard> _result) {
         super.onPostExecute(_result);
         mListItems.clear();
         if (_result != null) {
            for (TvVirtualCard c : _result) {
               mListItems.AddItem(new VirtualCardStateAdapter(c));
            }
         }
         mListItems.notifyDataSetChanged();
      }
   }

   private class StartTimeshiftTask extends AsyncTask<TvChannel, Integer, String> {
      Context mContext;

      public StartTimeshiftTask(Context _context) {
         mContext = _context;
      }

      @Override
      protected String doInBackground(TvChannel... _params) {
         if (_params != null) {
            String url = mService.startTimeshift(_params[0].getIdChannel(), "Android2");
            return url;
         }
         return null;
      }

      @Override
      protected void onPostExecute(String _result) {
         if (_result != null) {
            Util.showToast(mContext, _result);
         } else {
            Util.showToast(mContext, "Couldn't start timeshift");
         }
         refreshActiveCards();
      }
   }

   private class StopTimeshiftTask extends AsyncTask<String, Integer, Boolean> {
      Context mContext;

      public StopTimeshiftTask(Context _context) {
         mContext = _context;
      }

      @Override
      protected Boolean doInBackground(String... _params) {
         boolean result = mService.stopTimeshift(_params[0]);

         return result;
      }

      @Override
      protected void onPostExecute(Boolean _result) {
         if (_result) {
            Util.showToast(mContext, "Stop timeshift");
         } else {
            Util.showToast(mContext, "Couldn't stop timeshift");
         }
         refreshActiveCards();
      }
   }

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setHome(false);
      setTitle(R.string.title_tvserver_state);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverstateactivity);
      mListView = (ListView) findViewById(R.id.ListViewCards);
      mStartTimeshift = (Button) findViewById(R.id.ButtonStartTimeshift);
      mStartRecording = (Button) findViewById(R.id.ButtonStartRecording);
      mChannels = (Spinner) findViewById(R.id.SpinnerChannels);
      mGroups = (Spinner) findViewById(R.id.SpinnerGroups);

      mGroups.setOnItemSelectedListener(new OnItemSelectedListener() {

         @Override
         public void onItemSelected(AdapterView<?> _adapter, View _view, int _position, long _id) {
            Object selected = mGroups.getItemAtPosition(_position);
            fillChannelList((TvChannelGroup) selected);
         }

         @Override
         public void onNothingSelected(AdapterView<?> _adapter) {

         }
      });

      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            Object selected = mListView.getItemAtPosition(position);
         }
      });

      mListView.setOnItemLongClickListener(new OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> _item, View _view, final int _position,
               long _id) {
            final QuickAction qa = new QuickAction(_view);
            ActionItem sdCardAction = new ActionItem();

            sdCardAction.setTitle("Stop Timeshift");
            sdCardAction.setIcon(getResources().getDrawable(R.drawable.bubble_del));

            VirtualCardStateAdapter selected = (VirtualCardStateAdapter) mListView.getItemAtPosition(_position);
            final TvVirtualCard card = (TvVirtualCard) selected.getItem();

            sdCardAction.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View _view) {
                  stopTimeshift(card.getUser().getName());
                  qa.dismiss();
               }
            });
            qa.addActionItem(sdCardAction);
            qa.setAnimStyle(QuickAction.ANIM_AUTO);

            qa.show();
            return true;
         }
      });

      mStartTimeshift.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            startTimeshift();
         }
      });

      mStartRecording.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            startRecording();
         }
      });

      mService = DataHandler.getCurrentRemoteInstance();

      mListItems = new LazyLoadingAdapter(this);
      mListView.setAdapter(mListItems);

      mChannelItems = new ArrayAdapter<TvChannel>(this, android.R.layout.simple_spinner_item);
      mChannelItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      mChannels.setAdapter(mChannelItems);

      mGroupsItems = new ArrayAdapter<TvChannelGroup>(this, android.R.layout.simple_spinner_item);
      mGroupsItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      mGroups.setAdapter(mGroupsItems);

      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
      mShowAllGroup = prefs.getBoolean("tvserver_showall", true);

      refreshActiveCards();

      refreshGroups();
   }

   private void refreshGroups() {
      mLoadingDialog = ProgressDialog.show(TvServerStateActivity.this, " Loading Groups ",
            " Loading. Please wait ... ", true);
      mLoadingDialog.setCancelable(true);
      
      mGroupsUpdater = new UpdateGroupsTask();
      mGroupsUpdater.execute(0);
   }

   @Override
   protected void onStart() {
      super.onStart();
   }

   private void startTimeshift() {
      TvChannel channel = (TvChannel) mChannels.getSelectedItem();

      mTimeshiftStarter = new StartTimeshiftTask(this);
      mTimeshiftStarter.execute(channel);
   }

   private void stopTimeshift(String _name) {
      mTimeshiftStopper = new StopTimeshiftTask(this);
      mTimeshiftStopper.execute(_name);
   }

   private void startRecording() {
      Util.showToast(this, "Not implemented yet...");
   }

   private void fillChannelList(TvChannelGroup _group) {
      mLoadingDialog.cancel();
      mLoadingDialog = ProgressDialog.show(TvServerStateActivity.this, " Loading Channels ",
            " Loading. Please wait ... ", true);
      mLoadingDialog.setCancelable(true);
      
      mChannelUpdater = new UpdateChannelsTask();
      mChannelUpdater.execute(_group);
   }

   private void refreshActiveCards() {
      mCardsUpdater = new UpdateCardsTask();
      mCardsUpdater.execute(0);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu _menu) {
      MenuItem settingsItem = _menu.add(0, Menu.FIRST, Menu.NONE, "Refresh");
      settingsItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
            refreshActiveCards();
            return true;
         }
      });

      return true;
   }
}
