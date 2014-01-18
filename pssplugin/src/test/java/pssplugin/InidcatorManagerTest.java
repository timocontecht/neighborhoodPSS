package pssplugin;

import static org.junit.Assert.assertEquals;


import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;


import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.plugin.IndicatorManager;
import org.visico.neighborhoodpss.plugin.IndicatorPlugin;
import org.visico.neighborhoodpss.plugin.domain.Plugin;

public class InidcatorManagerTest {
	
/*
	public void createXML() throws JAXBException
	{
		IndicatorPluginInfo info = new IndicatorPluginInfo();
		info.setAuthor("Timo");
		info.setClassName("Test");
		info.setJar("Test");
		JAXBContext context = JAXBContext.newInstance(IndicatorPluginInfo.class);
	    Marshaller m = context.createMarshaller();
	    m.marshal(info, new File("test.xml"));
		
	}*/
	
	@Test
	public void initIndicatorByFileName() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, FileNotFoundException
	{
		ProjectDTO project = new ProjectDTO();
		IndicatorManager manager = new IndicatorManager(project);
		
		manager.initIndicatorByFileName("src/test/resources/testindicator1.xml", "src/test/resources/", ClassLoader.getSystemClassLoader());
		
		HashMap<Plugin, IndicatorPlugin> indicators = manager.getPlugins();
		ScenarioDTO scenario = new ScenarioDTO();
		for (Plugin key : indicators.keySet())
		{
			System.out.println(key.getName());
			indicators.get(key).calculate(scenario);
		}
		
		
	}
	
	@Test
	public void initIndicatorByName() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, FileNotFoundException
	{
		ProjectDTO project = new ProjectDTO();
		IndicatorManager manager = new IndicatorManager(project);
		
		manager.initIndicatorByIndicatorName("Test Indicator 1", "src/test/resources/", ClassLoader.getSystemClassLoader());
		
		HashMap<Plugin, IndicatorPlugin> indicators = manager.getPlugins();
		ScenarioDTO scenario = new ScenarioDTO();
		for (Plugin key : indicators.keySet())
		{
			System.out.println(key.getName());
			indicators.get(key).calculate(scenario);
		}
		
		
	}
	
	@Test
	public void getIndicatorList() 
	{
		ProjectDTO project = new ProjectDTO();
		IndicatorManager manager = new IndicatorManager(project);
		
		ArrayList<Plugin> indicators = manager.availableIndicators("src/test/resources/");
		assertEquals(indicators.get(0).getName(), "Test Indicator 1");
	}
	
	@Test
	public void additionalDataBuilding()
	{
		ProjectDTO project = new ProjectDTO();
		IndicatorManager manager = new IndicatorManager(project);
		
		ArrayList<Plugin> indicators = manager.availableIndicators("src/test/resources/");
		Plugin info = indicators.get(0);
		assertEquals(info.getData().getBuildingProperty().get(0).getName(), "Condition");
	}

}
