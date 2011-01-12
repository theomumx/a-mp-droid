package com.mediaportal.ampdroid.lists;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mediaportal.ampdroid.R;
public class LazyLoadingAdapter extends BaseAdapter {
   private Activity activity;
   private ArrayList<ILoadingAdapterItem> data;
   private static LayoutInflater inflater = null;
   private boolean showLoadingItem;
   private int itemLayout = 0;

   public ImageHandler imageLoader;

   public LazyLoadingAdapter(Activity _activity, int _itemLayout) {
      activity = _activity;

      inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      imageLoader = new ImageHandler(activity.getApplicationContext());
      data = new ArrayList<ILoadingAdapterItem>();
      itemLayout = _itemLayout;
   }

   public LazyLoadingAdapter(Activity _activity, int _itemLayout,
         ArrayList<ILoadingAdapterItem> _items) {
      this(_activity, _itemLayout);
      data = _items;
   }

   public void AddItem(ILoadingAdapterItem _item) {
      data.add(_item);
   }

   public int getCount() {
      return (showLoadingItem ? data.size() + 1 : data.size());
   }

   public Object getItem(int position) {
      return data.get(position);
   }

   public long getItemId(int position) {
      return position;
   }

   public static class ViewHolder {
      public TextView text;
      public ImageView image;
   }

   public View getView(int position, View convertView, ViewGroup parent) {
      View vi = convertView;
      SubtextViewHolder holder;

      if (convertView == null) {
         try {
            vi = inflater.inflate(itemLayout, null);

            // int height = parent.getHeight();
            // vi.setMinimumHeight((int) (height / 3));

            /*
             * RelativeLayout.LayoutParams fpParam = new
             * RelativeLayout.LayoutParams( LayoutParams.FILL_PARENT, );
             * vi.setLayoutParams(fpParam);
             */
         } catch (Exception ex) {
            Log.d("Adapter", ex.getMessage());
         }
         holder = new SubtextViewHolder();
         holder.text = (TextView) vi.findViewById(R.id.TextViewText);
         holder.image = (ImageView) vi.findViewById(R.id.ImageViewEventImage);
         holder.subtext = (TextView) vi.findViewById(R.id.TextViewSubtext);
         holder.title = (TextView) vi.findViewById(R.id.TextViewTitle);
         holder.progressBar = (ProgressBar) vi.findViewById(R.id.ProgressBarLoading);
         vi.setTag(holder);
      } else {
         holder = (SubtextViewHolder) vi.getTag();
      }

      if (data != null) {
         if (showLoadingItem && position == data.size()) {
            setLoadingItemsVisible(true, holder);

            if (holder.text != null)
               holder.text.setText("Loading...");

            if (holder.subtext != null)
               holder.subtext.setText("");
         } else if (data.size() > position) {
            setLoadingItemsVisible(true, holder);

            if (holder.title != null) {
               holder.title.setTypeface(null, Typeface.BOLD);

               holder.title.setTextColor(Color.WHITE);
               holder.title.setText(data.get(position).getTitle());
            }

            if (holder.text != null) {
               holder.text.setText(data.get(position).getText());
               int color = data.get(position).getTextColor();
               
               holder.text.setTextColor(Color.WHITE);
               //if (color != -99) {
               //   holder.text.setTextColor(color);
               //}
            }

            if (holder.subtext != null) {
               holder.subtext.setText(data.get(position).getSubText());
            }

            if (holder.image != null) {
               String image = data.get(position).getImage();
               String cache = data.get(position).getImageCacheName();

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

   private void setLoadingItemsVisible(boolean _visible, SubtextViewHolder _holder) {
      if (_visible) {
         if (_holder.progressBar != null)
            _holder.progressBar.setVisibility(View.GONE);
         if (_holder.image != null)
            _holder.image.setVisibility(View.VISIBLE);
         if (_holder.title != null)
            _holder.title.setVisibility(View.VISIBLE);
         if (_holder.subtext != null)
            _holder.subtext.setVisibility(View.VISIBLE);
      } else {
         if (_holder.progressBar != null)
            _holder.progressBar.setVisibility(View.VISIBLE);
         if (_holder.image != null)
            _holder.image.setVisibility(View.GONE);
         if (_holder.title != null)
            _holder.title.setVisibility(View.INVISIBLE);
         if (_holder.subtext != null)
            _holder.subtext.setVisibility(View.INVISIBLE);
      }
   }

   public void setShowLoadingItem(boolean showLoadingItem) {
      this.showLoadingItem = showLoadingItem;
   }

   public boolean isShowLoadingItem() {
      return showLoadingItem;
   }

   public void setRowHeight(int i) {
      // TODO Auto-generated method stub

   }
}
