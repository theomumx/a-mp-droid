package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;


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

   @JsonProperty("TagLine")
	public String getTagline() {
		return TagLine;
	}

   @JsonProperty("TagLine")
	public void setTagline(String tagline) {
		this.TagLine = tagline;
	}

   @JsonProperty("Filename")
	public String getFilename() {
		return Filename;
	}

   @JsonProperty("Filename")
	public void setFilename(String filename) {
		this.Filename = filename;
	}

   @JsonProperty("Genre")
	public String getGenreString() {
		return Genre;
	}

   @JsonProperty("Genre")
	public void setGenreString(String genreString) {
		this.Genre = genreString;
	}

   @JsonProperty("Year")
	public int getYear() {
		return Year;
	}

   @JsonProperty("Year")
	public void setYear(int year) {
		this.Year = year;
	}

   @JsonProperty("ParentalRating")
	public String getParentalRating() {
		return ParentalRating;
	}

   @JsonProperty("ParentalRating")
	public void setParentalRating(String parentalRating) {
		this.ParentalRating = parentalRating;
	}

   @JsonProperty("CoverThumbPath")
	public String getCoverThumbPath() {
		return CoverThumbPath;
	}

   @JsonProperty("CoverThumbPath")
	public void setCoverThumbPath(String coverThumbPath) {
		this.CoverThumbPath = coverThumbPath;
	}


   @JsonProperty("Id")
	public int getId() {
		return Id;
	}

   @JsonProperty("Id")
	public void setId(int id) {
		this.Id = id;
	}

   @JsonProperty("Title")
	public String getName() {
		return Title;
	}

   @JsonProperty("Title")
	public void setName(String name) {
		this.Title = name;
	}
}
