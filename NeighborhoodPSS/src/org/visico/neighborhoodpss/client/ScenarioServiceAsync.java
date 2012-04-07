package org.visico.neighborhoodpss.client;


import java.util.Set;

import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ScenarioServiceAsync {
	void saveScenarios(Set<Scenario> parentScenarios, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
