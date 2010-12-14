package com.mediaportal.remote.api.gmawebservice;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mediaportal.remote.api.IRemoteAccessApi;
import com.mediaportal.remote.api.gmawebservice.soap.WcfAccessHandler;
import com.mediaportal.remote.api.soap.Ksoap2ResultParser;
import com.mediaportal.remote.data.Movie;
import com.mediaportal.remote.data.MovieFull;
import com.mediaportal.remote.data.MusicAlbum;
import com.mediaportal.remote.data.Series;
import com.mediaportal.remote.data.SeriesEpisode;
import com.mediaportal.remote.data.SeriesFull;
import com.mediaportal.remote.data.SeriesSeason;
import com.mediaportal.remote.data.SupportedFunctions;

public class GmaWebserviceApi implements IRemoteAccessApi {
   private GmaWebserviceMovieApi m_moviesAPI;
   private GmaWebserviceSeriesApi m_seriesAPI;
   private GmaWebserviceMusicApi m_musicAPI;

   private String m_server;
   private int m_port;
   private WcfAccessHandler m_wcfService;

   private final String WCF_NAMESPACE = "http://tempuri.org/";
   private final String WCF_PREFIX = "http://";
   private final String WCF_SUFFIX = "/basic";
   private final String WCF_METHOD_PREFIX = "IMediaAccessService";

   // Method constants

   public GmaWebserviceApi(String _server, int _port) {
      m_server = _server;
      m_port = _port;

      m_wcfService = new WcfAccessHandler(WCF_PREFIX + m_server + ":" + m_port
            + WCF_SUFFIX, WCF_NAMESPACE, WCF_METHOD_PREFIX);
      m_moviesAPI = new GmaWebserviceMovieApi(m_wcfService);
      m_seriesAPI = new GmaWebserviceSeriesApi(m_wcfService);
      m_musicAPI = new GmaWebserviceMusicApi(m_wcfService);
   }

   public SupportedFunctions getSupportedFunctions() {
      SoapObject result = (SoapObject) m_wcfService
            .MakeSoapCall("MP_GetSupportedFunctions");

      SupportedFunctions functions = (SupportedFunctions) Ksoap2ResultParser
            .createObject((SoapObject) result.getProperty(0),
                  SupportedFunctions.class);
      ;

      return null;
   }

   @Override
   public ArrayList<Movie> getAllMovies() {
      return m_moviesAPI.getAllMovies();
   }

   @Override
   public int getMovieCount() {
      return m_moviesAPI.getMovieCount();
   }

   @Override
   public MovieFull getMovieDetails(int _movieId) {
      return m_moviesAPI.getMovieDetails(_movieId);
   }

   @Override
   public ArrayList<Movie> getMovies(int _start, int _end) {
      return m_moviesAPI.getMovies(_start, _end);
   }

   @Override
   public ArrayList<Series> getAllSeries() {
      return m_seriesAPI.getAllSeries();
   }

   @Override
   public ArrayList<Series> getSeries(int _start, int _end) {
      return m_seriesAPI.getSeries(_start, _end);
   }

   @Override
   public SeriesFull getFullSeries(int _seriesId) {
      return m_seriesAPI.getFullSeries(_seriesId);
   }

   @Override
   public int getSeriesCount() {
      return m_seriesAPI.getSeriesCount();
   }

   @Override
   public ArrayList<SeriesSeason> getAllSeasons(int _seriesId) {
      return m_seriesAPI.getAllSeasons(_seriesId);
   }

   @Override
   public ArrayList<SeriesEpisode> getAllEpisodes(int _seriesId) {
      return m_seriesAPI.getAllEpisodes(_seriesId);
   }

   @Override
   public ArrayList<SeriesEpisode> getAllEpisodesForSeason(int _seriesId,
         int _seasonNumber) {
      return m_seriesAPI.getAllEpisodesForSeason(_seriesId, _seasonNumber);
   }

   @Override
   public Bitmap getBitmap(String _id) {
      // from web
      try {
         URL myFileUrl = new URL(
               "http://bagga-laptop:4321/json/FS_GetImage/?path="
                     + URLEncoder.encode(_id, "UTF-8"));
         HttpURLConnection conn = (HttpURLConnection) myFileUrl
               .openConnection();
         conn.setDoInput(true);
         conn.connect();
         int length = conn.getContentLength();
         InputStream is = conn.getInputStream();

         Bitmap bmImg = BitmapFactory.decodeStream(is);

         return bmImg;
      } catch (Exception ex) {
         ex.printStackTrace();
         return null;
      }
   }

   @Override
   public ArrayList<MusicAlbum> getAllAlbums() {
      return m_musicAPI.getAllAlbums();
   }

   @Override
   public ArrayList<MusicAlbum> getAlbums(int _start, int _end) {
      return m_musicAPI.getAlbums(_start, _end);
   }

}
