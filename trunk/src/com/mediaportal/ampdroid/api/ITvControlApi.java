package com.mediaportal.remote.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mediaportal.remote.data.TvCard;
import com.mediaportal.remote.data.TvCardDetails;
import com.mediaportal.remote.data.TvChannel;
import com.mediaportal.remote.data.TvChannelDetails;
import com.mediaportal.remote.data.TvChannelGroup;
import com.mediaportal.remote.data.TvProgram;
import com.mediaportal.remote.data.TvRecording;
import com.mediaportal.remote.data.TvRtspClient;
import com.mediaportal.remote.data.TvSchedule;
import com.mediaportal.remote.data.TvUser;

public interface ITvControlApi extends IApiInterface {
	boolean TestConnectionToTVService();

	void AddSchedule(int channelId, String title, Date startTime, Date endTime,
			int scheduleType);

	String SwitchTVServerToChannelAndGetStreamingUrl(int channelId);

	String SwitchTVServerToChannelAndGetTimeshiftFilename(int channelId);

	void CancelCurrentTimeShifting();

	List<TvChannel> GetChannels(int groupId);

	List<TvRecording> GetRecordings();

	List<TvSchedule> GetSchedules();

	TvChannelDetails GetChannelById(int _channelId);

	TvProgram GetProgramById(int programId);

	List<TvProgram> GetProgramsForChannel(int channelId, Date startTime,
			Date endTime);

	boolean GetProgramIsScheduledOnChannel(int channelId, int programId);

	List<TvProgram> SearchPrograms(String searchTerm);

	ArrayList<TvChannelGroup> GetGroups();

	void CancelSchedule(int programId);

	List<TvCardDetails> GetCards();

	List<TvCard> GetActiveCards();

	List<TvRtspClient> GetStreamingClients();

	List<TvUser> GetActiveUsers();

	TvProgram GetCurrentProgramOnChannel(int channelId);

	String ReadSettingFromDatabase(String tagName);

	void WriteSettingToDatabase(String tagName, String value);

   List<TvChannel> GetChannels(int _groupId, int _startIndex, int _endIndex);

   int GetChannelsCount(int _groupId);

   List<TvChannelDetails> GetChannelsDetails(int groupId);

   List<TvChannelDetails> GetChannelsDetails(int groupId, int startIndex, int endIndex);

}
