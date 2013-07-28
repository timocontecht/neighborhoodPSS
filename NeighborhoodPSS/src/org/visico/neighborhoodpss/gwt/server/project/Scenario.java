package org.visico.neighborhoodpss.gwt.server.project;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.gwt.shared.dto.BuildingDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.BuildingNetworkDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.GeoNetworkDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.ScenarioDTO;

import javax.persistence.*;




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
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Scenario parent; 
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="scenario_id")
	private Set<Building> buildings = new HashSet<Building>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="scenario_id")
	private Set<GeoNetwork> geonetworks = new HashSet<GeoNetwork>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="scenario_id")
	private Set<BuildingNetwork> buildingnetworks = new HashSet<BuildingNetwork>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="parent_id")
	private Set<Scenario> children = new HashSet<Scenario>();
		
	
	public Set<GeoNetwork> getNetworks() {
		return geonetworks;
	}



	public void setNetworks(Set<GeoNetwork> networks) {
		this.geonetworks = networks;
	}



	@Transient
	private ScenarioDTO dto_object = null;
	
		
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
	public Scenario(ScenarioDTO s_dto)
	{
		
		this.label = s_dto.label();
		this.name = s_dto.getName();
		this.description = s_dto.getDescription();
		this.id = s_dto.getId();
		this.dto_object = s_dto;
		
		copyDependentObjects(s_dto);
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
		copyDependentObjects(s_dto);	
		
	}
	
	private void copyDependentObjects(ScenarioDTO s_dto)
	{
		Iterator<BuildingDTO> itb = s_dto.getBuildingDTOs().iterator();
		while (itb.hasNext())
		{
			BuildingDTO b = itb.next();
			this.addBuilding(new Building(b));
		}
		
		Iterator<GeoNetworkDTO> itn = s_dto.getNetworkDTOs().iterator();
		while (itn.hasNext())
		{
			GeoNetworkDTO n = itn.next();
			this.addGeoNetwork(new GeoNetwork(n));
		}
		
		Iterator<BuildingNetworkDTO> itbn = s_dto.getBuildingNetworkDTOs().iterator();
		while (itbn.hasNext())
		{
			BuildingNetworkDTO n = itbn.next();
			this.addBuildingNetwork(new BuildingNetwork(n));
		}
		
		Iterator<ScenarioDTO> its = s_dto.getChildren().iterator();
		while (its.hasNext())
		{
			this.addChild(new Scenario(its.next()));
		}
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
	
	public void addGeoNetwork (GeoNetwork n)
	{
		geonetworks.add(n);
	}
	
	public void addBuildingNetwork (BuildingNetwork n)
	{
		buildingnetworks.add(n);
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
		child.geonetworks = new HashSet<GeoNetwork>();
		child.geonetworks.addAll(this.geonetworks);
		child.buildingnetworks = new HashSet<BuildingNetwork>();
		child.buildingnetworks.addAll(this.buildingnetworks);
		
		this.children.add(child);
		return child;
	}
	
	
	public ScenarioDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new ScenarioDTO();
			dto_object.setId(this.getId());
			dto_object.setLabel(this.getLabel());
			dto_object.setName(this.getName());
			dto_object.setDescription(this.getDescription());
			
			if (this.parent != null)
				dto_object.setParent(this.getParent().dto_object);
			
			for (Building b : buildings)
				dto_object.addBuilingDTO(b.getDto_object());
			
			for (GeoNetwork n : geonetworks)
				dto_object.addGeoNetworkDTO(n.getDto_object());
			
			for (GeoNetwork n : geonetworks)
				dto_object.addGeoNetworkDTO(n.getDto_object());
			
			for (BuildingNetwork n : buildingnetworks)
				dto_object.addBuildingNetworkDTO(n.getDto_object());
			
			for (Scenario c : children)
				dto_object.addChild(c.getDto_object());
		}
		
		return dto_object;
	}

	public void setDto_object(ScenarioDTO dto_object) {
		this.dto_object = dto_object;
	}



	public void update_dtoIds() 
	{
		this.dto_object.setId(this.id);
		
		Iterator<Building> bit = buildings.iterator();
		while(bit.hasNext())
		{
			Building b = bit.next();
			b.update_dtoIds();
		}
		
		Iterator<GeoNetwork> nit = geonetworks.iterator();
		while(nit.hasNext())
		{
			GeoNetwork n = nit.next();
			n.update_dtoIds();
		}
		
	}


	
}
