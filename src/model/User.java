package model;
public class User {
	//variables are declared
	private String username;
	private String password;
	private String Cpassword;
	//constructors are created with different attributes
	public User(String username, String password, String cpassword) {
		
		this.username = username;
		this.password = password;
		this.Cpassword = cpassword;
	}
	public User(String username, String password) {
		this.username = username;
	   this.password = password;
    }
	//get() are used to get the values of Username
	public String getUsername() {
		return username;
	}
	//set() are used to set the values of Username
	public void setUsername(String username) {
		this.username = username;
	}
	//get() are used to get the values of Password
	public String getPassword() {
		return password;
	}
	//set() are used to set the values of Password
	public void setPassword(String password) {
		this.password = password;
	}
	//get() are used to get the values of confrimPassword
	public String getCpassword() {
		return Cpassword;
	}
	//set() are used to set the values of confrimPassword
	public void setCpassword(String cpassword) {
		this.Cpassword = cpassword;
	}	
}
