package deterioationIndicator;

import static org.junit.Assert.*;
import gui.DeterioationGUIMain;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.GeoPointDTO;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.gwt.server.project.db.GeoPoint;
import org.visico.neighborhoodpss.gwt.server.project.db.HibernateUtil;
import org.visico.neighborhoodpss.gwt.server.project.db.Project;
import org.visico.neighborhoodpss.gwt.server.project.db.Scenario;
import org.visico.neighborhoodpss.plugin.IndicatorPlugin;


public class GlobalIndicatorTest {
	
	ScenarioDTO scenario;
	
	@Before
	public void getScenario()
	{
		ScenarioDTO s_dto = null;
		// get the scenario from the database
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction tx = null;
	    try  
	    {
	    	tx = session.beginTransaction();
	    
		    Query q = session.createQuery("from Scenario");
		    ArrayList<Scenario> p = (ArrayList<Scenario>) q.list();
		    scenario = p.get(0).getDto_object();  
		    tx.commit();
		    session.close();
		    
	    }
	    catch (Exception e)
	    {
	    	if (tx != null) tx.rollback();
	    	session.close();
	    
	    }
	}
	
	@Test 
	public void calculateMidPoint()
	{
		BuildingDTO building = new BuildingDTO();
		
		ArrayList<GeoPointDTO> points = new ArrayList<GeoPointDTO>();
		GeoPointDTO pt1 = new GeoPointDTO(); pt1.setLatitude(0.0); pt1.setLongitude(0.0);
		GeoPointDTO pt2 = new GeoPointDTO(); pt2.setLatitude(0.0); pt2.setLongitude(1.0);
		GeoPointDTO pt3 = new GeoPointDTO(); pt3.setLatitude(1.0); pt3.setLongitude(1.0);
		GeoPointDTO pt4 = new GeoPointDTO(); pt4.setLatitude(1.0); pt4.setLongitude(0.0);
		points.add(pt1); points.add(pt2);  points.add(pt3);  points.add(pt4); 
		building.setPoints(points);
		
		BuildingNode node = new BuildingNode(building);
		//double center[] = node.getCenterPoint();
		assertEquals(0.5, node.x(), 0.0001);
		assertEquals(0.5, node.y(), 0.0001);
	}
	
	@Test
	public void CalculateDeterioationSimpleTerrain()  {
		
		// calculate Network
		
		// calculate assimilation according to number of time steps
	}

	@Test
	public void calculateNetworkTest ()
	{
		// calculate mid points of buildings
		
		// establish triangular mesh between buildings
		
		// establish building connection network
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void configurationTest ()  {
		IndicatorPlugin indicator = new DeterioationIndicator();
	    	indicator.calculate(scenario);
	}
	
	
}
