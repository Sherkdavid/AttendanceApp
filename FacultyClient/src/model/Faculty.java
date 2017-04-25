package model;

public class Faculty extends User{
	
	public Faculty(String id, String name, String mail, String department) {
		super(id, name, mail);
	}
	
	public void setDepartment(String dept)
	{
		department = dept;
	}
	
	public String getDepartment()
	{
		return department;
	}



}