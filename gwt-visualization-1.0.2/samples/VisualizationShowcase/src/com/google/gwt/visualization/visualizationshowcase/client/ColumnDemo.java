/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.visualization.visualizationshowcase.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.gwt.visualization.client.visualizations.ColumnChart.Options;

/**
 * Demo for ColumnDemo visualization.
 */
public class ColumnDemo implements LeftTabPanel.WidgetProvider {
  public Widget getWidget() {
    VerticalPanel result = new VerticalPanel();
    Options options = Options.create();
    options.setHeight(240);
    options.setTitle("Company Performance");
    options.setWidth(400);
    options.set3D(true);
    options.setShowCategories(true);
    options.setEnableTooltip(true);
    options.setMin(0);
    options.setMax(2000);

    DataTable data = Showcase.getCompanyPerformance();

    ColumnChart viz = new ColumnChart(data, options);
    Label status = new Label();
    Label onMouseOverAndOutStatus = new Label();
    viz.addSelectHandler(new SelectionDemo(viz, status));
    viz.addReadyHandler(new ReadyDemo(status));
    viz.addOnMouseOverHandler(new OnMouseOverDemo(onMouseOverAndOutStatus));
    viz.addOnMouseOutHandler(new OnMouseOutDemo(onMouseOverAndOutStatus));
    result.add(status);
    result.add(onMouseOverAndOutStatus);
    result.add(viz);
    return result;
  }
}
