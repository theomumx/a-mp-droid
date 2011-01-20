package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

public class TvCard {
   String CardName;
   TvChannel Channel;
   boolean IsTimeShifting;
   boolean IsRecording;

   @Override
   public String toString() {
      if (CardName != null) {
         return CardName;
      } else
         return "[Unknown Card]";
   }

   public String getCardName() {
      return CardName;
   }

   @JsonProperty("CardName")
   public void setCardName(String cardName) {
      CardName = cardName;
   }

   public TvChannel getChannel() {
      return Channel;
   }

   @JsonProperty("Channel")
   public void setChannel(TvChannel channel) {
      Channel = channel;
   }

   public boolean isIsTimeShifting() {
      return IsTimeShifting;
   }

   @JsonProperty("IsTimeShifting")
   public void setIsTimeShifting(boolean isTimeShifting) {
      IsTimeShifting = isTimeShifting;
   }

   public boolean isIsRecording() {
      return IsRecording;
   }

   @JsonProperty("IsRecording")
   public void setIsRecording(boolean isRecording) {
      IsRecording = isRecording;
   }
}
