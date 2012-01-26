package org.visico.neighborhoodpss.shared;

import org.visico.neighborhoodpss.client.Building;

public class Indicators 
{
	static public int knowledgeTransfer()
	{
		int size = Building.buildings.size();
		
		double value = 0;
		double max = max_area / EM_SQ * 610 * 5;
		
		
		
		for (int i=0; i<size; i++)
		{
			for (int j=i+1; j<size; j++)
			{
				Building a = Building.buildings.get(i);
				Building b = Building.buildings.get(j);
				int atype = typeInt(a.getType());
				int btype = typeInt(b.getType());
				double cluster = CLUSTER[atype][btype];
				double employees = (double)(a.getArea() / EM_SQ + b.getArea() / EM_SQ);
				value = value +  employees * cluster;
				
			}
		}
		
		return (int)(value / max * 100);
	}
	
	static private int typeInt(String type)
	{
		if (type.equals("Machinery"))
			return 0;
		else if (type.equals("Transport"))
			return 1;
		else if (type.equals("Metalling"))
			return 2;
		else if (type.equals("Construction"))
			return 3;
		else if (type.equals("Printing"))
			return 4;
		else if (type.equals("Chemicals"))
			return 5;
		else
			return -1;
	}
	
	static public int MarketD()
	{
		return 30;
	}
	
	static public int MarketNL()
	{
		return 80;
	}
	
	static public int MarketWorld()
	{
		return 100;
	}
	
	private static int [][] CLUSTER = 
		{
			{	0,	300,	350,	275,	610,	610,	610},
			{ 300,	  0,	350,	300,	610,	610,	610},
			{ 350,	350,	  0,	350,	610,	610,	610},
			{ 275,	300,	350,	  0, 	610,	590,	590},
			{ 610,	610, 	610,	610,	  0, 	590,	590},
			{ 610,	610,	610,	610,	590,	  0,	450}
		};
	
	private static int [] MARKET_D =
		{};
	
	// employees per square meter
	private static double EM_SQ = 40;
	
	// maximum area to calculate percentages
	private static double max_area = 4090825; 
}
