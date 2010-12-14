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
import com.mediaportal.remote.api.RemoteHandler;
import com.mediaportal.remote.api.soap.Ksoap2ResultParser;
import com.mediaportal.remote.data.TvCard;
import com.mediaportal.remote.data.TvCardDetails;
import com.mediaportal.remote.data.TvChannel;
import com.mediaportal.remote.data.TvChannelGroup;

@SuppressWarnings("unchecked")
public class TvServerActivity extends Activity {
   private ListView m_listView;

   private ArrayAdapter listItems;
   private ArrayList<Object> breadCrumbList;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.tvserveractivity);
      m_listView = (ListView) findViewById(R.id.ListViewItems);
      m_listView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            Object selected = m_listView.getItemAtPosition(position);
            handleListClick(v, position, selected);
         }
      });

      breadCrumbList = new ArrayList<Object>();

      listItems = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
      m_listView.setAdapter(listItems);

      initialSetup();
   }

   private void initialSetup() {
      RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
      if (service.isTvServiceActive()) {
         fillListViewRoot();
      } else {
         Toast toast = Toast.makeText(this, "No connection to tv server", Toast.LENGTH_SHORT);
         toast.show();
         breadCrumbList.clear();
         listItems.clear();
         listItems.add("Retry");
      }
   }

   private void handleListClick(View v, int position, Object selected) {
      if (selected.getClass().equals(String.class) && selected.equals("...")) {
         breadCrumbList.remove(breadCrumbList.size() - 1);
         if (breadCrumbList.size() > 0) {
            Object last = breadCrumbList.get(breadCrumbList.size() - 1);

            fillListView(last);
         } else {
            fillListViewRoot();
         }

      } else {
         breadCrumbList.add(selected);
         fillListView(selected);
      }
   }

   private void fillListViewRoot() {
      listItems.clear();

      listItems.add("Groups");
      listItems.add("Cards");
      listItems.add("Active Cards");

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
      listItems.clear();
      listItems.add("...");

      RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
      List<TvCard> cards = service.getTvCardsActive();

      if (cards == null)
         return;
      for (TvCard c : cards) {
         listItems.add(c);
      }
   }

   private void fillListViewWithCards() {
      listItems.clear();
      listItems.add("...");

      RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
      List<TvCardDetails> cards = service.getTvCards();

      if (cards == null)
         return;
      for (TvCardDetails c : cards) {
         listItems.add(c);
      }
   }

   private void fillListViewWithChannelGroups() {
      listItems.clear();
      listItems.add("...");

      RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
      ArrayList<TvChannelGroup> groups = service.getTvChannelGroups();

      if (groups == null)
         return;
      for (TvChannelGroup g : groups) {
         listItems.add(g);
      }

   }

   private void fillListViewWithChannels(TvChannelGroup selected) {
      listItems.clear();
      listItems.add("...");

      RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
      
      /*int count = service.getTvChannelsCount(2);
      for(int i = 0; i < count-1; i++){
         List<TvChannel> channels = service.getTvChannelsForGroup(2, i, 1);
         for (TvChannel c : channels) {
            listItems.add(c);
         }
      }*/
      List<TvChannel> channels = service.getTvChannelsForGroup(selected.getIdGroup());

      for (TvChannel c : channels) {
         listItems.add(c);
      }
   }

   private void fillListViewWithObject(Object selected) {
      listItems.clear();
      listItems.add("...");

      List<Field> fields = Ksoap2ResultParser.getAllFields(selected.getClass());

      for (int i = 0; i < fields.size(); i++) {
         fields.get(i).setAccessible(true);
         String fieldName = fields.get(i).getName();
         Object fieldValue;
         try {
            fieldValue = fields.get(i).get(selected);
            listItems.add(fieldName + ": " + fieldValue);
         } catch (Exception e) {
            listItems.add(fieldName + ": ?");
         }

      }
   }
}
