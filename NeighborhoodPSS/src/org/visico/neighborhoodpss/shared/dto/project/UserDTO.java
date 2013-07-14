package org.visico.neighborhoodpss.shared.dto.project;

import java.io.Serializable;

public class UserDTO implements Cloneable, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1646718522876342283L;
	
	private String name;
	private String email;
	private String password;
	private int id;
	
	public UserDTO()
	{
		
	}
	
	public UserDTO (UserDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated
		this.email = toCopy.email;
		this.name = toCopy.name;
		this.password = toCopy.password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
