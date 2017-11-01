package com.huaxun.bean;

public class UserBean implements java.io.Serializable {
    private String username;
	private String password;
    private String email;
    private String gender;
    private String usericon;
    
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUsericon() {
		return usericon;
	}
	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}
    public String toString() {
    	return "[username="+username+" password="+password+" email="
               +email+" gender="+gender+" usericon="+usericon+"]";
    }
    
}
