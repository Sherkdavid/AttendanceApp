package modules;
import users.*;
public class Module
{
	String id, name;
	Lecturer coordinator;
	public Module(String id, String name, Lecturer head)
	{
		setId(id);
		setName(name);
		setCoordinator(head);
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
	
	private void setCoordinator(Lecturer l)
	{
		coordinator = l;
	}
	
	
	
}