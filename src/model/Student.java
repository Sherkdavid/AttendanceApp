package model;

import java.io.Serializable;

public class Student extends User implements Serializable {
	String course_id;
	int year;
	
	public Student(String id, String name, String mail, int year)
	{
		super(id,name,mail);
		setYear(year);
	}
	public Student()
	{
	}
	public Student(String id, String name, String mail)
	{
		super(id,name,mail);
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}
	
	public void incrementYear()
	{
		year++;
	}
	public int getYear()
	{
		return year;
	}
}
