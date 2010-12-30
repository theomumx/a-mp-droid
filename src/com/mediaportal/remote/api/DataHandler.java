package com.mediaportal.remote.api;

import java.net.URL;
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
import com.mediaportal.remote.data.VideoShare;
import com.mediaportal.remote.data.commands.RemoteKey;

public class DataHandler {
   private RemoteClient client;
   private RemoteFunctions functions;
   private static List<DataHandler> clientList = new ArrayList<DataHandler>();
   private static int currentClient = 0;
   private static DataHandler dataHandler;

   private DataHandler(RemoteClient _client) {
      client = _client;
   }

   

   public static boolean setupRemoteHandler(RemoteClient _client) {
      DataHandler remoteHandler = new DataHandler(_client);
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

   public static DataHandler getCurrentRemoteInstance() {
      return getRemoteInstance(currentClient);
   }

   public static DataHandler getRemoteInstance(int _id) {
      for (DataHandler r : clientList) {
         if (r.client.getClientId() == _id)
            return r;
      }
      return null;
   }

   public static void setCurrentRemoteInstance(int _id) {
      currentClient = _id;
   }
   
   public List<VideoShare> getAllVideoShares(){
      return client.getRemoteAccessApi().getVideoShares();
   }

   public List<Movie> getAllMovies() {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllMovies();
   }

   public MovieFull getMovieDetails(int _movieId) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getMovieDetails(_movieId);
   }

   public Bitmap getBitmap(String _id) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getBitmap(_id);
   }

   public ArrayList<Series> getAllSeries() {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllSeries();
   }

   public ArrayList<Series> getSeries(int _start, int _end) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getSeries(_start, _end);
   }

   public SeriesFull getFullSeries(int _seriesId) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getFullSeries(_seriesId);
   }

   public int getSeriesCount() {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getSeriesCount();
   }

   public ArrayList<SeriesSeason> getAllSeasons(int _seriesId) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllSeasons(_seriesId);
   }

   public ArrayList<SeriesEpisode> getAllEpisodes(int _seriesId) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllEpisodes(_seriesId);
   }

   public ArrayList<SeriesEpisode> getAllEpisodesForSeason(int _seriesId, int _seasonNumber) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllEpisodesForSeason(_seriesId, _seasonNumber);
   }

   public ArrayList<MusicAlbum> getAllAlbums() {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllAlbums();
   }

   public SupportedFunctions getSupportedFunctions() {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getSupportedFunctions();
   }

   public List<MusicAlbum> getAlbums(int _start, int _end) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAlbums(_start, _end);
   }
   
   public Bitmap getImage(String _url) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getBitmap(_url);
   }
   

   public Bitmap getImage(String _url, int _maxWidth, int _maxHeight) {
      IRemoteAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getBitmap(_url, _maxWidth, _maxHeight);
   }
   

   public ArrayList<TvChannelGroup> getTvChannelGroups() {
      ITvControlApi tvApi = client.getTvControlApi();
      return tvApi.GetGroups();
   }

   public List<TvChannel> getTvChannelsForGroup(int _groupId) {
      ITvControlApi tvApi = client.getTvControlApi();
      return tvApi.GetChannels(_groupId);
   }

   public List<TvChannel> getTvChannelsForGroup(int _groupId, int _startIndex, int _endIndex) {
      ITvControlApi tvApi = client.getTvControlApi();
      return tvApi.GetChannels(_groupId, _startIndex, _endIndex);
   }

   public int getTvChannelsCount(int _groupId) {
      ITvControlApi tvApi = client.getTvControlApi();
      return tvApi.GetChannelsCount(_groupId);
   }

   public List<TvCardDetails> getTvCards() {
      ITvControlApi tvApi = client.getTvControlApi();
      return tvApi.GetCards();
   }

   public List<TvCard> getTvCardsActive() {
      ITvControlApi tvApi = client.getTvControlApi();
      return tvApi.GetActiveCards();
   }

   public boolean isTvServiceActive() {
      ITvControlApi tvApi = client.getTvControlApi();
      return tvApi.TestConnectionToTVService();
   }

   public void setFunctions(RemoteFunctions functions) {
      this.functions = functions;
   }

   public RemoteFunctions getFunctions() {
      return functions;
   }


   public void addClientControlListener(IClientControlListener _listener){
      client.getClientControlApi().addApiListener(_listener);
   }
   
   public boolean connectClientControl(){
      return client.getClientControlApi().connect();      
   }
   
   public void disconnectClientControl(){
      client.getClientControlApi().disconnect();
   }
   
   public boolean isClientControlConnected(){
      return client.getClientControlApi().isConnected();
   }
   
   public void sendRemoteButton(RemoteKey _button){
      client.getClientControlApi().sendKeyCommand(_button);
   }

   public void sendClientVolume(int _level) {
      client.getClientControlApi().setVolume(_level);      
   }
   
   public int getClientVolume(){
      return client.getClientControlApi().getVolume();
   }
   
   public URL getDownloadUri(String _filePath){
      return client.getRemoteAccessApi().getDownloadUri(_filePath);
   }



}
