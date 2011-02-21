package com.mediaportal.ampdroid.activities.media;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.FileInfo;
import com.mediaportal.ampdroid.data.VideoShare;
import com.mediaportal.ampdroid.utils.Util;

public class TabSharesActivity extends Activity {
   private ListView mListView;
   private ArrayAdapter<VideoShare> mListItems;
   private ArrayAdapter<FileInfo> mFileItems;
   private List<String> mBreadCrumb;
   private LoadSharesTask mSeriesLoaderTask;
   private DataHandler mService;
   private LoadFolderTask mFilesLoaderTask;
   private ProgressDialog mLoadingDialog;

   private class LoadSharesTask extends AsyncTask<Integer, Integer, List<VideoShare>> {
      @Override
      protected List<VideoShare> doInBackground(Integer... _params) {
         List<VideoShare> shares = mService.getAllVideoShares();

         return shares;
      }

      @Override
      protected void onPostExecute(List<VideoShare> _result) {
         mListItems.clear();
         
         if (_result != null) {
            for (VideoShare s : _result) {
               mListItems.add(s);
            }
         }

         mListView.setAdapter(mListItems);
         mListItems.notifyDataSetChanged();
         mLoadingDialog.dismiss();
      }
   }
   
   private class LoadFolderTask extends AsyncTask<String, Integer, List<FileInfo>> {
      @Override
      protected List<FileInfo> doInBackground(String... _params) {
         List<FileInfo> shares = mService.getFilesForFolder(_params[0]);

         return shares;
      }

      @Override
      protected void onPostExecute(List<FileInfo> _result) {
         mFileItems.clear();
         
         if (_result != null) {
            for (FileInfo f : _result) {
               mFileItems.add(f);
            }
         }

         mListView.setAdapter(mFileItems);
         mFileItems.notifyDataSetChanged();
         mLoadingDialog.dismiss();
      }
   }


   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tabsharesactivity);

      mListView = (ListView) findViewById(R.id.ListViewShares);
      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> _adapter, View _view, int _pos, long _id) {
            VideoShare selected = (VideoShare) mListView.getItemAtPosition(_pos);
            Util.showToast(_view.getContext(), selected.Path);
            // handleListClick(v, position, selected);
         }
      });
      
      mFileItems = new ArrayAdapter<FileInfo>(this, android.R.layout.simple_list_item_1);

      mListItems = new ArrayAdapter<VideoShare>(this, android.R.layout.simple_list_item_1);
      mListView.setAdapter(mListItems);
      mListView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> _adapter, View _view, int _pos, long _id) {
            if(_adapter.getAdapter().equals(mListItems)){
               VideoShare selected = (VideoShare) mListView.getItemAtPosition(_pos);
               loadFiles(selected.Path);
            }
         }
      });
      
      mBreadCrumb = new ArrayList<String>();

      mService = DataHandler.getCurrentRemoteInstance();

      loadShares();
   }
   
   private void loadShares(){
      mLoadingDialog = ProgressDialog.show(getParent(), " Loading Video Shares ",
            " Loading. Please wait ... ", true);
      mLoadingDialog.setCancelable(true);
      
      mSeriesLoaderTask = new LoadSharesTask();
      mSeriesLoaderTask.execute(0);
   }


   protected void loadFiles(String _path) {
      mLoadingDialog = ProgressDialog.show(getParent(), " Loading Files ",
            " Loading. Please wait ... ", true);
      mLoadingDialog.setCancelable(true);
      mBreadCrumb.add(_path);
      
      mFilesLoaderTask = new LoadFolderTask();
      mFilesLoaderTask.execute(_path);
   }
   
   
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event) {
       if (keyCode == KeyEvent.KEYCODE_BACK) {
          if(!mListView.getAdapter().equals(mListItems)){
             if(mBreadCrumb.size() == 1){
                mBreadCrumb.clear();
                
                loadShares();
             }
             else{
                String lastFolder = mBreadCrumb.get(mBreadCrumb.size() - 1);
                loadFiles(lastFolder);
                mBreadCrumb.remove(mBreadCrumb.size() - 1);
             }
             return true;
          }
          
       }
       return super.onKeyDown(keyCode, event);
   }


}
