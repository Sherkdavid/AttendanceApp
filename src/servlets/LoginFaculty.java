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

import model.Faculty;
import model.Student;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginFaculty")
public class LoginFaculty extends QueryServlet {
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public LoginFaculty() {
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
		//Build sql query
		String sql = "Select * FROM faculty_login " 
					+ "LEFT JOIN faculty ON faculty_login.faculty_id ='"+request.getParameter("faculty_id")+"' AND faculty.faculty_id=faculty_login.faculty_id";
		try {
			//Execute query
			ResultSet result = query.executeQuery(sql);
			Faculty user = null;
			//parse result set
			while(result.next())
			{
				if(result.getString("faculty_id").equals(request.getParameter("faculty_id")))
					if(result.getString("password").equals(request.getParameter("password")))
					user = new Faculty(result.getString("faculty_id"),result.getString("name"),result.getString("email"), result.getString("department"));
			}
			//close result set
			result.close();
			//write result list to output stream
			sendResult(request, response, user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//close database connection
		disconnect();
	}

}
