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
	private List<Row> rows;
	private BufferedReader input;

	/**
	 * @param pathname
	 */
	public MapFile(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param uri
	 */
	public MapFile(URI uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param child
	 */
	public MapFile(String parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param child
	 */
	public MapFile(File parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		MapFile file = new MapFile( args[0]);
		
		file.open();
		
		file.readRows();
		
		file.findAllAreas();
		
		file.printMaxArea();
		
		file.close();
	}
	
	/**
	 * Traverse the file and store the rows in the List of rows.
	 */
	private void readRows()
	{
		int rowNum = 0;
		
		// Read the first row.
		Row row = readRow( rowNum);
		
		if ( row != null) {
			rows = new ArrayList<Row>();
		}
		
		while ( row != null)
		{
			rows.add( row);
			rowNum++;
			row = readRow( rowNum);
		}
		
		row = null;
	}
	
	/**
	 *
	 * @return A row from the file.
	 * Reads a row of the file, returning a Row object.
	 */
	private Row readRow( int rowNum)
	{
		Row row  = null;
		String strRow = null;
		
		try {
			strRow = input.readLine();
			
	System.out.println(strRow);
			
			if ( strRow != null) {
				row = new Row( rowNum, strRow);
			}
		}
		
		catch ( IOException e) {
			System.out.println( "Error reading row");
		}
		
		strRow = null;
		
		return row;
	}

	
	private void findAllAreas() {
		
		Iterator<Row> iterator = null;
		Row row = null;
		
		if ( rows != null) {
			
			iterator = rows.iterator();
			
			while (iterator.hasNext()) {
				
				row = iterator.next();
			}
		}
		

	}
	
	private void printMaxArea() {
		
		StringBuffer strBuf = new StringBuffer("The largest area in the file is ");
		
		
		
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
	 * Close the CSV file
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
