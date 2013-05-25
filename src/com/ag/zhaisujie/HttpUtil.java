package com.ag.zhaisujie;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
/**
 *    HttpUtil.java
 *     <p>
 *     ����webservice
 *     Copyright: Copyright(c) 2013 
 *     @author Gavin_Feng
 *     </p>
 * 
 */
public class HttpUtil {
	public static String URL_WEBSERVICE_ENDPOINT="http://test.yunjiazheng.com/api/index.php/app/mobile/ws?=1";
	public static String URL_WEBSERVICE_NameSpace="urn:AppControllerwsdl";
	public static String URL_WEBSERVICE_GET_CODE="sendMessage";
	public static String URL_WEBSERVICE_LOGIN="login";
	public static String URL_WEBSERVICE_SET_ORDER="setOrder";
	public static String URL_WEBSERVICE_GET_ORDER_DETAIL="getOrderDetail";
	public static String URL_WEBSERVICE_CANCEL_ORDER="cancelOrder";
	public static String URL_WEBSERVICE_PAY_ORDER="payOrder";
	public static String URL_WEBSERVICE_CONFIRM_ORDER="confirmOrder";
	
	public static String getCode(String phone){

		String result="";
		 // ָ��WebService�������ռ�͵��õķ�����  
        SoapObject request = new SoapObject(URL_WEBSERVICE_NameSpace, URL_WEBSERVICE_GET_CODE); 
        request.addProperty("mobile", phone);  
        // ���ɵ���WebService������SOAP������Ϣ,��ָ��SOAP�İ汾  
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
        envelope.bodyOut = request;  
        envelope.setOutputSoapObject(request);
        HttpTransportSE transport = new HttpTransportSE(URL_WEBSERVICE_ENDPOINT);  
        transport.debug=true;
        try {  
            // ����WebService  
            transport.call(URL_WEBSERVICE_NameSpace+"#"+URL_WEBSERVICE_GET_CODE, envelope);  
            if (envelope.getResponse() != null) {  
            	result=envelope.getResponse().toString();  
            } 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;
	}
	
	public static String login(String phone,String pwd){

		String result="";
		 // ָ��WebService�������ռ�͵��õķ�����  
        SoapObject request = new SoapObject(URL_WEBSERVICE_NameSpace, URL_WEBSERVICE_LOGIN); 
        request.addProperty("mobile", phone);  
        request.addProperty("passCode", pwd);  
        // ���ɵ���WebService������SOAP������Ϣ,��ָ��SOAP�İ汾  
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
        envelope.bodyOut = request;  
        envelope.setOutputSoapObject(request);
        HttpTransportSE transport = new HttpTransportSE(URL_WEBSERVICE_ENDPOINT);  
        transport.debug=true;
        try {  
            // ����WebService  
            transport.call(URL_WEBSERVICE_NameSpace+"#"+URL_WEBSERVICE_LOGIN, envelope);  
            if (envelope.getResponse() != null) {  
            	result=envelope.getResponse().toString();  
            } 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;
	}
}
