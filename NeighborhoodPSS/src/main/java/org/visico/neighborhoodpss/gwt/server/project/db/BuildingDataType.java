package org.visico.neighborhoodpss.gwt.server.project.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.domain.project.BuildingDataDTO;
import org.visico.neighborhoodpss.domain.project.BuildingDataTypeDTO;

@Entity
@Table(name="BUILDING_DATA_TYPE")
public class BuildingDataType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5401193509429377495L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String type;
	
	@Column
	private double maximum;
	
	@Column
	private double minimum;
	
	@Column
	private String default_val;
	
	@Transient
	private BuildingDataTypeDTO dto_object = null;
	
	public BuildingDataType()  {
		
	}
	
	public BuildingDataType(BuildingDataTypeDTO dto_object)  {
		this.setDto_object(dto_object);
		this.setDefault_val(dto_object.getDefault_val());
		this.setId(dto_object.getId());
		this.setMaximum(dto_object.getMaximum());
		this.setMinimum(dto_object.getMinimum());
		this.setName(dto_object.getName());
		this.setType(dto_object.getType());
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

	public void setDto_object(BuildingDataTypeDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	public void update_dtoIds() {
		this.dto_object.setId(this.id);	
	}

	public BuildingDataTypeDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new BuildingDataTypeDTO();
			dto_object.setDefault_val(this.getDefault_val());
			dto_object.setId(this.getId());
			dto_object.setMaximum(this.getMaximum());
			dto_object.setMinimum(this.getMinimum());
			dto_object.setName(this.getName());
			dto_object.setType(this.getType());
			
		}
		return dto_object;
	}

}
	

