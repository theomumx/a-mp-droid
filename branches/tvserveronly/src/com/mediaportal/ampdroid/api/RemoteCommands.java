package com.mediaportal.ampdroid.api;

import com.mediaportal.ampdroid.data.commands.RemoteKey;

public class RemoteCommands {
   public static RemoteKey leftButton = new RemoteKey(0, "Left", "left", "");
   public static RemoteKey rightButton = new RemoteKey(1, "Right", "right", "");
   public static RemoteKey upButton = new RemoteKey(2, "Up", "up", "");
   public static RemoteKey downButton = new RemoteKey(3, "Down", "down", "");
   public static RemoteKey backButton = new RemoteKey(4, "Back", "back", "");
   public static RemoteKey okButton = new RemoteKey(4, "OK", "ok", "");
   public static RemoteKey infoButton = new RemoteKey(5, "Info", "info", "");
   public static RemoteKey pauseButton = new RemoteKey(6, "Pause", "pause", "");
   public static RemoteKey prevButton = new RemoteKey(7, "Prev", "rewind", "");
   public static RemoteKey nextButton = new RemoteKey(8, "Next", "forward", "");
   public static RemoteKey homeButton = new RemoteKey(9, "Home", "home", "");
   public static RemoteKey switchFullscreenButton = new RemoteKey(10, "Switch Fullscreen", "red", "");
   public static RemoteKey subtitlesButton = new RemoteKey(8, "Subtitles", "subtitles", "");
   public static RemoteKey audioTrackButton = new RemoteKey(9, "Audio Tracks", "audiotracks", "");
   public static RemoteKey menuButton = new RemoteKey(9, "Menu", "menu", "");
   public static RemoteKey channelUpButton = new RemoteKey(9, "Channel Up", "chup", "");
   public static RemoteKey channelDownButton = new RemoteKey(9, "Channel Down", "chdown", "");
}
