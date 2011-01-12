package com.mediaportal.remote.activities.media;

import java.io.File;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.api.ItemDownloaderService;
import com.mediaportal.remote.data.EpisodeDetails;
import com.mediaportal.remote.data.EpisodeFile;
import com.mediaportal.remote.data.SeriesEpisode;
import com.mediaportal.remote.lists.ILoadingAdapterItem;
import com.mediaportal.remote.lists.LazyLoadingAdapter;
import com.mediaportal.remote.lists.Utils;
import com.mediaportal.remote.lists.views.EpisodePosterViewAdapter;
import com.mediaportal.remote.quickactions.ActionItem;
import com.mediaportal.remote.quickactions.QuickAction;
import com.mediaportal.remote.utils.DownloaderUtils;

public class TabEpisodesActivity extends Activity {
   private ListView mlistView;
   private LazyLoadingAdapter mAdapter;
   MediaPlayer mMediaPlayer;
   DataHandler mService;

   private String mSeriesName;
   private int mSeriesId;

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
         int seasonNumber = extras.getInt("season_number");

         mService = DataHandler.getCurrentRemoteInstance();

         ArrayList<SeriesEpisode> seasons = mService.getAllEpisodesForSeason(mSeriesId,
               seasonNumber);
         mAdapter = new LazyLoadingAdapter(this, R.layout.listitem_thumb);
         for (int i = 0; i < seasons.size(); i++) {
            SeriesEpisode e = seasons.get(i);
            EpisodeDetails details = mService.getEpisode(e.getId());
            mAdapter.AddItem(new EpisodePosterViewAdapter(mSeriesId, details));
         }

         mlistView.setAdapter(mAdapter);

      } else {// activity called without movie id (shouldn't happen ;))

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
            final EpisodeDetails selected = (EpisodeDetails) ((ILoadingAdapterItem) _item
                  .getItemAtPosition(_position)).getItem();
            final EpisodeFile epFile = selected.getEpisodeFile();
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

   @Override
   public void onDestroy() {
      mAdapter.imageLoader.stopThread();
      mlistView.setAdapter(null);
      super.onDestroy();
   }
}
