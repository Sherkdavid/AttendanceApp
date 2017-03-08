package test;
import javax.servlet.ServletException;

import servlets.*;
public class DatabaseTest {

	public static void main(String[] args) throws ServletException {
		// TODO Auto-generated method stub
		//INSERT INTO lecturer(lecturer_id, lecturerName, lecturerEmail) VALUES ('CITF005', 'John Adams', 'jAdams@cit.ie');
		QueryServlet qs = new QueryServlet();
		qs.connect();
		//qs.testInsert("INSERT INTO lecturer(lecturer_id, lecturerName, lecturerEmail) VALUES ('CITF005', 'John Adams', 'jAdams@cit.ie')");
		qs.testQuery("SELECT * FROM lecturer");
		qs.disconnect();
	}

}
