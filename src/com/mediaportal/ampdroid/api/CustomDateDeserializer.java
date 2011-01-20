package com.mediaportal.ampdroid.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import android.util.Log;

public class CustomDateDeserializer extends JsonDeserializer {

   @Override
   public Object deserialize(JsonParser _parser, DeserializationContext _context)
         throws IOException, JsonProcessingException {
      String dateStr = _parser.getText();
      
      //the f*** format .NET uses here is \/Date(xxxxxxxxx+xxxx)\/ were xxx is a timestamp
      dateStr = dateStr.substring(6, dateStr.length() - 2);
      
      //TODO: this filters out the "+0100" timezone representation at the end of the string
      // this might lead to problems in the future :(
      int posPlus = dateStr.indexOf('+');
      int posMinus = dateStr.indexOf('+');
      
      if(posPlus != 0){
         dateStr = dateStr.substring(0, posPlus);
      }
      
      if(posMinus != 0){
         dateStr = dateStr.substring(0, posMinus);
      }

      long timestamp = Long.parseLong(dateStr);
      Date date = new Date(timestamp);
      return date;

   }

}
