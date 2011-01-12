package com.mediaportal.ampdroid.lists.views;

import java.util.Date;

import android.R;

import com.mediaportal.ampdroid.data.TvRecording;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;

public class TvServerRecordingsThumbsView implements ILoadingAdapterItem {
   TvRecording mRecording;

   public TvServerRecordingsThumbsView(TvRecording _recording) {
      mRecording = _recording;
   }

   @Override
   public String getText() {
      //TODO -> get channel name
      return "Channel: " + mRecording.getIdChannel();
   }

   @Override
   public String getSubText() {
      Date begin = mRecording.getStartTime();
      Date end = mRecording.getEndTime();
      if (begin != null && end != null) {
         return begin.toString() + " - " + end.toString();
      } else {
         return "Unknown time";
      }
   }

   @Override
   public String getImage() {
      return null;
   }

   @Override
   public String getImageCacheName() {
      return null;
   }

   @Override
   public String getTitle() {
      return mRecording.getTitle();
   }

   @Override
   public int getTextColor() {
      return 0;
   }

   @Override
   public int getSubTextColor() {
      return 0;
   }

   @Override
   public int getTitleColor() {
      return 0;
   }

   @Override
   public int getType() {
      return 0;
   }

   @Override
   public int getXml() {
      return com.mediaportal.ampdroid.R.layout.listitem_recordings_thumbs;
   }

   @Override
   public Object getItem() {
      return mRecording;
   }

}
