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
	public void initIndicatorByFileName(String xmlInfoName, String pathToIndicator, ClassLoader classLoader) throws JAXBException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
		
		// read Indicator Plugin information from xml
		File file= new File(xmlInfoName);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
 
		Plugin indPlugInfo = (Plugin) jaxbUnmarshaller.unmarshal(file);
		
		// initialize class
		File jarfile = new File(pathToIndicator + indPlugInfo.getJar());
		URL jarfileurl = new URL("jar", "","file:" + jarfile.getAbsolutePath()+"!/");
		System.out.println("Trying to load file from " + jarfileurl.getPath());
		URLClassLoader cl = URLClassLoader.newInstance(new URL[]{jarfileurl}, classLoader);
		Class<IndicatorPlugin> indPlugClass = (Class<IndicatorPlugin>) cl.loadClass(indPlugInfo.getClassName());
		IndicatorPlugin indPlug = indPlugClass.newInstance();
		
		// add to map
		plugins.put(indPlugInfo, indPlug);
	}
	
	@SuppressWarnings("unchecked")
	public void initIndicatorByIndicatorName(String indicatorName, String pathToIndicator, ClassLoader parentCL) throws JAXBException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
		
		for (Plugin p : availableIndicators(pathToIndicator))  {
			if (p.getName().equals(indicatorName))  {
				// initialize class
				File jarfile = new File(pathToIndicator + p.getJar());
				URL jarfileurl = new URL("jar", "","file:" + jarfile.getAbsolutePath()+"!/");
				System.out.println("Trying to load file from " + jarfileurl.getPath());
				URLClassLoader cl = URLClassLoader.newInstance(new URL[]{jarfileurl}, parentCL);
				Class<IndicatorPlugin> indPlugClass = (Class<IndicatorPlugin>) cl.loadClass(p.getClassName());
				IndicatorPlugin indPlug = indPlugClass.newInstance();
				
				// add to map
				plugins.put(p, indPlug);
			}
		}
	}

	public void removeIndicator(Plugin indPlugInfo)
	{
		plugins.remove(indPlugInfo);
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

	public void unregisterIndicatorByIndicatorName(String indicatorName,
			String pathToIndicators) {
		for (Plugin p : availableIndicators(pathToIndicators))  {
			if (p.getName().equals(indicatorName))  {
				
				// remove from map
				plugins.remove(p);
			}
		}
	}
}
