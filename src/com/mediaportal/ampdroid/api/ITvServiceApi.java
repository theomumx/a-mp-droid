package com.mediaportal.ampdroid.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mediaportal.ampdroid.data.TvCardDetails;
import com.mediaportal.ampdroid.data.TvChannel;
import com.mediaportal.ampdroid.data.TvChannelDetails;
import com.mediaportal.ampdroid.data.TvChannelGroup;
import com.mediaportal.ampdroid.data.TvProgram;
import com.mediaportal.ampdroid.data.TvProgramBase;
import com.mediaportal.ampdroid.data.TvRecording;
import com.mediaportal.ampdroid.data.TvRtspClient;
import com.mediaportal.ampdroid.data.TvSchedule;
import com.mediaportal.ampdroid.data.TvUser;
import com.mediaportal.ampdroid.data.TvVirtualCard;

public interface ITvServiceApi extends IApiInterface {
   boolean TestConnectionToTVService();

   void AddSchedule(int _channelId, String _title, Date _startTime, Date _endTime, int _scheduleType);

   String SwitchTVServerToChannelAndGetStreamingUrl(String _user, int channelId);

   String SwitchTVServerToChannelAndGetTimeshiftFilename(String _user, int channelId);

   boolean CancelCurrentTimeShifting(String _user);

   List<TvChannel> GetChannels(int groupId);

   List<TvRecording> GetRecordings();

   List<TvSchedule> GetSchedules();

   TvChannel GetChannelById(int _channelId);

   TvProgram GetProgramById(int programId);

   List<TvProgramBase> GetProgramsForChannel(int channelId, Date startTime, Date endTime);

   boolean GetProgramIsScheduledOnChannel(int channelId, int programId);

   List<TvProgram> SearchPrograms(String searchTerm);

   ArrayList<TvChannelGroup> GetGroups();

   void cancelScheduleByProgramId(int _programId);
   
   void cancelScheduleByScheduleId(int _scheduleId);

   List<TvCardDetails> GetCards();

   List<TvVirtualCard> GetActiveCards();

   List<TvRtspClient> GetStreamingClients();

   List<TvUser> GetActiveUsers();

   TvProgram GetCurrentProgramOnChannel(int channelId);

   String ReadSettingFromDatabase(String tagName);

   void WriteSettingToDatabase(String tagName, String value);

   List<TvChannel> GetChannels(int _groupId, int _startIndex, int _endIndex);

   int GetChannelsCount(int _groupId);

   List<TvChannelDetails> GetChannelsDetails(int groupId);

   List<TvChannelDetails> GetChannelsDetails(int groupId, int startIndex, int endIndex);

   TvChannelDetails GetChannelDetailedById(int channelId);

   List<TvProgram> GetProgramsDetailsForChannel(int _channelId, Date _startTime, Date _endTime);

   

}
