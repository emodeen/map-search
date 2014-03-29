/**
 * 
 */
package com.emodeen;

import java.io.File;
import java.net.URI;
import java.util.*;

/**
 * @author Eric
 *
 */
public class MapFile extends File {
	
	private List<Area> areas;
	private List<Row> rows;

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
		
		file.findAllAreas();
		
		file.printMaxArea();
	}

	
	private void findAllAreas() {
		
	}
	
	private void printMaxArea() {
		
		StringBuffer strBuf = new StringBuffer("The largest area in the file is ");
		
		
		
		System.out.println( strBuf.toString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
