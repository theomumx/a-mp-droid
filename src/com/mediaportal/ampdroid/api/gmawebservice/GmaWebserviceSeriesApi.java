package com.mediaportal.ampdroid.api.gmawebservice;

import java.util.ArrayList;
import java.util.Arrays;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

import com.mediaportal.ampdroid.api.gmawebservice.soap.WcfAccessHandler;
import com.mediaportal.ampdroid.api.soap.Ksoap2ResultParser;
import com.mediaportal.ampdroid.data.EpisodeDetails;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.data.SeriesEpisode;
import com.mediaportal.ampdroid.data.SeriesFull;
import com.mediaportal.ampdroid.data.SeriesSeason;

public class GmaWebserviceSeriesApi {

   private WcfAccessHandler m_wcfService;

   private static final String GET_ALL_SERIES = "MP_GetAllSeries";
   private static final String GET_SERIES = "MP_GetSeries";
   private static final String GET_SERIES_COUNT = "MP_GetSeriesCount";
   private static final String GET_FULL_SERIES = "MP_GetFullSeries";
   private static final String GET_ALL_SEASONS = "MP_GetAllSeasons";
   private static final String GET_SEASON = "MP_GetSeason";
   private static final String GET_ALL_EPISODES = "MP_GetAllEpisodes";
   private static final String GET_EPISODES = "MP_GetEpisodes";
   private static final String GET_EPISODES_COUNT = "MP_GetEpisodesCount";
   private static final String GET_EPISODES_COUNT_FOR_SEASON = "MP_GetEpisodesCountForSeason";
   private static final String GET_ALL_EPISODES_FOR_SEASON = "MP_GetAllEpisodesForSeason";
   private static final String GET_EPISODES_FOR_SEASON = "MP_GetEpisodesForSeason";
   private static final String GET_FULL_EPISODE = "MP_GetFullEpisode";

   public GmaWebserviceSeriesApi(WcfAccessHandler _wcfService) {
      m_wcfService = _wcfService;
   }

   public ArrayList<Series> getAllSeries() {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_SERIES);
      if (result != null) {
         Series[] series = (Series[]) Ksoap2ResultParser.createObject(result, Series[].class);

         if (series != null) {
            return new ArrayList<Series>(Arrays.asList(series));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ALL_SERIES);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALL_SERIES);
      }
      return null;
   }

   public ArrayList<Series> getSeries(int _start, int _end) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_SERIES, m_wcfService
            .CreateProperty("startIndex", _start), m_wcfService.CreateProperty("endIndex", _end));

      if (result != null) {
         Series[] series = (Series[]) Ksoap2ResultParser.createObject(result, Series[].class);

         if (series != null) {
            return new ArrayList<Series>(Arrays.asList(series));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_SERIES);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_SERIES);
      }
      return null;
   }

   public SeriesFull getFullSeries(int _seriesId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_FULL_SERIES, m_wcfService
            .CreateProperty("seriesId", _seriesId));
      if (result != null) {
         SeriesFull series = (SeriesFull) Ksoap2ResultParser.createObject(result, SeriesFull.class);

         if (series != null) {
            return series;
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_FULL_SERIES);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_FULL_SERIES);
      }
      return null;
   }

   public int getSeriesCount() {
      SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(GET_SERIES_COUNT);

      if (result != null) {
         Integer resultObject = (Integer) Ksoap2ResultParser.getPrimitive(result, Integer.class);
         return resultObject;
      } else {
         Log.d("Soap", "Error calling soap method " + GET_SERIES_COUNT);
      }
      return -99;
   }

   public ArrayList<SeriesSeason> getAllSeasons(int _seriesId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_SEASONS, m_wcfService
            .CreateProperty("seriesId", _seriesId));
      if (result != null) {
         SeriesSeason[] season = (SeriesSeason[]) Ksoap2ResultParser.createObject(result,
               SeriesSeason[].class);

         if (season != null) {
            return new ArrayList<SeriesSeason>(Arrays.asList(season));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ALL_SEASONS);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALL_SEASONS);
      }
      return null;
   }

   public SeriesSeason getSeason(int _seriesId, int _seasonNumber) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_SEASON, m_wcfService
            .CreateProperty("seriesId", _seriesId), m_wcfService.CreateProperty("seasonNumber",
            _seasonNumber));

      if (result != null) {
         SeriesSeason season = (SeriesSeason) Ksoap2ResultParser.createObject(result,
               SeriesSeason.class);

         if (season != null) {
            return season;
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_SEASON);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_SEASON);
      }
      return null;
   }

   public ArrayList<SeriesEpisode> getAllEpisodes(int _seriesId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_EPISODES, m_wcfService
            .CreateProperty("seriesId", _seriesId));
      if (result != null) {
         SeriesEpisode[] episodes = (SeriesEpisode[]) Ksoap2ResultParser.createObject(result,
               SeriesEpisode[].class);

         if (episodes != null) {
            return new ArrayList<SeriesEpisode>(Arrays.asList(episodes));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ALL_EPISODES);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALL_EPISODES);
      }
      return null;
   }

   public ArrayList<SeriesEpisode> getEpisodes(int _seriesId, int _start, int _end) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_EPISODES, m_wcfService
            .CreateProperty("seriesId", _seriesId), m_wcfService.CreateProperty("startIndex",
            _start), m_wcfService.CreateProperty("endIndex", _end));
      if (result != null) {
         SeriesEpisode[] episodes = (SeriesEpisode[]) Ksoap2ResultParser.createObject(result,
               SeriesEpisode[].class);

         if (episodes != null) {
            return new ArrayList<SeriesEpisode>(Arrays.asList(episodes));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_EPISODES);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_EPISODES);
      }
      return null;
   }

   public int getEpisodesCount() {
      SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(GET_EPISODES_COUNT);

      if (result != null) {
         Integer resultObject = (Integer) Ksoap2ResultParser.getPrimitive(result, Integer.class);
         return resultObject;
      } else {
         Log.d("Soap", "Error calling soap method " + GET_EPISODES_COUNT);
      }
      return -99;
   }

   public ArrayList<SeriesEpisode> getAllEpisodesForSeason(int _seriesId, int _seasonNumber) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_EPISODES_FOR_SEASON,
            m_wcfService.CreateProperty("seriesId", _seriesId), m_wcfService.CreateProperty(
                  "seasonNumber", _seasonNumber));
      if (result != null) {
         SeriesEpisode[] episodes = (SeriesEpisode[]) Ksoap2ResultParser.createObject(result,
               SeriesEpisode[].class);

         if (episodes != null) {
            return new ArrayList<SeriesEpisode>(Arrays.asList(episodes));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ALL_EPISODES_FOR_SEASON);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALL_EPISODES_FOR_SEASON);
      }
      return null;
   }

   public int getEpisodesCountForSeason() {
      SoapPrimitive result = (SoapPrimitive) m_wcfService
            .MakeSoapCall(GET_EPISODES_COUNT_FOR_SEASON);

      if (result != null) {
         Integer resultObject = (Integer) Ksoap2ResultParser.getPrimitive(result, Integer.class);
         return resultObject;
      } else {
         Log.d("Soap", "Error calling soap method " + GET_EPISODES_COUNT_FOR_SEASON);
      }
      return -99;
   }

   public ArrayList<SeriesEpisode> getEpisodesForSeason(int _seriesId, int _seasonId, int _start, int _end) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_EPISODES_FOR_SEASON, m_wcfService
            .CreateProperty("seriesId", _seriesId), m_wcfService.CreateProperty("seasonId",
            _seasonId), m_wcfService.CreateProperty("startIndex", _start), m_wcfService
            .CreateProperty("endIndex", _end));
      
      if (result != null) {
         SeriesEpisode[] episodes = (SeriesEpisode[]) Ksoap2ResultParser.createObject(result,
               SeriesEpisode[].class);

         if (episodes != null) {
            return new ArrayList<SeriesEpisode>(Arrays.asList(episodes));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_EPISODES_FOR_SEASON);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_EPISODES_FOR_SEASON);
      }
      return null;
   }
   
   public EpisodeDetails getFullEpisode(int _episodeId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_FULL_EPISODE, m_wcfService
            .CreateProperty("episodeId", _episodeId));
      if (result != null) {
         EpisodeDetails episode = (EpisodeDetails) Ksoap2ResultParser.createObject(result, EpisodeDetails.class);

         if (episode != null) {
            return episode;
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_FULL_EPISODE);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_FULL_EPISODE);
      }
      return null;
   }
}
