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
            //System.out.println("Logging Out");
            PrintWriter out = response.getWriter();
            HttpSession session=request.getSession(false);
            if(session != null)
            {
            	session.invalidate();  
            	out.println("<script type=\"text/javascript\">"); 
    			out.println("alert(\"You have successfully logged out.\");");
    			out.println("window.location.replace('home.html');");
    			out.println("</script>");
    			out.close(); 
            }
            else
            {
            	/*
            	request.getRequestDispatcher("home.html").include(request, response);
            	out.println("<h1>No Account Logged In</h1>");
            	out.close();
            	*/
            	out.println("<script type=\"text/javascript\">"); 
    			out.println("alert(\"No account is logged in, so cannot log out.\");");
    			out.println("window.location.replace('home.html');");
    			out.println("</script>");
    			out.close(); 
            }
    }  
}  