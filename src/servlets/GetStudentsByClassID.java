package servlets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import model.Student;

/**
 * Servlet implementation class QueryClassListServlet
 */
@WebServlet("/GetStudentsByClassID")
public class GetStudentsByClassID extends QueryServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetStudentsByClassID() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connect();
		//Build sql query
		String sql = "Select student.student_id, student.name,student.course_id,student.year,student.email,enrolment.class_id FROM student " 
					+ "LEFT JOIN enrolment ON enrolment.class_id ='"+request.getParameter("class_id")+"' AND enrolment.student_id=student.student_id";
		try {
			//Execute query
			ResultSet result = query.executeQuery(sql);
			ArrayList<Student> list = new ArrayList<Student>();
			//parse result set
			while(result.next())
			{
				list.add(new Student(result.getString("student_id"), result.getString("name"), result.getString("course_id"),result.getString("email"),result.getInt("year")));
			}
			//close result set
			result.close();
			//write result list to output stream
			sendResult(request, response, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//close database connection
		//disconnect();
	}
}
