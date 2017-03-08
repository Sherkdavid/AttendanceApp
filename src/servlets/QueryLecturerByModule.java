package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryLecturerByModule
 */
@WebServlet("/QueryLecturerByModule")
public class QueryLecturerByModule extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public QueryLecturerByModule() {
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
		String sql = "Select module_id, lecturer_id"
				+ "FROM module"
				+ "WHERE module_id = " + request.getAttribute("module_id")
				+ "LEFT JOIN lecturer ON module.lecturer_id=lecturer.lecturer_id";
		try {
			ResultSet result = query.executeQuery(sql);
			while(result.next())
			{
				System.err.println(result.getString(0) + " : " + result.getString(1) + " : " +result.getString(2) + " : " + result.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		disconnect();
	}

}
