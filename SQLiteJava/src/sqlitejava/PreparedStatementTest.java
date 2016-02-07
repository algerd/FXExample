
package  sqlitejava;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Locale;

/*
The PreparedStatement interface inherits from the Statement interface. It is preferred over the Statement
interface to execute a SQL statement. It precompiles the SQL statement provided DBMS supports a SQL statement
precompilation. It reuses the precompiled SQL statement if the statement is executed multiple times. It lets you
prepare a SQL statement, which is in a string format, using placeholders for input parameters.

A question mark in a SQL string is a placeholder for an input parameter whose value will be supplied before the
statement is executed. Suppose you want to use a PreparedStatement to insert a record in the person table. Your SQL
statement in a string format would be as follows:

    String sql = "insert into person " +
        "(person_id, first_name, last_name, gender, dob, income) values " +
        "(?, ?, ?, ?, ?, ?)";

In this example, each of the six question marks is a placeholder for a value. The first question mark is a
placeholder for person_id, the second one for first_name, and so on. Each placeholder has an index. The first
placeholder in a SQL string is given an index of 1, the second placeholder an index of 2, and so on. Note that the index
of the placeholder starts at 1, not 0.

You can create a PreparedStatement using the prepareStatement() method of the Connection object.
The prepareStatement() method is overloaded. In its simplest form, it accepts a SQL string as follows:

    String sql = "your sql statement goes here";
    Connection conn = Connect.getConnection();
    // Obtain a PreparedStatement for the sql
    PreparedStatement pstmt = conn.prepareStatement(sql);

The next step is to supply the values for the placeholders one-by-one using a setXxx() method of the
PreparedStatement interface, where Xxx is the data type of the placeholder. The setXxx() method accepts two
parameters: the first one is the index of the placeholder and the second one is the value for the placeholder.
The second argument for the setXxx() method must be compatible with Xxx, which is the data type of the placeholder.
If you want to set the values for the six placeholders for the INSERT statement to insert a record in the person table,
you do it as follows:
    pstmt.setInt(1, 301); // person_id
    pstmt.setString(2, "Tom"); // first name
    pstmt.setString(3, "Baker"); // last name
    pstmt.setString(4, "M"); // gender

Now it is time to send the SQL statement with the values for the placeholders to the database. You execute a SQL
statement in a PreparedStatement using one of its execute(), executeUpdate(), and executeQuery() methods.
These methods take no arguments. Recall that the Statement interface has the same methods, which take SQL strings
as their arguments. The PreparedStatement interface has added three methods with the same name, which take no
arguments, because it gets its SQL string when it is created.

    // Execute the INSERT statement in pstmt
    pstmt.executeUpdate();

How do you reuse a PreparedStatement? Simply repopulate the placeholder values and call one of its execute()
methods again. When you invoke the setXxx() method on a PreparedStatement object again, its previously set value
for the specified placeholder is overwritten with the new value. A PreparedStatement keeps holding the set values
for its placeholder even after it is executed. Therefore, if you want to set the same value for a placeholder for multiple
executions, you need to set the value for that placeholder only once. If you want to clear the values of all placeholders,
you can use the clearParameters() method of the PreparedStatement interface. 

When you are done with executing the statement in a PreparedStatement object, you need to close it using its
close() method.
    pstmt.close();

Listing demonstrates how to use a PreparedStatement object to execute an INSERT SQL statement. Note that
this example reuses the PreparedStatement to insert two records in the person table.
*/
public class PreparedStatementTest {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Connect.getConnection();
			pstmt = getInsertSQL(conn);

			// Отформатировать дату в требуемый строковый формат для передачи в бд.
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = Date.valueOf("1970-01-01");
            String dob = dateFormat.format(date);

			// Insert two person records  
			insertPerson(pstmt, 401, "Sara", "Jain", "F", dob, 0.0);
			insertPerson(pstmt, 501, "Su", "Chi", "F", null, 10000.0);

			// Commit the transaction  
			Connect.commit(conn);
			
			System.out.println("Updated person records successfully.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			Connect.rollback(conn);			
		}
		finally {
			Connect.closeStatement(pstmt);
			Connect.closeConnection(conn);
		}
	}

	public static void insertPerson(
        PreparedStatement pstmt, 
		int personId, 
        String firstName, 
        String lastName,
		String gender, 
        String dob, 
        double income) throws SQLException 
    {
		// Set all the input parameters  
		pstmt.setInt(1, personId);
		pstmt.setString(2, firstName);
		pstmt.setString(3, lastName);
		pstmt.setString(4, gender);

		// Set the dob value properly if it is null  
		if (dob == null) {
			pstmt.setNull(5, Types.DATE);
		} 
		else {
			pstmt.setString(5, dob);
		}
		pstmt.setDouble(6, income);

		// Execute the statement  
		pstmt.executeUpdate();
	}

	public static PreparedStatement getInsertSQL(Connection conn) throws SQLException {
		String SQL = "insert into person " + 
		  "(person_id, first_name, last_name, gender, dob, income) values " + 
		  "(?, ?, ?, ?, ?, ?)"; 
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt;
	}
}
