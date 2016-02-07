
package  sqlitejava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
You can get a result set from a database using a Statement, a PreparedStatement, or a CallableStatement. In simple
cases, you call executeQuery() method of a Statement object or a PreparedStatement object with a SELECT statement
that will return a ResultSet. Here is a typical way to get a forward-only scrollable result set:

    Connection conn = JDBCUtil.getConnection();
    Statement stmt = conn.createStatement();
    String sql = "select person_id, first_name, last_name, dob, income " +
    "from person";
    // Execute the query to get the result set
    ResultSet rs = stmt.executeQuery(sql);
    // Process the result set using the rs variable

The returned ResultSet from the executeQuery() method is already open, and it is ready to be looped through
to get the associated data. In the beginning, the cursor points before the first row in the result set. You must move the
cursor to a valid row before you can access the column’s values for that row. The next() method of the ResultSet is
used to move the cursor to the next row. When the next() method is called for the first time, it moves the cursor to the
first row in the result set.

It is very important to consider the return value of the next() method. It returns a boolean value. It returns
true if the cursor is positioned to a valid row. Otherwise, it returns false. If you call the next() method on an empty
ResultSet object for the first time, it will return false, because there is no valid row to move to. If the current row
is the last row in the result set, calling the next() method will position the cursor after the last row and it will return
false. A typical snippet of code for processing a forward-only scrollable ResultSet object is as follows:

    ResultSet rs = get a result set object;
    // Move the cursor to the next row by calling the next() method
    while(rs.next()) {
        // Process the current row in rs here
    }
    // Done with the ResultSet

(For SQLite)When a cursor is positioned after the last row in a forward-only scrollable ResultSet object, you cannot do anything
with it, except close it using its close() method. A forward-only scrollable object is like a create-use-and-throw item.
You cannot reopen a ResultSet either. To iterate through the result set data again, you must re-execute the query and
obtain a new ResultSet.

A ResultSet object lets you read the value of a column from its current row using one of its getXxx() method,
where Xxx is the data type of the column. There is one getXxx() method for each Xxx data type supported by JDBC. For
example, to read an int, double, String, Object, and Blob value from a column, you can use the getInt(), getDouble(),
getString(), getObject(), and getBlob() methods of the ResultSet interface, respectively. You must specify the index
or name of the column in the getXxx() method whose value you want to read. The getXxx() methods are overloaded.
One version accepts an int parameter, which lets you use the column index and another version accepts a String
parameter, which lets you use the column label. If the column label is not specified in the query, you can specify the
column name. The first column in the result set has an index of 1. Suppose you have the following ResultSet of a query:
    
    select person_id as "Person ID", first_name, last_name from person

In the ResultSet, the person_id column has a column index of 1, the first_name column has a column index
of 2, and the last_name column has a column index of 3. You have specified Person ID as the column label for the
person_id column. You have not specified the column labels for the first_name and last_name columns. To get the
value of the person_id column, you need to use either getInt(1) or getInt("PERSON ID"). To get the value of the
first_name column, you need to use either getString(2) or getString("first_name").

Tip: Using a column label or name in the getXxx() methods is case-insensitive. That is, you can use
getInt("person id") or getInt("PERSON ID") to get the value of a person_id column. I will use the term “column
name” in this chapter to refer to the column label or name.

It is a matter of personal preference whether to use a column index or a column name in a getXxx() method
of the ResultSet. Sometimes you may not know the name of the columns in advance, such as when the user
passes you a query to execute, and you have to use the data from the result set. When you do not know the column
names, you should use the column indexes. You can get the names of columns in a ResultSet object using the
ResultSetMetaData object.

The main() method calls both of these methods to print the details of the same person id. In this example, you are
not benefiting from precompilation of the PreparedStatement object, because you are calling this method separately
for each person id. If you want to execute the same PreparedStatement with different inputs multiple times, you store
the reference of the PreparedStatement in your program and reuse it.
*/
public class QueryPersonTest {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = Connect.getConnection();

			System.out.println("Using Statement Object...");
			displayPersonUsingStatement(conn, 101);
			displayPersonUsingStatement(conn, 102);

			System.out.println("Using PreparedStatement Object...");
			displayPersonUsingPreparedStatement(conn, 101);
			displayPersonUsingPreparedStatement(conn, 102);

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
		String SQL = "select person_id, first_name, last_name, " + 
		             " gender, dob, income from person " + 
		             " where person_id = " + inputPersonId;

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			printResultSet(rs);
		}
		finally {
			// Closing the Statement closes the associated ResultSet   
			Connect.closeStatement(stmt);
		}
	}

	public static void displayPersonUsingPreparedStatement(Connection conn, int inputPersonId) throws SQLException {

		String SQL = "select person_id, first_name, last_name, " + 
		             " gender, dob, income from person " + 
		             " where person_id = ?";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SQL);

			// Set the IN parameter for person_id  
			pstmt.setInt(1, inputPersonId);

			// Execute the query  
			ResultSet rs = pstmt.executeQuery();
			printResultSet(rs);
		}
		finally {
			// Closing the Statement closes the ResultSet  
			Connect.closeStatement(pstmt);
		}
	}

	public static void printResultSet(ResultSet rs) throws SQLException {
		while (rs.next()) {
			int personId = rs.getInt("person_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String gender = rs.getString("gender");          
			String dob = rs.getString("dob");           
			double income = rs.getDouble("income");   
            /*
            //You can rewrite the code inside the while-loop using the column indexes.          
            int personId = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String gender = rs.getString(4);
            String dob = rs.getDate(5);
            double income = rs.getDouble(6);
            */
			System.out.print("Person ID:" + personId);
			System.out.print(", First Name:" + firstName);
			System.out.print(", Last Name:" + lastName);
			System.out.print(", Gender:" + gender);
            System.out.print(", DOB:" + dob);
            System.out.println(", Income:" + income);			
		}
	}

}
