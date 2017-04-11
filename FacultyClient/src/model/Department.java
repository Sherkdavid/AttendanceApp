package model;

import java.io.Serializable;

public class Department implements Serializable{
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