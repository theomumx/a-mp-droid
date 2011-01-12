package com.mediaportal.ampdroid.api.gmawebservice;

import java.util.ArrayList;
import java.util.Arrays;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

import com.mediaportal.ampdroid.api.gmawebservice.soap.WcfAccessHandler;
import com.mediaportal.ampdroid.api.soap.Ksoap2ResultParser;
import com.mediaportal.ampdroid.data.MusicAlbum;
import com.mediaportal.ampdroid.data.MusicArtist;
import com.mediaportal.ampdroid.data.MusicTrack;

public class GmaWebserviceMusicApi {
   private WcfAccessHandler m_wcfService;

   private static final String GET_MUSIC_TRACK = "MP_GetMusicTrack";
   private static final String GET_ALL_ALBUMS = "MP_GetAllAlbums";
   private static final String GET_ALBUMS = "MP_GetAlbums";
   private static final String GET_ALBUMS_COUNT = "MP_GetAlbumsCount";
   private static final String GET_ALL_ARTISTS = "MP_GetAllArtists";
   private static final String GET_ARTISTS = "MP_GetArtists";
   private static final String GET_ARTISTS_COUNT = "MP_GetArtistsCount";
   private static final String GET_ALBUMS_BY_ARTIST = "MP_GetAlbumsByArtist";
   private static final String GET_SONGS_OF_ALBUM = "MP_GetSongsOfAlbum";
   private static final String FIND_MUSIC_TRACKS = "MP_FindMusicTracks";

   public GmaWebserviceMusicApi(WcfAccessHandler _wcfService) {
      m_wcfService = _wcfService;
   }

   MusicTrack getMusicTrack(int _trackId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_MUSIC_TRACK, m_wcfService
            .CreateProperty("trackId", _trackId));
      if (result != null) {
         MusicTrack song = (MusicTrack) Ksoap2ResultParser.createObject(result, MusicTrack.class);

         if (song != null) {
            return song;
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_MUSIC_TRACK);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_MUSIC_TRACK);
      }
      return null;
   }

   ArrayList<MusicAlbum> getAllAlbums() {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_ALBUMS);

      if (result != null) {
         MusicAlbum[] albums = (MusicAlbum[]) Ksoap2ResultParser.createObject(result,
               MusicAlbum[].class);

         if (albums != null) {
            return new ArrayList<MusicAlbum>(Arrays.asList(albums));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ALL_ALBUMS);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALL_ALBUMS);
      }
      return null;
   }

   ArrayList<MusicAlbum> getAlbums(int _startIndex, int _endIndex) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALBUMS, m_wcfService
            .CreateProperty("startIndex", _startIndex), m_wcfService.CreateProperty("endIndex",
            _endIndex));
      if (result != null) {
         MusicAlbum[] albums = (MusicAlbum[]) Ksoap2ResultParser.createObject(result,
               MusicAlbum[].class);

         if (albums != null) {
            return new ArrayList<MusicAlbum>(Arrays.asList(albums));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ALBUMS);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALBUMS);
      }
      return null;
   }

   int getAlbumsCount() {
      SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(GET_ALBUMS_COUNT);

      if (result != null) {
         Integer resultObject = (Integer) Ksoap2ResultParser.getPrimitive(result, Integer.class);
         return resultObject;
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALBUMS_COUNT);
      }
      return -99;
   }

   ArrayList<MusicArtist> getAllArtists() {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_ARTISTS);
      if (result != null) {
         MusicArtist[] artists = (MusicArtist[]) Ksoap2ResultParser.createObject(result,
               MusicArtist[].class);

         if (artists != null) {
            return new ArrayList<MusicArtist>(Arrays.asList(artists));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ALL_ARTISTS);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALL_ARTISTS);
      }
      return null;
   }

   ArrayList<MusicArtist> getArtists(int _startIndex, int _endIndex) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ARTISTS, m_wcfService
            .CreateProperty("startIndex", _startIndex), m_wcfService.CreateProperty("endIndex",
            _endIndex));
      if (result != null) {
         MusicArtist[] artists = (MusicArtist[]) Ksoap2ResultParser.createObject(result,
               MusicArtist[].class);

         if (artists != null) {
            return new ArrayList<MusicArtist>(Arrays.asList(artists));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ARTISTS);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ARTISTS);
      }
      return null;
   }

   int getArtistsCount() {
      SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(GET_ARTISTS_COUNT);

      if (result != null) {
         Integer resultObject = (Integer) Ksoap2ResultParser.getPrimitive(result, Integer.class);
         return resultObject;
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ARTISTS_COUNT);
      }
      return -99;
   }

   ArrayList<MusicAlbum> getAlbumsByArtist(String _artistName) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALBUMS_BY_ARTIST, m_wcfService
            .CreateProperty("artistName", _artistName));
      if (result != null) {
         MusicAlbum[] albums = (MusicAlbum[]) Ksoap2ResultParser.createObject(result,
               MusicAlbum[].class);

         if (albums != null) {
            return new ArrayList<MusicAlbum>(Arrays.asList(albums));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_ALBUMS_BY_ARTIST);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_ALBUMS_BY_ARTIST);
      }
      return null;
   }

   ArrayList<MusicTrack> getSongsOfAlbum(String _albumName, String _albumArtistName) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_SONGS_OF_ALBUM, m_wcfService
            .CreateProperty("albumName", _albumName), m_wcfService.CreateProperty(
            "albumArtistName", _albumArtistName));
      if (result != null) {
         MusicTrack[] songs = (MusicTrack[]) Ksoap2ResultParser.createObject(result,
               MusicTrack[].class);

         if (songs != null) {
            return new ArrayList<MusicTrack>(Arrays.asList(songs));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_SONGS_OF_ALBUM);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_SONGS_OF_ALBUM);
      }
      return null;
   }

   ArrayList<MusicTrack> findMusicTracks(String _album, String _artist, String _title) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(FIND_MUSIC_TRACKS, m_wcfService
            .CreateProperty("album", _album), m_wcfService.CreateProperty("artist", _artist),
            m_wcfService.CreateProperty("title", _title));
      if (result != null) {
         MusicTrack[] songs = (MusicTrack[]) Ksoap2ResultParser.createObject(result,
               MusicTrack[].class);

         if (songs != null) {
            return new ArrayList<MusicTrack>(Arrays.asList(songs));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + FIND_MUSIC_TRACKS);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + FIND_MUSIC_TRACKS);
      }
      return null;
   }
}
