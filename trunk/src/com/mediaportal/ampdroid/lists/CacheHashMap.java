package com.mediaportal.remote.lists;

import java.util.LinkedHashMap;

import android.graphics.Bitmap;

public class CacheHashMap extends LinkedHashMap<String, Bitmap> {

   private static final long serialVersionUID = 1399232902907446561L;
   private final int capacity;

   public CacheHashMap(int capacity)
   {
     super(capacity + 1, 1.1f, true);
     this.capacity = capacity;
   }

   @Override
   protected boolean removeEldestEntry(java.util.Map.Entry<String, Bitmap> eldest) {
      return size() > capacity;
   }


}
