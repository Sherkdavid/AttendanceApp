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

import users.Student;

/**
 * Servlet implementation class QueryClassListServlet
 */
@WebServlet("/QueryStudentsByClassID")
public class QueryStudentsByClassID extends QueryServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryStudentsByClassID() {
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
		//Build sql query
		String sql = "Select student.student_id, student.name,student.course_id,student.acc_year,student.email,classlist.class_id FROM classlist " 
					+ "LEFT JOIN student ON classlist.student_id=student.student_id AND classlist.class_id ='"+request.getParameter("class_id")+"'";
		try {
			//Execute query
			ResultSet result = query.executeQuery(sql);
			ArrayList<Student> list = new ArrayList();
			//parse result set
			while(result.next())
			{
				list.add(new Student(result.getString("student_id"), result.getString("name"), result.getString("course_id"),result.getString("email"),result.getInt("acc_year")));
			}
			//close result set
			result.close();
			//write result list to output stream
			ObjectOutputStream obj_out = new ObjectOutputStream(response.getOutputStream());
			obj_out.writeObject(list);
			obj_out.close();
			//obj_out.close();
			//Close output stream
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
		}
		//close database connection
		disconnect();
	}

}
