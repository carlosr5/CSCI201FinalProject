

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetailsServlet
 */
@WebServlet("/DetailsServlet")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
//    public AddServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      response.setContentType("text/html");
	      /*
	      PrintWriter out = response.getWriter();
	      String title = "Reading Checkbox Data";
	      String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

	      out.println(docType +
	         "<html>\n" +
	            "<head><title>" + title + "</title></head>\n" +
	            "<body bgcolor = \"#f0f0f0\">\n" +
	               "<h1 align = \"center\">" + title + "</h1>\n" +
	               "<ul>\n" +
	                  "  <li><b>Maths Flag : </b>: "
	                  + request.getParameter("maths") + "\n" +
	                  "  <li><b>Physics Flag: </b>: "
	                  + request.getParameter("physics") + "\n" +
	                  "  <li><b>Chemistry Flag: </b>: "
	                  + request.getParameter("chemistry") + "\n" +
	               "</ul>\n" +
	            "</body>" + 
	         "</html>");
		*/
	      
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int user = 1; 
	
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RecipeManager?user=root&password=RamTiger25");
			st = conn.createStatement();
			String queryName = "kwmdqd";
			rs = st.executeQuery("SELECT * from AllRecipes, Users WHERE Recipes.name = " + queryName + " AND Users.ID == Recipes.ID");	
			
			String des = rs.getString("des");
			String type = rs.getString("type");
			String prepTime = rs.getString("prepTime");
			String cookTime = rs.getString("cookTime");
			String ingred = rs.getString("ingredients");
			String steps = rs.getString("steps");
			
			System.out.print("<h2>" + des + "</h2>");
			
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
			
		*/
	}

}





