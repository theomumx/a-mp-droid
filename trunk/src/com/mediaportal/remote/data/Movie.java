package com.mediaportal.remote.data;

import android.graphics.Color;

import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;

public class Movie {
	private int Id;
	private String Title;
	private String TagLine;
	private String Filename;
	private String Genre;
	private int Year;
	private String ParentalRating;
	private String CoverThumbPath;
	
	public Movie()
	{

	}
	
	@Override 
	public String toString() {
		if(Title != null)
		{
			return Title;
		}
		else return super.toString();
    }
	
	public String getTagline() {
		return TagLine;
	}

	public void setTagline(String tagline) {
		this.TagLine = tagline;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		this.Filename = filename;
	}

	public String getGenreString() {
		return Genre;
	}

	public void setGenreString(String genreString) {
		this.Genre = genreString;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		this.Year = year;
	}

	public String getParentalRating() {
		return ParentalRating;
	}

	public void setParentalRating(String parentalRating) {
		this.ParentalRating = parentalRating;
	}

	public String getCoverThumbPath() {
		return CoverThumbPath;
	}

	public void setCoverThumbPath(String coverThumbPath) {
		this.CoverThumbPath = coverThumbPath;
	}


	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getName() {
		return Title;
	}

	public void setName(String name) {
		this.Title = name;
	}
}
