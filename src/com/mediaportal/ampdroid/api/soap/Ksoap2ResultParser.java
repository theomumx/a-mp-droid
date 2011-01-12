package com.mediaportal.remote.api.soap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

/**
 * Parses Soap Messages and return the representing object.
 * 
 * @author DieBagger
 */
public class Ksoap2ResultParser {

   @SuppressWarnings("unchecked")
   public static Object createObject(SoapObject _result, Class _outputType) {
      if (_result == null)
         return null;
      Object outputObject = null;
      if (_outputType.isArray()) {
         Class dataType = _outputType.getComponentType();

         outputObject = Array.newInstance(dataType, _result.getPropertyCount());
         for (int i = 0; i < _result.getPropertyCount(); i++) {
            if (dataType.isPrimitive() || dataType == String.class
                  || dataType == Date.class) {
               Object o = getPrimitive((SoapPrimitive) _result.getProperty(i),
                     dataType);
               Array.set(outputObject, i, o);
            } else {
               Object resultObject = _result.getProperty(i);
               if (resultObject != null) {
                  Object o = createObject((SoapObject) resultObject, dataType);
                  Array.set(outputObject, i, o);
               }
            }
         }
      } else {
         if (_outputType.isPrimitive()) {
            outputObject = getPrimitive((SoapPrimitive) _result.getProperty(0),
                  _outputType);
         } else {
            List<Field> fields = getAllFields(_outputType);
            try {
               outputObject = _outputType.newInstance();
            } catch (IllegalAccessException e) {
               e.printStackTrace();
            } catch (InstantiationException e) {
               e.printStackTrace();
            } catch (Exception e) {
               // todo: remove general exception for proper exc. handling
               e.printStackTrace();
            }
            if (outputObject != null) {
               for (int i = 0; i < fields.size(); i++) {
                  Class c = fields.get(i).getType();
                  fields.get(i).setAccessible(true);
                  String fieldName = fields.get(i).getName();
                  if (_result.hasProperty(fieldName)) {
                     if (c.isPrimitive() || c == String.class
                           || c == Date.class) {
                        // Object o = null;
                        Object o = null;
                        Object resultObject = _result.getProperty(fieldName);
                        if (resultObject != null) {
                           try {
                              o = getPrimitive((SoapPrimitive) resultObject, c);
                              fields.get(i).set(outputObject, o);
                           } catch (IllegalArgumentException e) {
                              e.printStackTrace();
                           } catch (IllegalAccessException e) {
                              e.printStackTrace();
                           } catch (Exception e) {
                              // todo: remove general exception for
                              // proper exc. handling
                              e.printStackTrace();
                           }
                        }
                     } else {
                        Object o = null;
                        Object resultObject = _result.getProperty(fieldName);
                        if (resultObject != null) {
                           try {
                              o = createObject((SoapObject) resultObject, c);
                              fields.get(i).set(outputObject, o);
                           } catch (IllegalAccessException e) {
                              e.printStackTrace();
                           } catch (Exception e) {
                              // todo: remove general exception for
                              // proper exc. handling
                              e.printStackTrace();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      return outputObject;
   }

   @SuppressWarnings("unchecked")
   public static Object getPrimitive(SoapPrimitive property, Class _class) {
      try {
         if (_class.equals(String.class)) {
            return property.toString();
         }
         if (_class.equals(Date.class)) {
            return null;
         }
         if (_class.equals(Integer.class) || _class.equals(int.class)) {
            return Integer.parseInt(property.toString());
         }
         if (_class.equals(Boolean.class) || _class.equals(boolean.class)) {
            return Boolean.parseBoolean(property.toString());
         }
         if (_class.equals(Long.class) || _class.equals(long.class)) {
            return Long.parseLong(property.toString());
         }
         if (_class.equals(Float.class) || _class.equals(float.class)) {
            return Float.parseFloat(property.toString());
         }
         if (_class.equals(Double.class) || _class.equals(double.class)) {
            return Double.parseDouble(property.toString());
         }
         if (_class.equals(Short.class) || _class.equals(short.class)) {
            return Short.parseShort(property.toString());
         }

      } catch (IllegalArgumentException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (Exception e) {
         // todo: remove general exception for proper exc. handling
         e.printStackTrace();
      }
      return null;
   }

   public static List<Field> getAllFields(Class<?> type) {
      List<Field> fields = new ArrayList<Field>();
      fillAllFields(fields, type);
      return fields;
   }

   private static void fillAllFields(List<Field> fields, Class<?> type) {
      for (Field field : type.getDeclaredFields()) {
         fields.add(field);
      }

      if (type.getSuperclass() != null && type.getSuperclass() != Object.class) {
         fillAllFields(fields, type.getSuperclass());
      }
   }

}
