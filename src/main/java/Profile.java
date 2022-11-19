import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
@WebServlet("/profile")
public class Profile extends HttpServlet {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                      throws ServletException, IOException {
    	//System.out.println("got request?");
    	//response.sendRedirect("account.html");
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        request.getRequestDispatcher("account.html").include(request, response);  
          
        HttpSession session=request.getSession(false);  
        if(session!=null){  
	        String uname=(String)session.getAttribute("username");  
	          
	        out.print("Hello, "+uname+". Welcome to the profile page. Your UID is: " + session.getAttribute("UID"));
	        //out.print("<br/><a href=\"logout\">Logout</a>");
        }  
        else{  
            request.getRequestDispatcher("home.html").include(request, response);  
            out.print("<h1>Please login first!</h1>");  
        }  
        out.close();  
    }  
}  