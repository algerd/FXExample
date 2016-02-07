
package  sqlitejava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
There is no method in the ResultSet interface that returns the number of rows in the result set.
The ResultSet interface contains a getRow() method that returns the current row number in the ResultSet. It
returns zero if there is no current row, such as when the cursor is before the first row or after the last row. The support
for the getRow() method is optional in a forward-only scrollable ResultSet. You can say that the getRow() method is
of no help in determining the number of rows in a ResultSet object. You will need to apply some custom logic to get
the number of rows in a result set. The following are some of the methods you can use to get the number of rows in a
result set. None of them are without disadvantages.
*/
public class CountRowTest {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = Connect.getConnection();
            System.out.println("countRowFromResultSet = " + countRowFromResultSet(conn));
            System.out.println("counrRowFromDb = " + countRowFromDb(conn));
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());		
		}
		finally {
			Connect.closeConnection(conn);
		}
	}
    
    /*
    This method applies a logic that loops through all rows using the next() method after getting the ResultSet. It
    maintains a counter variable, which is incremented by one for each loop-iteration. After exiting the loop, the counter
    variable contains the number of rows in the ResultSet.
    
    If you need the number of rows in a result set before you process its rows, this logic will force you to get the result
    set twice: once for getting the number of rows and once for processing the rows. Between the time when you get the
    first result set and when you get the second result set, the data in the database might change, which will make the row
    count from the first execution invalid. This method is foolproof only if you need the number of rows in the result set
    after you have looped through all the rows.
    */
    public static int countRowFromResultSet(Connection conn) throws SQLException {        
        String SQL = "select * from person";      
        Statement stmt = null;
        ResultSet rs = null;
        int rowCount = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);          
            while(rs.next()) {
                rowCount++;
            }
		}
		finally {
			// Closing the Statement closes the associated ResultSet   
			Connect.closeStatement(stmt);
		}              
        return rowCount;
    }
    
    /*
    This method executes a separate query to get the number of rows in a result set. Suppose you want to know the
    number of rows returned in a result set by executing a query.
    
    The value for the first column of the first row in the result set will give you the number of rows returned from your
    main query. However, this method suffers from the same drawback that rows in the database may change between the
    executions of the two queries.
    */
    public static int countRowFromDb(Connection conn) throws SQLException {
        String SQL = "select count(*) from person";
        Statement stmt = null;
        ResultSet rs = null;
        int rowCount = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
		}
		finally {
			// Closing the Statement closes the associated ResultSet   
			Connect.closeStatement(stmt);
		}       
        return rowCount;
    }

}
