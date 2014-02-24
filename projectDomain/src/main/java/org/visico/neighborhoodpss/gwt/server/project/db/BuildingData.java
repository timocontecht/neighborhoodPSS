package org.visico.neighborhoodpss.gwt.server.project.db;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.domain.project.BuildingDataDTO;


@Entity
@Table(name="BUILDING_DATA")
public class BuildingData implements Serializable {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 9087293933095245663L;

		@Id
		@GeneratedValue
		private int id;
		
		@ManyToOne
		@JoinColumn(name="type_id")
		private BuildingDataType type;
		
		@Column
		private String value;
		
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name="building_id")
		private Building building;
		
		@Transient
		private BuildingDataDTO dto_object = null;

		public BuildingData()  {
			
		}
		
		public BuildingData(BuildingDataDTO dto_object)
		{
			this.setDto_object(dto_object);
			this.setId(dto_object.getId());
			this.setBuilding(new Building(dto_object.getBuilding()));
			this.setType(new BuildingDataType(dto_object.getType()));
			this.setValue(dto_object.getValue());
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public BuildingDataType getType() {
			return type;
		}

		public void setType(BuildingDataType type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Building getBuilding() {
			return building;
		}

		public void setBuilding(Building building) {
			this.building = building;
		}
		
		public void update_dtoIds() {
			this.dto_object.setId(this.id);	
		}

		public BuildingDataDTO getDto_object() {
			if (dto_object == null)
			{
				dto_object = new BuildingDataDTO();
				dto_object.setId(this.getId());
				dto_object.setBuilding(this.getBuilding().getDto_object());
				dto_object.setType(this.getType().getDto_object());
				dto_object.setValue(this.getValue());
				
			}
			return dto_object;
		}

		public void setDto_object(BuildingDataDTO dto_object) {
			this.dto_object = dto_object;
		}
		
}
