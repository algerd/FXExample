
package  sqlitejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * All of methods of Connect are static and they are used to establish and close a database connection,
 * close a Statement, close a ResultSet, commit a transaction, rollback a transaction, etc.
 * Connect.main() method tests connection with db. 
 */
public class Connect {
    
	public static Connection getConnection() throws SQLException {
		// Register the SQLite driver
		DriverManager.registerDriver(new org.sqlite.JDBC());
        
        String urlDb = "jdbc:sqlite:src//resources//db//persontest.sqlite";
		// Get a connection 
		Connection conn = DriverManager.getConnection(urlDb);
		// Set the auto-commit off  
		conn.setAutoCommit(false);
		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null) {
				conn.commit();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn = null;     
		try {
			conn = Connect.getConnection();
			System.out.println("Connetced to the database.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Connect.closeConnection(conn);
		}
	}
}
