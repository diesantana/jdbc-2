package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet result = null;
		
		try {
			conn = DB.connectDB();
			st = conn.createStatement();
			result = st.executeQuery("SELECT * FROM department");
			
			while(result.next()) {
				System.out.println(result.getInt("Id") +", " + result.getString("Name"));
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeConnection();
			DB.closeStatement(st);
			DB.closeResultSet(result);
		}

	}

}
