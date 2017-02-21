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
@WebServlet("/QueryClassListServlet")
public class QueryClassListServlet extends QueryServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryClassListServlet() {
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
		String sql = "Select student_id FROM ClassList WHERE class_id = " + request.getAttribute("class_id") 
					+ " LEFT JOIN Students ON ClassList.class_id=Student.class_id";
		try {
			//Execute query
			ResultSet result = query.executeQuery(sql);
			ArrayList<Student> list = new ArrayList();
			while(result.next())
			{
				list.add(new Student(result.getString("student_id"), result.getString("name"), result.getString("email")));
			}
			result.close();
			ObjectOutputStream obj_out = new ObjectOutputStream(response.getOutputStream());
			obj_out.writeObject(list);
			obj_out.flush();
			obj_out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//parse results and send to clients via output strea
	}

}
