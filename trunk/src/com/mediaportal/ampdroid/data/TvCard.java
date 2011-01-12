package com.mediaportal.ampdroid.data;

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

   public void setCardName(String cardName) {
      CardName = cardName;
   }

   public TvChannel getChannel() {
      return Channel;
   }

   public void setChannel(TvChannel channel) {
      Channel = channel;
   }

   public boolean isIsTimeShifting() {
      return IsTimeShifting;
   }

   public void setIsTimeShifting(boolean isTimeShifting) {
      IsTimeShifting = isTimeShifting;
   }

   public boolean isIsRecording() {
      return IsRecording;
   }

   public void setIsRecording(boolean isRecording) {
      IsRecording = isRecording;
   }
}
