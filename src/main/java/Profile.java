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
        //request.getRequestDispatcher("account.html").include(request, response);  
        PrintWriter out=response.getWriter();  
          
        HttpSession session=request.getSession(false);  
        if(session!=null){  
	        String uname=(String)session.getAttribute("username");  
	        out.println("<script type=\"text/javascript\">"); 
	        out.println("window.location.replace('account.html');");
			out.println("</script>");
	        out.print("<h1>Hello, "+uname+". Welcome to the profile page. Your UID is: " + session.getAttribute("UID") + "</h1>");
	        //out.print("<br/><a href=\"logout\">Logout</a>");
        }  
        else{  
            out.println("<script type=\"text/javascript\">"); 
			out.println("alert(\"Please login to view your profile\");");
			out.println("window.location.replace('home.html');");
			out.println("</script>");
			out.close(); 
        }  
        out.close();  
    }  
}  