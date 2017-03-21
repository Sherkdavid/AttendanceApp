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

import users.Faculty;

/**
 * Servlet implementation class QueryFacultyByModule
 */
@WebServlet("/QueryFacultyByModule")
public class QueryFacultyByModule extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public QueryFacultyByModule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connect();
		String sql = "SELECT module.faculty_id, faculty.id, faculty.name, faculty.email, faculty.department"
				+ "FROM module"
				+ "LEFT JOIN faculty ON module.id =" + request.getParameter("module_id")
				+ "AND module.faculty_id=faculty.id";
		try {
			ResultSet result = query.executeQuery(sql);
			ArrayList<Faculty> list = new ArrayList<Faculty>();
			while(result.next())
			{
				list.add(new Faculty(result.getString("id"),result.getString("name"),result.getString("email"),result.getString("department")));
			}
			sendResult(request,response,list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		disconnect();
	}

}
