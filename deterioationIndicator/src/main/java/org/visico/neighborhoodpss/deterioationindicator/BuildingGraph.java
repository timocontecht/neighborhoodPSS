package org.visico.neighborhoodpss.deterioationindicator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import delaunay_triangulation.Delaunay_Triangulation;
import delaunay_triangulation.Triangle_dt;

public class BuildingGraph {
	Set<BuildingNode> nodes = new HashSet<BuildingNode>();
	Set<BuildingEdge> edges = new HashSet<BuildingEdge>();
	
	public void addNode(BuildingNode n)  {
		nodes.add(n);
	}
	
	public void addEdge(BuildingEdge e)  {
		if (nodes.contains(e.getStart()) == true && nodes.contains(e.getEnd()) == true)
			edges.add(e);
		else
			System.out.println("Edge not added as nodes are not part of this network. Add nodes first!");
	}

	
	public Set<BuildingNode> getNodes() {
		return nodes;
	}

	public Set<BuildingEdge> getEdges() {
		return edges;
	}

	public void buildGraph()  {
		generateMesh();
	}
	public Set<BuildingNode> getNeighbors(BuildingNode node)  {
		Set<BuildingNode> neighbors = new HashSet<BuildingNode>();
		for (BuildingEdge edge : edges)  {
			if (edge.start.equals(node))
				neighbors.add(edge.end);
			if (edge.end.equals(node))
				neighbors.add(edge.start);
		}
		return neighbors;
	}
	
	private void generateMesh() {
		// triangle mesh algorithm
		
		// collect all center points
		BuildingNode[] allNodes = new BuildingNode[nodes.size()];
		
		Iterator<BuildingNode> nodeit = nodes.iterator();
		int i = 0;
		while (nodeit.hasNext())  {
			allNodes[i] = nodeit.next();
			i++;
		}
			
		Delaunay_Triangulation t = new Delaunay_Triangulation(allNodes);
		
		Iterator<Triangle_dt> triangles = t.trianglesIterator();
		
		while (triangles.hasNext())  {
			Triangle_dt triangle = triangles.next();
			
			// check whether this is a complete triangle
			if (triangle.p1() == null || triangle.p2() == null || triangle.p3() == null)
				continue;
			BuildingEdge edge1 = new BuildingEdge((BuildingNode)triangle.p1(), 
					(BuildingNode)triangle.p2());
			BuildingEdge edge2 = new BuildingEdge((BuildingNode)triangle.p2(), 
					(BuildingNode)triangle.p3());
			BuildingEdge edge3 = new BuildingEdge((BuildingNode)triangle.p1(), 
					(BuildingNode)triangle.p3());
			
			
			edges.add(edge1);
			edges.add(edge2);
			edges.add(edge3);
		}
			
		
	}
}
