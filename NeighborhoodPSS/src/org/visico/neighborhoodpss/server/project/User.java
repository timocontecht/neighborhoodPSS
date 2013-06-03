package org.visico.neighborhoodpss.server.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.shared.dto.ScenarioDTO;
import org.visico.neighborhoodpss.shared.dto.UserDTO;

@Entity
@Table(name="USER")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4999263145306066227L;

	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Id
	@GeneratedValue
	private int id;
	
	@Transient
	private UserDTO dto_object = null;
	
	
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
	public UserDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new UserDTO();
			dto_object.setId(this.id);
			dto_object.setEmail(this.email);
			dto_object.setName(this.name);
			dto_object.setPassword(this.password);
		}
		return dto_object;
	}
	
	public void setDto_object(UserDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	public User()
	{
		
	}
	
	public User(UserDTO dto)
	{
		this.dto_object = dto;
		this.id = dto.getId();
		this.email = dto.getEmail();
		this.name = dto.getName();
		this.password = dto.getPassword();
	}
	
	public void update_dtoIds()
	{
		this.dto_object.setId(this.id);
	}
	
}
