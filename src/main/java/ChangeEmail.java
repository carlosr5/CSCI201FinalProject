

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
@WebServlet("/ChangeEmail")
public class ChangeEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//    public AddServlet() {
	//        super();
	//        // TODO Auto-generated constructor stub
	//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter(); 
		String newEmail= request.getParameter("email");
		String confirmNewEmail= request.getParameter("confirmEmail");
		if(newEmail.equals(confirmNewEmail))
		{
				HttpSession session=request.getSession(false);
				int uid = -1;
				if(session != null)
				{
					uid = (int)session.getAttribute("UID");
				}
				String CHANGE_PASSWORD = "UPDATE users SET hashPass = ? WHERE CustomerID =" + uid;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/groupprojtest?user=root&password=root");
					PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD);
					preparedStatement.setString(1, newEmail);
					preparedStatement.executeUpdate();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            // Step 2:Create a statement using connection object
	            out.println("<script>"); 
				out.println("alert(\"Your password has been successfully changed.\");"); 
				out.println("window.location.replace('home.html');");
				out.println("</script>");
				// Get complete hashed password in hex format
			
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
