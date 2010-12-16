package com.mediaportal.remote.api.mpclient;

public class MPClientMessage {
   private String action = "";
   private String filter = "";
   private String value = "";
   private String start = "0";
   private String pagesize = "0";
   private boolean shuffle;
   private boolean enqueue;
   private String tracks = "";

   public MPClientMessage(String _action, String _filter, String _value) {
      action = _action;
      filter = _filter;
      value = _value;
   }
}
