package modules;

public class Department{
	String id, name;
	public Department(String id, String name)
	{
		setId(id);
		setName(name);
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