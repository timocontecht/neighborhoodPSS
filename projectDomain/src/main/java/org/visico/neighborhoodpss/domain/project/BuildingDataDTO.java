package org.visico.neighborhoodpss.domain.project;

import com.google.gwt.user.client.rpc.IsSerializable;


public class BuildingDataDTO implements Cloneable, IsSerializable {
	private int id;
	private BuildingDataTypeDTO type;
	private String value;
	
	public BuildingDataDTO()  {
		
	}
	
	public BuildingDataDTO (BuildingDataDTO toCopy)
	{
		// do not copy id 
		this.setType(toCopy.getType());
		this.setValue(toCopy.getValue());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BuildingDataTypeDTO getType() {
		return type;
	}
	public void setType(BuildingDataTypeDTO type)  {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
