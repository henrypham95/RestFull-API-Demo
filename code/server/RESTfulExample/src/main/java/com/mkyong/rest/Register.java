package com.mkyong.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import old.Response;
import old.User;
import old.UserDAO;




@Path("/register")
public class Register {
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserUser(String inputFromClient){
		User userWillAdd = new Gson().fromJson(inputFromClient, User.class);
		System.out.println(userWillAdd);
		try {
			boolean updateSuccess=UserDAO.insertUser(userWillAdd);						
			Response response = new Response(true,200,"success",new ArrayList<User>());
			return new Gson().toJson(response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			Response response = new Response(false,500,e.getMessage(),new ArrayList<User>());
			return new Gson().toJson(response);
			
		}
		
	}
}
