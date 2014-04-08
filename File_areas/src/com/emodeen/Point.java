/**
 * 
 */
package com.emodeen;

/**
 * @author Eric
 *
 */
public class Point {

	private int xCoordinate;
	private int yCoordinate;
	private CharType type;
	/**
	 * @param type the type to set
	 */
	public void setType(CharType type) {
		this.type = type;
	}


	private Point leftPoint;
	private Point rightPoint;
	private Point abovePoint;
	private Point belowPoint;
	
	// Whether the Point has been added to an area.
	private boolean placedInArea;
	
	// Whether the point has been visited during the BFS.
	private boolean visited;
	
	//private List surroundingPoints;
	
	
	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}


	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}


	/**
	 * @return the areaFound
	 */
	public boolean isPlacedInArea() {
		return placedInArea;
	}


	/**
	 * @param areaFound the areaFound to set
	 */
	public void setPlacedInArea(boolean areaFound) {
		this.placedInArea = areaFound;
	}


	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	Point(int xCoordinate, int yCoordinate, CharType type) {

		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.type = type;
		this.visited = false;
	}


	void scanAdjacentPoints() {
		
		scanNorth();
		
	}
	
	private void scanNorth() {
		
		
	}
	
	private void scanSouth() {
		
	}
	
	private void scanEast() {
		
	}
	
	private void scanWest() {
		
	}


	/**
	 * @return the xCoordinate
	 */
	public int getxCoordinate() {
		return xCoordinate;
	}


	/**
	 * @return the yCoordinate
	 */
	public int getyCoordinate() {
		return yCoordinate;
	}


	/**
	 * @return the type
	 */
	public CharType getType() {
		return type;
	}
	
}
