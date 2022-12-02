

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
		PrintWriter out = response.getWriter();
		String docType =
				"<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String des = "";
		String type = "";
		String prepTime = "";
		String cookTime = "";
		String ingred = "";
		String steps = "";
		String img = "";
		String queryName = request.getParameter("dishSelect");
		//String queryName = "Cookies";

		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost/recipemanager?user=" + Globals.user + "&password=" + Globals.pass);
			st = conn.createStatement();

			rs = st.executeQuery("SELECT * FROM recipes WHERE recipes.Name = " + "'" + queryName + "';");	
			rs.next();
			des = rs.getString("Description");
			type = rs.getString("Type");
			prepTime = rs.getString("PrepTime");
			cookTime = rs.getString("CookTime");
			ingred = rs.getString("Ingredients");
			steps = rs.getString("Steps");
			img = rs.getString("image");


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

		out.println(docType +
		         "<html>\n" +
		            "<head><title>" + queryName + "</title></head>\n" +
		            "<link rel=\"stylesheet\" href=\"RecipeDetails.css\">" + 
		            "<body bgcolor = \"#f0f0f0\">\n" +
		            "<header>" + 
			    		"<a id=\"ReSCipe\" href=\"home.html\"><strong>ReSCipe</strong></a>\n" + 
			    		"<a class = \"links\" href=\"logout\">Log out</a>" +
			    		"<a class = \"links\" href=\"register.html\">Register</a>" +
			    		"<a class = \"links\" href=\"login.html\">Login</a>" + 
			    		"<a class = \"links\" href=\"profile\">Profile</a>" + 
			    		"<a class = \"links\" href=\"chatRoom\">Chat Room</a>" + 
			    		"<a class = \"links\" href=\"AddRecipe.html\">Create a Recipe</a>" +
                        "<a class = \"links\" href=\"MyRecipesServlet\">My Recipes</a>" + 
			    		"<a class = \"links\" href=\"allRecipes\">All Recipes</a>" + 
		    		"</header>\n" +
		               "<h1 align = \"center\">" + queryName + "</h1>\n" +
		               "<ul>\n" +
		               "<img src=\"" + img + "\" alt=\"" + queryName + "\"></b>\n" +  
		                  " <li><b>Description: </b>\n"
		                  + des + "\n" +
		                  "  <li><b>Type: </b>\n"
		                  + type + "\n" +
		                  "  <li><b>Prep Time: </b>\n"
		                  + prepTime + " minutes\n" +
		                  "  <li><b>Cook Time: </b> "
		                  + cookTime + " minutes\n" +
		                  "  <li><b>Ingredients: </b>\n"
		                  + ingred + "\n" +
		                  "  <li><b>Steps: </b>\n"
		                  + steps + "\n" +
		            
		               "</ul>\n" +
		            "</body>" + 
		         "</html>");
		  out.flush();
		  out.close();
	}

}





