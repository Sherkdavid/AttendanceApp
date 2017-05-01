package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Department;

/**
 * Servlet implementation class GetClassesByModuleID
 */
@WebServlet("/GetClassesByModuleID")
public class GetClassesByModuleID extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public GetClassesByModuleID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connect();
		//Build sql query
		String sql = "SELECT * FROM class WHERE module_id = '" + request.getParameter("module_id") + "'"; 
				
				try {
			//Execute query
			ResultSet result = query.executeQuery(sql);
			ArrayList<model.Class> list = new ArrayList<model.Class>();
			//parse result set
			while(result.next())
			{
				list.add(new model.Class(result.getString("class_id"), result.getString("module_id"), result.getString("lecturer_id"),result.getInt("lecture_count")));
			}
			//close result set
			result.close();
			//write result list to output stream
			sendResult(request, response, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}

}
