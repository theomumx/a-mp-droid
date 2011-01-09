package com.mediaportal.remote.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.quickactions.ActionItem;
import com.mediaportal.remote.activities.quickactions.QuickAction;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.TvChannel;
import com.mediaportal.remote.data.TvChannelGroup;
import com.mediaportal.remote.data.TvVirtualCard;
import com.mediaportal.remote.utils.Util;

public class TvServerStateActivity extends Activity {
   private ListView mListView;
   private Button mStartTimeshift;
   private Button mStartRecording;
   private Button mStopTimeshiftRecording;
   private Spinner mGroups;
   private Spinner mChannels;

   private ArrayAdapter mListItems;

   private DataHandler service;

   @Override
   public void onCreate(Bundle _savedInstanceState) {
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
         public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

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
            QuickAction qa = new QuickAction(_view);
            ActionItem sdCardAction = new ActionItem();

            sdCardAction.setTitle("Stop Timeshift");
            sdCardAction.setIcon(getResources().getDrawable(R.drawable.bubble_del));

            Object selected = mListView.getItemAtPosition(_position);
            final TvVirtualCard card = (TvVirtualCard) selected;

            sdCardAction.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View _view) {
                  boolean result = service.stopTimeshift(card.getUser().getName());
                  if (result) {
                     Util.showToast(_view.getContext(), "Stop timeshift");
                  }
                  else{
                     Util.showToast(_view.getContext(), "Couldn't stop timeshift");
                  }
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

      mListItems = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

      service = DataHandler.getCurrentRemoteInstance();
      if (service.isTvServiceActive()) {
         List<TvVirtualCard> cards = service.getTvCardsActive();

         if (cards != null) {
            for (TvVirtualCard c : cards) {
               mListItems.add(c);
            }
         }

         ArrayList<TvChannelGroup> groups = service.getTvChannelGroups();
         if (groups != null) {
            ArrayAdapter<TvChannelGroup> adapter = new ArrayAdapter<TvChannelGroup>(this,
                  android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mGroups.setAdapter(adapter);
         }

      }

      mListView.setAdapter(mListItems);

   }

   private void startTimeshift() {
      TvChannel channel = (TvChannel) mChannels.getSelectedItem();

      if (channel != null) {
         String url = service.startTimeshift(channel.getIdChannel());

         if (url != null) {
            Util.showToast(this, url);
         } else {
            Util.showToast(this, "Couldn't start timeshift");
         }
      }
   }

   private void startRecording() {
      TvChannel channel = (TvChannel) mChannels.getSelectedItem();

      if (channel != null) {
         String url = service.startTimeshift(channel.getIdChannel());

         if (url != null) {
            Util.showToast(this, url);
         } else {
            Util.showToast(this, "Couldn't start timeshift");
         }
      }
   }

   private void fillChannelList(TvChannelGroup _group) {
      List<TvChannel> channels = service.getTvChannelsForGroup(_group.getIdGroup()); // get
      // all
      // channels
      if (channels != null) {
         ArrayAdapter<TvChannel> adapter = new ArrayAdapter<TvChannel>(this,
               android.R.layout.simple_spinner_item, channels);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         mChannels.setAdapter(adapter);
      }
   }
}
