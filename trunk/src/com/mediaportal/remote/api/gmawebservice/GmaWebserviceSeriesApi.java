package com.mediaportal.remote.api.gmawebservice;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.mediaportal.remote.api.gmawebservice.soap.WcfAccessHandler;
import com.mediaportal.remote.data.SeriesEpisode;
import com.mediaportal.remote.data.SeriesSeason;
import com.mediaportal.remote.data.Series;
import com.mediaportal.remote.data.SeriesFull;

public class GmaWebserviceSeriesApi {
	
	private WcfAccessHandler m_wcfService;
	
	private static final String GET_ALL_SERIES = "MP_GetAllSeries";
	private static final String GET_SERIES = "MP_GetSeries";
	private static final String GET_SERIES_COUNT = "MP_GetSeriesCount";
	private static final String GET_FULL_SERIES = "MP_GetFullSeries";
	private static final String GET_ALL_SEASONS = "MP_GetAllSeasons";
	private static final String GET_ALL_EPISODES = "MP_GetAllEpisodes";
	private static final String GET_ALL_EPISODES_FOR_SEASON = "MP_GetAllEpisodesForSeason";

	
	public GmaWebserviceSeriesApi(WcfAccessHandler _wcfService) {
		m_wcfService = _wcfService;
	}
	
	public ArrayList<Series> getAllSeries() {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_SERIES);
		return createSeriesList(result);
	}

	public ArrayList<Series> getSeries(int _start, int _end) {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_SERIES,
				m_wcfService.CreateProperty("startIndex", _start), 
				m_wcfService.CreateProperty("endIndex", _end));
		return createSeriesList(result);
	}

	public SeriesFull getFullSeries(int _seriesId) {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_FULL_SERIES,
				m_wcfService.CreateProperty("seriesId", _seriesId));
		return createSeriesFull(result);
	}
	
	public int getSeriesCount() {
		SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(GET_SERIES_COUNT);
		return Integer.parseInt(result.toString());
	}
	
	public ArrayList<SeriesSeason> getAllSeasons(int _seriesId) {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_SEASONS,
				m_wcfService.CreateProperty("seriesId", _seriesId));
		return createSeasons(result);
	}

	public ArrayList<SeriesEpisode> getAllEpisodes(int _seriesId) {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_EPISODES,
				m_wcfService.CreateProperty("seriesId", _seriesId));
		return createEpisodeList(result);
	}

	public ArrayList<SeriesEpisode> getAllEpisodesForSeason(int _seriesId,
			int _seasonNumber) {
		SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(
				GET_ALL_EPISODES_FOR_SEASON, m_wcfService.CreateProperty("seriesId",
						_seriesId), m_wcfService.CreateProperty("seasonNumber",
						_seasonNumber));
		return createEpisodeList(result);
	}


	
	private static void fillSeriesFullObject(SeriesFull series,
			SoapObject seriesObject) {
		series.setSortName(seriesObject.getProperty("SortName").toString());
		series.setOrigName(seriesObject.getProperty("OrigName").toString());
		series.setStatus(seriesObject.getProperty("Status").toString());
		
		//read banner urls
		SoapObject bannerUrlsObject = (SoapObject)seriesObject.getProperty("BannerUrls");
		series.setBannerUrls(getStringArray(bannerUrlsObject));
		
		//read fanart urls
		//SoapObject fanartUrlsObject = (SoapObject)seriesObject.getProperty("FanartUrls");
		//series.setFanartUrls(GetStringArray(fanartUrlsObject));
		
		//read poster urls
		SoapObject posterUrlsObject = (SoapObject)seriesObject.getProperty("PosterUrls");
		series.setPosterUrls(getStringArray(posterUrlsObject));
		
		series.setContentRating(seriesObject.getProperty("ContentRating").toString());
		series.setNetwork(seriesObject.getProperty("Network").toString());
		series.setOverview(seriesObject.getProperty("Summary").toString());
		series.setAirsDay(seriesObject.getProperty("AirsDay").toString());
		series.setAirsTime(seriesObject.getProperty("AirsTime").toString());

		//read actor names
		SoapObject actorsObject = (SoapObject)seriesObject.getProperty("Actors");
		series.setActors(getStringArray(actorsObject));
		
		series.setEpisodesUnwatchedCount(Integer.parseInt(seriesObject.getProperty("EpisodesUnwatchedCount").toString()));
		series.setRuntime(Integer.parseInt(seriesObject.getProperty("Runtime").toString()));
		//series.setFirstAired(new Date(Date.parse(seriesObject.getProperty("FirstAired").toString())));
		series.setEpisodeOrder(seriesObject.getProperty("EpisodeOrder").toString());
		

		
	}
	
	private static ArrayList<String> getStringArray(SoapObject _object)
	{
		ArrayList<String> returnArray = new ArrayList<String>();
		for(int a = 0; a < _object.getPropertyCount(); a++)
		{
			returnArray.add(_object.getProperty(a).toString());
		}
		return returnArray;
	}
	
	public static SeriesFull createSeriesFull(SoapObject _object) {
		if(_object == null)return null;
		try
		{
				SeriesFull series = new SeriesFull();
				fillSeriesObject(series, _object);
				fillSeriesFullObject(series, _object);
				return series;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public static ArrayList<Series> createSeriesList(SoapObject _object) {
		if(_object == null)return null;
		try
		{
			ArrayList<Series> seriesList = new ArrayList<Series>();
			for(int i = 0; i < _object.getPropertyCount(); i++)
			{
				SoapObject seriesObject = (SoapObject)_object.getProperty(i);
				Series series = new Series();
				fillSeriesObject(series, seriesObject);
				seriesList.add(series);
			}

			return seriesList;
			
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	protected static void fillSeriesObject(Series series,
			SoapObject seriesObject) {
		series.setId(Integer.parseInt(seriesObject.getProperty("Id").toString()));
		series.setPrettyName(seriesObject.getProperty("PrettyName").toString());
		series.setEpisodeCount(Integer.parseInt(seriesObject.getProperty("EpisodeCount").toString()));
		series.setImdbId(seriesObject.getProperty("ImdbId").toString());
		series.setRating(Float.parseFloat(seriesObject.getProperty("Rating").toString()));
		series.setRatingCount(Integer.parseInt(seriesObject.getProperty("RatingCount").toString()));
		series.setFanartUrl(seriesObject.getProperty("CurrentFanartUrl").toString());
		series.setPosterUrl(seriesObject.getProperty("CurrentPosterUrl").toString());
		series.setBannerUrl(seriesObject.getProperty("CurrentBannerUrl").toString());
		series.setGenreString(seriesObject.getProperty("GenreString").toString());
		SoapObject genreObject = (SoapObject)seriesObject.getProperty("Genres");
		ArrayList<String> genres = new ArrayList<String>();
		for(int a = 0; a < genreObject.getPropertyCount(); a++)
		{
			genres.add(genreObject.getProperty(a).toString());
		}
		series.setGenres(genres);
		
	}
	public static Series createSeries(SoapObject _object) {
		if(_object == null)return null;
		try
		{
				Series series = new Series();
				fillSeriesObject(series, _object);
				return series;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public static ArrayList<SeriesSeason> createSeasons(SoapObject _object) {
		if (_object == null)
			return null;
		try {
			ArrayList<SeriesSeason> seasonList = new ArrayList<SeriesSeason>();
			for (int i = 0; i < _object.getPropertyCount(); i++) {
				SoapObject seasonObject = (SoapObject) _object.getProperty(i);
				SeriesSeason season = new SeriesSeason();
				season.setId(seasonObject.getProperty("Id").toString());
				season.setSeriesId(Integer.parseInt(seasonObject.getProperty(
						"SeriesId").toString()));
				season.setSeasonNumber(Integer.parseInt(seasonObject
						.getProperty("SeasonNumber").toString()));
				season.setEpisodesCount(Integer.parseInt(seasonObject
						.getProperty("EpsiodesCount").toString()));
				season.setEpisodesUnwatchedCount(Integer.parseInt(seasonObject
						.getProperty("EpsiodesCountUnwatched").toString()));
				season.setSeasonBanner(seasonObject.getProperty("SeasonBanner")
						.toString());
				// season.setAlternateSeasonBanners(seasonObject.getProperty("Title").toString());
				seasonList.add(season);
			}
			return seasonList;

		} catch (Exception ex) {
			return null;
		}
	}
	
	public static SeriesEpisode fillEpisode(SoapObject _object, SeriesEpisode _episode) {
		try {	
			if(_episode == null) return _episode;
			
			_episode.setId(Integer.parseInt(_object.getProperty("Id").toString()));
			_episode.setName(_object.getProperty("Name").toString());
			_episode.setSeasonNumber(Integer.parseInt(_object.getProperty("SeasonNumber").toString()));
			_episode.setEpisodeNumber(Integer.parseInt(_object.getProperty("EpisodeNumber").toString()));
			_episode.setWatched(Integer.parseInt(_object.getProperty("Watched").toString()));
			//movie.setFirstAired(Date.(_object.getProperty("Rating").toString()));
			_episode.setBannerUrl(_object.getProperty("BannerUrl").toString());
			_episode.setRating(Float.parseFloat(_object.getProperty("Rating").toString()));
			_episode.setMyRating(Float.parseFloat(_object.getProperty("MyRating").toString()));
			_episode.setHasLocalFile(Boolean.parseBoolean(_object.getProperty("HasLocalFile").toString()));

			return _episode;

		} catch (Exception ex) {
			return null;
		}
	}
	
	public static ArrayList<SeriesEpisode> createEpisodeList(SoapObject _object) {
		if (_object == null)
			return null;
		try {
			ArrayList<SeriesEpisode> epList = new ArrayList<SeriesEpisode>();
			for (int i = 0; i < _object.getPropertyCount(); i++) {
				SoapObject episodeObject = (SoapObject) _object.getProperty(i);

				SeriesEpisode episode = new SeriesEpisode();
				fillEpisode(episodeObject, episode);
				
				epList.add(episode);
			}

			return epList;

		} catch (Exception ex) {
			return null;
		}
	}

	public static SeriesEpisode createEpisode(SoapObject _object) {
		if (_object == null)
			return null;

		SeriesEpisode episode = new SeriesEpisode();
		fillEpisode(_object, episode);

		return episode;

	}

}
