package fr.insa.server.config.UserManager.model;

public class User {
	private int ID;
	private String firstname;
	private String lastname;
	private int IsAdmin;
	
    public User(ID, firstname, lastname, IsAdmin) {
    	this.ID = ID;
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.IsAdmin = IsAdmin;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }
    
    public void setPrivileges(int Privileges) {
    	this.IsAdmin = Privileges; // 1 = admin, 0 = classic user
    }
}
