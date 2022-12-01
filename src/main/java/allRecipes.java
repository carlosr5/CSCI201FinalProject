
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
    // post
    // when I click an item, make me a like "take me there" button which will go to
    // Adit's servlet
    // have recipe name, picture, and then have a button which has a link to adit's
    // servlet where button carries information which is the name of the dish

     	final long serialVersionUID = 1L;
			response.setContentType("text/html");
			

    	       "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

			Connection conn 

    
			String img = "";
			String name = "";
			String desc = "";
			String result = new String(docType +
			         "<html>\n" +
			            "<head><title>My Recipes</title></head>\n" +
			            "<link rel=\"stylesheet\" href=\"allRecipes.css\">" + 
			            "<body bgcolor = \"#f0f0f0\">\n" +
			            "<header>" + 
				    		"<a id=\"ReSCipe\" href=\"home.html\"><strong>ReSCipe</strong></a>\n" + 
				    		"<a class = \"links\" href=\"loggedout.html\">Log out</a>" +
				    		"<a class = \"links\" href=\"register.html\">Register</a>" +
				    		"<a class = \"links\" href=\"login.html\">Login</a>" + 
				    		"<a class = \"links\" href=\"account.html\">Profile</a>" + 
				    		"<a class = \"links\" href=\"chat.html\">Chat Room</a>" + 
				    		"<a class = \"links\" href=\"AddRecipe.html\">Create a Recipe</a>" + 
			    		"</header>\n");

			try {
    
    
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = DriverManager.getConnection("jdbc:mysql://localhost/allRecipes?user=root&password=Eiger123!!");
				st = conn.createStatement();
				
				rs = st.executeQuery("SELECT * FROM AllRecipes ;" );
				while(rs.next()) {
					img = rs.getString("image");
					name = rs.getString("name");
					desc = rs.getString("description");
					
					result += "<h1 align = \"center\">" + desc + "</h1>\n" +
							   "<ul>\n" +
							   "<img src=\"" + img + "\" alt=\"" + name + "\"></b>\n" +  		            
							   "</ul>\n" +  "<button id=\"recipe_button\" onclick=\"https://w3docs.com\">" + 
							      "View Recipe" +
							     " </button> ";
						
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

