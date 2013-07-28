package org.visico.neighborhoodpss.gwt.server.project;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class Network implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5457475366073332784L;

	@Id
	@GeneratedValue
	protected int id;
	
	@Column
	private String name;
	
	@Column 
	private String color;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	



	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Network()
	{
	}
	
	
	
	

	
	
	
}
