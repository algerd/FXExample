
package sqlitejava;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
If the person table already exists, the program prints the following error message for Java DB:
Table/View 'PERSON' already exists in Schema 'ROOT'.
*/
public class CreatePersonTable {
    
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = Connect.getConnection();

			// Create a SQL string
			String SQL = "create table person ( " +
			             "person_id integer not null, " +
			             "first_name text(20) not null, " +
			             "last_name text(20) not null, " +
			             "gender text(1) not null, " +
			             "dob text , " +
			             "income real," +
			             "primary key(person_id asc))";
                             
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(SQL);
			} 
			finally {
				Connect.closeStatement(stmt);
			}
			
			// Commit the transaction  
			Connect.commit(conn);

			System.out.println("Person table created.");
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			Connect.rollback(conn);			
		} 
		finally {
			Connect.closeConnection(conn);
		}
	}
}
