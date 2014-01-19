package org.visico.neighborhoodpss.gwt.client;


import java.util.ArrayList;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.IndicatorDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface IndicatorServiceAsync {
	void getIndicatorList(ProjectDTO project, AsyncCallback<ArrayList<IndicatorDTO>> asyncCallback);
	void activateIndicator (ProjectDTO project, String indicatorName, AsyncCallback<String> asyncCallback);
	void deactivateIndicator (ProjectDTO project, String indicatorName, AsyncCallback<String> asyncCallback);
	void buildingDataTypes (ProjectDTO project, AsyncCallback<Set<String>> asyncCallback);
}
