package test;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;

import model.Faculty;
import model.Student;
import servlets.*;
import tools.GetServletObject;
import tools.ServletInterfaceController;
public class DatabaseTest {

	public static void main(String[] args) throws Exception {
		ArrayList<Student> results;
		//Insert example 
		ServletInterfaceController req = new ServletInterfaceController("http://localhost:8080/GroupProject/");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("faculty_id", "F002");
		map.put("password", "DavidMurphy2436");
		
		Faculty user = (Faculty) req.sendPostRequest("LoginFaculty",map);
		
		if(user != null)
		{
			System.out.println(user.getName());
		}


	}
}