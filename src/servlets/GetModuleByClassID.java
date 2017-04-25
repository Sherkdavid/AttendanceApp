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

import model.Faculty;
import model.Module;

/**
 * Servlet implementation class GetModuleByID
 */
@WebServlet("/GetModuleByClassID")
public class GetModuleByClassID extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public GetModuleByClassID() {
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
		
		String sql = "SELECT *"
				+ "FROM class, module WHERE class.class_id = '" + request.getParameter("class_id") +
				"' AND class.module_id = module.module_id";
		try {
			ResultSet result = query.executeQuery(sql);
			result.next();
			Module mod;
			mod = new Module(result.getString("module_id"), result.getString("title"), result.getString("course_id"), result.getString("faculty_id"));
			sendResult(request,response,mod);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(e);
		}
		// TODO Auto-generated method stub
		disconnect();

	}

}
