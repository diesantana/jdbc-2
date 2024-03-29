package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	private static Connection conn = null;
	
	
	public static Connection connectDB() {
		try {
			if (conn == null) {
				Properties properties = loadProperties();
				String url = properties.getProperty("dburl");
				conn = DriverManager.getConnection(url, properties);
			}
			return conn;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	
	private static Properties loadProperties() {
		Properties props = new Properties();
		
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			// carrega as propriedades
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	// close Statement
	public static void closeStatement(Statement st) {
		try {
			if(st != null) {
				st.close();				
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	// close ResultSet
	public static void closeResultSet(ResultSet result) {
		try {
			result.close();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
