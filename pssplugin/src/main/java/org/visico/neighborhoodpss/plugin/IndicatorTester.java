package org.visico.neighborhoodpss.plugin;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.plugin.domain.Plugin;

public class IndicatorTester {

	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, JAXBException {
		ProjectDTO project = new ProjectDTO();
		IndicatorManager manager = new IndicatorManager(project);
		
		manager.initIndicatorByFileName(args[0], args[1], ClassLoader.getSystemClassLoader());
		
		HashMap<Plugin, IndicatorPlugin> indicators = manager.getPlugins();
		ScenarioDTO scenario = new ScenarioDTO();
		for (Plugin key : indicators.keySet())
		{
			System.out.println(key.getName());
			indicators.get(key).calculate(scenario);
		}

	}

}
