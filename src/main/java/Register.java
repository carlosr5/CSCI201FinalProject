import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String uname=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String confirmPass = request.getParameter("confirmpassword");
		System.out.println(password + " | " + confirmPass);
		if(!password.equals(confirmPass))
		{
			System.out.println("here");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			//response.sendRedirect("register.html");
			out.println("<script>"); 
			out.println("alert(\"Passwords don\'t match. Please try again.\");"); 
			out.println("window.location.replace('register.html');");
			out.println("</script>");
			//out.flush();
			//out.close();
		}
		else
		{
			User user = new User(firstName, lastName,uname, email, password);
			UserDao udao=new UserDao();
			boolean result = true;
			try {
				result = udao.registerUser(user);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result)
			{
				response.sendRedirect("registered.html");
			}
			else
			{
				PrintWriter out = response.getWriter(); 
				//response.sendRedirect("register.html");
				out.println("<script type=\"text/javascript\">"); 
				out.println("alert(\"An account with this email already exists.\");"); 
				out.println("window.location.replace('register.html');");
				out.println("</script>");
				//out.close();
			}			
		}
	}
}
