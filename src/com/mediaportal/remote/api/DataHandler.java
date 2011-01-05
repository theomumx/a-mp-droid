package com.mediaportal.remote.api;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import com.mediaportal.remote.data.EpisodeDetails;
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
   private static List<RemoteClient> clientList = new ArrayList<RemoteClient>();
   private static int currentClient = 0;
   private static DataHandler dataHandler;
   private IMediaAccessDatabase mediaDatabase;

   private DataHandler(RemoteClient _client) {
      client = _client;
   }

   public static boolean setupRemoteHandler(RemoteClient _client, boolean _checkConnection) {
      dataHandler = new DataHandler(_client);
      setFunctions(_client, false);

      return true;
   }
   
   private static void setFunctions(RemoteClient _client, boolean _checkConnection){
      dataHandler.functions = new RemoteFunctions();

      ITvControlApi tvApi = _client.getTvControlApi();
      if (tvApi != null) {
         dataHandler.functions.setTvEnabled(true);
         if (_checkConnection) {
            dataHandler.functions.setTvAvailable(tvApi.TestConnectionToTVService());
         }
      } else {
         dataHandler.functions.setTvEnabled(false);
      }

      IMediaAccessApi mediaApi = _client.getRemoteAccessApi();
      if (mediaApi != null) {
         dataHandler.functions.setMediaEnabled(true);
         if (_checkConnection) {
            // todo: test function for media access
            dataHandler.functions.setMediaAvailable(true);
         }
      } else {
         dataHandler.functions.setMediaEnabled(false);
      }

      IMediaAccessApi remoteApi = _client.getRemoteAccessApi();
      if (remoteApi != null) {
         dataHandler.functions.setRemoteEnabled(true);
         if (_checkConnection) {
            // todo: test function for remote access
            dataHandler.functions.setRemoteAvailable(true);
         }
      } else {
         dataHandler.functions.setRemoteEnabled(false);
      }
   }

   public static DataHandler getCurrentRemoteInstance() {
      return dataHandler;
   }

   public static void clearRemotClients() {
      clientList.clear();
   }

   public static void addRemoteClient(RemoteClient _client) {
      clientList.add(_client);
   }

   public static List<RemoteClient> getRemoteClients() {
      return clientList;
   }

   public static void updateRemoteClient(RemoteClient mClient) {
      for (int i = 0; i < clientList.size(); i++) {
         if (clientList.get(i).getClientId() == mClient.getClientId()) {
            clientList.set(i, mClient);
         }
      }
   }

   public static void setCurrentRemoteInstance(int _id) {
      for (RemoteClient c : clientList) {
         dataHandler.client = c;
         setFunctions(dataHandler.client, false);
      }
   }

   public List<VideoShare> getAllVideoShares() {
      return client.getRemoteAccessApi().getVideoShares();
   }

   public List<Movie> getAllMovies() {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllMovies();
   }

   public MovieFull getMovieDetails(int _movieId) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getMovieDetails(_movieId);
   }

   public Bitmap getBitmap(String _id) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getBitmap(_id);
   }

   public ArrayList<Series> getAllSeries() {
      // mediaDatabase.getAllSeries();
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllSeries();
   }

   public ArrayList<Series> getSeries(int _start, int _end) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getSeries(_start, _end);
   }

   public SeriesFull getFullSeries(int _seriesId) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getFullSeries(_seriesId);
   }

   public int getSeriesCount() {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getSeriesCount();
   }

   public ArrayList<SeriesSeason> getAllSeasons(int _seriesId) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllSeasons(_seriesId);
   }

   public ArrayList<SeriesEpisode> getAllEpisodes(int _seriesId) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllEpisodes(_seriesId);
   }

   public ArrayList<SeriesEpisode> getAllEpisodesForSeason(int _seriesId, int _seasonNumber) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllEpisodesForSeason(_seriesId, _seasonNumber);
   }

   public EpisodeDetails getEpisode(int _id) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getEpisode(_id);
   }

   public ArrayList<MusicAlbum> getAllAlbums() {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAllAlbums();
   }

   public SupportedFunctions getSupportedFunctions() {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getSupportedFunctions();
   }

   public List<MusicAlbum> getAlbums(int _start, int _end) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getAlbums(_start, _end);
   }

   public Bitmap getImage(String _url) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
      return remoteAccess.getBitmap(_url);
   }

   public Bitmap getImage(String _url, int _maxWidth, int _maxHeight) {
      IMediaAccessApi remoteAccess = client.getRemoteAccessApi();
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

   public void addClientControlListener(IClientControlListener _listener) {
      client.getClientControlApi().addApiListener(_listener);
   }

   public boolean connectClientControl() {
      return client.getClientControlApi().connect();
   }

   public void disconnectClientControl() {
      client.getClientControlApi().disconnect();
   }

   public boolean isClientControlConnected() {
      return client.getClientControlApi().isConnected();
   }

   public void sendRemoteButton(RemoteKey _button) {
      client.getClientControlApi().sendKeyCommand(_button);
   }

   public void sendClientVolume(int _level) {
      client.getClientControlApi().setVolume(_level);
   }

   public int getClientVolume() {
      return client.getClientControlApi().getVolume();
   }

   public URL getDownloadUri(String _filePath) {
      return client.getRemoteAccessApi().getDownloadUri(_filePath);
   }

}
