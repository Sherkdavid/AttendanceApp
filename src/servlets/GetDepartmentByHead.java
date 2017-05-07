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

import model.Department;

/**
 * Servlet implementation class GetDepartments
 */
@WebServlet("/GetDepartmentByHead")
public class GetDepartmentByHead extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public GetDepartmentByHead() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connect();
		//Build sql query
		String sql = "SELECT * FROM department WHERE h_o_d = '" + request.getParameter("faculty_id") + "'"; 
				
				try {
			//Execute query
			ResultSet result = query.executeQuery(sql);
			ArrayList<Department> list = new ArrayList<Department>();
			//parse result set
			while(result.next())
			{
				list.add(new Department(result.getString("name"), result.getString("h_o_d")));
			}
			//close result set
			result.close();
			//write result list to output stream
			sendResult(request, response, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}

}
