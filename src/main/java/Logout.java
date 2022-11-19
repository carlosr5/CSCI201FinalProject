import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
@WebServlet("/logout")
public class Logout extends HttpServlet {  
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            //response.setContentType("text/html");  
            //request.getRequestDispatcher("link.html").include(request, response);  
            System.out.println("Logging Out");
            HttpSession session=request.getSession(false);
            if(session != null)
            {
            	session.invalidate();  
            	response.sendRedirect("loggedout.html");
            }
            else
            {
            	PrintWriter out = response.getWriter();
            	/*
            	request.getRequestDispatcher("home.html").include(request, response);
            	out.println("<h1>No Account Logged In</h1>");
            	out.close();
            	*/
            	response.sendRedirect("home.html");
            	out.println("<h1>Please login first!</h1>");
            }
    }  
}  