package org.visico.neighborhoodpss.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.plugin.domain.Plugin;

public class IndicatorManager {
	HashMap<Plugin, IndicatorPlugin> plugins = new HashMap<Plugin, IndicatorPlugin>();
	ProjectDTO project = null;
	
	public IndicatorManager(ProjectDTO project) {
		this.project = project;
	}
	
	@SuppressWarnings("unchecked")
	public void initIndicator(String xmlInfoName, String pathToIndicator) throws JAXBException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
		
		// read Indicator Plugin information from xml
		File file = new File(xmlInfoName);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
 
		Plugin indPlugInfo = (Plugin) jaxbUnmarshaller.unmarshal(file);
		
		// initialize class
		File jarfile = new File(pathToIndicator + indPlugInfo.getJar());
		URL jarfileurl = new URL("jar", "","file:" + jarfile.getAbsolutePath()+"!/");
		URLClassLoader cl = URLClassLoader.newInstance(new URL[]{jarfileurl});
		Class<IndicatorPlugin> indPlugClass = (Class<IndicatorPlugin>) cl.loadClass(indPlugInfo.getClassName());
		IndicatorPlugin indPlug = indPlugClass.newInstance();
		
		// add to map
		plugins.put(indPlugInfo, indPlug);
	}

	public ArrayList<Plugin> availableIndicators(String pathToIndicator) 
	{
		ArrayList<Plugin> indicators = new ArrayList<Plugin>();
		
		File folder = new File(pathToIndicator);
		
		for (final File entry : folder.listFiles())
		{
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Plugin indPlugInfo = (Plugin) jaxbUnmarshaller.unmarshal(entry);
				indicators.add(indPlugInfo);
			}
			catch(JAXBException ex)
			{
				System.out.println("File " + entry.getName() + " is not a valid xml indicator file!");
			}
		}
		
		return indicators;
	}
	
	public HashMap<Plugin, IndicatorPlugin> getPlugins() {
		return plugins;
	}
}
