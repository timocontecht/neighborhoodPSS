package org.visico.neighborhoodpss.gwt.server.tests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.BuildingEdgeDTO;
import org.visico.neighborhoodpss.domain.project.BuildingNetworkDTO;
import org.visico.neighborhoodpss.domain.project.GeoEdgeDTO;
import org.visico.neighborhoodpss.domain.project.GeoNetworkDTO;
import org.visico.neighborhoodpss.domain.project.GeoPointDTO;
import org.visico.neighborhoodpss.domain.project.NodeDTO;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.domain.project.UserDTO;

import com.google.gwt.dev.util.collect.HashSet;


public class CaseProject 
{
	/** 
	 * Functions creates the xml table to compare this example in the tests ...
	 * 
	 * @param argsjava main 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws DatabaseUnitException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws Exception
	 */
	public static void main(String [ ] args) throws ClassNotFoundException, SQLException, DatabaseUnitException, FileNotFoundException, IOException
    {
        // database connection
       // Class driverClass = Class.forName("com.mysql.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost/NEIGHBORHOODPSS?" +
                "user=NHPSSUSER&password=RESU#2008_2010");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        
        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        XmlDataSet.write(fullDataSet, new FileOutputStream("update.xml"));
    }
	
	public CaseProject(int caseNr)
	{
		this.caseNr = caseNr;
		if (caseNr == 1)
		{
			// add project
			labels_dto = new ArrayList<String>();
			scenarios_parents_dto = new HashSet<ScenarioDTO>();
			all_scenarios_dto = new HashSet<ScenarioDTO>();
			
			ScenarioDTO parent1 = new ScenarioDTO("Parent 1");
			labels_dto.add(parent1.label());
			scenarios_parents_dto.add(parent1);
			
			GeoPointDTO p1 = new GeoPointDTO();
			p1.setLatitude(0.0); p1.setLongitude(0.0);
			
			GeoPointDTO p2 = new GeoPointDTO();
			p2.setLatitude(1.0); p2.setLongitude(0.0);
			
			GeoPointDTO p3 = new GeoPointDTO();
			p3.setLatitude(1.0); p3.setLongitude(1.0);
			
			GeoPointDTO p4 = new GeoPointDTO();
			p4.setLatitude(0.0); p4.setLongitude(1.0);
			
			//TODO: add nodes and edges and users
			
			ArrayList<GeoPointDTO> pts = new ArrayList<GeoPointDTO>();
			pts.add(p1); pts.add(p2); pts.add(p3); pts.add(p4);
			
			BuildingDTO b1 = new BuildingDTO();
			b1.setPoints(pts);
			b1.setType("Construction");
			parent1.addBuilingDTO(b1);
			
			GeoNetworkDTO n1 = new GeoNetworkDTO();
			n1.setName("Traffic");
			parent1.addGeoNetworkDTO(n1);
			
			ScenarioDTO parent2 = new ScenarioDTO("Parent 2");
			//parent2.setId(2);
			labels_dto.add(parent2.label());
			scenarios_parents_dto.add(parent2);
			all_scenarios_dto.addAll(scenarios_parents_dto);
			
			BuildingDTO b2 = new BuildingDTO();
			b2.setPoints(pts);
			b2.setType("Machinery");
			parent2.addBuilingDTO(b2);
			
			BuildingNetworkDTO n2 = new BuildingNetworkDTO();
			n2.setName("Knowledge Exchange");
			parent1.addBuildingNetworkDTO(n2);
			
			ScenarioDTO child1_parent1 = parent1.createChild();
			//child1_parent1.setId(3);
			labels_dto.add(child1_parent1.label());
			all_scenarios_dto.add(child1_parent1);
			
			
			ScenarioDTO child2_parent1 = parent1.createChild();
			//child2_parent1.setId(4);
			labels_dto.add(child2_parent1.label());
			all_scenarios_dto.add(child2_parent1);
			
			ScenarioDTO child_child1_parent1 = child1_parent1.createChild();
			labels_dto.add(child_child1_parent1.label());
			all_scenarios_dto.add(child_child1_parent1);
			
			ScenarioDTO child_parent2 = parent2.createChild();
			labels_dto.add(child_parent2.label());
			all_scenarios_dto.add(child_parent2);
		}
		
		else // simple case
		{
			p = new ProjectDTO();
			p.setLatitude(1.0);
			p.setLongitude(1.0);
			p.setName("Test");
			
			UserDTO u = new UserDTO();
			u.setEmail("baron.timo@gmail.com");
			u.setName("Timo Hartmann");
			u.setPassword("pass");
			p.addUser(u);
			
			labels_dto = new ArrayList<String>();
			scenarios_parents_dto = new HashSet<ScenarioDTO>();
			all_scenarios_dto = new HashSet<ScenarioDTO>();
			
			GeoPointDTO p1 = new GeoPointDTO();
			p1.setLatitude(0.0); p1.setLongitude(0.0);
			
			GeoPointDTO p2 = new GeoPointDTO();
			p2.setLatitude(1.0); p2.setLongitude(0.0);
			
			GeoPointDTO p3 = new GeoPointDTO();
			p3.setLatitude(1.0); p3.setLongitude(1.0);
			
			GeoPointDTO p4 = new GeoPointDTO();
			p4.setLatitude(0.0); p4.setLongitude(1.0);
			
			NodeDTO nd1 = new NodeDTO();
			nd1.setLatitude(0.0); nd1.setLongitude(1.0); 
			nd1.setInflow(10.0); nd1.setOutflow(5.0);
			
			NodeDTO nd2 = new NodeDTO();
			nd2.setLatitude(0.0); nd2.setLongitude(1.0); 
			nd2.setInflow(0.0); nd2.setOutflow(0.0);
			
			GeoEdgeDTO e = new GeoEdgeDTO();
			e.setCapacity(10.0); 
			e.setStart_node(nd1); e.setEnd_node(nd2);
			
			ArrayList<GeoPointDTO> pts = new ArrayList<GeoPointDTO>();
			pts.add(p1); pts.add(p2); pts.add(p3); pts.add(p4);
			
			ArrayList<NodeDTO> nds = new ArrayList<NodeDTO>();
			nds.add(nd1); nds.add(nd2);
			
			ArrayList<GeoEdgeDTO> eds = new ArrayList<GeoEdgeDTO>();
			eds.add(e);
			
			BuildingDTO b1 = new BuildingDTO();
			b1.setPoints(pts);
			b1.setType("Construction");
			
			BuildingDTO b2 = new BuildingDTO();
			b1.setPoints(pts);
			b1.setType("Construction");
			
			GeoNetworkDTO n1 = new GeoNetworkDTO();
			n1.setName("Traffic");
			n1.setNodes(nds);
			n1.setEdges(eds);
			
			BuildingNetworkDTO n2 = new BuildingNetworkDTO();
			n2.setName("Knowledge");
			n2.addBuilding(b1);
			n2.addBuilding(b2);
			
			ScenarioDTO parent = new ScenarioDTO("Simple Test");
			parent.addBuilingDTO(b1);
			parent.addGeoNetworkDTO(n1);
			parent.addBuildingNetworkDTO(n2);
			p.addParentScenario(parent);
			
			ScenarioDTO child = parent.createChild();
			scenarios_parents_dto.add(parent);
			all_scenarios_dto.add(parent); all_scenarios_dto.add(child);
			labels_dto.add(parent.getLabel()); labels_dto.add(child.getLabel());
		}
		
	}
	
	public ArrayList<String> getLabels_dto() {
		return labels_dto;
	}
	public void setLabels_dto(ArrayList<String> labels_dto) {
		this.labels_dto = labels_dto;
	}
	public HashSet<ScenarioDTO> getScenarios_parents_dto() {
		return scenarios_parents_dto;
	}
	public void setScenarios_parents_dto(HashSet<ScenarioDTO> scenarios_parents_dto) {
		this.scenarios_parents_dto = scenarios_parents_dto;
	}
	public HashSet<ScenarioDTO> getAll_scenarios_dto() {
		return all_scenarios_dto;
	}
	public void setAll_scenarios_dto(HashSet<ScenarioDTO> all_scenarios_dto) {
		this.all_scenarios_dto = all_scenarios_dto;
	}

	public void updateCase()
	{
		if (caseNr == 0)
		{
			ScenarioDTO parent = scenarios_parents_dto.iterator().next();
			
			// add a new building to the existing child
			GeoPointDTO p1 = new GeoPointDTO();
			p1.setLatitude(1.0); p1.setLongitude(1.0);
			
			GeoPointDTO p2 = new GeoPointDTO();
			p2.setLatitude(2.0); p2.setLongitude(1.0);
			
			GeoPointDTO p3 = new GeoPointDTO();
			p3.setLatitude(2.0); p3.setLongitude(2.0);
			
			GeoPointDTO p4 = new GeoPointDTO();
			p4.setLatitude(1.0); p4.setLongitude(2.0);
			
			ArrayList<GeoPointDTO> pts = new ArrayList<GeoPointDTO>();
			pts.add(p1); pts.add(p2); pts.add(p3); pts.add(p4);
			
			NodeDTO nd1 = new NodeDTO();
			nd1.setLatitude(0.0); nd1.setLongitude(1.0); 
			nd1.setInflow(10.0); nd1.setOutflow(5.0);
			
			NodeDTO nd2 = new NodeDTO();
			nd2.setLatitude(0.0); nd2.setLongitude(1.0); 
			nd2.setInflow(0.0); nd2.setOutflow(0.0);
			
			GeoEdgeDTO e = new GeoEdgeDTO();
			e.setStart_node(nd1); e.setEnd_node(nd2);
			e.setCapacity(0.0);
			
			ArrayList<NodeDTO> nds = new ArrayList<NodeDTO>();
			nds.add(nd1); nds.add(nd2);
			
			
			BuildingDTO b = new BuildingDTO();
			b.setPoints(pts);
			b.setType("Chemical");
			
			BuildingDTO b2 = new BuildingDTO();
			b2.setPoints(pts);
			b2.setType("Construction");
			
			ArrayList<GeoEdgeDTO> eds = new ArrayList<GeoEdgeDTO>();
			eds.add(e);
			ArrayList<BuildingEdgeDTO> beds = new ArrayList<BuildingEdgeDTO>();
			BuildingEdgeDTO bed = new BuildingEdgeDTO();
			bed.setStart_building(b);
			bed.setEnd_building(b2);
			beds.add(bed);
			
			
			GeoNetworkDTO n = new GeoNetworkDTO();
			n.setName("Newly Updated");
			n.setNodes(nds);
			n.setEdges(eds);
			
			
			BuildingNetworkDTO bn = new BuildingNetworkDTO();
			bn.setName("Newly Updated");
			bn.addBuilding(b2); bn.addBuilding(b);
			bn.setEdges(beds);
			
			parent.getChildren().iterator().next().addBuilingDTO(b);
			parent.getChildren().iterator().next().addBuilingDTO(b2);
			parent.getChildren().iterator().next().addGeoNetworkDTO(n);
			parent.getChildren().iterator().next().addBuildingNetworkDTO(bn);
			
			// add a new child
			ScenarioDTO child = parent.createChild();
			
			all_scenarios_dto.add(child);
			
			p.setName("Updated Project");
			p.getUsers().iterator().next().setPassword("pass changed");
		}
	}
	
	public ProjectDTO getP() {
		return p;
	}

	public void setP(ProjectDTO p) {
		this.p = p;
	}

	ArrayList<String> labels_dto;
	HashSet<ScenarioDTO> scenarios_parents_dto;
	HashSet<ScenarioDTO> all_scenarios_dto;
	ProjectDTO p; 
	int caseNr = 0;
	
	
}
