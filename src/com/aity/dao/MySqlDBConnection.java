package com.aity.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MySqlDBConnection {

	private static DataSource mysqlDbSource = null;
	private static Context context = null;

	public static DataSource getMySqlDbSource() {
		
		if(mysqlDbSource != null)
			return mysqlDbSource;
		
		try{
			
			if(context == null)
				context = new InitialContext();
			
			mysqlDbSource = (DataSource) context.lookup("MySqlJNDIsource");
			
		}catch (Exception e) {
			e.getMessage();
		}
		
		return mysqlDbSource;
		
	}
}
