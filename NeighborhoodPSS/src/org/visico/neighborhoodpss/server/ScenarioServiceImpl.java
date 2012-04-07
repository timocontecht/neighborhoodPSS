package org.visico.neighborhoodpss.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.client.ScenarioService;
import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ScenarioServiceImpl extends RemoteServiceServlet implements
		ScenarioService 
{

	

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	} */

	@Override
	public String saveScenarios(Set<Scenario> scenarios)
			throws IllegalArgumentException 
	{
		String test = "Timo";
		test = test + " & Natasha";
		
		Iterator<Scenario> it = scenarios.iterator();
		while (it.hasNext())
		{
			//Scenario parent = scenarios.get(i);
			//Set<Scenario> children = parent.getChildren();
		}
		
		
		return "Scenarios Saved";
	}
}
