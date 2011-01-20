package com.mediaportal.ampdroid.database;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.mediaportal.ampdroid.data.Movie;

public class SqliteAnnotationsHelper {
   private static class AccessHolder {
      private Method method;
      private String columnName;
      private String columnType;
      private int columnIndex;
   }

   public static <T> String getCreateTableStringFromClass(Class<T> _class) {

      TableProperty tableProp = _class.getAnnotation(TableProperty.class);
      String tableName = tableProp.value();

      Method[] methods = _class.getMethods();

      String createString = "create table " + tableName
            + " ( RowId integer primary key autoincrement";
      for (Method m : methods) {
         if (!m.getReturnType().equals(Void.TYPE)) {
            ColumnProperty column = m.getAnnotation(ColumnProperty.class);

            if (column != null) {
               String columnName = column.value();
               String columnType = column.type();
               createString += ", " + columnName + " " + columnType;
            }
         }
      }
      createString += ");";

      return createString;
   }

   public static <T> String getReadAllColumnsFromTableString(Class<T> _class) {
      TableProperty tableProp = _class.getAnnotation(TableProperty.class);
      String tableName = tableProp.value();

      Method[] methods = _class.getMethods();

      String createString = "create table " + tableName
            + " ( RowId integer primary key autoincrement";
      for (Method m : methods) {
         if (!m.getReturnType().equals(Void.TYPE)) {
            ColumnProperty column = m.getAnnotation(ColumnProperty.class);

            if (column != null) {
               String columnName = column.value();
               String columnType = column.type();
               createString += ", " + columnName + " " + columnType;
            }
         }
      }
      createString += ");";

      return createString;
   }

   public static <T> List<T> getObjectsFromCursor(Cursor _cursor, Class<T> _class, int _limit) {
      try {
         Method[] allMethods = _class.getMethods();
         List<AccessHolder> setters = new ArrayList<AccessHolder>();

         for (Method m : allMethods) {
            if (m.getReturnType().equals(Void.TYPE)) {
               ColumnProperty column = m.getAnnotation(ColumnProperty.class);

               if (column != null) {
                  String columnName = column.value();
                  int columnIndex = _cursor.getColumnIndex(columnName);
                  if (columnIndex != -1) {// column exists
                     AccessHolder holder = new AccessHolder();
                     holder.columnName = columnName;
                     holder.columnType = column.type();
                     holder.columnIndex = columnIndex;
                     holder.method = m;
                     setters.add(holder);
                  }
               }
            }
         }

         List<T> returnList = new ArrayList<T>();

         int count = 0;
         do {
            T returnObject = _class.newInstance();
            for (AccessHolder h : setters) {
               if (h.columnType.equals("text")) {
                  h.method.invoke(returnObject, _cursor.getString(h.columnIndex));
               }
               if (h.columnType.equals("integer")) {
                  h.method.invoke(returnObject, _cursor.getInt(h.columnIndex));
               }
               if (h.columnType.equals("float")) {
                  h.method.invoke(returnObject, _cursor.getFloat(h.columnIndex));
               }

            }
            count++;
            returnList.add(returnObject);
         } while (_cursor.moveToNext() && (count < _limit && _limit != 0));

         return returnList;
      } catch (IllegalAccessException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (InstantiationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IllegalArgumentException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (InvocationTargetException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return null;
   }

   public static HashMap<Integer, List<AccessHolder>> contentValues = new HashMap<Integer, List<AccessHolder>>();

   public static <T> ContentValues getContentValuesFromObject(Object _object, Class<T> _class) {
      try {
         int hashCode = _class.hashCode();
         List<AccessHolder> getters = null;
         if (!contentValues.containsKey(hashCode)) {
            getters = new ArrayList<AccessHolder>();
            Method[] methods = _class.getMethods();
            for (Method m : methods) {
               if (!m.getReturnType().equals(Void.TYPE)) {
                  ColumnProperty column = m.getAnnotation(ColumnProperty.class);

                  if (column != null) {
                     AccessHolder holder = new AccessHolder();
                     holder.columnName = column.value();
                     holder.columnType = column.type();
                     holder.method = m;
                     getters.add(holder);
                  }
               }
            }

            contentValues.put(hashCode, getters);
         } else {
            getters = contentValues.get(hashCode);
         }

         ContentValues returnObject = new ContentValues();
         for (AccessHolder a : getters) {
            // column exists
            if (a.columnType.equals("text")) {
               returnObject.put(a.columnName, (String) a.method.invoke(_object));
            } else if (a.columnType.equals("integer")) {
               returnObject.put(a.columnName, (Integer) a.method.invoke(_object));
            } else if (a.columnType.equals("float")) {
               returnObject.put(a.columnName, (Float) a.method.invoke(_object));
            }
         }

         return returnObject;
      } catch (IllegalAccessException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IllegalArgumentException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (InvocationTargetException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return null;
   }
}
