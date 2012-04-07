package org.visico.neighborhoodpss.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



public class Scenario implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 732022436294521332L;
	
	public Scenario (String n)
	{
		buildings = new HashSet<Building>();
		children = new HashSet<Scenario>();
		label = "";
		name = n;
	}
	
	private Scenario()
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

	public Scenario getParent() {
		return parent;
	}

	public void setParent(Scenario parent) {
		this.parent = parent;
	}

	public Long getId_db() {
		return id_db;
	}

	public void setId_db(Long id_db) {
		this.id_db = id_db;
	}

	public Set<Scenario> getChildren() {
		return children;
	}

	public void setChildren(Set<Scenario> children) {
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
	
	public void addChild(Scenario child)
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
	
	
	
	public Scenario createChild()
	{
		Scenario child;
		child = (Scenario)this.clone();
		return child;
	}
	
	protected Scenario clone()
	{
		Scenario child = new Scenario();
		
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
		child.children = new HashSet<Scenario>();
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
	private Scenario parent; 
	private Long id_db;
	
	private Set<Scenario> children;
	private Set<Building> buildings;
}
