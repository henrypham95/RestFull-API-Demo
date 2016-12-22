package com.mkyong.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import old.Response;
import old.User;
import old.UserDAO;

@Path("/user")
public class AllInOne {
	/**
	 * gett all
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllUser() {
		try{
		List<User> users = UserDAO.getAllUser();
		Response response = new Response(true,200,"success",users);

		return new Gson().toJson(response);
		}catch (Exception e) {
			Response response = new Response(false,500,e.getMessage(),new ArrayList<User>());
			return new Gson().toJson(response);
		}
		
		
	
	}
	
	/**
	 * insert
	 * @param inputFromClient
	 * @return
	 */
	
	
	
//	@POST
//	@Path("/register")
//	@Consumes(MediaType.TEXT_PLAIN)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String inserUser(String inputFromClient){
//		User userWillAdd = new Gson().fromJson(inputFromClient, User.class);
//		try {
//			boolean updateSuccess=UserDAO.insertUser(userWillAdd);						
//			Response response = new Response(true,200,"success",new ArrayList<User>());
//			return new Gson().toJson(response);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//			Response response = new Response(false,500,e.getMessage(),new ArrayList<User>());
//			return new Gson().toJson(response);
//			
//		}
//		
//	}
	/**
	 * updateUser
	 */
	
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String inputFromClient){
		User userWillUpdate = new Gson().fromJson(inputFromClient, User.class);
		System.out.println(userWillUpdate.toString());
		try {
			boolean updateSuccess=UserDAO.updateUser(userWillUpdate);						
			Response response = new Response(true,200,"success",new ArrayList<User>());
			return new Gson().toJson(response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			Response response = new Response(false,500,e.getMessage(),new ArrayList<User>());
			return new Gson().toJson(response);
			
		}
	}
	
	/**
	 * Dell User
	 * @param inputFromClient
	 * @return
	 */
	
	@PUT
	@Path("/{phone}")
	@Produces(MediaType.TEXT_PLAIN)
	public String dellUSer( @PathParam("phone") String phone){
		 
		try {
			boolean updateSuccess=UserDAO.deleteUser(phone);					
			Response response = new Response(true,200,"success",new ArrayList<User>());
			return new Gson().toJson(response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			Response response = new Response(false,500,e.getMessage(),new ArrayList<User>());
			return new Gson().toJson(response);
			
		}
	}
	
	
	
	
	
	
	
	@GET
	@Path("/{phone}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserByPhone(@PathParam("phone") String phone){
		List<User> users;
		try {
			users = UserDAO.getUserByPhone(phone);
			Response response = new Response(true,200,"success",users);
			System.out.println(new Gson().toJson(response));
			return new Gson().toJson(response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Response response = new Response(false,500,e.getMessage(),new ArrayList<User>());
			return new Gson().toJson(response);
		}
		 
	}
	@GET
	@Path("/testconnection")
	@Produces(MediaType.TEXT_PLAIN)
	public String testConnection(){
		return "Success";
	}

}
