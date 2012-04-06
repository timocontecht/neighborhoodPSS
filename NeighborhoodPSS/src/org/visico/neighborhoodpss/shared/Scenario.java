package org.visico.neighborhoodpss.shared;

import java.util.ArrayList;

public class Scenario implements Cloneable
{
	public Scenario (String n)
	{
		buildings = new ArrayList<Building>();
		children = new ArrayList<Scenario>();
		label = "";
		name = n;
	}
	
	private Scenario()
	{
		
	}
	
	public String name()
	{
		return name;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public String description()
	{
		return description;
	}
	
	public void setDescription(String d)
	{
		description = d;
	}
	
	public void setBuildings(ArrayList<Building> b)
	{
		buildings = b;
	}
	
	public ArrayList<Building> getBuildings()
	{
		return buildings;
	}
	
	public void addBuilding(Building b)
	{
		buildings.add(b);
	}
	
	public String label()
	{
		return label;
	}
	
	private void addChild(Scenario child)
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
	
	public ArrayList<Scenario> children()
	{
		return children;
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
		
		
		child.name = this.name();
		child.description = this.description;
		child.id = this.id;
		child.parent = this;
		child.children = new ArrayList<Scenario>();
		child.buildings = new ArrayList<Building>();
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
	
	private ArrayList<Scenario> children;
	private ArrayList<Building> buildings;
}
