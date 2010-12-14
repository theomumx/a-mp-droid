package com.mediaportal.remote.api.gmawebservice;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.mediaportal.remote.api.gmawebservice.soap.WcfAccessHandler;
import com.mediaportal.remote.data.Movie;
import com.mediaportal.remote.data.MovieFull;

class GmaWebserviceMovieApi {
	private WcfAccessHandler m_wcfService;
	
	private static final String GET_ALL_MOVIES = "MP_GetAllMovies";
	private static final String GET_MOVIES_COUNT = "MP_GetMovieCount";
	private static final String GET_MOVIES = "MP_GetMovies";
	private static final String GET_MOVIE_DETAILS = "MP_GetFullMovie";
	
	public GmaWebserviceMovieApi(WcfAccessHandler _wcfService) {
		m_wcfService = _wcfService;
	}

	ArrayList<Movie> getAllMovies() {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_MOVIES);
		//return MPMovie.createMPMovieList(result);
		return createMovieList(result);
	}

	int getMovieCount() {
		SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(GET_MOVIES_COUNT);
		return Integer.parseInt(result.toString());
	}

	MovieFull getMovieDetails(int _movieId) {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_MOVIE_DETAILS,
				 m_wcfService.CreateProperty("movieId", _movieId));
		return createMovieFull(result);
	}

	ArrayList<Movie> getMovies(int _start, int _end) {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_MOVIES,
				m_wcfService.CreateProperty("startIndex", _start), 
				m_wcfService.CreateProperty("endIndex", _end));
		return createMovieList(result);
	}
	
	public static MovieFull createMovieFull(SoapObject _object) {
		if (_object == null)
			return null;
		try {

			MovieFull movie = new MovieFull();
			fillBasicMovie(_object, movie);
			fillFullMovie(_object, movie);
			return movie;

		} catch (Exception ex) {
			return null;
		}
	}
	
	public static ArrayList<Movie> createMovieList(SoapObject _object) {
		if(_object == null)return null;
		try
		{
			ArrayList<Movie> movies = new ArrayList<Movie>();
			for(int i = 0; i < _object.getPropertyCount(); i++)
			{
				SoapObject movieObject = (SoapObject)_object.getProperty(i);
				Movie movie = new Movie();
				fillBasicMovie(movieObject, movie);
				movies.add(movie);
			}

			return movies;
			
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	protected static void fillBasicMovie(SoapObject movieObject, Movie movie) {
		movie.setId(Integer.parseInt(movieObject.getProperty("Id").toString()));
		movie.setName(movieObject.getProperty("Title").toString());
		movie.setTagline(movieObject.getProperty("TagLine").toString());
		movie.setFilename(movieObject.getProperty("Filename").toString());
		movie.setGenreString(movieObject.getProperty("Genre").toString());
		/*if (movieObject.getProperty("ParentalRating") != null) {
			movie.setParentalRating(movieObject.getProperty("ParentalRating")
					.toString());
		}/*/
		movie.setYear(Integer.parseInt(movieObject.getProperty("Year").toString()));
		movie.setCoverThumbPath(movieObject.getProperty("CoverThumbPath").toString());
	}
	
	protected static void fillFullMovie(SoapObject movieObject, MovieFull movie) {
		movie.setDiscId(movieObject.getProperty("DiscId").toString());
		movie.setHash(movieObject.getProperty("Hash").toString());
		movie.setPart(Integer.parseInt(movieObject.getProperty("Part").toString()));
		movie.setDuration(Integer.parseInt(movieObject.getProperty("Duration").toString()));
		movie.setVideoWidth(Integer.parseInt(movieObject.getProperty("VideoWidth").toString()));
		movie.setVideoHeight(Integer.parseInt(movieObject.getProperty("VideoHeight").toString()));
		movie.setVideoResolution(movieObject.getProperty("VideoResolution").toString());
		movie.setVideoCodec(movieObject.getProperty("VideoCodec").toString());
		movie.setAudioCodec(movieObject.getProperty("AudioCodec").toString());
		movie.setAudioChannels(movieObject.getProperty("AudioChannels").toString());
		movie.setHasSubtitles(Boolean.parseBoolean(movieObject.getProperty("HasSubtitles").toString()));
		movie.setVideoFormat(movieObject.getProperty("VideoFormat").toString());
		
		movie.setAlternateTitlesString(movieObject.getProperty("AlternateTitles").toString());
		movie.setSortBy(movieObject.getProperty("SortBy").toString());
		movie.setDirectorsString(movieObject.getProperty("Directors").toString());
		movie.setWritersString(movieObject.getProperty("Writers").toString());
		movie.setActorsString(movieObject.getProperty("Actors").toString());
		movie.setCertification(movieObject.getProperty("Certification").toString());
		movie.setLanguage(movieObject.getProperty("Language").toString());
		movie.setSummary(movieObject.getProperty("Summary").toString());
	}

}
