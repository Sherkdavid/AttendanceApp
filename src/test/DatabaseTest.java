package test;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;

import com.mysql.fabric.Response;

import servlets.*;
import tools.GetServletObject;
import users.Student;
public class DatabaseTest {

	public static void main(String[] args) {
		ArrayList<Student> results;
		try {
			//Must cast for expected object
			GetServletObject req = new GetServletObject("http://138.68.147.88:8080/GroupProject/");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("class_id", "AOOP1");
			boolean result;
			result = (boolean) req.sendPostRequest("TestServlet",map);
			System.out.print(result);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
