package org.visico.neighborhoodpss.client;


import java.util.Set;

import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("scenario")
public interface ScenarioService extends RemoteService 
{
	String saveScenarios(Set<Scenario> scenarios) throws IllegalArgumentException;
	
}
