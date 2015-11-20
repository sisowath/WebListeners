package com.atoudeft.entites;

public class User {	
            // attribut(s)
    private int id;
    private String username;
    private String password;
    private String etat;
            // methode(s)
    // constructeur(s)
    public User() {
        this(1, "", "", "");
    }	   
    public User(int id, String username, String password, String etat) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.etat = etat;
    }
	// accesseur(s)
    public int getId()  {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getEtat() {
        return this.etat;
    }
	// mutateur(s)  
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
}