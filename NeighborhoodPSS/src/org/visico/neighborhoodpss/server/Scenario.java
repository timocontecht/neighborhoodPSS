package org.visico.neighborhoodpss.server;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.shared.ScenarioDTO;

import javax.persistence.*;
import org.hibernate.annotations.Index;


@Entity
@Table(name="SCENARIO")
public class Scenario implements Cloneable, Serializable
{
	
	// label is automatically created
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String label;
	@Column
	private String name;
	@Column
	private String description;
	@ManyToOne
	private Scenario parent; 
	@Transient
	private Set<Building> buildings;
	
	@Transient
	private ScenarioDTO dto_object;
	@Transient
	private Set<Scenario> children;
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 732022436294521332L;
	
	/** 
	 * 
	 * 
	 * @param n
	 */
	public static Set<Scenario> createFromParentDTO(ScenarioDTO parentScenario)
	{
		HashSet<Scenario> scenarios = new HashSet<Scenario>();
		
		Scenario p = new Scenario(parentScenario);
		scenarios.add(p);
		Iterator<ScenarioDTO> it = parentScenario.getChildren().iterator();
		while (it.hasNext())
		{
			ScenarioDTO child = it.next();
			scenarios.addAll(createDTO(child, p));
			
		}
		
		return scenarios;
	}
	
	/** Recursive function to generate all children DTOs from the scenario hierarchy.
	 * 
	 * @param parent_dto
	 * @param parent
	 * @return
	 */
	private static Set<Scenario> createDTO(ScenarioDTO child_dto, Scenario parent)
	{
		HashSet<Scenario> scenarios = new HashSet<Scenario>();
		
		Scenario s = new Scenario(child_dto, parent);
		scenarios.add(s);
		
		Iterator<ScenarioDTO> it = child_dto.getChildren().iterator();
		while (it.hasNext())
		{
			ScenarioDTO child = it.next();
			scenarios.addAll(createDTO(child, s));
		}
		
		return scenarios;
	}
	
	
	public Scenario (String n)
	{
		buildings = new HashSet<Building>();
		children = new HashSet<Scenario>();
		label = "";
		name = n;
	}
	
	public Scenario()
	{
		
	}
	
	
	/** construction to construct Scenario object from DTO without parent
	 * 
	 * @param s_dto
	 */
	private Scenario(ScenarioDTO s_dto)
	{
		this.label = s_dto.label();
		this.name = s_dto.getName();
		this.description = s_dto.getDescription();
		this.id = s_dto.getId();
		this.dto_object = s_dto;
		//TODO convert buildings from DTO to object		
	}
	
	/**
	 * construct scenario with parent.
	 * @param s_dto and the parent
	 */
	private Scenario(ScenarioDTO s_dto, Scenario parent)
	{
		this.label = s_dto.label();
		this.name = s_dto.getName();
		this.description = s_dto.getDescription();
		this.id = s_dto.getId();
		this.parent = parent;
		this.dto_object = s_dto;
		//TODO convert buildings from DTO to object		
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
		this.dto_object.setId(id);
	}

	public Scenario getParent() {
		return parent;
	}

	public void setParent(Scenario parent) {
		this.parent = parent;
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
	
	
	
	
	
	
	public ScenarioDTO getDto_object() {
		return dto_object;
	}

	public void setDto_object(ScenarioDTO dto_object) {
		this.dto_object = dto_object;
	}


	
}
