package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetClassByID
 */
@WebServlet("/GetClassByID")
public class GetClassByID extends QueryServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetClassByID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connect();
		//Build sql query
		String sql = "SELECT * FROM class WHERE class_id = '" + request.getParameter("class_id") + "'"; 
				
				try {
			//Execute query
			ResultSet result = query.executeQuery(sql);
			//parse result set
			result.next();
			model.Class sub =new model.Class(result.getString("class_id"), result.getString("module_id"), result.getString("lecturer_id"),result.getInt("lecture_count"));
			//close result set
			result.close();
			//write result list to output stream
			sendResult(request, response, sub);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
