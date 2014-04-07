/**
 * 
 */
package com.emodeen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * @author Eric
 *
 */
public class MapFile extends File {
	
	private List<Area> areas;
	private Point[][] points;
	private BufferedReader input;
	int rootNode = 0;
	private int maxArea;
	
	//boolean [] visited;
	//int NNodes;
	//int[][]  adjMatrix;

	/**
	 * @param pathname
	 */
	public MapFile( String path) {
		super( path);
		
		
	}


	
	/**
	public void bfs()
	{
	      // BFS uses Queue data structure

		Queue<Integer> q = new LinkedList<Integer>();

	    q.add(rootNode);
	    visited[rootNode] = true;

	    //printNode(rootNode);

	    while( !q.isEmpty() )
	    {
	       int n, child;

	       n = (q.peek()).intValue();
	       
	       System.out.println("n= " + n);

	       child = getUnvisitedChildNode(n);
	       
	       System.out.println("unvisited child node= " + child);

	       if ( child != -1 )
	       {
	    	  // set to true when the x-coordinate equals 1 and has been visited.
	          visited[child] = true;
	          
	          System.out.println("visited[" + child + "] set to true");

	          //printNode(child);

	          q.add(child);
	       }
	       else
	       {
	          q.remove();
	       }
	    }

	       clearVisited();      //Clear visited property of nodes
	   }
*/

	
	   /**
	    * 
	    * @param n The y-coordinate
	    * @return Returns the x-coordinate for all unvisited nodes that equal 1 for the passed in y-coordinate. 
	    
	   int getUnvisitedChildNode(int n)
	   {
	      int j;

	      for ( j = 0; j < NNodes; j++ )
	      {
	    	  if ( adjMatrix[n][j] > 0 )
	    	  {
	    		  if ( ! visited[j] )
	    			  return(j);
	    	  }
	      }

	      return(-1);
	   }
	   */
	
	/**
	 * This method returns a Point that is adjacent to the parent point and has not been visited.
	 * @param node. The parent node.
	 * @return
	 
	private Point getUnvisitedChildNode( Point parent) {
		
		 Point child = null;

		 int n = parent.getyCoordinate();

	     for (int j = 0; j < points.length; j++)
	     {
	    	 if ( points[n][j].getType() == CharType.CAPITAL_X)
	         {
	    		 if ( !points[n][j].isVisited())
	    			 return child;
	         	}
	      	}

	     return null;
	}
	
	/**
	 * 
	 * @param p The Point to use as the root of the search.
	 
	private void breadthFirstSearch( Point rootNode) {
		
		// BFS uses Queue data structure
		Queue queue = new LinkedList();
		
		queue.add( rootNode);
		
		// Add the point to the area.
		printNode( rootNode.getxCoordinate() + "," + rootNode.getyCoordinate());
		
		rootNode.setVisited( true);
		
		while(!queue.isEmpty()) {
		
			// Retrieve the head node in the queue.
			Point parent = (Point)queue.peek();
			
			Point child = null;
			
	        child = getUnvisitedChildNode( parent);

	        if ( child != null)
	        {
	           child.setVisited( true);

	   		   printNode( rootNode.getxCoordinate() + "," + rootNode.getyCoordinate());
	           
	           queue.add(child);
	        }
	     
	        else
	        {
	           queue.remove();
	        }
	     }
		
		// Clear visited property of nodes
		clearVisited();
	}
	*/
	
	   /**
	void clearVisited()
	{
	   for (int i = 0; i < points.length; i++) {
			
		   for(int j=0; j < points[i].length; j++) {
	      
			   points[i][j].setVisited(false);
		   }
	   }
	}
	*/
	   
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MapFile file = new MapFile( args[0]);
		int numRows = 0;
		
		file.open();
		
		file.loadPointsArray();
		
		file.setMaxArea();
		
		file.printMaxArea();
		
		file.close();
	}
	
	/**
	 */
	private void loadPointsArray()
	{
		int numRows = 0;
		String strRow = null;
		int strLen = 0;
		List<Point> pointList = new ArrayList<Point>();
		CharType type = null;
		
		try {
			do {
				strRow = input.readLine();
				
				if ((strRow != null) && (!strRow.equals(""))) {
					
					numRows++;
					strLen = strRow.length();

					// Iterate through the points in the row, storing them in the points array.
					for(int i=0; i < strRow.length(); i++) {
									
						if ( strRow.charAt(i) == '.') {
							type = CharType.DOT;
						}
									
						else if (strRow.charAt(i) == 'X') {
							type = CharType.CAPITAL_X;
						}
								
						else {
							// illegal character
						}
									
						pointList.add( new Point( i, numRows-1, type));
					}
				}
			} 
			
			while( strRow != null);
		}
		
		catch ( IOException e) {
			System.out.println( "Error reading row");
		}
		
		points = new Point[strLen][numRows];
		
		Iterator<Point> iterator = pointList.iterator();
		
		for(int y=0; y < points.length; y++) {
			for(int z=0; z < points[y].length; z++) {
				points[y][z] = iterator.next();
			}
		}
	}
	
	/**
	 * Later, I can re-factor this to use a List of Points.
	 */
	private void setMaxArea() {
		
		int tempArea = 0;
		
		for(int i=0; i < points.length; i++) {
			
			for(int j=0; j < points[i].length; j++) {
				
				if( points[i][j].getType() == CharType.DOT) {
					
					tempArea = floodFill( points[i][j], CharType.DOT, CharType.AMPERSAND);
					
					if ( tempArea > maxArea) {
						this.maxArea = tempArea;
					}
				}
			}
		}
	}
	
	private int floodFill( Point startPoint, CharType findChar, CharType replaceChar) {
	
		int areaSize = 0;
		int xCoord = startPoint.getxCoordinate();
		int yCoord = startPoint.getyCoordinate();
		
		if ( findChar == replaceChar) {
			return 0;
		}
		
		if ( startPoint.getType() != findChar) {
			return 0;
		}
		
		startPoint.setType( replaceChar);
		areaSize++;
		
		if (xCoord != 0) {
			floodFill( points[xCoord-1][yCoord], findChar, replaceChar);
		}
		
		if (xCoord != (points.length-1)) {
			floodFill( points[xCoord+1][yCoord], findChar, replaceChar);
		}
		
		if (yCoord != 0) {
			floodFill( points[xCoord][yCoord-1], findChar, replaceChar);
		}
		
		if (yCoord != (points[xCoord].length-1)) {
			floodFill( points[xCoord][yCoord+1], findChar, replaceChar);
		}
		
		return areaSize;
	}
	

	
	/**
	 * Traverse the file and store the points in the array of points.
	 
	private void read()
	{
		int rowNum = 0;

		initializePointsArray();
		
		// Read the first row.
		String row = readRow( rowNum);
		
		while ((row != null) && (!row.equals("")))
		{
			rowNum++;
			row = readRow( rowNum);
		}
		
		row = null;
	}
	
	/**
	 *
	 * @return A row from the file.
	 * Reads a row of the file, returning a Row object.
	private String readRow( int rowNum)
	{
		String strRow = null;
		CharType type = null;
		
		try {
			
			strRow = input.readLine();
		}
		
		catch ( IOException e) {
			System.out.println( "Error reading row");
		}
			

		
		strRow = null;
		
		return strRow;
	}
*/
	
	/**
	 * Scan the file to find areas of dots.
	 */
	private void lookForAreas() {
		
		Area currentArea = null;

		for(int i=0; i < points.length; i++) {
			
			for(int j=0; j < points[i].length; j++) {
				
				if( points[i][j].getType() == CharType.DOT) {
					
					// Check point to the NORTH, and add it to the area if needed.
					if( (points[i][j-1] != null) && (points[i][j-1].getType() == CharType.DOT)) {
						
						if( currentArea == null) {
							currentArea = new Area();
						}
						
						currentArea.addPoint( points[i][j-1]);
					}
				}
			}
		}
	}
	

	
	private void printMaxArea() {
		
		StringBuffer strBuf = new StringBuffer("The largest area in the file is ").append(this.maxArea);
		
		System.out.println( strBuf.toString());
	}
	
	
	/**
	 * Open the file.
	 */
	private void open()
	{
		try {
			input = new BufferedReader( new FileReader( this));
		}
		
		catch ( IOException e) {
			System.out.println( "Error opening file");
		}
	}
	
	
	/**
	 * Close the file
	 */
	private void close()
	{
		try {
			input.close();
		}
		
		catch ( IOException e) {
			System.out.println( "Error closing file");
		}
		
		input = null;
	}
	
}
