package org.visico.neighborhoodpss.client;



import java.util.ArrayList;

import org.visico.neighborhoodpss.shared.ProjectDTO;
import org.visico.neighborhoodpss.shared.UserDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
			loginTable.setText(0,0, "loged in as");
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
			projectTable = new FlexTable();
			
			ScenarioServiceAsync service = GWT.create(ScenarioService.class);
			
			try 
			{
				service.getProjects(MainPanel.getInstance().getUser(), new AsyncCallback<ArrayList<ProjectDTO>>()
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
							for (int i=0; i<result.size(); i++)
							{
								ProjectDTO p = result.get(i);
								projectTable.setText(i,0, p.getName());
								projectTable.setWidget(i,3, new Button("Open"));
							}
						}
				});
				
				add(projectTable);
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
	private FlexTable projectTable;
	private UserDTO u;
	
	
	
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
