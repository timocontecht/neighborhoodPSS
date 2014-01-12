package org.visico.neighborhoodpss.gwt.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IndicatorDTO implements IsSerializable {

	String name;
	String description;
	String author;
	String version;
	
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

	
}
