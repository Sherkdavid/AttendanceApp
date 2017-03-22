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
	
			//Insert example 
			GetServletObject req = new GetServletObject("http://localhost:8080/GroupProject/");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("faculty_id", "R0012020");
			map.put("name", "David Murphy");
			map.put("email", "dmurphy10@mycit.ie");
			map.put("department", "Computing");
			req.sendPostRequest("InsertIntoFaculty",map);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
