package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertIntoClass
 */
@WebServlet("/InsertIntoClass")
public class InsertIntoClass extends QueryServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertIntoClass() {
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
		String result;
		try {
			connect();
		} catch (Exception e1) {
			sendResult(request,response,e1.toString());
		}
		String sql = "INSERT INTO class(class_id,module_id,lecturer_id)"
				+ "VALUES ('"+request.getParameter("class_id")+"','"+
				request.getParameter("module_id")+"','"+request.getParameter("lecturer_id")+"')";
		try {
			query.execute(sql);
			result = "Entry successful";
		} catch (SQLException e) {
			result = "Error parsing entry to database\nDebug : " + e.getSQLState().toString();
			log(e);
		}
		sendResult(request,response,result);
		disconnect();	
	}

}
