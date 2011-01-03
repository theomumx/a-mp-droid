package com.mediaportal.remote.activities.quickactions;


import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;

/**
 * Action item, displayed as menu with icon and text.
 * 
 * @author Lorensius. W. L. T
 *
 */
public class ActionItem {
	private Drawable mIcon;
	private String mTitle;
	private OnClickListener mListener;
	
	/**
	 * Constructor
	 */
	public ActionItem() {}
	
	/**
	 * Constructor
	 * 
	 * @param _icon {@link Drawable} action icon
	 */
	public ActionItem(Drawable _icon) {
		this.mIcon = _icon;
	}
	
	/**
	 * Set action title
	 * 
	 * @param _title action title
	 */
	public void setTitle(String _title) {
		this.mTitle = _title;
	}
	
	/**
	 * Get action title
	 * 
	 * @return action title
	 */
	public String getTitle() {
		return this.mTitle;
	}
	
	/**
	 * Set action icon
	 * 
	 * @param _icon {@link Drawable} action icon
	 */
	public void setIcon(Drawable _icon) {
		this.mIcon = _icon;
	}
	
	/**
	 * Get action icon
	 * @return  {@link Drawable} action icon
	 */
	public Drawable getIcon() {
		return this.mIcon;
	}
	
	/**
	 * Set on click listener
	 * 
	 * @param _listener on click listener {@link View.OnClickListener}
	 */
	public void setOnClickListener(OnClickListener _listener) {
		this.mListener = _listener;
	}
	
	/**
	 * Get on click listener
	 * 
	 * @return on click listener {@link View.OnClickListener}
	 */
	public OnClickListener getListener() {
		return this.mListener;
	}
}