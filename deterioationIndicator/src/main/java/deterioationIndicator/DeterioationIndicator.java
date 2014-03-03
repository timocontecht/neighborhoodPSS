package deterioationIndicator;


import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.plugin.IndicatorPlugin;
import org.visico.neighborhoodpss.plugin.buildingvisualization.BuildingVisualization;
import org.visico.neighborhoodpss.plugin.buildingvisualization.BuildingVisualizationList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


/**
 * @author HartmannT
 *
 */
public class DeterioationIndicator extends IndicatorPlugin {
	private static int TIME_STEPS = 1;
	private static double threshold = 0.99;
	
	ScenarioDTO scenario;
	BuildingGraph graph = new BuildingGraph();

	@Override
	public void calculate(ScenarioDTO scenario) {
		this.scenario = scenario;
		createGraph();
		propagateDeterioation();
		generateOutput();
	}
	
	private void generateOutput() {
		// create the jaxb class
		BuildingVisualizationList xmlListClass = new BuildingVisualizationList();
		for (BuildingNode b : graph.nodes)
		{
			BuildingVisualization viz = new BuildingVisualization();
			viz.setBuildingID(b.getBuilding().getId());
			viz.setColor(getConditionDisplayColor(b.getCondition()));
			xmlListClass.getBuilding().add(viz);
		}
		
		try  {
			// marshall the class
			File file = new File("Deterioation_"+ scenario.getId() + ".xml");
			
			JAXBContext jaxbContext = JAXBContext.newInstance(BuildingVisualizationList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			jaxbMarshaller.marshal(xmlListClass, file);
			jaxbMarshaller.marshal(xmlListClass, System.out);
		}
		catch(Exception ex)  {
			ex.printStackTrace();
		}
		
	}

	private String getConditionDisplayColor(int condition) {
		String color;
		switch(condition)  {
			case 1:
				color = "#40FF00";
				break;
			case 2:
				color = "#FFFF00";
				break;
			case 3:
				color = "#FF8000";
				break;
			case 4:
				color = "#FF0000";
				break;
			case 5:
				color = "#3B0B0B";
				break;
			default:
				color = "#FFFFFF";
		}	
		return color;
	}

	private void propagateDeterioation() {
		for (int i=0; i< TIME_STEPS; i++)
			deterioate();
	}
	
	

	public Set<BuildingNode> getBuildingNodes() {
		return graph.nodes;
	}

	public void createGraph() {
		
		for (BuildingDTO b : scenario.getBuildingDTOs())  {
			BuildingNode node = new BuildingNode(b);
			graph.addNode(node);
		}
		graph.buildGraph();
	}

	
	/**
	 * 
	 * @return an double array of size 4  with the min and max coordinates
	 */
	public double[] getScaleInfo() {
		double[] widthHeight = new double[4];
		
		double min_x = Double.MAX_VALUE, max_x = 0, min_y = Double.MAX_VALUE, max_y = 0;
		
		for (BuildingNode n : graph.getNodes())  {
			double x = n.x();
			double y = n.y();
			
			if (x < min_x) min_x = x;
			if (x > max_x) max_x = x;
			if (y < min_y) min_y = y;
			if (y > max_y) max_y = y;
		}
		
		widthHeight[0] = min_x;
		widthHeight[1] = min_y;
		widthHeight[2] = max_x;
		widthHeight[3] = max_y;
		
		return widthHeight;
	}
			
	public void deterioate()  {
		for (BuildingNode n : graph.getNodes())  {
			int surroundingConditionAgg = 0;
			int surroundingBuildings = 0;
			
			for (BuildingNode neighbors : graph.getNeighbors(n) )  {
				surroundingBuildings++;
				if (neighbors instanceof BuildingNode)  {
					surroundingConditionAgg += ((BuildingNode) neighbors).getCondition();
				}
			}
			if ( (n.getCondition() - surroundingConditionAgg / surroundingBuildings) > threshold )
				n.reduceCondition(1);
		}
	}

	public BuildingGraph getGraph() {
		return graph;
	}
	
	
}
