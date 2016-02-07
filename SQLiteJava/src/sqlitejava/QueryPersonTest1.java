
package  sqlitejava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*

*/
public class QueryPersonTest1 {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = Connect.getConnection();

			displayPersonUsingStatement(conn, 101);

			// Commit the transaction  
			Connect.commit(conn);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			Connect.rollback(conn);			
		}
		finally {
			Connect.closeConnection(conn);
		}
	}

	public static void displayPersonUsingStatement(Connection conn, int inputPersonId) throws SQLException {
		String SQL = "select person_id from person ";

		Statement stmt = null;
        ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			int countRow = 0;
            while (rs.next()) {
                countRow++;
            }
            System.out.println("" + countRow);
        }
		finally { 
			Connect.closeStatement(stmt);
		}
        
        
	}


	public static void printResultSet(ResultSet rs) throws SQLException {
        int countRow = 0;
		while (rs.next()) {
            countRow++;
		}
        System.out.println("" + countRow);
	}

}
