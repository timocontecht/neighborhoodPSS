package org.visico.neighborhoodpss.domain.project;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class ObjectTests {
	
	@Test
	public void testBuildingDataTypeDTOHash()
	{
		HashSet<BuildingDataTypeDTO> set = new HashSet<BuildingDataTypeDTO>();
		
		BuildingDataTypeDTO type1 = new BuildingDataTypeDTO();
		type1.setName("Type1");
		set.add(type1);
		assertEquals(1, set.size());
		
		set.add(type1);
		assertEquals(1, set.size());
		
		BuildingDataTypeDTO type2 = new BuildingDataTypeDTO();
		type2.setName("Type1");
		set.add(type2);
		assertEquals(1, set.size());
		
		type2.setName("Type2");
		set.add(type2);
		assertEquals(2, set.size());
		
	}
}
