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

	// Whether the point has been visited during the BFS.
	private boolean visited;
	
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
	
	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(CharType type) {
		this.type = type;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
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
