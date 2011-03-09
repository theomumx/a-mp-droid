package com.mediaportal.ampdroid.remote;

import org.codehaus.jackson.annotate.JsonProperty;

public class RemotePluginMessage {
   private RemotePlugin[] mPlugins;

   @JsonProperty("Plugins")
   public void setPlugins(RemotePlugin[] plugins) {
      mPlugins = plugins;
   }

   @JsonProperty("Plugins")
   public RemotePlugin[] getPlugins() {
      return mPlugins;
   }
}
