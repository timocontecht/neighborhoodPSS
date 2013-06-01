package org.visico.neighborhoodpss.client;

import java.util.ArrayList;

import org.visico.neighborhoodpss.shared.dto.NetworkDTO;
import org.visico.neighborhoodpss.shared.dto.ScenarioDTO;
import org.visico.neighborhoodpss.shared.patterns.ObserverInterface;
import org.visico.neighborhoodpss.shared.patterns.ScenarioEditMediator;
import org.visico.neighborhoodpss.shared.patterns.Subject;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;


public class NetworkTable extends Composite implements ObserverInterface
{
	private ScenarioDTO scenario;
	private CellTable<NetworkDTO> table = new CellTable<NetworkDTO>();
	final SingleSelectionModel<NetworkDTO> selectionModel = new SingleSelectionModel<NetworkDTO>();
	ScenarioEditMediator mediator; 
	
	public NetworkTable(ScenarioDTO s, ScenarioEditMediator med)
	{
		this.scenario = s;
		this.scenario.addObserver(this);
		
		this.mediator = med;
		
		TextColumn<NetworkDTO> nameColumn = new TextColumn<NetworkDTO>() {

			@Override
			public String getValue(NetworkDTO object) {
				return object.getName();
			}
			
		};
		
		final SafeHtmlCell coloredCell = new SafeHtmlCell(); 
		Column<NetworkDTO, SafeHtml> colorColumn = new Column<NetworkDTO, SafeHtml>(coloredCell)
				{

					//this is still quite some fix. But it works to color the cell in the 
					// respective color of the network
					@Override
					public SafeHtml getValue(NetworkDTO object) {
						SafeHtmlBuilder sb = new SafeHtmlBuilder(); 
						 sb.appendHtmlConstant("<div style='color: #" + object.getColor() 
                                 + "; background-color: #" 
                                 + object.getColor()
                                 + ";'>"); 
						 sb.appendEscaped("Color"); 
						 sb.appendHtmlConstant("</div>"); 
						 return sb.toSafeHtml(); 
					}
			
				};
				
		final CheckboxCell showNetworkCell = new CheckboxCell();
		Column<NetworkDTO, Boolean> checkBoxColumn = new Column<NetworkDTO, Boolean>(showNetworkCell)
				{

					@Override
					public Boolean getValue(NetworkDTO object) {
						return Boolean.TRUE;
						//return selectionModel.isSelected(object);
					}

					
			
				};
				
		table.addColumn(nameColumn);
		table.addColumn(colorColumn, "Color");
		table.addColumn(checkBoxColumn, "Visible");
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		      public void onSelectionChange(SelectionChangeEvent event) {
		        NetworkDTO selected = selectionModel.getSelectedObject();
		        if (selected != null) {
		          mediator.setSelectedNetwork(selected);
		        }
		      }
		    });
		table.setSelectionModel(selectionModel);
		
		fillTable();
		
		initWidget(table);
	}

	
	private void fillTable() 
	{
		ArrayList<NetworkDTO> scenarios = new ArrayList<NetworkDTO>();
		scenarios.addAll(scenario.getBuildingNetworkDTOs());
		scenarios.addAll(scenario.getGeoNetworkDTOs());
		
		table.setRowCount(scenarios.size());
		table.setRowData(0, scenarios);
		
		
	}


	@Override
	public void update(Subject o) {
		fillTable();
		mediator.setSelectedNetwork(null);
	}


	public NetworkDTO getSelectedNetwork() {
		return selectionModel.getSelectedObject();
	}
	

}
