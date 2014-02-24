package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;








import org.visico.neighborhoodpss.domain.project.GeoPointDTO;

import delaunay_triangulation.Triangle_dt;
import deterioationIndicator.BuildingEdge;
import deterioationIndicator.BuildingGraph;
import deterioationIndicator.BuildingNode;
import deterioationIndicator.DeterioationIndicator;



public class DeterioationGUIMain extends JFrame {  
	
	private static final long serialVersionUID = -3303646628858791611L;

	// Named-constants for dimensions
	   public static final int CANVAS_WIDTH = 640;
	   public static final int CANVAS_HEIGHT = 480;
	 
	   private DrawCanvas canvas;  // Declare an instance the drawing canvas (extends JPanel)
	   private DeterioationIndicator indicator;
	   
	   private double scale_x, scale_y;
	   private double[] area;
	   
	   /** Constructor to set up the GUI components */
	   public DeterioationGUIMain(DeterioationIndicator ind) {
		   this.indicator = ind;
	      canvas = new DrawCanvas();    // Construct the drawing canvas
	      
	      area = ind.getScaleInfo();
	      
	      scale_x = (area[2] - area[0]) / (CANVAS_WIDTH - 100);
	      scale_y = (area[3] - area[1]) / (CANVAS_HEIGHT - 100);
	      
	      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
	      this.setContentPane(canvas);   
	        
	      //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
	      this.pack();              // Either pack() the components; or setSize()
	      this.setTitle("Deterioation Indicator GUI");  // this JFrame sets the title
	      
	   }
	 
	   /**
	    * DrawCanvas (inner class) is a JPanel used for custom drawing
	    */
	   private class DrawCanvas extends JPanel {
	     

		/**
		 * 
		 */
		private static final long serialVersionUID = -5572459779676557215L;

		// Override paintComponent to perform your own painting
	      @Override
	      public void paintComponent(Graphics g) {
	         super.paintComponent(g);     // paint parent's background
	         
	         setBackground(Color.WHITE);
	     
	         drawBuildings(g);
	         drawCenterPoints(g);
	         drawMesh(g);
	      }

		private void drawMesh(Graphics g) {
			BuildingGraph graph = indicator.getGraph();
			g.setColor (Color.GRAY);
			
			int[] p1 = new int[2];
			int[] p2 = new int[2];
			
			
			for (BuildingEdge e : indicator.getGraph().getEdges())  {
					p1 = scaledPoint(e.getStart().x(), e.getStart().y());
					p2 = scaledPoint(e.getEnd().x(), e.getEnd().y());
					g.drawLine(p1[0], p1[1], p2[0], p2[1]);
			}
			
			
		}

		private void drawCenterPoints(Graphics g) {
			
			for (BuildingNode n : indicator.getBuildingNodes())  {
				int[] pt = scaledPoint(n.x(), n.y());
				g.setColor(Color.DARK_GRAY);
				g.drawOval(pt[0], pt[1], 5, 5);
				g.setColor(Color.GRAY);
				g.fillOval(pt[0], pt[1], 5, 5);
			}
		}

		private void drawBuildings(Graphics g) {
			
			for (BuildingNode n : indicator.getBuildingNodes())  {
				
				List<GeoPointDTO> pts = new ArrayList<GeoPointDTO>(n.getBuilding().getPoints());
				// close the polygon
				pts.add(pts.get(0));
				
				Polygon buildingPoly = new Polygon();
				for (int i=0; i < pts.size() ; i++)  {
					int[] pt1 = scaledPoint(pts.get(i).getLongitude(), pts.get(i).getLatitude());
					buildingPoly.addPoint(pt1[0], pt1[1]);
				}
				g.setColor(Color.BLACK);
				g.drawPolygon(buildingPoly);
				
				
				switch (n.getCondition())  {
				case 1: 
				case 2: 
					g.setColor(Color.RED);
					break;
				case 3:
				case 4:
					g.setColor(Color.MAGENTA);
					break;
				case 5:
				case 6:
					g.setColor(Color.ORANGE);
					break;
				case 7:
				case 8:
					g.setColor(Color.YELLOW);
					break;
				case 9:
				case 10:
					g.setColor(Color.GREEN);
					break;
				}
				
				g.fillPolygon(buildingPoly);
				
				g.setColor(Color.BLACK);
				int[] textPoint = scaledPoint(n.x(), n.y());
				
				g.drawString("" + n.getCondition(), textPoint[0] + 4, textPoint[1] + 20);
				g.drawString("" + n.getBuilding().getId(), textPoint[0] - 20, textPoint[1] + 20);
			}
		}
	   }
	   
	
	 
	   /** Entry main method */
	   public static void main(String[] args) {
	      /*// Run the GUI codes on the Event-Dispatching thread for thread safety
	      SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            new DeterioationGUIMain( new DeterioationIndicator()); // Let the constructor do the job
	         }
	      });*/
		   final DeterioationIndicator indicator = new DeterioationIndicator();
	    	//indicator.calculate(scenario);
	    	DeterioationGUIMain main = new DeterioationGUIMain(indicator); // Let the constructor do the job
	    	main.setVisible(true);
	   }
	   
	   private int[] scaledPoint(double x, double y)  {
		   int[] pt = new int[2];
		   
		   x = x - area[0];
		   y = y - area[1];
		   
		   pt[0] = (int) (x / scale_x) + 50;
		   pt[1] = (int) (y / scale_y) + 50;
		   
		   return pt;
	   }
	   

}
