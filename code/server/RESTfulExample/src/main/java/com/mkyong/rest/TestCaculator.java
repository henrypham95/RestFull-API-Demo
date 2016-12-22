package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/TinhToan")
public class TestCaculator {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/add")
	public String add(@QueryParam("soa") String soa, @QueryParam("sob") String sob) {
		int tong,soa1,sob1;
		soa1 = Integer.parseInt(soa);
		sob1 = Integer.parseInt(sob);
		tong = soa1+sob1;
		
		
		return String.valueOf(tong);
	}

	 
}
