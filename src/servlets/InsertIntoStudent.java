package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertIntoStudent
 */
@WebServlet("/InsertIntoStudent")
public class InsertIntoStudent extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see QueryServlet#QueryServlet()
	 */
	public InsertIntoStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubString result;
		String result;
		try {
			connect();
		} catch (Exception e1) {
			sendResult(request,response,e1.toString());
		}
		String sql = "INSERT INTO student(student_id,name,course_id,year,email)"
				+ "VALUES ('"+request.getParameter("student_id")+"','"+request.getParameter("name")+"','"+
				request.getParameter("course_id")+"','"+request.getParameter("year")+"','"+request.getParameter("email")+"')";
		try {
			query.execute(sql);
			result = "Entry successful";
		} catch (SQLException e) {
			result = "Error parsing entry to database\nDebug : " + e.getSQLState().toString();
			sendResult(request,response,e.getSQLState());
			e.printStackTrace();
		}
		sendResult(request,response,result);
		disconnect();
	}

}
