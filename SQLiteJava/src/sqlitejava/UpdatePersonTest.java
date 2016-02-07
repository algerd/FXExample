
package  sqlitejava;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatePersonTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = Connect.getConnection();

			// Give everyone a 5% raise  
			giveRaise(conn, 5.0);

			// Commit the transaction  
			Connect.commit(conn);
			
			System.out.println("Updated person records successfully.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			Connect.rollback(conn);			
		}
		finally {
			Connect.closeConnection(conn);
		}
	}

	public static void giveRaise(Connection conn, double percentRaise) throws SQLException {
		String SQL = "update person set income = income + income * " + (percentRaise/100);
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int updatedCount = stmt.executeUpdate(SQL);

			// Print how many records were updated  
			System.out.println("Gave raise to " + updatedCount + " person(s).");
		}
		finally {
			Connect.closeStatement(stmt);
		}
	}
}
