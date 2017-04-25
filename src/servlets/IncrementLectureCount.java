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

import model.Student;

/**
 * Servlet implementation class IncrementLectureCount
 */
@WebServlet("/IncrementLectureCount")
public class IncrementLectureCount extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public IncrementLectureCount() {
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
		String sql ="UPDATE class SET lecture_count = lecture_count +1 WHERE class_id ='" +request.getParameter("class_id")+"'"; 	
			try {
			//Execute query
			query.execute(sql);
			//parse result set
			//write result list to output stream
			sendResult(request, response, true);
		} catch (SQLException e) {
			sendResult(request,response,false);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//close database connection
		disconnect();
	}

}
