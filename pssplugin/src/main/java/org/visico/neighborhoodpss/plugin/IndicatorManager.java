package org.visico.neighborhoodpss.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.plugin.domain.BuildingProperty;
import org.visico.neighborhoodpss.plugin.domain.Plugin;

public class IndicatorManager {
	HashMap<String, IndicatorPlugin> plugins = new HashMap<String, IndicatorPlugin>();
	ProjectDTO project = null;
	
	public IndicatorManager(ProjectDTO project) {
		this.project = project;
	}
	
	@SuppressWarnings("unchecked")
	public void initIndicatorByFileName(String xmlInfoName, String pathToIndicator, ClassLoader classLoader) throws JAXBException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
		System.out.println("IndicatorManager:initIndicatorByFileName - Path to indicator: " + pathToIndicator);
		System.out.println("IndicatorManager:initIndicatorByFileName - XML file name: " + xmlInfoName);
		
		// read Indicator Plugin information from xml
		File file= new File(xmlInfoName);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
 
		Plugin indPlugInfo = (Plugin) jaxbUnmarshaller.unmarshal(file);
		
		// initialize class
		File jarfile = new File(pathToIndicator + indPlugInfo.getJar());
		System.out.println("IndicatorManager:initIndicatorByFileName - Will load file from " + pathToIndicator + indPlugInfo.getJar());
		URL jarfileurl = new URL("jar", "","file:" + jarfile.getAbsolutePath()+"!/");
		System.out.println("IndicatorManager:initIndicatorByFileName- Tried to load file from " + jarfileurl.getPath());
		URLClassLoader cl = URLClassLoader.newInstance(new URL[]{jarfileurl}, classLoader);
		Class<IndicatorPlugin> indPlugClass = (Class<IndicatorPlugin>) cl.loadClass(indPlugInfo.getClassName());
		IndicatorPlugin indPlug = indPlugClass.newInstance();
		indPlug.setPluginInfo(indPlugInfo);
		
		// add to map
		plugins.put(indPlugInfo.getClassName(), indPlug);
	}
	
	@SuppressWarnings("unchecked")
	public void initIndicatorByIndicatorName(String indicatorName, String pathToIndicator, ClassLoader parentCL) throws JAXBException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
		
		System.out.println("IndicatorManager:initIndicatorByIndicatorName - Path to indicator: " + pathToIndicator);
		for (Plugin p : availableIndicators(pathToIndicator))  {
			if (p.getName().equals(indicatorName))  {
				// initialize class
				System.out.println("Will load file from " + pathToIndicator + "/" + p.getJar());
				JarFile jarfile = new JarFile(pathToIndicator);
				Enumeration e = jarfile.entries();
				
				URL[] urls = { new URL("jar:file" + pathToIndicator +"!/") };
				
				URLClassLoader cl = URLClassLoader.newInstance(urls);
				
				 while (e.hasMoreElements()) {
		                JarEntry je = (JarEntry) e.nextElement();
		                if(je.isDirectory() || !je.getName().endsWith(".class")){
		                    continue;
		                }
		                // -6 because of .class
		                String className = je.getName().substring(0,je.getName().length()-6);
		                className = className.replace('/', '.');
		                Class c = cl.loadClass(className);
		            }
				
				Class<? extends IndicatorPlugin> indPlugClass =  IndicatorPlugin cl..getClass();
				IndicatorPlugin indPlug = indPlugClass.newInstance();
				indPlug.setPluginInfo(p);
				
				// add to map
				plugins.put(p.getClassName(), indPlug);
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
		 
		System.out.println("IndicatorManager:availableIndicators - Path for indicator plugins " + pathToIndicator );
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
				System.out.println("IndicatorManager:availableIndicators - File " + entry.getName() + " is not a valid xml indicator file!");
			}
		}
		
		return indicators;
	}
	
	public HashMap<String, IndicatorPlugin> getPlugins() {
		return plugins;
	}

	public void unregisterIndicatorByIndicatorName(String indicatorName,
			String pathToIndicators) {
		for (Plugin p : availableIndicators(pathToIndicators))  {
			if (p.getName().equals(indicatorName))  {
				// remove from map
				plugins.remove(p.getClassName());
			}
		}
	}

	public Set<String> getBuildingDataTypes() {
		Set<String> buildingData = new HashSet<String>();

		for (String name : plugins.keySet())
		{
			IndicatorPlugin p = plugins.get(name);
			Plugin pluginInfo = p.getPluginInfo();
			
			for (BuildingProperty property : pluginInfo.getData().getBuildingProperty())
			{
				buildingData.add(property.getName());
			}
		}

		return buildingData;
	}
}
