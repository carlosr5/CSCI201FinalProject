import java.io.Serializable;
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
    private String email;
    private String password;
    private int UID;
    private String firstName;
    private String lastName;
    public User(String first, String last, String uname, String email, String password)
    {
    	firstName = first;
    	lastName = last;
    	username = uname;
    	this.email = email;
    	this.password = password;
    }
    public User(String email, String password)
    {
    	this.email = email;
    	this.password = password;
    }
    public String getName()
    {
    	return lastName + ", " + firstName;
    }
    public String getFirst()
    {
    	return firstName;
    }
    public String getLast()
    {
    	return lastName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUID(int id)
    {
    	this.UID = id;
    }
    public int getUID()
    {
    	return UID;
    }
    
}
