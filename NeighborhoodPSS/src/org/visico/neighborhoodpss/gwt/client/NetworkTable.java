package org.visico.neighborhoodpss.gwt.client;

import java.util.ArrayList;

import org.visico.neighborhoodpss.gwt.shared.patterns.ObserverInterface;
import org.visico.neighborhoodpss.gwt.shared.patterns.ScenarioEditMediator;
import org.visico.neighborhoodpss.gwt.shared.patterns.Subject;
import org.visico.neighborhoodpss.gwt.shared.dto.GeoNetworkDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.NetworkDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.ScenarioDTO;

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


public class NetworkTable extends Composite 
{
	//private ScenarioDTO scenario;
	private CellTable<NetworkDTO> table = new CellTable<NetworkDTO>();
	final SingleSelectionModel<NetworkDTO> selectionModel = new SingleSelectionModel<NetworkDTO>();
	ScenarioEditMediator mediator; 
	
	public NetworkTable( ScenarioEditMediator med)
	{
		
		this.mediator = med;
		med.registerNetworkTable(this);
		
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
		
		initWidget(table);
	}
	
	public void fillTable(ArrayList<GeoNetworkDTO> scenarios) 
	{
		table.setRowCount(scenarios.size());
		table.setRowData(0, scenarios);
	}

	public NetworkDTO getSelectedNetwork() {
		return selectionModel.getSelectedObject();
	}
}
