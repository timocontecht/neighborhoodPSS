package org.visico.neighborhoodpss.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NetworkDTO implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4908867498424445477L;
	private String name;
	private int id;
	private ScenarioDTO scenario;
	
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
	
	public NetworkDTO clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		NetworkDTO clone = new NetworkDTO();
		
		clone.setName(this.getName());
		clone.setScenario(this.getScenario());
		
		
		return clone;
	}

}
