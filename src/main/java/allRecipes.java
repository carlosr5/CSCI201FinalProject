
import java.sql.*;


import java.io.IOException;  
import java.io.PrintWriter;  
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

@WebServlet("/allRecipes")
public class allRecipes extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//post 
	//when I click an item, make me a like "take me there" button which will go to Adit's servlet
	//have recipe name, picture, and then have a button which has a link to adit's servlet where button carries information which is the name of the dish
	

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 	final long serialVersionUID = 1L;
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter(); 
			 String docType =
				       "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			
			String img = "";
			String name = "";
//			String desc = "";
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
                    "<a class = \"links\" href=\"allRecipes\">All Recipes</a>" + 
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
				
				rs = st.executeQuery("SELECT * FROM recipes ;" );
				int counter = 0; 
				while(rs.next()) {
					img = rs.getString("image");
					name = rs.getString("name");
					counter++;
//					desc = rs.getString("description");
					//will all be wrapped in the form and then from there you make the button and 
					//make a form where input type text is just a button
					//
					result += "<h1 align = \"center\">" + name + "</h1>\n" +
							   "<ul>\n" +
							   "<img src=\"" + img + "\" alt=\"" + name + "\"></b>\n" +  		            
							   "</ul>\n" +  "<form name=\"DetailsServlet\" method=\"GET\" action=\"DetailsServlet\">\n"
							   		+ "<input type=\"radio\" id=\"valueOf(counter)\" name=\"dishSelect\" value=\"" + name + "\">\n"
							   		+ "<label for=\"valueOf(counter)\">" + name + "</label><br>";
							;
					
					
					
					
				}
				//the link should be able to relay the name of the recipe being selected to the servlet
	
			
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
			
			result += "<input type=\"submit\" name=\"submit\" value=\"Submit\" />\n" + "</form>\n" + "</body>" + 
			         "</html>";
			out.print(result);
			 out.flush();
			 out.close();
				
		
		}

	}

