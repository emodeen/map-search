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

	private List<Character> chars;
	private int rowNum;
	private String strRow;
	
	
	/**
	 * @param yCoordinate
	 * @param strRow
	 */
	Row(int rowNum, String strRow) {
		
		Character c = null;
		CharType type = null;
		
		this.rowNum = rowNum;
		this.strRow = strRow;
		
		chars = new ArrayList<Character>();
		
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
			
			c = new Character( i, rowNum, type);
			chars.add( c);
		}
	}

	

}
