package com.mediaportal.remote.activities.lists;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    
    public static String createFormattedDate(Date date){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    	String dateString = dateFormat.format(date);
    	//String dateString = getTrailingNumber(date.get) + "." + 
    	//getTrailingNumber(date.getMonth()) +"." + date.getYear();
    	return dateString;
    }
    
	public static String getWeekDayString(String weekDay) {
		if(weekDay.equals("0")) return "Montag";
		if(weekDay.equals("1")) return "Dienstag";
		if(weekDay.equals("2")) return "Mittwoch";
		if(weekDay.equals("3")) return "Donnerstag";
		if(weekDay.equals("4")) return "Freitag";
		if(weekDay.equals("5")) return "Samstag";
		if(weekDay.equals("6")) return "Sonntag";
		return null;
	}
}