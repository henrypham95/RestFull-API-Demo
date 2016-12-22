package com.mkyong.rest;

import javax.xml.soap.SOAPEnvelope;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.PUBLIC_MEMBER;

public class LogicDocControoler {
	private static final String BASE_PATH = "http://localhost:3000";
	public static void main(String args[]){
		LogicDocControoler logicDocControoler = new LogicDocControoler();
		try{
			
			String sid = logicDocControoler.doPostLogin("admin", "admin");
			//String sid = restClient.doPostLoginJSON("admin", "admin123");
			System.out.println("sid: " + sid);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
public static String doPostLogin(String username, String password) throws Exception {
	
	CloseableHttpClient httpclient = getHTTPClient(username, password);

	String input = "{ \"username\" : \"" +username +"\", \"password\" : \"" +password +"\" }";
	System.out.println(input);

	StringEntity entity = new StringEntity(input, ContentType.create("application/json", Consts.UTF_8));
	HttpPost httppost = new HttpPost(BASE_PATH + "/services/rest/auth/login");
	httppost.setEntity(entity);

	CloseableHttpResponse response = httpclient.execute(httppost);
	try {
		HttpEntity rent = response.getEntity();
		if (rent != null) {
			String respoBody = EntityUtils.toString(rent, "UTF-8");
			System.out.println(respoBody);
			return respoBody;
		}
	} finally {
		response.close();
	}

	return null;
}
private static CloseableHttpClient getHTTPClient(String username, String password) {
	CredentialsProvider credsProvider = new BasicCredentialsProvider();
    credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
    
    CloseableHttpClient httpclient = HttpClients.custom()
            .setDefaultCredentialsProvider(credsProvider)
            .build();
    
    return httpclient;
}
}
