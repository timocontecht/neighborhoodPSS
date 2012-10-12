package org.visico.neighborhoodpss.shared;


import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.server.Building;



public class Indicators 
{
	static public int knowledgeTransfer(Set<Building> buildings)
	{
		
		double value = 0;
		double max = max_area / EM_SQ * 610 * 5;
		
		
		Iterator<Building> it = buildings.iterator();
		Building a = it.next();
		while (it.hasNext())
		{
			Building b = it.next();
			int atype = typeInt(a.getType());
			int btype = typeInt(b.getType());
			double cluster = CLUSTER[atype][btype];
			double employees = (double)(a.getArea() / EM_SQ + b.getArea() / EM_SQ);
			value = value +  employees * cluster;
			a = b;
		}
		
		return (int)(value / max * 100);
	}
	
	static public int MarketD(Set<Building> buildings)
	{
		
		double value = 0;
		double max = max_area / EM_SQ * 4;
		
		Iterator<Building> it = buildings.iterator();
		while (it.hasNext())
		{
			Building a = it.next();
			value = value + a.getArea() / EM_SQ * MARKET_D[typeInt(a.getType())];
		}
		
		return (int) (value/max*100);
	}
	
	static public int MarketNL(Set<Building> buildings)
	{
		double value = 0;
		double max = max_area / EM_SQ * 13;
		
		Iterator<Building> it = buildings.iterator();
		while (it.hasNext())
		{
			Building a = it.next();
			value = value + a.getArea() / EM_SQ * MARKET_NL[typeInt(a.getType())];
		}
		
		return (int) (value/max*100);
	}
	
	static public int MarketWorld(Set<Building> buildings)
	{
		double value = 0;
		double max = max_area / EM_SQ * 50;
		
		Iterator<Building> it = buildings.iterator();
		while (it.hasNext())
		{
			Building a = it.next();
			value = value + a.getArea() / EM_SQ * MARKET_WORLD[typeInt(a.getType())];
		}
		
		return (int) (value/max*100);
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
	
	
	
	private static int [][] CLUSTER = 
		{
			{	0,	300,	350,	275,	610,	610,	610},
			{ 300,	  0,	350,	300,	610,	610,	610},
			{ 350,	350,	  0,	350,	610,	610,	610},
			{ 275,	300,	350,	  0, 	610,	590,	590},
			{ 610,	610, 	610,	610,	  0, 	590,	590},
			{ 610,	610,	610,	610,	590,	  0,	450}
		};
	
	private static double [] MARKET_D =
		{3,	2,	0.5,	4,	2,	1, 1.5};
	
	private static double[] MARKET_NL =
		{4,	9,	10,	10,	0,	6,	13,	9};
	
	private static double[] MARKET_WORLD =
		{18,	50,	17,	17,	20,	20,	20};
	
	
	
	// employees per square meter
	private static double EM_SQ = 40;
	
	// maximum area to calculate percentages
	private static double max_area = 4090825; 
}
