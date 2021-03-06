package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Department;

/**
 * Servlet implementation class GetDepartmentByName
 */
@WebServlet("/GetDepartmentByName")
public class GetDepartmentByName extends QueryServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDepartmentByName() {
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
		connect();
		//Build sql query
		String sql = "SELECT * FROM department WHERE name= '" + request.getParameter("name") + "'"; 
				
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
