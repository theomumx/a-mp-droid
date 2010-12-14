package com.mediaportal.remote.api.gmawebservice;

import java.util.ArrayList;
import java.util.Arrays;

import org.ksoap2.serialization.SoapObject;

import com.mediaportal.remote.api.gmawebservice.soap.WcfAccessHandler;
import com.mediaportal.remote.api.soap.Ksoap2ResultParser;
import com.mediaportal.remote.data.MusicAlbum;
import com.mediaportal.remote.data.MusicArtist;
import com.mediaportal.remote.data.MusicTrack;

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

   MusicTrack getMusicTrack(int trackId) {
      return null;
   }

   ArrayList<MusicAlbum> getAllAlbums() {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALL_ALBUMS);
      MusicAlbum[] albums = (MusicAlbum[]) Ksoap2ResultParser.createObject(result, MusicAlbum[].class);
      ;

      if (albums != null) {
         return new ArrayList<MusicAlbum>(Arrays.asList(albums));
      } else {
         return null;
      }
   }

   ArrayList<MusicAlbum> getAlbums(int startIndex, int endIndex) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ALBUMS, m_wcfService.CreateProperty("startIndex",
            0), m_wcfService.CreateProperty("endIndex", 10));

      MusicAlbum[] albums = (MusicAlbum[]) Ksoap2ResultParser.createObject(result, MusicAlbum[].class);

      if (albums != null) {
         return new ArrayList<MusicAlbum>(Arrays.asList(albums));
      } else {
         return null;
      }
   }

   int getAlbumsCount() {
      return 0;
   }

   ArrayList<MusicArtist> getAllArtists() {
      return null;
   }

   ArrayList<MusicArtist> getArtists(int startIndex, int endIndex) {
      return null;
   }

   int getArtistsCount() {
      return 0;
   }

   ArrayList<MusicAlbum> getAlbumsByArtist(String artistName) {
      return null;
   }

   ArrayList<MusicTrack> getSongsOfAlbum(String albumName, String albumArtistName) {
      return null;
   }

   ArrayList<MusicTrack> findMusicTracks(String album, String artist, String title) {
      return null;
   }
}
