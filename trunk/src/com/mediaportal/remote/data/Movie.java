package com.mediaportal.remote.data;

import android.graphics.Color;

import com.mediaportal.remote.activities.lists.ILoadingAdapter;

public class Movie implements ILoadingAdapter {
	private int id;
	private String name;
	private String tagline;
	private String filename;
	private String genreString;
	private int year;
	private String parentalRating;
	private String coverThumbPath;
	
	public Movie()
	{

	}
	
	@Override 
	public String toString() {
		if(name != null)
		{
			return name;
		}
		else return super.toString();
    }
	
	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getGenreString() {
		return genreString;
	}

	public void setGenreString(String genreString) {
		this.genreString = genreString;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getParentalRating() {
		return parentalRating;
	}

	public void setParentalRating(String parentalRating) {
		this.parentalRating = parentalRating;
	}

	public String getCoverThumbPath() {
		return coverThumbPath;
	}

	public void setCoverThumbPath(String coverThumbPath) {
		this.coverThumbPath = coverThumbPath;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getImage() {
		return this.coverThumbPath;
	}

	@Override
	public String getSubText() {
		return this.tagline;
	}

	@Override
	public String getText() {
		return Integer.toString(this.year);
	}

	@Override
	public int getTextColor() {
		return Color.WHITE;
	}

	@Override
	public String getTitle() {
		return this.name;
	}
}
