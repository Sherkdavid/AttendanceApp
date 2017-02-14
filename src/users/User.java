package users;

public class User {
	String id, name, email;
	public User(String id, String name, String mail)
	{
		setId(id);
		setName(name);
		setEmail(mail);
	}
	
	private void setEmail(String mail) {
		email = mail;
	}
	public String getEmail()
	{
		return email;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	private void setId(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}

}
