/**
 * 
 */
package com.emodeen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Eric Modeen
 *
 */
public class MapFile extends File {
	
	private Point[][] points;
	private BufferedReader input;
	int rootNode = 0;
	private int maxArea;
	
	/**
	 * @param pathname
	 */
	public MapFile( String path) {

		super( path);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MapFile file = new MapFile( args[0]);
		
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
		List<Point> pointList = null;
		CharType type = null;
		int maxStrLen = 0;
		
		// This is a list of lists, one per row in the file.
		List<List<Point>> masterList = new ArrayList<List<Point>>();
		
		try {
			do {
				strRow = input.readLine();
				
				if ((strRow != null) && (!strRow.equals(""))) {
					
					pointList = new ArrayList<Point>();
					numRows++;
					
					strLen = strRow.length();
					
					if ( strLen > maxStrLen) {
						maxStrLen = strLen;
					}

					// Loop through the points in the row
					for(int i=0; i < strRow.length(); i++) {
									
						if ( strRow.charAt(i) == '.') {
							type = CharType.DOT;
						}
									
						else if (strRow.charAt(i) == 'X') {
							type = CharType.CAPITAL_X;
						}
								
						pointList.add( new Point( i, numRows-1, type));
					}
				}
				
				masterList.add( pointList);
				//pointList = null;
			} 
			
			while( strRow != null);
		}
		
		catch ( IOException e) {
			System.out.println( "Error reading row");
		}
		
		points = new Point[numRows][maxStrLen];
		
		Iterator<List<Point>> listIterator = masterList.iterator();
		
		// Loop through the list of file rows
		while (listIterator.hasNext()) {
			
			List<Point> pointList2 = (List<Point>)listIterator.next();
			
			Iterator<Point> iterator2 = pointList2.iterator();
			
			// Loop through the Points in each row.
			while (iterator2.hasNext()) {
				
				Point p = (Point) iterator2.next();
				points[p.getyCoordinate()][p.getxCoordinate()] = p;
			}
		}
	}
	
	/**
	 */
	private void setMaxArea() {
		
		int areaSize = 0;
		
		for(int i=0; i < points.length; i++) {
			
			for(int j=0; j < points[i].length; j++) {
				
				areaSize = 0;
				
				if (points[i][j] != null) {
					areaSize = floodFill( points[i][j], areaSize);
					
					if ( areaSize > maxArea) {
						this.maxArea = areaSize;
					}
				}
			}
		}
	}
	
	private int floodFill( Point startPoint, int areaSize) {
	
		int xCoord = startPoint.getxCoordinate();
		int yCoord = startPoint.getyCoordinate();
		
		// Do not change the character if it is not a dot.
		if ( startPoint.getType() != CharType.DOT) {
			return areaSize;
		}
		
		// Check if this point has been visited.
		if ( startPoint.isVisited()) {
			return areaSize;
		}
		
		// Change the character to an ampersand, and increase the size of the current area.
		startPoint.setType( CharType.AMPERSAND);
		startPoint.setVisited( true);
		areaSize++;
		
		// one step to the west
		if (xCoord != 0) {
			areaSize = floodFill( points[yCoord][xCoord-1], areaSize);
		}
		
		// one step to the east
		if (xCoord != (points[yCoord].length-1)) {
			areaSize = floodFill( points[yCoord][xCoord+1], areaSize);
		}
		
		// one step to the north
		if (yCoord != 0) {
			areaSize = floodFill( points[yCoord-1][xCoord], areaSize);
		}
		
		// one step to the south
		if (yCoord != (points.length-1)) {
			areaSize = floodFill( points[yCoord+1][xCoord], areaSize);
		}
		
		return areaSize;
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
