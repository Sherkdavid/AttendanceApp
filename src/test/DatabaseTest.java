package test;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;

import com.mysql.fabric.Response;

import model.Faculty;
import model.Student;
import servlets.*;
import tools.GetServletObject;
public class DatabaseTest {

	public static void main(String[] args) {
		ArrayList<Student> results;
		try {
	
			//Insert example 
			GetServletObject req = new GetServletObject("http://138.68.147.88:8080/GroupProject/");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("class_id", "COM:001:01");
			ArrayList<Faculty> list = (ArrayList<Faculty>) req.sendPostRequest("QueryFacultyByClass",map);
			for(Faculty s:list)
			{
				System.err.println(s.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
