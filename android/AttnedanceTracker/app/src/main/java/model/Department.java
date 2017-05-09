package model;

import java.io.Serializable;

public class Department implements Serializable{
	private String id, name, dept_head;
	public Department(String name, String head)
	{
		setHead(head);
		setName(name);
	}
	
	private void setHead(String head) {
		dept_head = head;
	}
	
	public String getHead()
	{
		return dept_head;
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

	public String toString()
	{
		return getName();
	}
}