
package application;

import java.sql.*;

public class LoginModel {

    private Connection connector;
    
    public LoginModel() {
        connector = SqliteConnection.connector();
        if (connector == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
    }
    
    public boolean isDbConnected() {
        try {
            return !connector.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean isLogin(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from employee where username = ? and password = ?";
        try {
            preparedStatement = connector.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? true : false;
            
        } catch (Exception e) {
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }       
    }
       
}
