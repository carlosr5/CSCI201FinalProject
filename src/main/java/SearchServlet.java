

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

import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DetailsServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
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


		String searchName = request.getParameter("name");
		String searchType = request.getParameter("recipeType");
		if(searchType != null)
		{
			searchType = searchType.toUpperCase();
		}
		System.out.println(searchName + " | " + searchType);
		String rec_ing = "";
		String img = "";
		HttpSession session = request.getSession(false);
		int ID = -1;
		if(session != null)
		{
			ID = (int)session.getAttribute("UID");
		}

		String result = new String(docType +
		         "<html>\n" +
		            "<head><title>My Recipes</title></head>\n" +
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
		    		"</header>\n");


		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost/recipemanager?user=" + Globals.user + "&password=" + Globals.pass);
			st = conn.createStatement();

			//if (ID != null && ID.length() > 0) { }

			if(searchName == null)
			{
				rs = st.executeQuery("SELECT * FROM recipes WHERE Type LIKE " + "'%" + searchType + "%';");
			}
			else
			{				
				rs = st.executeQuery("SELECT * FROM recipes WHERE Name LIKE " + "'%" + searchName + "%';");					
			}
			while (rs.next()) {
				rec_ing = rs.getString("image");
				String name = rs.getString("Name");
				result += "<h1 align = \"center\">" + name + "</h1>\n" + "<ul>\n" +
						   "<img src=\"" + rec_ing + "\" alt=\"" + name + "\"></b>\n" +  		            
						   "</ul>\n";

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





