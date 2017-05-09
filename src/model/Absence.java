package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Absence implements Serializable
{
	Timestamp date;
	String student_id, class_id;

	public Absence(String student, String class_id,Timestamp date)
	{
		setStudent(student);
		setClassId(class_id);
		this.date = date;
	}

	private void setStudent(String student_id)
	{
		this.student_id = student_id;
	}
	
	public String getStudent()
	{
		return student_id;
	}
	
	private void setClassId(String class_id)
	{
		this.class_id = class_id;
	}
	
	public String getClassId()
	{
		return class_id;
	}
	public String toString()
	{
		return date.toString();
	}
}