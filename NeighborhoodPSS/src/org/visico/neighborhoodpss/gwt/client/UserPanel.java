package org.visico.neighborhoodpss.gwt.client;



import java.util.ArrayList;

import org.visico.neighborhoodpss.gwt.shared.dto.ProjectDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.UserDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class UserPanel extends HorizontalPanel implements ClickHandler
{
	static private UserPanel instance = null;
	
	static UserPanel getInstance()
	{
		if (instance == null)
		{
			instance = new UserPanel();
		}
		return instance;
	}
	
	private UserPanel()
	{
		refresh();
	}
	
	private void refresh()
	{
		this.clear();
		drawLoginTable();
		drawProjectTable();
	}
	
	private void drawLoginTable()
	{
		loginTable = new FlexTable();
		
		if (u == null)
		{
		
			loginTable = new FlexTable();
			loginTable.setText(0, 0, "Username:");
		    userNameTB = new TextBox();
		    loginTable.setWidget(0, 1, userNameTB);
		    
		    loginTable.setText(1, 0, "Password:");
		    passwordTB = new PasswordTextBox();
		    loginTable.setWidget(1, 1, passwordTB);
		    
		    //rememberMeCB = new CheckBox("remember me");
		    //loginTable.setWidget(2, 1, rememberMeCB);
		    
		    final Button loginBtn = new Button("Login");
		    loginBtn.addClickHandler(this);
		    loginTable.setWidget(2, 1, loginBtn);
		    
		}
		else
		{
			loginTable.setText(0,0, "Logged in as");
			loginTable.setText(1,0, u.getName());
			
			Button logout_btn = new Button("Log out");
			logout_btn.addClickHandler(new ClickHandler()
			{

				public void onClick(ClickEvent event) 
				{
					MainPanel.getInstance().setUser(null);
					refresh();
				}
				
			});
			loginTable.setWidget(2,0, logout_btn);
			
			
		}
		add(loginTable);
		
	}
	
	private void drawProjectTable()
	{
		if (u != null)
		{
			projectList = new ListBox();
			projectList.setVisibleItemCount(1);
			
			ScenarioServiceAsync service = GWT.create(ScenarioService.class);
			
			try 
			{
				service.getProjects(u, new AsyncCallback<ArrayList<ProjectDTO>>()
				{
						public void onFailure(Throwable caught) 
						{
							// Show the RPC error message to the user
							DialogBox dialogBox = new DialogBox();	
							dialogBox.setText("Remote Procedure Call - Failure");
							dialogBox.center();
						}
	
						public void onSuccess(ArrayList<ProjectDTO> result) 
						{
							if (result == null)
							{
								Window.alert("Failed to load projects from server!");
							}
							else
							{
								projects = new ArrayList<ProjectDTO>();
								for (int i=0; i<result.size(); i++)
								{
									ProjectDTO p = result.get(i);
									projects.add(p);
									projectList.addItem(p.getName(), Integer.toString(i));
								}
							}
						}
				});
				
				add(projectList);
				
				Button selectProject = new Button("Open Project");
				add(selectProject);
				selectProject.addClickHandler(new ClickHandler()
				{
					@Override
					public void onClick(ClickEvent event) {
						int selection = Integer.parseInt(projectList.getValue(projectList.getSelectedIndex()));
						HierarchyPanel.getInstance().setProject(projects.get(selection));
					}
					
				});
			} 
			catch (IllegalArgumentException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}
	
	private TextBox userNameTB;
	private PasswordTextBox passwordTB;
	//private CheckBox rememberMeCB;
	private FlexTable loginTable;
	private ListBox projectList;
	private UserDTO u;
	private ArrayList<ProjectDTO> projects;
	
	
	
	@Override
	public void onClick(ClickEvent event) 
	{
		ScenarioServiceAsync service = GWT.create(ScenarioService.class);

		try 
		{
			service.login(userNameTB.getText(), passwordTB.getText(), new AsyncCallback<UserDTO>() 
			{
					public void onFailure(Throwable caught) 
					{
						// Show the RPC error message to the user
						DialogBox dialogBox = new DialogBox();	
						dialogBox.setText("Remote Procedure Call - Failure");
						dialogBox.center();
					}

					public void onSuccess(UserDTO result) 
					{
						if (result == null)
							Window.alert("Login failed. Please verify your user name and password!");
						else
						{
							u = result;
							refresh();
						}
					}

					
			});
		} 
		catch (IllegalArgumentException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
}
