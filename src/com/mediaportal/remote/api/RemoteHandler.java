package com.mediaportal.remote.api;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import com.mediaportal.remote.data.Movie;
import com.mediaportal.remote.data.MovieFull;
import com.mediaportal.remote.data.MusicAlbum;
import com.mediaportal.remote.data.RemoteClient;
import com.mediaportal.remote.data.Series;
import com.mediaportal.remote.data.SeriesEpisode;
import com.mediaportal.remote.data.SeriesFull;
import com.mediaportal.remote.data.SeriesSeason;
import com.mediaportal.remote.data.SupportedFunctions;
import com.mediaportal.remote.data.TvCard;
import com.mediaportal.remote.data.TvCardDetails;
import com.mediaportal.remote.data.TvChannel;
import com.mediaportal.remote.data.TvChannelGroup;

public class RemoteHandler {
   private RemoteClient client;
   private RemoteFunctions functions;
   private static List<RemoteHandler> clientList = new ArrayList<RemoteHandler>();
   private static int currentClient = 0;
   private static RemoteHandler remoteHandler;

   private RemoteHandler(RemoteClient _client) {
      client = _client;
   }

   

   public static boolean setupRemoteHandler(RemoteClient _client) {
      RemoteHandler remoteHandler = new RemoteHandler(_client);
      RemoteFunctions functions = new RemoteFunctions();

      ITvControlApi tvApi = _client.getTvControlApi();
      if (tvApi != null) {
         functions.setTvEnabled(true);
         functions.setTvAvailable(tvApi.TestConnectionToTVService());
      } else {
         functions.setTvEnabled(false);
      }
      
      IRemoteAccessApi mediaApi = _client.getRemoteAccessApi();
      if (mediaApi != null) {
         functions.setMediaEnabled(true);
         functions.setMediaAvailable(true);//todo: test function for media access
      } else {
         functions.setMediaEnabled(false);
      }
      
      IRemoteAccessApi remoteApi = _client.getRemoteAccessApi();
      if (remoteApi != null) {
         functions.setRemoteEnabled(true);
         functions.setRemoteAvailable(true);//todo: test function for remote access
      } else {
         functions.setRemoteEnabled(false);
      }

      clientList.add(remoteHandler);
      return true;
   }

   public static RemoteHandler getCurrentRemoteInstance() {
      return getRemoteInstance(currentClient);
   }

   public static RemoteHandler getRemoteInstance(int _id) {
      for (RemoteHandler r : clientList) {
         if (r.client.getClientId() == _id)
            return r;
      }
      return null;
   }

   public static void setCurrentRemoteInstance(int _id) {
      currentClient = _id;
   }

   public List<Movie> getAllMovies() {
      return client.getRemoteAccessApi().getAllMovies();
   }

   public MovieFull getMovieDetails(int _movieId) {
      return client.getRemoteAccessApi().getMovieDetails(_movieId);
   }

   public Bitmap getBitmap(String _id) {
      return client.getRemoteAccessApi().getBitmap(_id);
   }

   public ArrayList<Series> getAllSeries() {
      return client.getRemoteAccessApi().getAllSeries();
   }

   public ArrayList<Series> getSeries(int _start, int _end) {
      return client.getRemoteAccessApi().getSeries(_start, _end);
   }

   public SeriesFull getFullSeries(int _seriesId) {
      return client.getRemoteAccessApi().getFullSeries(_seriesId);
   }

   public int getSeriesCount() {
      return client.getRemoteAccessApi().getSeriesCount();
   }

   public ArrayList<SeriesSeason> getAllSeasons(int _seriesId) {
      return client.getRemoteAccessApi().getAllSeasons(_seriesId);
   }

   public ArrayList<SeriesEpisode> getAllEpisodes(int _seriesId) {

      return client.getRemoteAccessApi().getAllEpisodes(_seriesId);
   }

   public ArrayList<SeriesEpisode> getAllEpisodesForSeason(int _seriesId, int _seasonNumber) {
      return client.getRemoteAccessApi().getAllEpisodesForSeason(_seriesId, _seasonNumber);
   }

   public ArrayList<MusicAlbum> getAllAlbums() {
      return client.getRemoteAccessApi().getAllAlbums();
   }

   public SupportedFunctions getSupportedFunctions() {
      return client.getRemoteAccessApi().getSupportedFunctions();
   }

   public List<MusicAlbum> getAlbums(int _start, int _end) {
      return client.getRemoteAccessApi().getAlbums(_start, _end);
   }

   public ArrayList<TvChannelGroup> getTvChannelGroups() {
      return client.getTvControlApi().GetGroups();
   }

   public List<TvChannel> getTvChannelsForGroup(int _groupId) {
      return client.getTvControlApi().GetChannels(_groupId);
   }

   public List<TvChannel> getTvChannelsForGroup(int _groupId, int _startIndex, int _endIndex) {
      return client.getTvControlApi().GetChannels(_groupId, _startIndex, _endIndex);
   }

   public int getTvChannelsCount(int _groupId) {
      return client.getTvControlApi().GetChannelsCount(_groupId);
   }

   public List<TvCardDetails> getTvCards() {
      return client.getTvControlApi().GetCards();
   }

   public List<TvCard> getTvCardsActive() {
      return client.getTvControlApi().GetActiveCards();
   }

   public boolean isTvServiceActive() {
      return client.getTvControlApi().TestConnectionToTVService();
   }

   public void setFunctions(RemoteFunctions functions) {
      this.functions = functions;
   }

   public RemoteFunctions getFunctions() {
      return functions;
   }

}
