package com.example.kieran.attnedancetracker.modules;
public class Module
{
	String id, name, lecturer_id, dept_id;
	public Module(String id, String name, String lecturer_id, String dept_id)
	{
		setId(id);
		setName(name);
		setLecturer(lecturer_id);
		setDepartment(dept_id);
	}
	
	private void setDepartment(String department)
	{
		dept_id = department;
	}
	
	public String getDepartment()
	{
		return dept_id;
	}
	
	private void setId(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}
	
	private void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	private void setLecturer(String lecturer)
	{
		lecturer_id = lecturer;
	}
	
	public String getLecturer()
	{
		return lecturer_id;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	
	
}