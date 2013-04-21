package org.visico.neighborhoodpss.server;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Network implements Cloneable, Serializable
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
	
	



	public Network()
	{
	}
	
	
	
	

	
	
	
}
