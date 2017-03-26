package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertIntoModule
 */
@WebServlet("/InsertIntoModule")
public class InsertIntoModule extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public InsertIntoModule() {
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
		String result;
		connect();
		String sql = "INSERT INTO module(module_id,title,course_id,faculty_id)"
				+ "VALUES ('"+request.getParameter("module_id")+"','"+request.getParameter("title")+"','"+
				request.getParameter("course_id")+"','"+request.getParameter("faculty_id")+"')";
		try {
			query.execute(sql);
			result = "Entry successful";
		} catch (SQLException e) {
			result = "Error parsing entry to database\nDebug : " + e.getSQLState().toString();
			e.printStackTrace();
		}
		sendResult(request,response,result);
		disconnect();
		}

}
