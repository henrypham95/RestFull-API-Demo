package com.mkyong.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.soap.SOAPMessage;

@Path("/tichhop")
public class TichHop {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/login")
	public String login(@QueryParam("username") String userName, @QueryParam("password") String passWord) throws Exception {
		try {
			String mdResturn=MoodleController.login(userName, passWord);
			String ldReturn=LogicDocControoler.doPostLogin(userName, passWord);
			String iliasReturn=ILIASController.login("ilias",userName, passWord);
			String rs=mdResturn+"$"+ldReturn+"$"+iliasReturn;
			System.out.println(mdResturn);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/create-user")
	public String createUser(@QueryParam("moodletoken") String moodleToken, @QueryParam("username") String userName,
			@QueryParam("password") String passWord, @QueryParam("firstname") String firstName,
			@QueryParam("lastname") String lastName, @QueryParam("email") String email) {

		return MoodleController.createUser(moodleToken, userName, passWord, firstName, lastName, email);
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/login1")
	public String login1(@QueryParam("username") String userName, @QueryParam("password") String passWord) throws Exception  {
		return LogicDocControoler.doPostLogin(userName, passWord);
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/login2")
	public String login2(@QueryParam("client") String client, @QueryParam("username") String userName, @QueryParam("password") String passWord) throws Exception  {
		return ILIASController.login(client,userName, passWord);
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/create-course")
	public String createCourseMoodle(@QueryParam("moodletoken") String moodleToken,@QueryParam("iliassid") String iliassid,@QueryParam("fullname") String fullname,@QueryParam("shortname") String shortname ){
		
		try {
			String mdReturn= MoodleController.createrCourse(moodleToken,fullname , shortname, "1");
			String ilReturn =  ILIASController.GetIdCourse(iliassid, "1", "2");
			return mdReturn+"$"+ilReturn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/create-course-ilias")
	public String createCourseIliase(@QueryParam("sid") String sid,@QueryParam("targetid") String targetid,@QueryParam("crsxml") String crsxml) throws Exception{
		return ILIASController.GetIdCourse(sid, targetid, crsxml);
	}
}

