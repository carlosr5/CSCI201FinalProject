

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
@WebServlet("/MyRecipesServlet")
public class MyRecipesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
//    public AddServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		response.setContentType("text/html");
	      
	    PrintWriter out = response.getWriter();
	    String docType =
			       "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

	    
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String name = "";
		String rec_ing = "";
		
	    String result = new String(docType +
		         "<html>\n" +
		            "<head><title>My Recipes</title></head>\n" +
		            "<link rel=\"stylesheet\" href=\"general_stylesheet.css\">" + 
		            "<body bgcolor = \"#f0f0f0\">\n" +
		            "<header>" + 
			    		"<a id=\"ReSCipe\" href=\"home.html\"><strong>ReSCipe</strong></a>\n" + 
			    		"<a class = \"links\" href=\"loggedout.html\">Log out</a>" +
			    		"<a class = \"links\" href=\"register.html\">Register</a>" +
			    		"<a class = \"links\" href=\"login.html\">Login</a>" + 
			    		"<a class = \"links\" href=\"userprofile.html\">Profile</a>" + 
			    		"<a class = \"links\" href=\"chat.html\">Chat Room</a>" + 
			    		"<a class = \"links\" href=\"AddRecipe.html\">Create a Recipe</a>" + 
		    		"</header>\n" +
						"<br><br><br><br><br><br>\n" +
						"<form method=\"post \" name=\"frm\" action=\"MyRecipesServlet\">\n" +
						"<table border=\"0\" width=\"300\" align=\"center\" bgcolor=\"#CDFFFF\">\n" +
						"<tr><td colspan=2 style=\"font-size:12pt\" align=\"center\">\n" + 
						"<h3>Search Recipe</h3></td></tr>\n" + 
						"<tr><td ><b>Recipe Name</b></td>\n" +
						"<td>: <input  type=\"text\" name=\"name\" id=\"name\">\n" +
						"</td></tr>\n" + 
						"<tr><td ><b>Ingredients</b></td> \n" +
						"<td>: <input  type=\"text\" name=\"rec_ing\" id=\"rec_ing\">\n" +
						"</td></tr>\n" + 
						"<tr><td colspan=2 align=\"center\">\n" +
						"<input  type=\"submit\" name=\"submit\" value=\"Search\"></td></tr>\n" +
						"</table> \n" +
						"</form>\n"
			    		
	
	    		);
	    
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RecipeManager?user=root&password=RamTiger25");
			st = conn.createStatement();
				
			//if (ID != null && ID.length() > 0) { }
				
			
			rs = st.executeQuery("SELECT * FROM AllRecipes WHERE AllRecipes.name = " + "'" + name + "';");	
			while (rs.next()) {
				rec_ing = rs.getString("rec_ing");
				name = rs.getString("name");
				result +=  "<h1 align = \"center\">" + name + "</h1>\n";
				
			}
			
			rs = st.executeQuery("SELECT * FROM AllRecipes WHERE AllRecipes.ingredients = " + "'" + rec_ing + "';");	
			while (rs.next()) {
				rec_ing = rs.getString("rec_ing");
				name = rs.getString("name");
				result +=  "<h1 align = \"center\">" + name + "</h1>\n";
				
			}
		   
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
      
		result += "</body>" + 
	         "</html>";
	    out.print(result);
		  out.flush();
		  out.close();
		
	}

}





