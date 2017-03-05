package test;
import javax.servlet.ServletException;

import servlets.*;
public class DatabaseTest {

	public static void main(String[] args) throws ServletException {
		// TODO Auto-generated method stub
		QueryServlet qs = new QueryServlet();
		qs.connect();
		qs.disconnect();
	}

}
