
package  sqlitejava;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
Listing contains the complete code that inserts three records in the person table. Note that it uses utility
methods of the Connect class to perform some of the activities such as getting a Connection object,
closing Statement object, committing/rolling back a transaction, etc. If you run the program in Listing more than
once, it will print an error message stating that you are trying to insert duplicate key in the person table because you
have defined the person_id as the primary key in the table, and every time you run the program, it inserts the same
set of person_id values.

SQLite does not have a storage class set aside for storing dates and/or times. 
Instead, the built-in Date And Time Functions of SQLite are capable of storing dates and times as TEXT, REAL, or INTEGER values:
    TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
    REAL as Julian day numbers, the number of days since noon in Greenwich on November 24, 4714 B.C. according to the proleptic Gregorian calendar.
    INTEGER as Unix Time, the number of seconds since 1970-01-01 00:00:00 UTC.
Applications can chose to store dates and times in any of these formats and freely convert between formats using the built-in date and time functions.
*/
public class InsertPersonTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = Connect.getConnection();
			
			// Insert 3 person records
			insertPerson(conn, 101, "John", "Jacobs", "M", "'1970-01-01'", 60000);
			insertPerson(conn, 102, "Donna", "Duncan","F", "'1960-01-01'", 70000);
			insertPerson(conn, 103, "Buddy", "Rice", "M", "'1975-01-01'", 45000);

			// Commit the transaction  
			Connect.commit(conn);
			System.out.println("Inserted persons successfully.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			Connect.rollback(conn);			
		}
		finally {
			Connect.closeConnection(conn);
		}
	}

	public static void insertPerson(
        Connection conn, 
        int personId,
		String firstName, 
        String lastName, 
        String gender, 
        String dob,
		double income) throws SQLException 
    {		
		// Create a SQL string  
		String SQL = "insert into person " + 
		             "(person_id, first_name, last_name," + 
		             " gender, dob, income) " + 
		             "values " + 
		             "(" + personId + ", " + 
		              "'" + firstName + "'" + ", " + 
		              "'" + lastName + "'" + ", " + 
		              "'" + gender + "'" + ", " + 
		              dob + ", " + 
		              income + ")";

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
		}
		finally {
			Connect.closeStatement(stmt);
		}
	}
}
