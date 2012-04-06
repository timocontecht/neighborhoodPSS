package org.visico.neighborhoodpss.client;

import java.util.ArrayList;

import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ScenarioServiceAsync {
	void saveScenarios(ArrayList<Scenario> parentScenarios, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
