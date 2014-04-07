/**
 * 
 */
package com.emodeen;

import java.util.*;

/**
 * @author Eric
 *
 */
public class Area {

	/**
	 * 
	 */
	Area() {
		
		contents = new ArrayList<Point>();
	}

	// The dot characters that make up the area.
	private List<Point> contents;
	
	// The 'X' characters that make up the area's boundary.
	private List<Point> boundary;
	
	// The number of dot characters in the area.
	private int size;
	
	void addPoint( Point p) {
		
		contents.add( p);
	}
}
