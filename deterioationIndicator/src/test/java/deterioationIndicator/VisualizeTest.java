package deterioationIndicator;

import static org.junit.Assert.*;
import gui.DeterioationGUIMain;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.visico.neighborhoodpss.deterioationindicator.DeterioationIndicator;
import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.BuildingDataDTO;
import org.visico.neighborhoodpss.domain.project.BuildingDataTypeDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.gwt.server.project.db.HibernateUtil;
import org.visico.neighborhoodpss.gwt.server.project.db.Scenario;

public class VisualizeTest {
	static private int[] conditions = {5, 5, 5, 1, 5, 1, 5, 1, 5, 1, 5, 1, 5, 5, 5, 5, 1, 1, 1, 1};
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
	/*    
	    // set the extra data value
	    BuildingDataTypeDTO type = new BuildingDataTypeDTO();
	    type.setName("Condition");
	    int i=0;
	    
	    HashMap<Integer, Integer> conditions = new HashMap();
	    conditions.put(1, 1);
	    conditions.put(2, 1); 
	    conditions.put(3, 5); 
	    conditions.put(4, 5); 
	    conditions.put(5, 5);
	    conditions.put(6, 5);
	    conditions.put(7, 1);
	    conditions.put(8, 5);
	    conditions.put(9, 1);
	    conditions.put(10, 5);
	    conditions.put(11, 1);
	    conditions.put(12, 5);
	    conditions.put(13, 1);
	    conditions.put(14, 5);
	    conditions.put(15, 5);
	    conditions.put(16, 5);
	    conditions.put(17, 5);
	    conditions.put(18, 5);
	    
	    for (BuildingDTO b : scenario.getBuildingDTOs())  {
	    	HashMap<BuildingDataTypeDTO, BuildingDataDTO> data = new HashMap<BuildingDataTypeDTO, BuildingDataDTO>();
	    	BuildingDataDTO data_dto = new BuildingDataDTO();
	    	data_dto.setType(type);
	    	data_dto.setValue(Integer.toString(conditions.get(b.getId())));
	    	data.put(type, data_dto);
	    	b.setData(data);
	    	i++;
	    }*/	
	}
	
	@Test
	public void visualizeIndicator()  {
		// Run the GUI codes on the Event-Dispatching thread for thread safety
		final DeterioationIndicator indicator = new DeterioationIndicator();
		indicator.setScenario(scenario);
		indicator.createGraph();
    	
    	                                               
    	DeterioationGUIMain main = new DeterioationGUIMain(indicator); // Let the constructor do the job
    	main.setVisible(true);
    	int result = JOptionPane.showConfirmDialog(null,"Initial condition correct?", "Unit Test", JOptionPane.YES_NO_OPTION);
		assertEquals (result, JOptionPane.YES_OPTION);
		main.setVisible(false);
		
    	for (int i=0; i<10; i++)  {
    		indicator.deterioate();
	    	main = new DeterioationGUIMain(indicator); // Let the constructor do the job
	    	main.setVisible(true);
	    	result = JOptionPane.showConfirmDialog(null,"Network propagated correct?", "Unit Test", JOptionPane.YES_NO_OPTION);
			assertEquals (result, JOptionPane.YES_OPTION);	
			main.setVisible(false);
    	}
	}
	
	
}
