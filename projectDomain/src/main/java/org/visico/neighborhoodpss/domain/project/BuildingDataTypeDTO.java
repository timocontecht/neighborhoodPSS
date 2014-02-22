package org.visico.neighborhoodpss.domain.project;

import com.google.gwt.user.client.rpc.IsSerializable;


public class BuildingDataTypeDTO implements Cloneable, IsSerializable {
	private int id;
	private String name;
	private String type;
	private double maximum;
	private double minimum;
	private String default_val;

	public BuildingDataTypeDTO()  {
		
	}
	
	public BuildingDataTypeDTO (BuildingDataTypeDTO toCopy)  {
		this.setDefault_val(toCopy.getDefault_val());
		this.setMaximum(toCopy.getMaximum());
		this.setMinimum(toCopy.getMinimum());
		this.setName(toCopy.getName());
		this.setType(toCopy.getType());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMaximum() {
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	public String getDefault_val() {
		return default_val;
	}

	public void setDefault_val(String default_val) {
		this.default_val = default_val;
	}

	

	@Override
	public int hashCode() {
		int hashCode = 31 * (name !=null ? name.hashCode() : 0);
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
	        return false;
     	if (obj == this)
            return true;
        if (!(obj instanceof BuildingDataTypeDTO))
            return false;
        
        BuildingDataTypeDTO dt = (BuildingDataTypeDTO) obj;
        
        return dt.getName().equals(this.getName());
	}
}
