package org.visico.neighborhoodpss.shared.dto.project;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import org.visico.neighborhoodpss.shared.dto.project.BuildingDTO;
import org.visico.neighborhoodpss.shared.patterns.ObserverInterface;
import org.visico.neighborhoodpss.shared.patterns.Subject;




public class ScenarioDTO extends Subject implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 732022436294521332L;
	
	public ScenarioDTO (String n)
	{
		label = "";
		name = n;
	}
	

	public ScenarioDTO()
	{
		
	}
	
	public ScenarioDTO (ScenarioDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated
		
		
		if (toCopy.parent == null)
		{
			this.label = Integer.toString(toCopy.children.size());
		}
		else
		{
			this.label = toCopy.label + "." +  Integer.toString(toCopy.children.size());
		}
		
		
		this.name = toCopy.getName();
		this.description = toCopy.description;
		//child.id = this.id;
		this.parent = toCopy;
		
		// need to clone the buildings and networks as well
		Iterator<BuildingDTO> bit = toCopy.BuildingDTOs.iterator();
		while (bit.hasNext())
		{
			BuildingDTO b = bit.next();
			this.BuildingDTOs.add(new BuildingDTO(b));
		}
		
		Iterator<GeoNetworkDTO> nit = toCopy.GeoNetworkDTOs.iterator();
		while (nit.hasNext())
		{
			GeoNetworkDTO n = nit.next();
			this.GeoNetworkDTOs.add(new GeoNetworkDTO(n));
		}
		
		Iterator<BuildingNetworkDTO> bnit = toCopy.BuildingNetworkDTOs.iterator();
		while (bnit.hasNext())
		{
			BuildingNetworkDTO n = bnit.next();
			this.BuildingNetworkDTOs.add(new BuildingNetworkDTO(n));
		}
		
		toCopy.children.add(this);
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

	public Set<GeoNetworkDTO> getNetworkDTOs() {
		return GeoNetworkDTOs;
	}

	public void setGeoNetworkDTOs(Set<GeoNetworkDTO> networkDTOs) {
		GeoNetworkDTOs = networkDTOs;
		notifyObservers();
	}
	
	public void setBuildingNetworkDTOs(Set<BuildingNetworkDTO> networkDTOs) {
		BuildingNetworkDTOs = networkDTOs;
		notifyObservers();
	}

	

	public Set<GeoNetworkDTO> getGeoNetworkDTOs() {
		return GeoNetworkDTOs;
	}


	public Set<BuildingNetworkDTO> getBuildingNetworkDTOs() {
		return BuildingNetworkDTOs;
	}


	public void setBuildingDTOs(Set<BuildingDTO> buildingDTOs) {
		BuildingDTOs = buildingDTOs;
	}


	public Set<BuildingDTO> getBuildingDTOs() {
		return BuildingDTOs;
	}

	public void setBuilingDTOs(Set<BuildingDTO> BuildingDTOs) {
		this.BuildingDTOs = BuildingDTOs;
	}

	public void addBuilingDTO(BuildingDTO b)
	{
		BuildingDTOs.add(b);
	}
	
	public void deleteGeoNetwork(GeoNetworkDTO n) {
		GeoNetworkDTOs.remove(n);
		notifyObservers();
	}

	public void addGeoNetworkDTO(GeoNetworkDTO n)
	{
		GeoNetworkDTOs.add(n);
		notifyObservers();
	}
	
	public void addBuildingNetworkDTO(BuildingNetworkDTO n)
	{
		BuildingNetworkDTOs.add(n);
		notifyObservers();
	}
	
	public void deleteBuildingNetwork(BuildingNetworkDTO n) {
		BuildingNetworkDTOs.remove(n);
		notifyObservers();
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
		child = new ScenarioDTO(this);
		return child;
	}
	
	
	// label is automatically created
	private String label;
	private String name;
	private String description;
	private int id;
	private ScenarioDTO parent; 
	
	
	private Set<ScenarioDTO> children = new HashSet<ScenarioDTO>();;
	private Set<BuildingDTO> BuildingDTOs = new HashSet<BuildingDTO>();
	private Set<GeoNetworkDTO> GeoNetworkDTOs = new HashSet<GeoNetworkDTO>();
	private Set<BuildingNetworkDTO> BuildingNetworkDTOs = new HashSet<BuildingNetworkDTO>();

	public void deleteBuilding(BuildingDTO buildingDTO) {
		BuildingDTOs.remove(buildingDTO);
	}

	
	
}
