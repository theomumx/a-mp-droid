package com.mediaportal.remote.data;

import java.sql.Date;

public class MovieFull extends Movie {
	private String discId;
	private String hash;
	private int part;
	private int duration;
	private int videoWidth;
	private int videoHeight;
	private String videoResolution;
	private String videoCodec;
	private String audioCodec;
	private String audioChannels;
	private boolean hasSubtitles;
	private String videoFormat;
	private String AlternateTitlesString;
	private String sortBy;
	private String directorsString;
	private String actorsString;
	private String writersString;
	private String certification;
	private String language;
	private String summary;
	private double score;
	private int popularity;
	private Date dateAdded;
	private int runtime;
	private String imdbId;
	private String coverPathAlternate;
	private String coverPath;
	private String backdropPath;

	public String getDiscId() {
		return discId;
	}

	public void setDiscId(String discId) {
		this.discId = discId;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getVideoWidth() {
		return videoWidth;
	}

	public void setVideoWidth(int videoWidth) {
		this.videoWidth = videoWidth;
	}

	public int getVideoHeight() {
		return videoHeight;
	}

	public void setVideoHeight(int videoHeight) {
		this.videoHeight = videoHeight;
	}

	public String getVideoResolution() {
		return videoResolution;
	}

	public void setVideoResolution(String videoResolution) {
		this.videoResolution = videoResolution;
	}

	public String getVideoCodec() {
		return videoCodec;
	}

	public void setVideoCodec(String videoCodec) {
		this.videoCodec = videoCodec;
	}

	public String getAudioChannels() {
		return audioChannels;
	}

	public void setAudioChannels(String audioChannels) {
		this.audioChannels = audioChannels;
	}

	public boolean isHasSubtitles() {
		return hasSubtitles;
	}

	public void setHasSubtitles(boolean hasSubtitles) {
		this.hasSubtitles = hasSubtitles;
	}

	public String getVideoFormat() {
		return videoFormat;
	}

	public void setVideoFormat(String videoFormat) {
		this.videoFormat = videoFormat;
	}

	public String getAlternateTitlesString() {
		return AlternateTitlesString;
	}

	public void setAlternateTitlesString(String alternateTitlesString) {
		AlternateTitlesString = alternateTitlesString;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getDirectorsString() {
		return directorsString;
	}

	public void setDirectorsString(String directorsString) {
		this.directorsString = directorsString;
	}

	public String getActorsString() {
		return actorsString;
	}

	public void setActorsString(String actorsString) {
		this.actorsString = actorsString;
	}

	public String getWritersString() {
		return writersString;
	}

	public void setWritersString(String writersString) {
		this.writersString = writersString;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getCoverPathAlternate() {
		return coverPathAlternate;
	}

	public void setCoverPathAlternate(String coverPathAlternate) {
		this.coverPathAlternate = coverPathAlternate;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}
	
	public void setAudioCodec(String audioCodec) {
		this.audioCodec = audioCodec;
	}

	public String getAudioCodec() {
		return audioCodec;
	}
}
