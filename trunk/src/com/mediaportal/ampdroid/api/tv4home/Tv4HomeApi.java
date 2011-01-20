package com.mediaportal.ampdroid.api.tv4home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.util.Log;

import com.mediaportal.ampdroid.api.ITvServiceApi;
import com.mediaportal.ampdroid.api.gmawebservice.soap.WcfAccessHandler;
import com.mediaportal.ampdroid.api.soap.Ksoap2ResultParser;
import com.mediaportal.ampdroid.data.TvCardDetails;
import com.mediaportal.ampdroid.data.TvChannel;
import com.mediaportal.ampdroid.data.TvChannelDetails;
import com.mediaportal.ampdroid.data.TvChannelGroup;
import com.mediaportal.ampdroid.data.TvProgram;
import com.mediaportal.ampdroid.data.TvRecording;
import com.mediaportal.ampdroid.data.TvRtspClient;
import com.mediaportal.ampdroid.data.TvSchedule;
import com.mediaportal.ampdroid.data.TvUser;
import com.mediaportal.ampdroid.data.TvVirtualCard;

/**
 * Wrapper for the functionality offered by the Tv4Home WCF webservice
 * 
 * See http://tv4home.codeplex.com/ for more details on the webservice
 * 
 * @author DieBagger
 */
public class Tv4HomeApi implements ITvServiceApi {
   private final String TEST_SERVICE = "TestConnectionToTVService";
   private final String GET_GROUPS = "GetGroups";
   private final String GET_CHANNELS = "GetChannelsBasic";
   private final String GET_CHANNELS_INDEX = "GetChannelsBasicByRange";
   private final String GET_CHANNELS_DETAILS = "GetChannelsDetailed";
   private final String GET_CHANNELS_DETAILS_INDEX = "GetChannelsDetailedByRange";   
   private final String GET_CHANNELS_COUNT = "GetChannelCount";
   private final String GET_CARDS = "GetCards";
   private final String GET_ACTIVE_CARDS = "GetActiveCards";
   private final String ADD_SCHEDULE = "AddSchedule";
   private final String CANCEL_SCHEDULE = "CancelSchedule";
   private final String DELETE_SCHEDULE = "DeleteSchedule";
   private final String CANCEL_CURRENT_TIMESHIFT = "CancelCurrentTimeShifting";
   private final String GET_CHANNEL_BY_ID = "GetChannelBasicById";
   private final String GET_CHANNEL_DETAILED_BY_ID = "GetChannelDetailedById";
   private final String GET_ACTIVE_USERS = "GetActiveUsers";
   private final String GET_CURRENT_PROGRAM_CHANNEL = "GetCurrentProgramOnChannel";
   private final String GET_PROGRAM = "GetProgramById";
   private final String GET_PROGRAM_IS_SCHEDULED_CHANNEL = "GetProgramIsScheduledOnChannel";
   private final String GET_PROGRAMS_FOR_CHANNEL = "GetProgramsForChannel";
   private final String GET_RECORDINGS = "GetRecordings";
   private final String GET_SCHEDULES = "GetSchedules";
   private final String GET_STREAMING_CLIENTS = "GetStreamingClients";
   private final String SEARCH_PROGRAMS = "SearchPrograms";
   private final String DATABASE_READ = "ReadSettingFromDatabase";
   private final String DATABASE_WRITE = "WriteSettingToDatabase";
   private final String SWITCH_CHANNEL_GET_URL = "SwitchTVServerToChannelAndGetStreamingUrl";
   private final String SWITCH_CHANNEL_GET_FILE = "SwitchTVServerToChannelAndGetTimeshiftFilename";

   private String m_server;
   private int m_port;
   private WcfAccessHandler m_wcfService;

   private final String WCF_NAMESPACE = "http://tv4home.codeplex.com";
   private final String WCF_PREFIX = "http://";
   private final String WCF_SUFFIX = "/TV4Home.Server.CoreService/TVEInteractionService";
   private final String WCF_METHOD_PREFIX = "http://tv4home.codeplex.com/ITVEInteraction/";

   // Method constants

   public Tv4HomeApi(String _server, int _port) {
      m_server = _server;
      m_port = _port;

      m_wcfService = new WcfAccessHandler(WCF_PREFIX + m_server + ":" + m_port + WCF_SUFFIX,
            WCF_NAMESPACE, WCF_METHOD_PREFIX);
   }
   
   @Override
   public String getAddress() {
      return m_server;
   }
   
   @Override
   public String getServer() {
      return m_server;
   }

   @Override
   public int getPort() {
      return m_port;
   }

   @Override
   public void AddSchedule(int _channelId, String _title, Date _startTime, Date _endTime,
         int _scheduleType) {
      //m_wcfService = new WcfAccessHandler(WCF_PREFIX + "bagga-laptop" + ":" + m_port + WCF_SUFFIX,
      //      WCF_NAMESPACE, WCF_METHOD_PREFIX);
      m_wcfService.MakeSoapCall(ADD_SCHEDULE, m_wcfService.CreateProperty("channelId", _channelId),
            m_wcfService.CreateProperty("title", _title), m_wcfService.CreateProperty("startTime",
                  _startTime), m_wcfService.CreateProperty("endTime", _endTime), m_wcfService
                  .CreateProperty("scheduleType", _scheduleType));
   }

   @Override
   public boolean CancelCurrentTimeShifting(String _user) {
      SoapPrimitive result = (SoapPrimitive)m_wcfService.MakeSoapCall(CANCEL_CURRENT_TIMESHIFT,
            m_wcfService.CreateProperty("userName", _user));
      
      Boolean resultObject = (Boolean) Ksoap2ResultParser.getPrimitive(result, Boolean.class);

      return resultObject;
   }

   @Override
   public void cancelScheduleByProgramId(int _programId) {
      m_wcfService.MakeSoapCall(CANCEL_SCHEDULE, m_wcfService.CreateProperty("programId",
            _programId));
   }
   
   public void cancelScheduleByScheduleId(int _scheduleId) {
      m_wcfService.MakeSoapCall(DELETE_SCHEDULE, m_wcfService.CreateProperty("scheduleId",
            _scheduleId));
   }

   @Override
   public List<TvVirtualCard> GetActiveCards() {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ACTIVE_CARDS);

      TvVirtualCard[] channels = (TvVirtualCard[]) Ksoap2ResultParser.createObject(result, TvVirtualCard[].class);

      if (channels != null) {
         return new ArrayList<TvVirtualCard>(Arrays.asList(channels));
      } else {
         return null;
      }
   }

   @Override
   public List<TvUser> GetActiveUsers() {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_ACTIVE_USERS);

      TvUser[] channels = (TvUser[]) Ksoap2ResultParser.createObject(result, TvUser[].class);

      if (channels != null) {
         return new ArrayList<TvUser>(Arrays.asList(channels));
      } else {
         return null;
      }
   }

   @Override
   public List<TvCardDetails> GetCards() {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_CARDS);

      TvCardDetails[] channels = (TvCardDetails[]) Ksoap2ResultParser.createObject(result,
            TvCardDetails[].class);

      if (channels != null) {
         return new ArrayList<TvCardDetails>(Arrays.asList(channels));
      } else {
         return null;
      }
   }

   @Override
   public TvChannel GetChannelById(int _channelId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_CHANNEL_BY_ID, m_wcfService
            .CreateProperty("channelId", _channelId));

      TvChannel channel = (TvChannel) Ksoap2ResultParser.createObject(result, TvChannelDetails.class);

      return channel;
   }
   
   @Override
   public TvChannelDetails GetChannelDetailedById(int _channelId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_CHANNEL_DETAILED_BY_ID, m_wcfService
            .CreateProperty("channelId", _channelId));

      TvChannelDetails channel = (TvChannelDetails) Ksoap2ResultParser.createObject(result, TvChannelDetails.class);

      return channel;
   }

   @Override
   public List<TvChannel> GetChannels(int _groupId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_CHANNELS, m_wcfService
            .CreateProperty("groupId", _groupId));

      TvChannel[] channels = (TvChannel[]) Ksoap2ResultParser.createObject(result,
            TvChannel[].class);

      if (channels != null) {
         return new ArrayList<TvChannel>(Arrays.asList(channels));
      } else {
         return null;
      }
   }
   
   @Override
   public List<TvChannelDetails> GetChannelsDetails(int _groupId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_CHANNELS_DETAILS, m_wcfService
            .CreateProperty("groupId", _groupId));

      TvChannelDetails[] channels = (TvChannelDetails[]) Ksoap2ResultParser.createObject(result,
            TvChannelDetails[].class);

      if (channels != null) {
         return new ArrayList<TvChannelDetails>(Arrays.asList(channels));
      } else {
         return null;
      }
   }

   @Override
   public List<TvChannel> GetChannels(int _groupId, int _startIndex, int _endIndex) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_CHANNELS_INDEX, m_wcfService
            .CreateProperty("groupId", _groupId), m_wcfService.CreateProperty("startIndex",
            _startIndex), m_wcfService.CreateProperty("endIndex", _endIndex));

      TvChannel[] channels = (TvChannel[]) Ksoap2ResultParser.createObject(result,
            TvChannel[].class);

      if (channels != null) {
         return new ArrayList<TvChannel>(Arrays.asList(channels));
      } else {
         return null;
      }
   }
   
   @Override
   public List<TvChannelDetails> GetChannelsDetails(int _groupId, int _startIndex, int _endIndex) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_CHANNELS_DETAILS_INDEX, m_wcfService
            .CreateProperty("groupId", _groupId), m_wcfService.CreateProperty("startIndex",
            _startIndex), m_wcfService.CreateProperty("endIndex", _endIndex));

      TvChannelDetails[] channels = (TvChannelDetails[]) Ksoap2ResultParser.createObject(result,
            TvChannelDetails[].class);

      if (channels != null) {
         return new ArrayList<TvChannelDetails>(Arrays.asList(channels));
      } else {
         return null;
      }
   }

   @Override
   public int GetChannelsCount(int _groupId) {
      SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(GET_CHANNELS_COUNT,
            m_wcfService.CreateProperty("groupId", _groupId));

      Integer resultObject = (Integer) Ksoap2ResultParser.getPrimitive(result, Integer.class);

      return resultObject;
   }

   @Override
   public TvProgram GetCurrentProgramOnChannel(int _channelId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_CURRENT_PROGRAM_CHANNEL,
            m_wcfService.CreateProperty("channelId", _channelId));

      TvProgram program = (TvProgram) Ksoap2ResultParser.createObject(result, TvProgram.class);

      return program;
   }

   @Override
   public ArrayList<TvChannelGroup> GetGroups() {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_GROUPS);

      if (result != null) {
         TvChannelGroup[] groups = (TvChannelGroup[]) Ksoap2ResultParser.createObject(result,
               TvChannelGroup[].class);

         if (groups != null) {
            return new ArrayList<TvChannelGroup>(Arrays.asList(groups));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + GET_GROUPS);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + GET_GROUPS);
      }
      return null;
   }

   @Override
   public TvProgram GetProgramById(int _programId) {
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(GET_PROGRAM, m_wcfService
            .CreateProperty("programId", _programId));

      TvProgram program = (TvProgram) Ksoap2ResultParser.createObject(result, TvProgram.class);

      return program;
   }

   @Override
   public boolean GetProgramIsScheduledOnChannel(int _channelId, int _programId) {
      SoapPrimitive result = (SoapPrimitive) m_wcfService
            .MakeSoapCall(GET_PROGRAM_IS_SCHEDULED_CHANNEL);

      Boolean resultObject = (Boolean) Ksoap2ResultParser.getPrimitive(result, Boolean.class);

      return resultObject;
   }

   @Override
   public List<TvProgram> GetProgramsForChannel(int _channelId, Date _startTime, Date _endTime) {
      String methodName = GET_PROGRAMS_FOR_CHANNEL;
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(methodName, m_wcfService
            .CreateProperty("channelId", _channelId), m_wcfService.CreateProperty("startTime",
            _startTime), m_wcfService.CreateProperty("endTime", _endTime));

      if (result != null) {
         TvProgram[] groups = (TvProgram[]) Ksoap2ResultParser.createObject(result,
               TvProgram[].class);

         if (groups != null) {
            return new ArrayList<TvProgram>(Arrays.asList(groups));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + methodName);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + methodName);
      }
      return null;
   }

   @Override
   public List<TvRecording> GetRecordings() {
      String methodName = GET_RECORDINGS;
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(methodName);

      if (result != null) {
         TvRecording[] groups = (TvRecording[]) Ksoap2ResultParser.createObject(result,
               TvRecording[].class);

         if (groups != null) {
            return new ArrayList<TvRecording>(Arrays.asList(groups));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + methodName);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + methodName);
      }
      return null;
   }

   @Override
   public List<TvSchedule> GetSchedules() {
      String methodName = GET_SCHEDULES;
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(methodName);

      if (result != null) {
         TvSchedule[] groups = (TvSchedule[]) Ksoap2ResultParser.createObject(result,
               TvSchedule[].class);

         if (groups != null) {
            return new ArrayList<TvSchedule>(Arrays.asList(groups));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + methodName);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + methodName);
      }
      return null;
   }

   @Override
   public List<TvRtspClient> GetStreamingClients() {
      String methodName = GET_STREAMING_CLIENTS;
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(methodName);

      if (result != null) {
         TvRtspClient[] groups = (TvRtspClient[]) Ksoap2ResultParser.createObject(result,
               TvRtspClient[].class);

         if (groups != null) {
            return new ArrayList<TvRtspClient>(Arrays.asList(groups));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + methodName);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + methodName);
      }
      return null;
   }

   @Override
   public String ReadSettingFromDatabase(String _tagName) {
      SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(DATABASE_READ, m_wcfService
            .CreateProperty("tagName", _tagName));

      String resultObject = (String) Ksoap2ResultParser.getPrimitive(result, String.class);

      return resultObject;
   }

   @Override
   public void WriteSettingToDatabase(String _tagName, String _value) {
      m_wcfService.MakeSoapCall(DATABASE_WRITE, m_wcfService.CreateProperty("tagName", _tagName),
            m_wcfService.CreateProperty("value", _value));

   }

   @Override
   public List<TvProgram> SearchPrograms(String _searchTerm) {
      String methodName = SEARCH_PROGRAMS;
      SoapObject result = (SoapObject) m_wcfService.MakeSoapCall(methodName, m_wcfService
            .CreateProperty("searchTerm", _searchTerm));

      if (result != null) {
         TvProgram[] groups = (TvProgram[]) Ksoap2ResultParser.createObject(result,
               TvProgram[].class);

         if (groups != null) {
            return new ArrayList<TvProgram>(Arrays.asList(groups));
         } else {
            Log.d("Soap", "Error parsing result from soap method " + methodName);
         }
      } else {
         Log.d("Soap", "Error calling soap method " + methodName);
      }
      return null;
   }

   @Override
   public String SwitchTVServerToChannelAndGetStreamingUrl(String _user, int _channelId) {
      SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(SWITCH_CHANNEL_GET_URL,
            m_wcfService.CreateProperty("userName", _user), m_wcfService.CreateProperty("channelId", _channelId));

      String resultObject = (String) Ksoap2ResultParser.getPrimitive(result, String.class);

      return resultObject;
   }

   @Override
   public String SwitchTVServerToChannelAndGetTimeshiftFilename(String _user, int _channelId) {
      SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(SWITCH_CHANNEL_GET_FILE,
            m_wcfService.CreateProperty("userName", _user), m_wcfService.CreateProperty("channelId", _channelId));

      String resultObject = (String) Ksoap2ResultParser.getPrimitive(result, String.class);

      return resultObject;
   }

   @Override
   public boolean TestConnectionToTVService() {
      SoapPrimitive result = (SoapPrimitive) m_wcfService.MakeSoapCall(TEST_SERVICE);

      if (result != null) {
         Boolean resultObject = (Boolean) Ksoap2ResultParser.getPrimitive(result, Boolean.class);
         return resultObject;
      }
      return false;
   }



}
