package com.mediaportal.remote.activities.lists;

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

import com.mediaportal.remote.R;


public class LazyLoadingAdapter extends BaseAdapter {
	private Activity activity;
	private ArrayList<ILoadingAdapter> data;
	private static LayoutInflater inflater = null;
	private boolean showLoadingItem;
	public ImageHandler imageLoader;

	public LazyLoadingAdapter(Activity _activity) {
		activity = _activity;

		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageHandler(activity.getApplicationContext());
		data = new ArrayList<ILoadingAdapter>();
	}

	public LazyLoadingAdapter(Activity _activity,
			ArrayList<ILoadingAdapter> _items) {
		this(_activity);
		data = _items;
	}

	public void AddItem(ILoadingAdapter _item) {
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
				vi = inflater.inflate(R.layout.everyweek_listitem, null);

				//int height = parent.getHeight();
				//vi.setMinimumHeight((int) (height / 3));
				
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
			holder.image = (ImageView) vi
					.findViewById(R.id.ImageViewEventImage);
			holder.subtext = (TextView) vi.findViewById(R.id.TextViewSubtext);
			holder.title = (TextView) vi.findViewById(R.id.TextViewTitle);
			holder.progressBar = (ProgressBar) vi
					.findViewById(R.id.ProgressBarLoading);
			vi.setTag(holder);
		} else {
			holder = (SubtextViewHolder) vi.getTag();
		}

		if (data != null) {
			if (showLoadingItem && position == data.size()) {
				setLoadingItemsVisible(true, holder);

				holder.text.setText("Loading...");
				holder.subtext.setText("");
			} else if (data.size() > position) {
				setLoadingItemsVisible(true, holder);

				holder.title.setTypeface(null, Typeface.BOLD);
				holder.title.setTextColor(Color.WHITE);
				holder.title.setText(data.get(position).getTitle());
				
				holder.text.setText(data.get(position).getText());
				int color = data.get(position).getTextColor();
				if (color != -99) {
					holder.text.setTextColor(color);
				}
				holder.subtext.setText(data.get(position).getSubText());
				String image = data.get(position).getImage();

				if (image != null && !image.equals("")) {
					holder.image.setTag(image);
					imageLoader.DisplayImage(data.get(position).getImage(),
							activity, holder.image);
				} else {// todo: defaultimage
					holder.image.setImageResource(R.drawable.mplogo);
				}

			}
		}

		return vi;
	}

	private void setLoadingItemsVisible(boolean _visible,
			SubtextViewHolder _holder) {
		if (_visible) {
			_holder.progressBar.setVisibility(View.GONE);
			_holder.image.setVisibility(View.VISIBLE);
			_holder.title.setVisibility(View.VISIBLE);
			_holder.subtext.setVisibility(View.VISIBLE);
		} else {
			_holder.progressBar.setVisibility(View.VISIBLE);
			_holder.image.setVisibility(View.GONE);
			_holder.title.setVisibility(View.INVISIBLE);
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
