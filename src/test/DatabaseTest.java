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

	public static void main(String[] args) {
		ArrayList<Student> results;
		//Insert example 
		GetServletObject req = new GetServletObject("http://138.68.147.88:8080/GroupProject/");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("class_id", "COM:001:01");
		Object result = null;
		try {
			result = req.sendPostRequest("QueryFacultyByClass",map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Faculty> list = (ArrayList<Faculty>) result;
		for(Faculty s:list)
		{
			System.err.println(s.getName());
		}


	}
}