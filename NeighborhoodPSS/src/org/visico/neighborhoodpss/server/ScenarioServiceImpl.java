package org.visico.neighborhoodpss.server;

import java.util.ArrayList;

import org.visico.neighborhoodpss.client.ScenarioService;
import org.visico.neighborhoodpss.shared.FieldVerifier;
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
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public String saveScenarios(ArrayList<Scenario> scenarios)
			throws IllegalArgumentException 
	{
		String test = "Timo";
		test = test + " & Natasha";
		
		for (int i=0; i<scenarios.size(); i++)
		{
			Scenario parent = scenarios.get(i);
			ArrayList<Scenario> children = parent.children();
		}
		
		
		return "Scenarios Saved";
	}
}
