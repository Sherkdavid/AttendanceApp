package test;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;

import servlets.*;
import users.Student;
public class DatabaseTest {

	public static void main(String[] args) {
		GetServletObject req = new GetServletObject("http://localhost:8080/GroupProject/");
		ArrayList<Student> results;
		try {
			//Must cast for expected object
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("class_id", "AOOP1");
			results = (ArrayList<Student>) req.sendPostRequest("QueryStudentsByClassID",map);
			for(Student o:results)
			{
				System.err.println(o.toString());
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
