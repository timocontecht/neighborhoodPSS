package org.visico.neighborhoodpss.domain.project;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gwt.user.client.rpc.IsSerializable;


public class BuildingDataTypeDTO implements Cloneable, IsSerializable {
	private int id;
	private String name;
	private String type;
	private double maximum;
	private double minimum;
	private double default_val;

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

	public double getDefault_val() {
		return default_val;
	}

	public void setDefault_val(double default_val) {
		this.default_val = default_val;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int hashCode = new HashCodeBuilder(17,31).append(name).hashCode();
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
        
        return new EqualsBuilder().append(name, dt.name).isEquals();
	}
	
	
	
	
}
