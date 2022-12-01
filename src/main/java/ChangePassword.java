

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//    public AddServlet() {
	//        super();
	//        // TODO Auto-generated constructor stub
	//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter(); 
		String newPass = request.getParameter("password");
		String confirmNewPass = request.getParameter("confirmPassword");
		if(newPass.equals(confirmNewPass))
		{
			System.out.println("here");
			MessageDigest md;
			String hashedPassword;
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(newPass.getBytes());
				// Add password bytes to digest
				
				// Get the hash's bytes
				byte[] bytes = md.digest();
				
				// This bytes[] has bytes in decimal format. Convert it to hexadecimal format
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bytes.length; i++) {
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				
				hashedPassword = sb.toString();
				HttpSession session=request.getSession(false);
				int uid = -1;
				if(session != null)
				{
					uid = (int)session.getAttribute("UID");
				}
				String CHANGE_PASSWORD = "UPDATE users SET hashPass = ? WHERE CustomerID =" + uid;
				Class.forName("com.mysql.jdbc.Driver");
	        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/groupprojtest?user=root&password=root");
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD);
	            preparedStatement.setString(1, hashedPassword);
	            preparedStatement.executeUpdate();
	            out.println("<script>"); 
				out.println("alert(\"Your password has been successfully changed.\");"); 
				out.println("window.location.replace('home.html');");
				out.println("</script>");
				// Get complete hashed password in hex format
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			response.setContentType("text/html;charset=UTF-8");
			out.println("<script>"); 
			out.println("alert(\"Passwords don\'t match. Please try again.\");"); 
			out.println("window.location.replace('changePassword.html');");
			out.println("</script>");
			
		}
		out.close();


	}
}
