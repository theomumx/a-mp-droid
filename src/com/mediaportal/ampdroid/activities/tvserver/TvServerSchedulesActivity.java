package com.mediaportal.ampdroid.activities.tvserver;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvProgram;
import com.mediaportal.ampdroid.data.TvSchedule;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.views.TvServerSchedulesDetailsView;
import com.mediaportal.ampdroid.quickactions.ActionItem;
import com.mediaportal.ampdroid.quickactions.QuickAction;
import com.mediaportal.ampdroid.utils.Util;

public class TvServerSchedulesActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mListView;
   private LazyLoadingAdapter mAdapter;
   private UpdateSchedulesTask mRecordingsUpdater;
   private CancelScheduleTask mCancelScheduleTask;

   private class CancelScheduleTask extends AsyncTask<ILoadingAdapterItem, Boolean, Boolean> {
      private Context mContext;

      private CancelScheduleTask(Context _context) {
         mContext = _context;
      }

      @Override
      protected Boolean doInBackground(ILoadingAdapterItem... _params) {
         ILoadingAdapterItem item = _params[0];
         TvSchedule schedule = (TvSchedule) item.getItem();
         mService.cancelTvScheduleByScheduleId(schedule.getIdSchedule());
         mAdapter.removeItem(item);
         
         return true;
      }

      @Override
      protected void onPostExecute(Boolean _result) {
         if (_result) {
            Util.showToast(mContext, "Schedule cancelled");

            mAdapter.notifyDataSetChanged();
         } else {
            Util.showToast(mContext, "Couldn't cancel schedule");
         }
      }
   }

   private class UpdateSchedulesTask extends AsyncTask<Integer, Integer, List<TvSchedule>> {
      @Override
      protected List<TvSchedule> doInBackground(Integer... _params) {
         List<TvSchedule> recordings = mService.getTvSchedules();
         return recordings;
      }

      @Override
      protected void onPostExecute(List<TvSchedule> _result) {
         if (_result != null) {
            for (TvSchedule s : _result) {
               mAdapter.AddItem(new TvServerSchedulesDetailsView(s));
            }

            mListView.setAdapter(mAdapter);
         }
         mAdapter.showLoadingItem(false);
      }
   }

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setHome(false);
      setTitle(R.string.title_tvserver_schedules);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverschedulesactivity);

      mListView = (ListView) findViewById(R.id.ListViewSchedules);

      mService = DataHandler.getCurrentRemoteInstance();
      mAdapter = new LazyLoadingAdapter(this);
      mListView.setAdapter(mAdapter);
      mService = DataHandler.getCurrentRemoteInstance();

      mListView.setOnItemLongClickListener(new OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> _item, View _view, int _pos, long _id) {
            final ILoadingAdapterItem item = (ILoadingAdapterItem) mListView
                  .getItemAtPosition(_pos);
            final QuickAction qa = new QuickAction(_view);

            ActionItem addScheduleAction = new ActionItem();
            addScheduleAction.setTitle("Cancel Recording");
            addScheduleAction.setIcon(getResources().getDrawable(R.drawable.bubble_del));
            addScheduleAction.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View _view) {
                  mCancelScheduleTask = new CancelScheduleTask(_view.getContext());
                  mCancelScheduleTask.execute(item);

                  qa.dismiss();
               }
            });
            qa.addActionItem(addScheduleAction);
            qa.show();
            return true;
         }
      });
      refreshSchedules();
   }

   private void refreshSchedules() {
      mAdapter.showLoadingItem(true);
      mAdapter.setLoadingText("Loading Schedules ...");
      mRecordingsUpdater = new UpdateSchedulesTask();
      mRecordingsUpdater.execute(0);
   }
}
