package com.mediaportal.ampdroid.lists;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.lists.views.LoadingItemAdapter;

public class LazyLoadingAdapter extends BaseAdapter {
   private Activity activity;
   private ArrayList<ILoadingAdapterItem> data;
   private static LayoutInflater inflater = null;
   private boolean showLoadingItem;
   public ImageHandler imageLoader;
   private ILoadingAdapterItem loadingIndicator;

   public LazyLoadingAdapter(Activity _activity) {
      activity = _activity;

      inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      imageLoader = new ImageHandler(activity.getApplicationContext());
      data = new ArrayList<ILoadingAdapterItem>();
      loadingIndicator = new LoadingItemAdapter("");
   }

   public LazyLoadingAdapter(Activity _activity, ArrayList<ILoadingAdapterItem> _items) {
      this(_activity);
      data = _items;
   }

   public void AddItem(ILoadingAdapterItem _item) {
      data.add(_item);
   }

   public int getCount() {
      return (showLoadingItem ? data.size() + 1 : data.size());
   }

   public Object getItem(int position) {
      if (position == data.size()) {
         return loadingIndicator;
      } else {
         return data.get(position);
      }
      
   }

   public long getItemId(int position) {
      return position;
   }

   public static class ViewHolder {
      public TextView text;
      public ImageView image;
   }

   @Override
   public int getItemViewType(int position) {
      if (position == data.size()) {
         return loadingIndicator.getType();
      } else {
         return data.get(position).getType();
      }
   }

   @Override
   public int getViewTypeCount() {
      return 10;
   }

   public View getView(int position, View convertView, ViewGroup parent) {
      View vi = convertView;
      ViewHolder holder = null;
      if (data != null) {
         ILoadingAdapterItem item = null;
         if (position >= data.size()) {
            item = loadingIndicator;
         } else {
            item = data.get(position);
         }

         if (convertView == null) {
            try {
               vi = inflater.inflate(item.getXml(), null);
               holder = item.createViewHolder(vi);
               vi.setTag(holder);
            } catch (Exception ex) {
               Log.d("Adapter", ex.getMessage());
            }
         } else {
            holder = (SubtextViewHolder) vi.getTag();
         }

         if (holder != null) {
            item.fillViewFromViewHolder(holder);

            if (holder.image != null) {
               String image = item.getImage();
               String cache = item.getImageCacheName();

               if (image != null && !image.equals("")) {
                  holder.image.setTag(image);
                  imageLoader.DisplayImage(image, cache, activity, holder.image);
               } else {// todo: defaultimage
                  holder.image.setImageResource(R.drawable.mp_logo_2);
               }
            }
         }
      }

      return vi;
   }

   public void showLoadingItem(boolean showLoadingItem) {
      this.showLoadingItem = showLoadingItem;
   }

   public boolean isLoadingItemShown() {
      return showLoadingItem;
   }

   public void setLoadingText(String _text) {
      ((LoadingItemAdapter) loadingIndicator).setLoadingText(_text);
   }

   public void clear() {
      data.clear();
   }

   public void removeItem(ILoadingAdapterItem _item) {
      data.remove(_item);
   }
}
