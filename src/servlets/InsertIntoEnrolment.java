package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertIntoEnrolment
 */
@WebServlet("/InsertIntoEnrolment")
public class InsertIntoEnrolment extends QueryServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertIntoEnrolment() {
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
		String result;
		try {
			connect();
		} catch (Exception e1) {
			sendResult(request,response,e1.toString());
		}
		String sql = "INSERT INTO enrolment(student_id,class_id,enrol_date)"
				+ "VALUES ('"+request.getParameter("student_id")+"','"+request.getParameter("class_id")+"','"+
				Timestamp.valueOf(request.getParameter("enrol_date"))+"')";
		try {
			query.execute(sql);
			result = "Entry successful";
		} catch (SQLException e) {
			result = "Error parsing entry to database\nDebug : " + e.getSQLState().toString();
			log(e);
		}
		sendResult(request,response,result);
		disconnect();
	}

}
