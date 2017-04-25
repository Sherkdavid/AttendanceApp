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

import model.Module;

/**
 * Servlet implementation class GetModuleByID
 */
@WebServlet("/GetModuleByID")
public class GetModuleByID extends QueryServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetModuleByID() {
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
		// TODO Auto-generated method stub
connect();
		
		String sql = "SELECT *"
				+ "FROM module WHERE module.faculty_id = '" + request.getParameter("faculty_id")+"'";
		try {
			ResultSet result = query.executeQuery(sql);
			Module mod = new Module(result.getString("module_id"), result.getString("title"), result.getString("course_id"), result.getString("faculty_id"));				
			sendResult(request,response,mod);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(e);
		}
		// TODO Auto-generated method stub
		disconnect();
	

	}

}
