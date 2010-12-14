package com.mediaportal.remote.data;

import java.util.Date;

public class TvRecording extends TvProgramBase {
   String FileName;
   int IdRecording;
   int Idschedule;
   int IdServer;
   boolean IsManual;
   boolean IsRecording;
   int KeepUntil;
   Date KeepUntilDate;
   boolean ShouldBeDeleted;
   int StopTime;
   int TimesWatched;

}
