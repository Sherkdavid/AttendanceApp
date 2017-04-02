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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result;
		try {
			connect();
		} catch (Exception e1) {
			sendResult(request,response,e1.toString());
		}
		String sql = "INSERT INTO faculty(faculty_id,name,email,department)"
				+ "VALUES ('"+request.getParameter("faculty_id")+"','"+request.getParameter("name")+"','"+
				request.getParameter("email")+"','"+request.getParameter("department")+"')";
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
