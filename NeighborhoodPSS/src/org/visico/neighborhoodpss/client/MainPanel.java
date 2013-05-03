package org.visico.neighborhoodpss.client;

import org.visico.neighborhoodpss.shared.dto.UserDTO;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;

public class MainPanel extends DockLayoutPanel
{
	static private MainPanel instance = null;
	
	UserDTO user = null;
	
	public UserDTO getUser() {
		return user;
	}



	public void setUser(UserDTO user) {
		this.user = user;
	}



	static public MainPanel getInstance()
	{
		if (instance == null)
			instance = new MainPanel(Unit.EM);
		return instance;
	}
	
	
	
	private MainPanel(Unit unit) {
		super(unit);
		draw();
	}

	private void draw() {
		//addNorth(UserPanel.getInstance(), 10);
		
		MainTab panel = MainTab.getInstance();
		add(panel);
	}

	
}
