package com.mediaportal.remote.api.gmawebservice.soap;

import java.io.IOException;
import java.util.TimerTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.xmlpull.v1.XmlPullParserException;


public class WcfAccessHandler {
	private String m_url;
	private String m_namespace;
	private String m_methodPrefix;
	public WcfAccessHandler(String _url, String _namespace, String _methodPrefix) {
		m_url = _url;// "http://10.1.0.247:44321/GmaWebService/MediaAccessService?wsdl"
		m_namespace = _namespace; // "http://tempuri.org/"
		m_methodPrefix = _methodPrefix; //IMediaAccessService
	}

	public Object MakeSoapCall(String _method, PropertyInfo... _parameters) {
		try {
			HttpTransportSE trans = new HttpTransportSE(m_url);

			SoapObject request = new SoapObject(m_namespace, _method);
			SoapSerializationEnvelope env = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			env.dotNet = true;
			env.setOutputSoapObject(request);

			if (_parameters != null) {
				for (PropertyInfo p : _parameters) {
					request.addProperty(p);
				}
			}

			trans.call(m_methodPrefix + _method, env);
			return env.getResponse();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		}

	}

	public PropertyInfo CreateProperty(String _attribute, Object _value) {
		PropertyInfo info = new PropertyInfo();
		info.setName(_attribute);
		info.setValue(_value);
		return info;
	}
}
