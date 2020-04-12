package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface {

	public int signUp(User user) throws ClassNotFoundException {
		String sql = "insert into users2(username, password)VALUES(?,?)";

		int result = 0;
		try
		{
			Connection connection = ConnectionManager.getConnection();
			//Create a Preparedstatement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,user.getUsername());
			preparedStatement.setString(2,user.getPassword());
			System.out.println(preparedStatement);
			//Execute the query or update query
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	public boolean loginUser(User user) {
		boolean status = false;
		try{
			Connection connection = ConnectionManager.getConnection();
		
				//Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement("select * from users2 where username = ? and password = ? ");
		
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}
