package com.mediaportal.ampdroid.lists;

import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;

import android.view.View;


public interface ILoadingAdapterItem {
	String getImage();
	String getImageCacheName();
	int getType();
	int getXml();
	Object getItem();
	ViewHolder createViewHolder(View _view);
	void fillViewFromViewHolder(ViewHolder _holder);
}
