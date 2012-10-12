package org.visico.neighborhoodpss.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.server.Building;



public class ScenarioDTO implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 732022436294521332L;
	
	public ScenarioDTO (String n)
	{
		buildings = new HashSet<Building>();
		children = new HashSet<ScenarioDTO>();
		label = "";
		name = n;
	}
	

	private ScenarioDTO()
	{
		
	}
	
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ScenarioDTO getParent() {
		return parent;
	}

	public void setParent(ScenarioDTO parent) {
		this.parent = parent;
	}

	
	public Set<ScenarioDTO> getChildren() {
		return children;
	}

	public void setChildren(Set<ScenarioDTO> children) {
		this.children = children;
	}

	public Set<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(Set<Building> buildings) {
		this.buildings = buildings;
	}

	public void addBuilding(Building b)
	{
		buildings.add(b);
	}
	
	public String label()
	{
		return label;
	}
	
	public void addChild(ScenarioDTO child)
	{
		children.add(child);
	}
	
	public boolean hasParent()
	{
		if (parent == null)
			return false;
		else
			return true;
	}
	
	
	
	public ScenarioDTO createChild()
	{
		ScenarioDTO child;
		child = (ScenarioDTO)this.clone();
		return child;
	}
	
	protected ScenarioDTO clone()
	{
		ScenarioDTO child = new ScenarioDTO();
		
		if (parent == null)
		{
			child.label = Integer.toString(children.size());
		}
		else
		{
			child.label = label + "." +  Integer.toString(children.size());
		}
		
		
		child.name = this.getName();
		child.description = this.description;
		child.id = this.id;
		child.parent = this;
		child.children = new HashSet<ScenarioDTO>();
		child.buildings = new HashSet<Building>();
		child.buildings.addAll(this.buildings);
		
		this.children.add(child);
		return child;
	}
	
	
	// label is automatically created
	private String label;
	private String name;
	private String description;
	private int id;
	private ScenarioDTO parent; 
	
	
	private Set<ScenarioDTO> children;
	private Set<Building> buildings;
}
