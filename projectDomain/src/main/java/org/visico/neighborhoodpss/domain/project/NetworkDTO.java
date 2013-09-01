package org.visico.neighborhoodpss.domain.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class NetworkDTO implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4908867498424445477L;
	private String name;
	private int id;
	private ScenarioDTO scenario;
	private String color; 
	 
	
	public NetworkDTO()
	{
		
	}
	
	public NetworkDTO (NetworkDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated
		this.setName(toCopy.getName());
		this.setScenario(toCopy.getScenario());
		this.setColor(toCopy.getColor());
	}
	
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ScenarioDTO getScenario() {
		return scenario;
	}
	public void setScenario(ScenarioDTO scenario) {
		this.scenario = scenario;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	
}
