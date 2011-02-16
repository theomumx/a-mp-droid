package com.mediaportal.ampdroid.activities.tvserver;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.activities.BaseActivity;
import com.mediaportal.ampdroid.activities.StatusBarActivityHandler;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvProgram;
import com.mediaportal.ampdroid.data.TvProgramBase;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.lists.views.TvServerProgramsDetailsViewItem;
import com.mediaportal.ampdroid.quickactions.ActionItem;
import com.mediaportal.ampdroid.quickactions.QuickAction;
import com.mediaportal.ampdroid.utils.Util;

public class TvServerChannelDetailsActivity extends BaseActivity {
   private DataHandler mService;
   private ListView mEpgView;
   private LoadEpgTask mEpgLoaderTask;
   private Spinner mDaysSpinner;
   private Button mPrevDayButton;
   private Button mNextDayButton;
   private ArrayAdapter<EpgDay> mDaysAdapter;
   private LazyLoadingAdapter mEpgAdapter;
   private TextView mChannelNameText;
   private TextView mCurrentDateTextView;
   private int mChannelId;
   private String mChannelName;

   private AddScheduleTask mAddScheduleTask;
   private CancelScheduleTask mCancelScheduleTask;
   private StatusBarActivityHandler mStatusBarHandler;

   private class CancelScheduleTask extends AsyncTask<TvProgramBase, Boolean, Boolean> {
      private Context mContext;

      private CancelScheduleTask(Context _context) {
         mContext = _context;
      }

      @Override
      protected Boolean doInBackground(TvProgramBase... _params) {
         TvProgramBase program = _params[0];
         mService.cancelTvScheduleByProgramId(program.getIdProgram());

         program.setIsScheduled(false);

         return true;
      }

      @Override
      protected void onPostExecute(Boolean _result) {
         if (_result) {
            Util.showToast(mContext, "Schedule cancelled");

            mEpgAdapter.notifyDataSetInvalidated();
         } else {
            Util.showToast(mContext, "Couldn't cancel schedule");
         }
      }
   }

   private class AddScheduleTask extends AsyncTask<TvProgramBase, Boolean, Boolean> {
      private Context mContext;

      private AddScheduleTask(Context _context) {
         mContext = _context;
      }

      @Override
      protected Boolean doInBackground(TvProgramBase... _params) {
         TvProgramBase program = _params[0];
         mService.addTvSchedule(program.getIdChannel(), program.getTitle(), program.getStartTime(),
               program.getEndTime());

         program.setIsScheduled(true);

         return true;
      }

      @Override
      protected void onPostExecute(Boolean _result) {
         if (_result) {
            Util.showToast(mContext, "Schedule added");

            mEpgAdapter.notifyDataSetInvalidated();
         } else {
            Util.showToast(mContext, "Couldn't add schedule");
         }
      }
   }

   private class LoadEpgTask extends AsyncTask<Integer, Integer, List<TvProgramBase>> {
      private Context mContext;

      private LoadEpgTask(Context _context) {
         mContext = _context;
      }

      @Override
      protected List<TvProgramBase> doInBackground(Integer... _params) {
         EpgDay day = mDaysAdapter.getItem(_params[0]);
         Date begin = day.getDayBegin();
         Date end = day.getDayEnd();

         List<TvProgramBase> programs = mService.getTvBaseEpgForChannel(mChannelId, begin, end);

         return programs;
      }

      @Override
      protected void onProgressUpdate(Integer... values) {
         super.onProgressUpdate(values);
      }

      @Override
      protected void onPostExecute(List<TvProgramBase> _result) {
         if (_result != null) {
            for (TvProgramBase p : _result) {
               mEpgAdapter.addItem(new TvServerProgramsDetailsViewItem(p));
            }
         }
         mEpgAdapter.showLoadingItem(false);
         mEpgAdapter.notifyDataSetChanged();
      }
   }

   private class EpgDay {
      private int mDaysFromToday;
      private Date mDayBegin;
      private Date mDayEnd;

      private EpgDay(int _daysFromToday, Date _begin, Date _end) {
         mDayBegin = _begin;
         mDayEnd = _end;
         mDaysFromToday = _daysFromToday;
      }

      public Date getDayBegin() {
         return mDayBegin;
      }

      public Date getDayEnd() {
         return mDayEnd;
      }

      @Override
      public String toString() {
         switch (mDaysFromToday) {
         case -1:
            return getString(R.string.days_yesterday);
         case 0:
            return getString(R.string.days_today);
         default:
            return getString(R.string.days_tomorrow);
         }

      }
   }

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      setTitle(R.string.title_tvserver_epg);
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tvserverchanneldetailsactivity);

      Bundle extras = getIntent().getExtras();
      if (extras != null) {
         mChannelId = extras.getInt("channel_id");
         mChannelName = extras.getString("channel_name");
         mEpgView = (ListView) findViewById(R.id.ListViewChannels);
         mEpgAdapter = new LazyLoadingAdapter(this);
         mEpgView.setAdapter(mEpgAdapter);
         mService = DataHandler.getCurrentRemoteInstance();
         mStatusBarHandler = new StatusBarActivityHandler(this, mService);
         mStatusBarHandler.setHome(false);

         mDaysSpinner = (Spinner) findViewById(R.id.SpinnerDay);
         mDaysAdapter = new ArrayAdapter<EpgDay>(this, android.R.layout.simple_spinner_item);
         mDaysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         Calendar cal = Calendar.getInstance();
         cal.set(Calendar.AM_PM, 0);
         cal.set(Calendar.HOUR, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);
         Date begin = cal.getTime();
         cal.add(Calendar.DATE, 1);
         Date end = cal.getTime();

         mDaysAdapter.add(new EpgDay(0, begin, end));

         begin = cal.getTime();
         cal.add(Calendar.DATE, 1);
         end = cal.getTime();

         mDaysAdapter.add(new EpgDay(1, begin, end));

         mDaysSpinner.setAdapter(mDaysAdapter);

         mNextDayButton = (Button) findViewById(R.id.ButtonNextDay);
         mPrevDayButton = (Button) findViewById(R.id.ButtonPrevDay);
         mChannelNameText = (TextView)findViewById(R.id.TextViewChannelName);
         mCurrentDateTextView = (TextView)findViewById(R.id.TextViewCurrentDate);
         
         mChannelNameText.setText(mChannelName);

         mDaysSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> _adapter, View _view, int _pos, long _id) {
               refreshEpg(_pos);
               mCurrentDateTextView.setText(mDaysAdapter.getItem(_pos).toString());
               setPrevNextButtonEnabled(_pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
               // TODO Auto-generated method stub

            }
         });

         mNextDayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               int pos = mDaysSpinner.getSelectedItemPosition();

               if (pos + 1 < mDaysAdapter.getCount()) {
                  mDaysSpinner.setSelection(pos + 1);
               }
            }
         });

         mPrevDayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               int pos = mDaysSpinner.getSelectedItemPosition();

               if (pos > 0) {
                  mDaysSpinner.setSelection(pos - 1);
               }
            }
         });

         mEpgView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> _item, View _view, final int _pos,
                  long _id) {
               ILoadingAdapterItem item = (ILoadingAdapterItem) mEpgView.getItemAtPosition(_pos);
               final TvProgramBase program = (TvProgramBase) item.getItem();

               final QuickAction qa = new QuickAction(_view);

               if (program.isIsScheduled()) {
                  ActionItem addScheduleAction = new ActionItem();
                  addScheduleAction.setTitle("Cancel Recording");
                  addScheduleAction.setIcon(getResources().getDrawable(R.drawable.bubble_del));
                  addScheduleAction.setOnClickListener(new OnClickListener() {
                     @Override
                     public void onClick(View _view) {
                        mCancelScheduleTask = new CancelScheduleTask(_view.getContext());
                        mCancelScheduleTask.execute(program);

                        qa.dismiss();
                     }
                  });
                  qa.addActionItem(addScheduleAction);
               } else {
                  ActionItem addScheduleAction = new ActionItem();
                  addScheduleAction.setTitle("Record this");
                  addScheduleAction.setIcon(getResources().getDrawable(
                        R.drawable.quickaction_recording));
                  addScheduleAction.setOnClickListener(new OnClickListener() {
                     @Override
                     public void onClick(View _view) {
                        mAddScheduleTask = new AddScheduleTask(_view.getContext());
                        mAddScheduleTask.execute(program);

                        qa.dismiss();
                     }
                  });
                  qa.addActionItem(addScheduleAction);
               }
               qa.show();
               return true;
            }
         });
      }
   }

   private void setPrevNextButtonEnabled(int _pos) {
      int size = mDaysAdapter.getCount();

      if (_pos + 1 < size && size != 0) {
         mNextDayButton.setEnabled(true);
      } else {
         mNextDayButton.setEnabled(false);
      }

      if (_pos == 0 || size == 0) {
         mPrevDayButton.setEnabled(false);
      } else {
         mPrevDayButton.setEnabled(true);
      }

   }

   private void refreshEpg(int _position) {
      mEpgAdapter.clear();
      mEpgAdapter.showLoadingItem(true);
      mEpgAdapter.notifyDataSetChanged();
      mEpgLoaderTask = new LoadEpgTask(this);
      mEpgLoaderTask.execute(_position);

   }
}
