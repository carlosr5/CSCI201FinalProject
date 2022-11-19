import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class UserDao {
	public boolean registerUser(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO users" +
            "  (firstName, lastName, displayname, email, hashPass) VALUES " +
            " ( ?, ?, ?, ?, ?);";

        int result = 0;
        //System.out.println("Received Information");
        //Class.forName("com.mysql.jdbc.Driver");
        String hashedPassword = null;

        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/groupprojtest?user=root&password=root");
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getFirst());
            preparedStatement.setString(2, user.getLast());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getEmail());
            String plaintextPass = user.getPassword();
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(plaintextPass.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
              sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            hashedPassword = sb.toString();
            preparedStatement.setString(5, hashedPassword);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            System.out.println(result);

        }
        catch (SQLIntegrityConstraintViolationException dupe)
        {
        	return false;
        }
        catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return true;
    }
	public ArrayList<User> loginUser(User user) throws ClassNotFoundException {
        String FIND_USERS_SQL = "SELECT * FROM users" +
            "  WHERE email=? AND hashPass=? ";

        //int result = 0;
        //System.out.println("Received Information");
        //Class.forName("com.mysql.jdbc.Driver");
        String hashedPassword = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/groupprojtest?user=root&password=root");
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERS_SQL);
            preparedStatement.setString(1, user.getEmail());
            String plaintextPass = user.getPassword();
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(plaintextPass.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
              sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            hashedPassword = sb.toString();
            preparedStatement.setString(2, hashedPassword);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<User> matchUsers = new ArrayList<User>();
            while(rs.next())
            {
            	String first = rs.getString("firstName");
            	String last = rs.getString("lastName");
            	String uname = rs.getString("displayname");
            	String email = rs.getString("email");
            	String hashPass = rs.getString("hashPass");
            	User temp = new User(first, last, uname,email,hashPass);
            	temp.setUID(Integer.parseInt(rs.getString("userID")));
            	matchUsers.add(temp);
            }
            
            System.out.println(matchUsers.size());
            return matchUsers;

        }
        catch (SQLIntegrityConstraintViolationException dupe)
        {
        	return new ArrayList<User>();
        }
        catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ArrayList<User>();
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
