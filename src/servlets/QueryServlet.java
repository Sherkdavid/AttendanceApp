package servlets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import test.DatabaseTest;
import tools.GetServletObject;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	Statement query;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void connect() throws ServletException
    {
    	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    	final String DB_URL = "jdbc:mysql://localhost/db";
    	final String user = "root";
    	final String password = "letmein";
    	
    	try {
			Class.forName(JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(DB_URL, user, password);
			query = conn.createStatement();
			System.err.println("Connection Established");
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void disconnect()
    {
    	try {
			query.close();
			conn.close();
			System.err.println("Connection closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void testQuery(String req)
    {
		try {
			ResultSet set = query.executeQuery(req);
			while(set.next())
			{
				System.out.println(set.getString(1) + " " + set.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void sendResult(HttpServletRequest req, HttpServletResponse res, Object result)
    {
		ObjectOutputStream obj_out;
		try {
			obj_out = new ObjectOutputStream(res.getOutputStream());
			obj_out.writeObject(result);
			obj_out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Sample usage of GetServletObject class
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Build sql query
		String sql = "";
		try {
			//Execute query
			ResultSet result = query.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//parse results and send to clients via output stream
	}

}
