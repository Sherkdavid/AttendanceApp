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

import model.Module;

/**
 * Servlet implementation class GetModulesByDepartment
 */
@WebServlet("/GetModulesByDepartment")
public class GetModulesByDepartment extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public GetModulesByDepartment() {
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
		// TODO Auto-generated method stub
				connect();
				
				String sql = "SELECT *"
						+ "FROM module WHERE module.department = '" + request.getParameter("department")+"'";
				try {
					ResultSet result = query.executeQuery(sql);
					ArrayList<Module> list = new ArrayList<Module>();
					while(result.next())
					{
						list.add(new Module(result.getString("module_id"), result.getString("title"), result.getString("department"), result.getString("faculty_id")));				
					}
					sendResult(request,response,list);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log(e);
				}
				// TODO Auto-generated method stub
				disconnect();
			
	}

}
