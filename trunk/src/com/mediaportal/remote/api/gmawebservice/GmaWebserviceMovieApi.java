package com.mediaportal.remote.api.gmawebservice;

import java.util.ArrayList;
import java.util.Arrays;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

import com.mediaportal.remote.api.gmawebservice.soap.WcfAccessHandler;
import com.mediaportal.remote.api.soap.Ksoap2ResultParser;
import com.mediaportal.remote.data.Movie;
import com.mediaportal.remote.data.MovieFull;

class GmaWebserviceMovieApi {
	private WcfAccessHandler m_wcfService;
	
	private static final String GET_ALL_MOVIES = "MP_GetAllMovies";
	private static final String GET_MOVIES_COUNT = "MP_GetMovieCount";
	private static final String GET_MOVIES = "MP_GetMovies";
	private static final String GET_MOVIE_DETAILS = "MP_GetFullMovie";
	private static final String SEARCH_FOR_MOVIE = "MP_SearchForMovie";
	
	public GmaWebserviceMovieApi(WcfAccessHandler _wcfService) {
		m_wcfService = _wcfService;
	}

	ArrayList<Movie> getAllMovies() {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_MOVIES);
      if (result != null) {
         Movie[] movies = (Movie[]) Ksoap2ResultParser.createObject(result,
               Movie[].class);

         if (movies != null) {
            return new ArrayList<Movie>(Arrays.asList(movies));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ALL_MOVIES);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALL_MOVIES);
      }
      return null;
	}

	int getMovieCount() {
		SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(GET_MOVIES_COUNT);

      if (result != null) {
         Integer resultObject = (Integer) Ksoap2ResultParser.getPrimitive(result, Integer.class);
         return resultObject;
      } else {
         Log.d("Soap", "Error calling soap method " + GET_MOVIES_COUNT);
      }
      return -99;
	}

	MovieFull getMovieDetails(int _movieId) {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_MOVIE_DETAILS,
				 m_wcfService.CreateProperty("movieId", _movieId));
      if (result != null) {
         MovieFull movie = (MovieFull) Ksoap2ResultParser.createObject(result, MovieFull.class);

         if (movie != null) {
            return movie;
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_MOVIE_DETAILS);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_MOVIE_DETAILS);
      }
      return null;
	}

	ArrayList<Movie> getMovies(int _start, int _end) {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_MOVIES,
				m_wcfService.CreateProperty("startIndex", _start), 
				m_wcfService.CreateProperty("endIndex", _end));
      if (result != null) {
         Movie[] movies = (Movie[]) Ksoap2ResultParser.createObject(result,
               Movie[].class);

         if (movies != null) {
            return new ArrayList<Movie>(Arrays.asList(movies));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_MOVIES);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_MOVIES);
      }
      return null;
	}
	
	  ArrayList<Movie> searchForMovie(String _searchString) {
	      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(SEARCH_FOR_MOVIE,
	            m_wcfService.CreateProperty("searchString", _searchString));
	      if (result != null) {
	         Movie[] movies = (Movie[]) Ksoap2ResultParser.createObject(result,
	               Movie[].class);

	         if (movies != null) {
	            return new ArrayList<Movie>(Arrays.asList(movies));
	         } else {
	            Log.d("Soap", "Error parsing result from soap method " + SEARCH_FOR_MOVIE);
	         }
	      } else {
	         Log.d("Soap", "Error calling soap method " + SEARCH_FOR_MOVIE);
	      }
	      return null;
	   }
}
