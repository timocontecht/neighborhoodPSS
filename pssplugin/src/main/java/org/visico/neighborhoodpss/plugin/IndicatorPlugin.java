package org.visico.neighborhoodpss.plugin;

import org.visico.neighborhoodpss.domain.project.ScenarioDTO;



public abstract class IndicatorPlugin {
	

	public abstract void calculate(ScenarioDTO scenario);
}
