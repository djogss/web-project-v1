package com.aity.rest.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.aity.dao.MySqlDBConnection;

enum Version{
	v1
}

@Path("/v1/status/*")
public class SimpleAPI {

//	
	private static final String api_release_version  = "1";
	private static final String api_official_version = "00.0" + api_release_version +".00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getTitle() {
		return "<p> Java Web Service </p>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/version/")
	public String getApiVersion() {
		return "API version: " + api_official_version;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/connection")
	public String getDBConnection() throws SQLException {
		String dbMessage = "no db connection";
		String sql = "select now() DATETIME from dual";
		
		Connection conn = null;
		PreparedStatement query = null;
		
		try {
			conn = MySqlDBConnection.getMySqlDbSource().getConnection();
			
			query = conn.prepareStatement(sql);
			
			ResultSet rs = query.executeQuery();
			
			dbMessage = "<p> MySql DB connection working </p>";
			while(rs.next())
				dbMessage += rs.getString("DATETIME");
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return dbMessage;
	}
}
