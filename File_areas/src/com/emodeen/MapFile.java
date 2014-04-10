/**
 * 
 */
package com.emodeen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * @author Eric Modeen
 *
 */
public class MapFile extends File {
	
	private static final long serialVersionUID = 1L;
	private Point[][] points;
	private BufferedReader input;
	private int maxArea;
	
	/**
	 * A constructor that creates a new MapFile object based on the pathname.
	 * @param pathname The path to the file.
	 */
	public MapFile( String path) {

		super( path);
	}

	/**
	 * @param args Pass in the path to the file as the first argument (args[0]) when running the class.
	 */
	public static void main(String[] args) {
		
		MapFile file = new MapFile( args[0]);
		
		if ( file.fileOK()) {
			
			file.open();
			
			file.readFile();
			
			file.setMaxArea();
			
			file.printMaxArea();
			
			file.close();
		}
	}
	
	/**
	 * This method traverses the file rows, and stores the rows in a list of Lists.
	 */
	private void readFile()
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
			} 
			
			while( strRow != null);
		}
		
		catch ( IOException e) {
			System.out.println( "Error reading row");
		}
		
		loadPointsArray(masterList, maxStrLen, numRows);
	}
	
	/**
	 * This method loads the file characters stored in the List objects into a 2-dimensional array.
	 * @param masterList This is a list of lists, with one list for each row in the file.
	 * @param maxStrLen This is the length of the longest row in the file.
	 * @param numRows The number of rows in the file.
	 */
	private void loadPointsArray(List<List<Point>> masterList, int maxStrLen, int numRows) {
	
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
	 * This method loops through the 2D array of points to find the largest area in the file. 
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
	
	/**
	 * This method is based on the standard floodFill algorithm.  It takes the starting point that is passed in, and changes it to the replacement character if needed.
	 * Then the method recursively inspects the points to the north, south, east, and west until no more dot characters can be found.
	 * @param startPoint A Point object representing the starting point to inspect.
	 * @param areaSize The size of the current area being examined in the file.  It is a count for the recursive function calls.
	 * @return Returns an integer that represents the size of the area connected to the starting point that is passed in.
	 * This size in incremented each time another character replacement is made.  
	 */
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
	
	/**
	 * Print the largest area in the file to the console.
	 */
	private void printMaxArea() {
		
		StringBuffer strBuf = new StringBuffer("The largest area in the file is ").append(this.maxArea);
		
		System.out.println( strBuf.toString());
	}
	
	/**
	 * 
	 * @return This method returns true if the file is a regular file and it can be read.
	 */
	private boolean fileOK() {
		
		boolean fileOK = false;
		boolean readable = false;
		boolean regular = false;
		
		Path path = Paths.get(this.getAbsolutePath());
		
		if ( Files.isRegularFile(path)) {
			regular = true;
		}
		
		else {
			System.out.println("The specified file does not exist, is not a regular file, or it cannot be determined if the file is a regular file or not.");
		}
		
		if ( Files.isReadable(path)) {
			readable = true;
		}
		
		else {
			System.out.println("The specified file is not readable.");
		}
		
		if ( regular && readable) {
			fileOK = true;
		}
		
		return fileOK;
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
