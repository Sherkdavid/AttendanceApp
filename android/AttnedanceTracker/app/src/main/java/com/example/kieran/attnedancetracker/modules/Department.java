package com.example.kieran.attnedancetracker.modules;

public class Department{
	String id, name, dept_head;
	public Department(String id, String name, String head)
	{
		setHead(head);
		setId(id);
		setName(name);
	}
	
	private void setHead(String head) {
		dept_head = head;
	}
	
	public String getHead()
	{
		return dept_head;
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
}