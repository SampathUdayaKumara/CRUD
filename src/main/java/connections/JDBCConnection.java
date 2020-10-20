package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnection {
	
	private static final String driverName ="com.mysql.cj.jdbc.Driver";
	private static final String url ="jdbc:mysql://127.0.0.1:3306/crud";
	private static final String userName ="root";
	private static final String password ="";
	
	private static Connection connection;
	
	static {
		try {
			Class.forName(driverName);
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Driver class not found");
		}
	}
	
	public static Connection getConnection() throws SQLException {
		connection= DriverManager.getConnection(url,userName,password);
		return connection;
	}
	
	public static void closeConnection(Connection con) throws SQLException {
		if(con != null) {
			con.close();
		}	
	}
	
	public static void closePrepaerdStatment(PreparedStatement stmt) throws SQLException {
		if(stmt != null) {
			stmt.close();
		}	
	}
	
	public static void closeResultSet(ResultSet rs) throws SQLException {
		if(rs != null) {
			rs.close();
		}	
	}
}
