

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
<<<<<<< HEAD
=======
import javax.servlet.http.HttpSession;
>>>>>>> 16429356c0b38e61414712e46b5316166791b3cc

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
		
<<<<<<< HEAD
		String name = "";
		String rec_ing = "";
=======
		String img = "";
		String name = "";
		HttpSession session = request.getSession(false);
		int ID = -1;
		if(session != null)
		{
			ID = (int)session.getAttribute("UID");
		}
>>>>>>> 16429356c0b38e61414712e46b5316166791b3cc
		
	    String result = new String(docType +
		         "<html>\n" +
		            "<head><title>My Recipes</title></head>\n" +
<<<<<<< HEAD
		            "<link rel=\"stylesheet\" href=\"general_stylesheet.css\">" + 
=======
		            "<link rel=\"stylesheet\" href=\"RecipeDetails.css\">" + 
>>>>>>> 16429356c0b38e61414712e46b5316166791b3cc
		            "<body bgcolor = \"#f0f0f0\">\n" +
		            "<header>" + 
			    		"<a id=\"ReSCipe\" href=\"home.html\"><strong>ReSCipe</strong></a>\n" + 
			    		"<a class = \"links\" href=\"loggedout.html\">Log out</a>" +
			    		"<a class = \"links\" href=\"register.html\">Register</a>" +
			    		"<a class = \"links\" href=\"login.html\">Login</a>" + 
<<<<<<< HEAD
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
	    
=======
			    		"<a class = \"links\" href=\"account.html\">Profile</a>" + 
			    		"<a class = \"links\" href=\"chat.html\">Chat Room</a>" + 
			    		"<a class = \"links\" href=\"AddRecipe.html\">Create a Recipe</a>" + 
		    		"</header>\n");
>>>>>>> 16429356c0b38e61414712e46b5316166791b3cc
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
<<<<<<< HEAD
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RecipeManager?user=root&password=RamTiger25");
=======
			conn = DriverManager.getConnection("jdbc:mysql://localhost/recipemanager?user=" + Globals.user + "&password=" + Globals.pass);
>>>>>>> 16429356c0b38e61414712e46b5316166791b3cc
			st = conn.createStatement();
				
			//if (ID != null && ID.length() > 0) { }
				
<<<<<<< HEAD
			
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
=======
			if(ID == -1)
			{
				rs = st.executeQuery("SELECT * FROM recipes ;");	
			}
			else
			{				
				rs = st.executeQuery("SELECT * FROM recipes WHERE recipes.ID = " + "'" + ID + "';");	
			}
			while (rs.next()) {
				img = rs.getString("image");
				name = rs.getString("name");
				result +=  "<h1 align = \"center\">" + name + "</h1>\n" +
					   "<ul>\n" +
					   "<img src=\"" + img + "\" alt=\"" + name + "\"></b>\n" +  		            
					   "</ul>\n"; 
>>>>>>> 16429356c0b38e61414712e46b5316166791b3cc
				
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





