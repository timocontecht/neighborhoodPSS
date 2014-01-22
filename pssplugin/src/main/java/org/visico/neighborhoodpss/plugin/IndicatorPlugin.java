package org.visico.neighborhoodpss.plugin;

import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.plugin.domain.Plugin;



public abstract class IndicatorPlugin {
	Plugin pluginInfo;
	
	public Plugin getPluginInfo() {
		return pluginInfo;
	}
	
	public void setPluginInfo(Plugin pluginInfo) {
		this.pluginInfo = pluginInfo;
	}

	public abstract void calculate(ScenarioDTO scenario);
}
