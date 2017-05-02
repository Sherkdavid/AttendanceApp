package test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;

import tools.ServletInterfaceController;

public class LocalTestInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServletInterfaceController req = new ServletInterfaceController("http://138.68.147.88:8080/GroupProject/");
		HashMap<String, String> department = new HashMap<String, String>();
		department.put("name", "Computing");
		//department.put("h_o_d", "F001");

		HashMap<String, String> faculty = new HashMap<String, String>();
		faculty.put("faculty_id", "F001");
		faculty.put("name", "John Johnson");
		faculty.put("email", "jjo_hnson@gmail.io");
		faculty.put("department", "Computing");

		
		HashMap<String, String> module = new HashMap<String, String>();
		module.put("module_id","COM:001");
		module.put("title", "Networking");
		module.put("department", "Computing");
		module.put("faculty_id", "F001");

		HashMap<String, String> classInstance = new HashMap<String, String>();
		classInstance.put("class_id", "COM:001:01");
		classInstance.put("module_id", "COM:001");
		classInstance.put("lecturer_id", "F001");

		HashMap<String,String> enrolment = new HashMap<String, String>();
		enrolment.put("student_id","R002");
		enrolment.put("class_id", "COM:001:01");
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		System.out.println(stamp.toString());
		enrolment.put("enrol_date", stamp.toString());

		HashMap<String,String> student = new HashMap<String, String>();
		student.put("student_id", "R002");
		student.put("name", "JohnnyBai");
		student.put("department", "Computing");
		student.put("year","1");
		student.put("email", "jb10@mycit.ie");
		try {
			//req.sendPostRequest("InsertIntoDepartment", department);
			//System.out.println(req.sendPostRequest("InsertIntoFaculty", faculty));
			//req.sendPostRequest("InsertIntoModule",module);
			req.sendPostRequest("InsertIntoClass", classInstance);
			//req.sendPostRequest("InsertIntoStudent", student);
			req.sendPostRequest("InsertIntoEnrolment", enrolment);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}