package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Absence;

/**
 * Servlet implementation class GetAbsenceForStudent
 */
@WebServlet("/GetAbsenceForStudent")
public class GetAbsenceForStudent extends QueryServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see QueryServlet#QueryServlet()
     */
    public GetAbsenceForStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connect();
		String sql = "SELECT * FROM absence"
				+ "WHERE student_id = '" + request.getParameter("student_id")+"'";
		ArrayList<Absence> list = new ArrayList<Absence>();
		try {
			ResultSet result = query.executeQuery(sql);
			while(result.next())
			{
				list.add(new Absence(result.getString("student_id"), result.getString("class_id"), Timestamp.valueOf(result.getString("date"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e)
		{
			log(e);
		}
		sendResult(request,response,list);
		disconnect();
	}

}
