package deterioationIndicator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.plugin.IndicatorPlugin;

import delaunay_triangulation.Delaunay_Triangulation;
import delaunay_triangulation.Point_dt;
import delaunay_triangulation.Triangle_dt;


/**
 * @author HartmannT
 *
 */
public class DeterioationIndicator extends IndicatorPlugin {
	private static int timesteps = 20;
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
		// TODO Auto-generated method stub
		
	}

	private void propagateDeterioation() {
		// TODO Auto-generated method stub
		// network deterioation algorithm 
	}
	
	

	public Set<BuildingNode> getBuildingNodes() {
		return graph.nodes;
	}

	private void createGraph() {
		
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
