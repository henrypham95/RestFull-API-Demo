package com.mkyong.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MoodleController {
	public static String SERVICE_NAME = "tf";
	
	
	public static String login(String userName, String passWord) throws NullPointerException{

		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost/moodle/login/token.php")
				.queryParam("username", userName).queryParam("password", passWord).queryParam("service", SERVICE_NAME);
		System.out.println(webResource.toString());
		ClientResponse response = webResource.accept("text/plain").get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);
		System.out.println(output);
		JsonObject jo = new JsonParser().parse(output).getAsJsonObject();
		String asd=jo.get("token").getAsString();
		 
		return asd;
	}

	public static String createUser(String adminToken, String userName, String passWord, String firstName,
			String lastName, String email) {
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost/moodle/webservice/rest/server.php")
				.queryParam("wstoken", adminToken).queryParam("wsfunction", "core_user_create_users")
				.queryParam("moodlewsrestformat", "json").queryParam("users[0][username]", userName)
				.queryParam("users[0][password]", passWord).queryParam("users[0][firstname]", firstName)
				.queryParam("users[0][lastname]", lastName).queryParam("users[0][email]", email);
		System.out.println(webResource.toString());
		ClientResponse response = webResource.accept("text/plain").post(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		return output;
	}
	public static String createrCourse(String adminToken,String fullNameOfCourse,String shortName ,String catalogyID){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost/moodle/webservice/rest/server.php")
				.queryParam("wstoken", adminToken).queryParam("wsfunction", "core_course_create_courses")
				.queryParam("moodlewsrestformat", "json").queryParam("courses[0][fullname]", fullNameOfCourse)
				.queryParam("courses[0][shortname]", shortName).queryParam("courses[0][categoryid]", catalogyID);
		System.out.println(webResource.toString());
		ClientResponse response = webResource.accept("text/plain").post(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		return output;
	}
}
	

/// webservice/rest/server.php