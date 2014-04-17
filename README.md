Map-search
==========

Map-search is a Java program that finds the largest area in a graph.  

Running the program:

Run the class com.emodeen.MapFile, passing the path to the input map file as a parameter.  
For example, you could run “java MapFile C:\Users\mapfile.txt”.  A sample map file is included.

Definitions:

An 'area' is a set of ‘.’ characters bounded by ‘X’ characters.

Assumptions: 

The input file will contain only ‘.’ and ‘X’ characters.

The algorithm:

1.	Read the file, storing each character in a Point object, and each row of Points in a List object.
2.	Load the Point objects into a two-dimensional array.
3.	Loop through the array of Point objects, calling floodFill() for each point.
4.	The floodFill() method contains the standard flood-fill algorithm.  The method is called recursively to find the points that are adjacent to the point that was passed in, and returns the size of the connected area found.
5.	Finally, the largest area that was found is printed to the console.
