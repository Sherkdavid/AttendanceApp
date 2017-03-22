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

import users.Faculty;

/**
 * Servlet implementation class QueryLecturerByClass
 */
@WebServlet("/QueryLecturerByClass")
public class QueryLecturerByClass extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public QueryLecturerByClass() {
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
		String sql = "SELECT lecturer_id, class_id"
				+ "FROM class WHERE class_id = " + request.getAttribute("class_id") + 
				"LEFT JOIN lecturer ON class.lecturer_id = lecturer.lecturer_id";
		try {
			ResultSet result = query.executeQuery(sql);
			while(result.next())
			{
				System.err.println(result.getString(0) + " : " + result.getString(1) + " : " +result.getString(2) + " : " + result.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}

}
