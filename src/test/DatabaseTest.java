package test;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import model.Faculty;
import model.Module;
import model.Student;
import servlets.*;
import tools.GetServletObject;
import tools.ServletInterfaceController;
public class DatabaseTest {

	public static void main(String[] args) throws Exception {
		ArrayList<Student> results;
		//Insert example 
		ServletInterfaceController req = new ServletInterfaceController("http://138.68.147.88:8080/GroupProject/");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("module_id", "COM:001");
		
		System.out.println("Testing for department");
		Module res = (Module) req.sendPostRequest("GetModuleByID",map);
		
		if(res != null)
		{

		}
		
		System.out.println(req.sendPostRequest("TestServlet", map));


	}
}