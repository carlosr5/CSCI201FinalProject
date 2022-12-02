import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user = new User(email, password);
		UserDao udao=new UserDao();
		ArrayList<User> matchUsers = null;
		boolean result;
		try {
			matchUsers = udao.loginUser(user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = matchUsers.size() != 0;
		if(result)
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", matchUsers.get(0).getUsername());
			session.setAttribute("firstName", matchUsers.get(0).getFirst());
			session.setAttribute("lastName", matchUsers.get(0).getLast());
			session.setAttribute("UID", matchUsers.get(0).getUID());
			
			response.sendRedirect("profile");
		}
		else
		{
			PrintWriter out = response.getWriter(); 
			//response.sendRedirect("register.html");
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert(\"These credentials don\'t match any accounts in our system. Please verify that the email and password entered are correct.\");"); 
			out.println("window.location.replace('login.html');");
			out.println("</script>");
			out.close();
		}			
	}
	 
}
