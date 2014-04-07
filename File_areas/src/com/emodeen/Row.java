/**
 * 
 */
package com.emodeen;

import java.util.*;

/**
 * @author Eric
 *
 */
public class Row {

	private List<Point> points;
	private int rowNum;
	private String strRow;
	
	
	/**
	 * @param yCoordinate
	 * @param strRow
	 */
	Row(int rowNum, String strRow) {
		
		Point p = null;
		CharType type = null;
		
		this.rowNum = rowNum;
		this.strRow = strRow;
		
		points = new ArrayList<Point>();
		
		// Store the characters in the chars List.
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
			
			//p = new Point( i, this, type);
			points.add( p);
		}
	}

	void search() {
		
		Iterator<Point> iterator = null;
		Point p = null;
		
		iterator = points.iterator();
			
		while (iterator.hasNext()) {
				
			p = iterator.next();
			//p.search();
		}
	}
}
