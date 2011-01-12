package com.mediaportal.remote.lists;


public interface ILoadingAdapterItem {
	String getText();
	String getSubText();
	String getImage();
	String getImageCacheName();
	String getTitle();
	int getTextColor();
	int getSubTextColor();
	int getTitleColor();
	int getType();
	int getXml();
	
	Object getItem();
	
}
