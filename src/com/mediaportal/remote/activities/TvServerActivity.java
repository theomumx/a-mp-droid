package com.mediaportal.remote.activities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.api.soap.Ksoap2ResultParser;
import com.mediaportal.remote.data.TvCardDetails;
import com.mediaportal.remote.data.TvChannel;
import com.mediaportal.remote.data.TvChannelGroup;
import com.mediaportal.remote.data.TvVirtualCard;

@SuppressWarnings("unchecked")
public class TvServerActivity extends Activity {
   private ListView mListView;

   private ArrayAdapter mListItems;
   private ArrayList<Object> mBreadCrumbList;

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserveractivity);
      
      mListView = (ListView) findViewById(R.id.ListViewItems);
      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            Object selected = mListView.getItemAtPosition(position);
            handleListClick(v, position, selected);
         }
      });

      mBreadCrumbList = new ArrayList<Object>();

      mListItems = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
      mListView.setAdapter(mListItems);

      initialSetup();
   }

   private void initialSetup() {
      DataHandler service = DataHandler.getCurrentRemoteInstance();
      if (service.isTvServiceActive()) {
         fillListViewRoot();
      } else {
         Toast toast = Toast.makeText(this, "No connection to tv server", Toast.LENGTH_SHORT);
         toast.show();
         mBreadCrumbList.clear();
         mListItems.clear();
         mListItems.add("Retry");
      }
   }

   private void handleListClick(View _view, int _position, Object _selected) {
      if (_selected.getClass().equals(String.class) && _selected.equals("...")) {
         mBreadCrumbList.remove(mBreadCrumbList.size() - 1);
         if (mBreadCrumbList.size() > 0) {
            Object last = mBreadCrumbList.get(mBreadCrumbList.size() - 1);

            fillListView(last);
         } else {
            fillListViewRoot();
         }

      } else {
         mBreadCrumbList.add(_selected);
         fillListView(_selected);
      }
   }

   private void fillListViewRoot() {
      mListItems.clear();

      mListItems.add("Groups");
      mListItems.add("Cards");
      mListItems.add("Active Cards");

   }

   private void fillListView(Object _object) {
      if (_object.getClass().equals(String.class)) {
         if(_object.equals("Retry")){
            initialSetup();
         }         
         if (_object.equals("Groups")) {
            fillListViewWithChannelGroups();
         }
         if (_object.equals("Cards")) {
            fillListViewWithCards();
         }
         if (_object.equals("Active Cards")) {
            fillListViewWithActiveCards();
         }
      }
      if (_object.getClass().equals(TvChannelGroup.class)) {
         fillListViewWithChannels((TvChannelGroup) _object);
      }

      if (_object.getClass().equals(TvChannel.class)) {
         fillListViewWithObject((TvChannel) _object);
      }

      if (_object.getClass().equals(TvCardDetails.class)) {
         fillListViewWithObject((TvCardDetails) _object);
      }
   }

   private void fillListViewWithActiveCards() {
      mListItems.clear();
      mListItems.add("...");

      DataHandler service = DataHandler.getCurrentRemoteInstance();
      List<TvVirtualCard> cards = service.getTvCardsActive();

      if (cards == null)
         return;
      for (TvVirtualCard c : cards) {
         mListItems.add(c);
      }
   }

   private void fillListViewWithCards() {
      mListItems.clear();
      mListItems.add("...");

      DataHandler service = DataHandler.getCurrentRemoteInstance();
      List<TvCardDetails> cards = service.getTvCards();

      if (cards == null)
         return;
      for (TvCardDetails c : cards) {
         mListItems.add(c);
      }
   }

   private void fillListViewWithChannelGroups() {
      mListItems.clear();
      mListItems.add("...");

      DataHandler service = DataHandler.getCurrentRemoteInstance();
      ArrayList<TvChannelGroup> groups = service.getTvChannelGroups();

      if (groups == null)
         return;
      for (TvChannelGroup g : groups) {
         mListItems.add(g);
      }

   }

   private void fillListViewWithChannels(TvChannelGroup _selected) {
      mListItems.clear();
      mListItems.add("...");

      DataHandler service = DataHandler.getCurrentRemoteInstance();
      
      /*int count = service.getTvChannelsCount(2);
      for(int i = 0; i < count-1; i++){
         List<TvChannel> channels = service.getTvChannelsForGroup(2, i, 1);
         for (TvChannel c : channels) {
            listItems.add(c);
         }
      }*/
      List<TvChannel> channels = service.getTvChannelsForGroup(_selected.getIdGroup());

      for (TvChannel c : channels) {
         mListItems.add(c);
      }
   }

   private void fillListViewWithObject(Object _selected) {
      mListItems.clear();
      mListItems.add("...");

      List<Field> fields = Ksoap2ResultParser.getAllFields(_selected.getClass());

      for (int i = 0; i < fields.size(); i++) {
         fields.get(i).setAccessible(true);
         String fieldName = fields.get(i).getName();
         Object fieldValue;
         try {
            fieldValue = fields.get(i).get(_selected);
            mListItems.add(fieldName + ": " + fieldValue);
         } catch (Exception e) {
            mListItems.add(fieldName + ": ?");
         }

      }
   }
}
