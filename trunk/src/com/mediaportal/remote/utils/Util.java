package com.mediaportal.remote.utils;

import android.content.Context;
import android.os.Vibrator;
import android.widget.Toast;

public class Util {
   public static void Vibrate(Context _context, int _time) {
      Vibrator v = (Vibrator) _context.getSystemService(Context.VIBRATOR_SERVICE);
      v.vibrate(_time);
   }
   
   public static Toast toast;
   public static void showToast(Context _context, String _text) {
      if(toast != null){
         toast.cancel();
      }
      toast = Toast.makeText(_context, "Remote not connected", Toast.LENGTH_SHORT);
      toast.show();
   }
}
