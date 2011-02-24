package com.mediaportal.ampdroid.activities.media;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseTabActivity;
import com.mediaportal.ampdroid.activities.StatusBarActivityHandler;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ILoadingListener;
import com.mediaportal.ampdroid.lists.views.SeriesBannerViewAdapterItem;
import com.mediaportal.ampdroid.lists.views.SeriesPosterViewAdapterItem;
import com.mediaportal.ampdroid.lists.views.SeriesTextViewAdapterItem;
import com.mediaportal.ampdroid.lists.views.SeriesThumbViewAdapterItem;
import com.mediaportal.ampdroid.lists.views.ViewTypes;

public class TabSeriesActivity extends Activity implements ILoadingListener {
   private ListView mListView;
   private LazyLoadingAdapter mAdapter;
   DataHandler mService;
   private LoadSeriesTask mSeriesLoaderTask;
   private int mSeriesLoaded = 0;
   private BaseTabActivity mBaseActivity;
   private StatusBarActivityHandler mStatusBarHandler;

   private class LoadSeriesTask extends AsyncTask<Integer, List<Series>, Boolean> {
      @SuppressWarnings("unchecked")
      @Override
      protected Boolean doInBackground(Integer... _params) {
         int seriesCount = mService.getSeriesCount();
         int loadItems = mSeriesLoaded + _params[0];

         while (mSeriesLoaded < loadItems) {
            List<Series> series = mService.getSeries(mSeriesLoaded, mSeriesLoaded + 4);
            if(series == null){
               return false;
            }
            publishProgress(series);
            mSeriesLoaded += 5;
         }

         if (mSeriesLoaded < seriesCount) {
            return false;// not yet finished;
         } else {
            return true;// finished
         }
      }

      @Override
      protected void onProgressUpdate(List<Series>... values) {
         if (values != null) {
            List<Series> series = values[0];
            for (Series s : series) {
               mAdapter.addItem(ViewTypes.TextView.ordinal(), new SeriesTextViewAdapterItem(s));
               mAdapter.addItem(ViewTypes.PosterView.ordinal(), new SeriesPosterViewAdapterItem(s));
               mAdapter.addItem(ViewTypes.ThumbView.ordinal(), new SeriesThumbViewAdapterItem(s));
               mAdapter.addItem(ViewTypes.BannerView.ordinal(), new SeriesBannerViewAdapterItem(s));
            }
         }
         mAdapter.notifyDataSetChanged();
         super.onProgressUpdate(values);
      }

      @Override
      protected void onPostExecute(Boolean _result) {
         if (_result) {
            mAdapter.showLoadingItem(false);
         }
         mStatusBarHandler.setLoading(false);
         mSeriesLoaderTask = null;
      }

   }

   @Override
   public void EndOfListReached() {
      loadFurtherSeriesItems();
   }

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tabseriesactivity);

      mBaseActivity = (BaseTabActivity) getParent().getParent();

      mService = DataHandler.getCurrentRemoteInstance();
      mStatusBarHandler = new StatusBarActivityHandler(mBaseActivity, mService);
      mStatusBarHandler.setHome(false);

      mAdapter = new LazyLoadingAdapter(this);
      mAdapter.addView(ViewTypes.TextView.ordinal());
      mAdapter.addView(ViewTypes.PosterView.ordinal());
      mAdapter.addView(ViewTypes.ThumbView.ordinal());
      mAdapter.addView(ViewTypes.BannerView.ordinal());
      mAdapter.setView(ViewTypes.PosterView.ordinal());
      mAdapter.setLoadingListener(this);

      mListView = (ListView) findViewById(R.id.ListViewVideos);
      mListView.setAdapter(mAdapter);

      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> _adapter, View _view, int _position, long _id) {
            ILoadingAdapterItem selectedItem = (ILoadingAdapterItem) mListView
                  .getItemAtPosition(_position);
            Series selectedSeries = (Series) selectedItem.getItem();
            if (selectedSeries != null) {
               Intent myIntent = new Intent(_view.getContext(), TabSeriesDetailsActivity.class);
               myIntent.putExtra("series_id", selectedSeries.getId());
               myIntent.putExtra("series_name", selectedSeries.getPrettyName());
               myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

               // Create the view using FirstGroup's LocalActivityManager
               View view = TabSeriesActivityGroup.getGroup().getLocalActivityManager()
                     .startActivity("series_details", myIntent).getDecorView();

               // Again, replace the view
               TabSeriesActivityGroup.getGroup().replaceView(view);
            }
         }
      });

      mAdapter.setLoadingText("Loading Series ...");
      mAdapter.showLoadingItem(true);

      loadFurtherSeriesItems();
   }

   private void loadFurtherSeriesItems() {
      if (mSeriesLoaderTask == null) {
         mSeriesLoaderTask = new LoadSeriesTask();
         mStatusBarHandler.setLoading(true);
         mSeriesLoaderTask.execute(20);
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu _menu) {
      super.onCreateOptionsMenu(_menu);
      SubMenu viewItem = _menu.addSubMenu(0, Menu.FIRST + 1, Menu.NONE, "Views");

      MenuItem textSettingsItem = viewItem.add(0, Menu.FIRST + 1, Menu.NONE, "Text");
      MenuItem posterSettingsItem = viewItem.add(0, Menu.FIRST + 2, Menu.NONE, "Poster");
      MenuItem thumbsSettingsItem = viewItem.add(0, Menu.FIRST + 3, Menu.NONE, "Thumbs");
      MenuItem bannerSettingsItem = viewItem.add(0, Menu.FIRST + 4, Menu.NONE, "Banner");

      textSettingsItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
            mAdapter.setView(ViewTypes.TextView.ordinal());
            mAdapter.notifyDataSetInvalidated();
            return true;
         }
      });
      
      posterSettingsItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
            mAdapter.setView(ViewTypes.PosterView.ordinal());
            mAdapter.notifyDataSetInvalidated();
            return true;
         }
      });

      thumbsSettingsItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
            mAdapter.setView(ViewTypes.ThumbView.ordinal());
            mAdapter.notifyDataSetInvalidated();
            return true;
         }
      });

      bannerSettingsItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
            mAdapter.setView(ViewTypes.BannerView.ordinal());
            mAdapter.notifyDataSetInvalidated();
            return true;
         }
      });

      return true;
   }
}
