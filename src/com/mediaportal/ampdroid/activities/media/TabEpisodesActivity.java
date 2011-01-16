package com.mediaportal.ampdroid.activities.media;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.api.ItemDownloaderService;
import com.mediaportal.ampdroid.data.EpisodeDetails;
import com.mediaportal.ampdroid.data.EpisodeFile;
import com.mediaportal.ampdroid.data.SeriesEpisode;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.Utils;
import com.mediaportal.ampdroid.lists.views.EpisodePosterViewAdapter;
import com.mediaportal.ampdroid.quickactions.ActionItem;
import com.mediaportal.ampdroid.quickactions.QuickAction;
import com.mediaportal.ampdroid.utils.DownloaderUtils;

public class TabEpisodesActivity extends Activity {
   private ListView mlistView;
   private LazyLoadingAdapter mAdapter;
   MediaPlayer mMediaPlayer;
   DataHandler mService;

   private String mSeriesName;
   private int mSeriesId;
   private int mSeasonNumber;

   private LoadEpisodesTask mEpisodesLoaderTask;

   private class LoadEpisodesTask extends AsyncTask<Integer, List<SeriesEpisode>, Boolean> {
      @Override
      protected Boolean doInBackground(Integer... _params) {
         int seriesCount = mService.getEpisodesCountForSeason(mSeriesId, mSeasonNumber);

         int cursor = 0;
         while (cursor < seriesCount) {
            ArrayList<SeriesEpisode> episodes = mService.getEpisodesForSeason(mSeriesId,
                  mSeasonNumber, cursor, cursor + 4);
            publishProgress(episodes);

            cursor += 5;
         }

         return true;
      }

      @Override
      protected void onProgressUpdate(List<SeriesEpisode>... values) {
         if (values != null) {
            List<SeriesEpisode> episodes = values[0];
            for (SeriesEpisode e : episodes) {
               //EpisodeDetails details = mService.getEpisode(e.getId());
               mAdapter.AddItem(new EpisodePosterViewAdapter(mSeriesId, e));
            }
         }
         mAdapter.notifyDataSetChanged();
         super.onProgressUpdate(values);
      }

      @Override
      protected void onPostExecute(Boolean _result) {
         mAdapter.showLoadingItem(false);
      }
   }

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tabepisodesactivity);
      mlistView = (ListView) findViewById(R.id.ListView);
      mMediaPlayer = new MediaPlayer();

      Bundle extras = getIntent().getExtras();
      if (extras != null) {
         mSeriesId = extras.getInt("series_id");
         mSeriesName = extras.getString("series_name");
         mSeasonNumber = extras.getInt("season_number");

         mService = DataHandler.getCurrentRemoteInstance();

         mAdapter = new LazyLoadingAdapter(this);
         mlistView.setAdapter(mAdapter);
         
         refreshEpisodes();

      } else {// activity called without bundle infos (shouldn't happen ;))

      }

      mlistView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> _adapter, View _view, int _position, long _id) {
            ILoadingAdapterItem selectedItem = (ILoadingAdapterItem) mlistView
                  .getItemAtPosition(_position);

            SeriesEpisode selectedEp = (SeriesEpisode) selectedItem.getItem();
         }
      });

      mlistView.setOnItemLongClickListener(new OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> _item, View _view, final int _position,
               long _id) {
            final SeriesEpisode selected = (SeriesEpisode) ((ILoadingAdapterItem) _item
                  .getItemAtPosition(_position)).getItem();
            EpisodeDetails details = mService.getEpisode(selected.getId());
            final EpisodeFile epFile = details.getEpisodeFile();
            String dirName = DownloaderUtils.getTvEpisodePath(mSeriesName, selected);
            final String fileName = dirName
                  + Utils.getFileNameWithExtension(epFile.getFileName(), "\\");

            QuickAction qa = new QuickAction(_view);
            ActionItem sdCardAction = new ActionItem();

            sdCardAction.setTitle("Download to sd card");
            sdCardAction.setIcon(getResources().getDrawable(R.drawable.quickaction_sdcard));
            sdCardAction.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View _view) {
                  String url = mService.getDownloadUri(epFile.getFileName());
                  Intent download = new Intent(_view.getContext(), ItemDownloaderService.class);
                  download.putExtra("url", url);
                  download.putExtra("name", fileName);
                  startService(download);
               }
            });
            qa.addActionItem(sdCardAction);

            final File localFileName = new File(DownloaderUtils.getBaseDirectory() + "/" + fileName);

            if (localFileName.exists()) {
               ActionItem playItemAction = new ActionItem();

               playItemAction.setTitle("Play episode");
               playItemAction.setIcon(getResources().getDrawable(R.drawable.quickaction_sdcard));
               playItemAction.setOnClickListener(new OnClickListener() {
                  @Override
                  public void onClick(View _view) {
                     String url = mService.getDownloadUri(epFile.getFileName());
                     Intent download = new Intent(Intent.ACTION_VIEW);
                     download.setDataAndType(Uri.parse(localFileName.toString()), "video/*");
                     startActivity(download);

                  }
               });

               qa.addActionItem(playItemAction);
            }

            qa.setAnimStyle(QuickAction.ANIM_AUTO);

            qa.show();
            return true;
         }
      });
   }

   private void refreshEpisodes() {
      mAdapter.setLoadingText("Loading Series ...");
      mAdapter.showLoadingItem(true);
      mEpisodesLoaderTask = new LoadEpisodesTask();
      mEpisodesLoaderTask.execute(0);

   }

   @Override
   public void onDestroy() {
      mAdapter.imageLoader.stopThread();
      mlistView.setAdapter(null);
      super.onDestroy();
   }
}
