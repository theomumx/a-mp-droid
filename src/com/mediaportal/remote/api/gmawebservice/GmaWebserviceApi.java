package com.mediaportal.remote.api.gmawebservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import org.ksoap2.serialization.SoapObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.mediaportal.remote.api.IMediaAccessApi;
import com.mediaportal.remote.api.gmawebservice.soap.WcfAccessHandler;
import com.mediaportal.remote.api.soap.Ksoap2ResultParser;
import com.mediaportal.remote.data.EpisodeDetails;
import com.mediaportal.remote.data.Movie;
import com.mediaportal.remote.data.MovieFull;
import com.mediaportal.remote.data.MusicAlbum;
import com.mediaportal.remote.data.Series;
import com.mediaportal.remote.data.SeriesEpisode;
import com.mediaportal.remote.data.SeriesFull;
import com.mediaportal.remote.data.SeriesSeason;
import com.mediaportal.remote.data.SupportedFunctions;
import com.mediaportal.remote.data.VideoShare;

public class GmaWebserviceApi implements IMediaAccessApi {
   private GmaWebserviceMovieApi m_moviesAPI;
   private GmaWebserviceSeriesApi m_seriesAPI;
   private GmaWebserviceMusicApi m_musicAPI;

   private String m_server;
   private int m_port;
   private WcfAccessHandler m_wcfService;

   private final String WCF_NAMESPACE = "http://tempuri.org/";
   private final String WCF_PREFIX = "http://";
   private final String WCF_SUFFIX = "/basic";
   private final String WCF_METHOD_PREFIX = "http://tempuri.org/IMediaAccessService/";

   private final String GET_SUPPORTED_FUNCTIONS = "MP_GetSupportedFunctions";

   private final String JSON_PREFIX = "http://";
   private final String JSON_SUFFIX = "/json";

   // Method constants

   public GmaWebserviceApi(String _server, int _port) {
      m_server = _server;
      m_port = _port;

      m_wcfService = new WcfAccessHandler(WCF_PREFIX + m_server + ":" + m_port + WCF_SUFFIX,
            WCF_NAMESPACE, WCF_METHOD_PREFIX);
      m_moviesAPI = new GmaWebserviceMovieApi(m_wcfService);
      m_seriesAPI = new GmaWebserviceSeriesApi(m_wcfService);
      m_musicAPI = new GmaWebserviceMusicApi(m_wcfService);
   }
   
   @Override
   public String getAddress() {
      return m_server;
   }

   public SupportedFunctions getSupportedFunctions() {
      String methodName = GET_SUPPORTED_FUNCTIONS;
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(methodName);

      if (result != null) {
         SupportedFunctions groups = (SupportedFunctions) Ksoap2ResultParser.createObject(result,
               SupportedFunctions.class);

         if (groups != null) {
            return groups;
         } else {
            Log.d("Soap", "Error parsing result from soap method " + methodName);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + methodName);
      }
      return null;
   }
   
   @Override
   public ArrayList<VideoShare> getVideoShares(){
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall("MP_GetVideoShares");

      if (result != null) {
         VideoShare[] albums = (VideoShare[]) Ksoap2ResultParser.createObject(result,
               VideoShare[].class);

         if (albums != null) {
            return new ArrayList<VideoShare>(Arrays.asList(albums));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + "MP_GetVideoShares");
         }
      } else {
         Log.d("Soap", "Error calling soap method " + "MP_GetVideoShares");
      }
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
   public ArrayList<SeriesEpisode> getAllEpisodesForSeason(int _seriesId, int _seasonNumber) {
      return m_seriesAPI.getAllEpisodesForSeason(_seriesId, _seasonNumber);
   }
   
   @Override
   public EpisodeDetails getEpisode(int _episodeId) {
      return m_seriesAPI.getFullEpisode(_episodeId);
   }

   @Override
   public Bitmap getBitmap(String _url) {
      URL myFileUrl = null;
      Bitmap bmImg = null;
      try {
         myFileUrl = new URL(JSON_PREFIX + m_server + ":" + m_port + JSON_SUFFIX
               + "/FS_GetImage/?path=" + URLEncoder.encode(_url, "UTF-8"));
         HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
         conn.setDoInput(true);
         conn.connect();
         InputStream is = conn.getInputStream();

         bmImg = BitmapFactory.decodeStream(is);
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return bmImg;
   }
   
   @Override
   public Bitmap getBitmap(String _url, int _maxWidth, int _maxHeight) {
      URL myFileUrl = null;
      Bitmap bmImg = null;
      try {
         myFileUrl = new URL(JSON_PREFIX + m_server + ":" + m_port + JSON_SUFFIX
               + "/FS_GetImageResized/?path=" + URLEncoder.encode(_url, "UTF-8") 
               + "&maxWidth=" + _maxWidth + "&maxHeight=" + _maxHeight);
         HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
         conn.setDoInput(true);
         conn.connect();
         InputStream is = conn.getInputStream();

         bmImg = BitmapFactory.decodeStream(is);
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return bmImg;
   }
   
   @Override
   public URL getDownloadUri(String _filePath){
      URL fileUrl = null;
      try {
         fileUrl = new URL(JSON_PREFIX + m_server + ":" + m_port + JSON_SUFFIX
                  + "/FS_GetMediaItem/?path=" + URLEncoder.encode(_filePath, "UTF-8"));
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      return fileUrl;
      
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
