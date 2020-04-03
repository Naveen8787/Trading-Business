package businesslogic;

public class Validations {
	public boolean checkUserDetails(String username, String password) {
		if(username.equals("Naveen8787") && password.equals("Naveen@123"))
			return true;
		else 
			return false;
	}
	private final static String[] users = {"Noone","one","two","three","joker"};
	private final static String[] passwords = {"Noone@123", "one@123", "two@123", "three@123", "joker@123"};
	private final static String[] Cpasswords= {"Noone@123", "one@123", "two@123", "three@123", "joker@123"};
	public boolean checkUserDetails1(String username, String password,String Cpassword) {
		//if(password.equals(Cpassword)) {
		 for (int i=0;i!=users.length;++i) {
		        if (users[i].equals(username) && passwords[i].equals(password) && Cpasswords[i].equals(Cpassword))
				{
		            return true;
		        }
		}
		return false;
	}
	/*	public boolean checkUserDetails2(String username, String password) {
	User one = new User(username,password);	
	User two=new User(username,password);
	if(username.equals(one.getUsername()) && password.equals(one.getPassword())) 
		//if(checkUserDetails2(username,password)==checkUserDetails(username,password))
      return true;
    else 
    	return false;
		}*/
  //public boolean checkUserDetails2(String username, String password) {
	// return true;
	//}
		
	public boolean checkUserDetails2(String username, String password) {
	    for (int i=0;i!=users.length;++i) {
	        if (users[i].equals(username) && passwords[i].equals(password)) {
	            return true;
	        }
	    }
	    return false;
	}
}