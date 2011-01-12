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
   public static RemoteKey pauseButton = new RemoteKey(5, "Pause", "pause", "");
   public static RemoteKey prevButton = new RemoteKey(5, "Prev", "rewind", "");
   public static RemoteKey nextButton = new RemoteKey(5, "Next", "forward", "");
}
