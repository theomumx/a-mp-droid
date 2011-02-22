package com.mediaportal.ampdroid.utils;

import java.util.Date;

public class DateTimeHelper {

   public static Date parseDate(String _dateString) {
      return new Date();
   }

   public static String getDateString(Date _date, boolean _getHours) {
      if (_getHours) {
         return (String) android.text.format.DateFormat.format("yyyy-MM-dd kk:mm", _date);
      } else {
         return (String) android.text.format.DateFormat.format("yyyy-MM-dd", _date);
      }

   }

   public static String getTimeString(Date _date) {
      return (String) android.text.format.DateFormat.format("kk:mm", _date);
   }

   public static String getDayOfWeek(Date _day) {
      switch (_day.getDay()) {
      case 1:
         return "Monday";
      case 2:
         return "Tuesday";
      case 3:
         return "Wednesday";
      case 4:
         return "Thursday";
      case 5:
         return "Friday";
      case 6:
         return "Saturday";
      case 7:
         return "Sunday";
      default:
         return "Unknown";
      }
   }

}
