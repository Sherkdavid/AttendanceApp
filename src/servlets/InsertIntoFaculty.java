package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertIntoFaculty
 */
@WebServlet("/InsertIntoFaculty")
public class InsertIntoFaculty extends QueryServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertIntoFaculty() {
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
		boolean result;
		connect();
		String sql = "INSERT INTO faculty(faculty_id,name,email,department)"
				+ "VALUES ('"+request.getParameter("faculty_id")+"','"+request.getParameter("name")+"','"+
				request.getParameter("email")+"','"+request.getParameter("department")+"')";
		try {
			result = query.execute(sql);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		sendResult(request,response,result);
		disconnect();
	}

}
