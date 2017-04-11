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

import model.Class;

/**
 * Servlet implementation class GetClassByLecturer
 */
@WebServlet("/GetClassByLecturer")
public class GetClassByLecturer extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public GetClassByLecturer() {
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
		connect();
		//Build sql query
		String sql = "SELECT * FROM class WHERE lecturer_id = '" + request.getParameter("lecturer_id") + "'"; 
				
				try {
			//Execute query
			ResultSet result = query.executeQuery(sql);
			ArrayList<model.Class> list = new ArrayList<model.Class>();
			//parse result set
			while(result.next())
			{
				list.add(new model.Class(result.getString("class_id"), result.getString("module_id"), result.getString("lecturer_id")));
			}
			for(model.Class c: list)
			{
				System.out.println(c.getClassId());
			}
			//close result set
			result.close();
			//write result list to output stream
			sendResult(request, response, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
