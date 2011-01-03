package com.mediaportal.remote.activities.media;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.activities.lists.LazyLoadingAdapter;
import com.mediaportal.remote.activities.lists.views.SeriesPosterViewAdapter;
import com.mediaportal.remote.activities.quickactions.ActionItem;
import com.mediaportal.remote.activities.quickactions.QuickAction;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.Series;

public class TabSeriesActivity extends Activity {
   private ListView mListView;
   private LazyLoadingAdapter mAdapter;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tabseriesactivity);

      DataHandler service = DataHandler.getCurrentRemoteInstance();
      List<Series> series = service.getAllSeries();
      // List<TvSeason> season = service.getAllSeasons(series.get(0).getId());

      mAdapter = new LazyLoadingAdapter(this, R.layout.listitem_poster);

      if (series != null) {
         for (Series s : series) {
            mAdapter.AddItem(new SeriesPosterViewAdapter(s));
         }
      }

      mListView = (ListView) findViewById(R.id.ListViewVideos);
      mListView.setAdapter(mAdapter);

      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> _adapter, View _view, int _position, long _id) {
            ILoadingAdapterItem selectedItem = (ILoadingAdapterItem) mListView
                  .getItemAtPosition(_position);
            // Toast toast = Toast.makeText(v.getContext(), selectedSeries
            // .getTitle(), Toast.LENGTH_SHORT);
            // toast.show();
            Series selectedSeries = (Series) selectedItem.getItem();
            Intent myIntent = new Intent(_view.getContext(), TabSeriesDetailsActivity.class);
            myIntent.putExtra("series_id", selectedSeries.getId());
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //startActivity(myIntent);

            // Create the view using FirstGroup's LocalActivityManager
            View view = TabSeriesActivityGroup.getGroup().getLocalActivityManager().startActivity("series_details",
                  myIntent).getDecorView();

            // Again, replace the view
            TabSeriesActivityGroup.getGroup().replaceView(view);
         }
      });

      final ActionItem chart = new ActionItem();

      chart.setTitle("Chart");
      chart.setIcon(getResources().getDrawable(R.drawable.chart));
      chart.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View _view) {
            Toast.makeText(_view.getContext(), "Chart selected", Toast.LENGTH_SHORT).show();
         }
      });

      final ActionItem production = new ActionItem();

      production.setTitle("Products");
      production.setIcon(getResources().getDrawable(R.drawable.production));
      production.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View _view) {
            Toast.makeText(_view.getContext(), "Products selected", Toast.LENGTH_SHORT).show();
         }
      });

      mListView.setOnItemLongClickListener(new OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> _adapter, View _view, int _position, long _id) {
            QuickAction qa = new QuickAction(_view);

            qa.addActionItem(chart);
            qa.addActionItem(production);
            qa.setAnimStyle(QuickAction.ANIM_AUTO);

            qa.show();
            return true;
         }
      });

   }
}
