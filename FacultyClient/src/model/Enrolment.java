package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Enrolment implements Serializable{
	private String student_id,class_id;
	private Timestamp date_of;
	
	public Enrolment(String student, String class_id, Timestamp date)
	{
		this.student_id = student;
		this.class_id = class_id;
		date_of = date;
	}

	public String getStudent_id() {
		return student_id;
	}

	public String getClass_id() {
		return class_id;
	}

	public Timestamp getDate_of() {
		return date_of;
	}
}
