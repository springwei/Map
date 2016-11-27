package com.test.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public Map<String, BigDecimal> getLatAndLngByAddress(String addr){
        String address = "";
        String lat = "";
        String lng = "";
        try {  
            address = java.net.URLEncoder.encode(addr,"UTF-8");  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } 
        String url = String.format("http://api.map.baidu.com/geocoder/v2/?"
        +"ak=4rcKAZKG9OIl0wDkICSLx8BA&output=json&address=%s",address);
        URL myURL = null;
        URLConnection httpsConn = null;  
        //����ת��
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {

        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                    lat = data.substring(data.indexOf("\"lat\":") 
                    + ("\"lat\":").length(), data.indexOf("},\"precise\""));
                    lng = data.substring(data.indexOf("\"lng\":") 
                    + ("\"lng\":").length(), data.indexOf(",\"lat\""));
                }
                insr.close();
            }
        } catch (IOException e) {

        }
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        map.put("lat", new BigDecimal(lat));
        map.put("lng", new BigDecimal(lng));
        return map;
}
	
	public static void main(String[] args) {
		Test t  = new Test();
		Map<String, BigDecimal> address = t.getLatAndLngByAddress("����");
		Iterator<Entry<String, BigDecimal>> iterator = address.entrySet().iterator();
		while(iterator.hasNext())
		{
			 Entry<String, BigDecimal> next = iterator.next();
			
			 System.out.println("key��ֵΪ---"+next.getKey()+"");
			System.out.println("value��ֵΪ---"+next.getValue());
		}
		
	}
}
