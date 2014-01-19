package org.visico.neighborhoodpss.gwt.shared.dto;

import java.util.HashSet;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.BuildingDataTypeDTO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IndicatorDTO implements IsSerializable {

	String name;
	String description;
	String author;
	String version;
	private boolean activated;
	
	Set<BuildingDataTypeDTO> buildingDataTypes = new HashSet<BuildingDataTypeDTO>();
	
	public IndicatorDTO()
	{
		
	}
	
	public IndicatorDTO(String name, String description, String author, String version) {
		this.name = name;
		this.description = description;
		this.author = author;
		this.version = version;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setActivated(boolean b) {
		this.activated = b;
	}

	public boolean getActivated()
	{
		return activated;
	}

	public Set<BuildingDataTypeDTO> getBuildingDataTypes() {
		return buildingDataTypes;
	}

	public void setBuildingDataTypes(Set<BuildingDataTypeDTO> buildingDataTypes) {
		this.buildingDataTypes = buildingDataTypes;
	}
	
	
}
