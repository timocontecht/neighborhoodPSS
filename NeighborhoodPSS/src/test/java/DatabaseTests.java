import static org.junit.Assert.*; 

import java.util.ArrayList;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.visico.neighborhoodpss.gwt.server.project.db.Building;
import org.visico.neighborhoodpss.gwt.server.project.db.BuildingData;
import org.visico.neighborhoodpss.gwt.server.project.db.BuildingDataType;
import org.visico.neighborhoodpss.gwt.server.project.db.HibernateUtil;

public class DatabaseTests {

	@Test
	public void TestAddBuildingDataWrite ()
	{
		Building b = new Building();
		
		BuildingData bd = new BuildingData();
		BuildingDataType bdt = new BuildingDataType();
		bdt.setDefault_val("3.0");
		bdt.setMaximum(3.0);
		bdt.setMinimum(3.0);
		bdt.setName("Test");
		bdt.setType("int");
		
		bd.setType(bdt);
		ArrayList<BuildingData> bdl = new ArrayList<BuildingData>();
		bdl.add(bd);
		b.setData(bdl);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Transaction tx = null;
	    
    	try  
    	{
		    tx = session.beginTransaction();
		    
		    session.saveOrUpdate(b);
			b.update_dtoIds();
			
			tx.commit();
			
    	}
    	catch (Exception e)
	    {
	    	if (tx != null) tx.rollback();
	    }
	}
}
