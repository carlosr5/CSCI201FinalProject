

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
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//    public AddServlet() {
	//        super();
	//        // TODO Auto-generated constructor stub
	//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter(); 
		HttpSession session=request.getSession(false);  
		if(session==null){  
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert(\"Please login to create a recipe\");");
			out.println("window.location.replace('home.html');");
			out.println("</script>");
			out.close(); 
		} 
		else
		{
			String name = request.getParameter("name");
			String imageURL = request.getParameter("img");
			String des = request.getParameter("des");
			String prepTime = request.getParameter("prepTime");
			String cookTime = request.getParameter("cookTime");
			String type = request.getParameter("dishType");
			String ingredients = request.getParameter("ingredients");
			String steps = request.getParameter("steps");

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

				st.executeUpdate("INSERT INTO AllRecipes (Name, Description, Type, PrepTime, CookTime, Ingredients, Steps, ID, image)\nVALUES (" 
						+ "'" + name + "', '" +  des + "', '" 
						+ type + "', '" + prepTime + "', '" + cookTime  
						+ "', '" + ingredients  + "', '" + steps + "', '" + user + "', '" + imageURL + "');");
				out.println("<script type=\"text/javascript\">"); 
				out.println("window.location.replace('home.html');");
				out.println("</script>");
				out.close();
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
		}

	}

}
