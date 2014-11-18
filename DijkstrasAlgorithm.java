package WarmUp;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

//Terry Schmidt, November 2014

public class DijkstrasAlgorithm {
	public static void main(String[] args) {
    	StdIn.fromFile("SP adjacency matrix.txt"); //open file
    	int amountOfVertices = StdIn.readInt(); //read first integer
    	System.out.println("Number of vertices: " + amountOfVertices);
    	
    	//create vertices
    	Vertex v1 = new Vertex("Vertex 1");
    	Vertex v2 = new Vertex("Vertex 2");
    	Vertex v3 = new Vertex("Vertex 3");
    	Vertex v4 = new Vertex("Vertex 4");
    	Vertex v5 = new Vertex("Vertex 5");
    	Vertex v6 = new Vertex("Vertex 6");
    	Vertex v7 = new Vertex("Vertex 7");
    	Vertex v8 = new Vertex("Vertex 8");
    	Vertex v9 = new Vertex("Vertex 9");
    	
    	
    	int[] allAdjacencies = StdIn.readInts();
    	
    	//create adjacency arrays
    	int[] v1adjacencies = new int[9];
    	int[] v2adjacencies = new int[9];
    	int[] v3adjacencies = new int[9];
    	int[] v4adjacencies = new int[9];
    	int[] v5adjacencies = new int[9];
    	int[] v6adjacencies = new int[9];
    	int[] v7adjacencies = new int[9];
    	int[] v8adjacencies = new int[9];
    	int[] v9adjacencies = new int[9];
    	
    	System.out.println("");
    	System.out.println("Adjacency Matrix: ");
    	
    	
    	// populate adjacency array for each vertex
    	for(int i = 0; i < 9; i++) {
    		v1adjacencies[i] = allAdjacencies[i];
    		System.out.print(v1adjacencies[i] + "     ");
    	}
    	
    	System.out.println("");
    	int counter = 0;
    	for(int i = 9; i < 18; i++) {
    		v2adjacencies[counter] = allAdjacencies[i]; 
    		System.out.print(v2adjacencies[counter] + "     ");
    		counter++;
    	}
    	
    	System.out.println("");
    	counter = 0;
    	for(int i = 18; i < 27; i++) {
    		v3adjacencies[counter] = allAdjacencies[i];
    		System.out.print(v3adjacencies[counter] + "     ");
    		counter++;
    	}
    	
    	System.out.println("");
    	counter = 0;
    	for(int i = 27; i < 36; i++) {
    		v4adjacencies[counter] = allAdjacencies[i];
    		System.out.print(v4adjacencies[counter] + "     ");
    		counter++;
    	}
    	
    	System.out.println("");
    	counter = 0;
    	for(int i = 36; i < 45; i++) {
    		v5adjacencies[counter] = allAdjacencies[i];
    		System.out.print(v5adjacencies[counter] + "     ");
    		counter++;
    	}
    	
    	System.out.println("");
    	counter = 0;
    	for(int i = 45; i < 54; i++) {
    		v6adjacencies[counter] = allAdjacencies[i];
    		System.out.print(v6adjacencies[counter] + "     ");
    		counter++;
    	}
    	
    	System.out.println("");
    	counter = 0;
    	for(int i = 54; i < 63; i++) {
    		v7adjacencies[counter] = allAdjacencies[i];
    		System.out.print(v7adjacencies[counter] + "     ");
    		counter++;
    	}
    	
    	System.out.println("");
    	counter = 0;
    	for(int i = 63; i < 72; i++) {
    		v8adjacencies[counter] = allAdjacencies[i];
    		System.out.print(v8adjacencies[counter] + "     ");
    		counter++;
    	}
    	
    	System.out.println("");
    	counter = 0;
    	for(int i = 72; i < 81; i++) {
    		v9adjacencies[counter] = allAdjacencies[i];
    		System.out.print(v9adjacencies[counter] + "     ");
    		counter++;
    	}
    	
    	//create edges from the adjacency arrays, those left blank are 0
    	v1.adjacencies = new Edge[] { 
    			new Edge(v2, v1adjacencies[1]),
    			new Edge(v3, v1adjacencies[2]),
    			new Edge(v4, v1adjacencies[3])
    	};
    	
    	v2.adjacencies = new Edge[] { 
    			new Edge(v3, v2adjacencies[2]),
    			new Edge(v5, v2adjacencies[4])
    	};
    	
    	v3.adjacencies = new Edge[] { 
    			new Edge(v5, v3adjacencies[4]),
    			new Edge(v6, v3adjacencies[5])
    	};
    	
    	v4.adjacencies = new Edge[] { 
    			new Edge(v3, v4adjacencies[2]),
    			new Edge(v7, v4adjacencies[6])
    	};
    	
    	v5.adjacencies = new Edge[] { 
    			new Edge(v8, v5adjacencies[7])
    	};
    	
    	v6.adjacencies = new Edge[] { 
    			new Edge(v7, v6adjacencies[6]),
    			new Edge(v8, v6adjacencies[7]),
    			new Edge(v9, v6adjacencies[8])
    	};
    	
    	v7.adjacencies = new Edge[] { 
    			new Edge(v9, v7adjacencies[8])
    	};
    	
    	v8.adjacencies = new Edge[] { 
    			new Edge(v2, v8adjacencies[1]),
    			new Edge(v9, v8adjacencies[8])
    	};
    	
    	v9.adjacencies = new Edge[] { 
    			new Edge(v4, v9adjacencies[3]),
    			new Edge(v8, v9adjacencies[7])
    	};
    	
    	System.out.println("");
    	System.out.println("");
    	
    	 Vertex[] vertices = {v1, v2, v3, v4, v5, v6, v7, v8, v9};

         pathFinder(v1);
         
         for (Vertex v: vertices) {
         			System.out.println("Distance to " + v + ": " + v.minimumDistance);
         			List<Vertex> path = findShortestPathTo(v);
         			System.out.println("Path: " + path);
         			System.out.println("");
         }
    }
	
		    public static void pathFinder(Vertex sourceVertex) {
		        sourceVertex.minimumDistance = 0.;
		        PriorityQueue<Vertex> queueOfVertices = new PriorityQueue<Vertex>();
		        queueOfVertices.add(sourceVertex);
		
		        	while (!queueOfVertices.isEmpty()) {
		        		Vertex z = queueOfVertices.poll();
		
		        			// Visit each edge exiting z
		        			for (Edge e : z.adjacencies) {
		        				Vertex v = e.target;
		        				double weight = e.weight;
		        				double distanceThroughZ = z.minimumDistance + weight;
		        					if (distanceThroughZ < v.minimumDistance) {
		        						queueOfVertices.remove(v);
		
		        						v.minimumDistance = distanceThroughZ ;
		        						v.previousVertex = z;
		        						queueOfVertices.add(v);
		        					}
		        			}
		        	}
		    }

    		public static List<Vertex> findShortestPathTo(Vertex someVertex) {
    			List<Vertex> path = new ArrayList<Vertex>();
    				for (Vertex vertex = someVertex; vertex != null; vertex = vertex.previousVertex) {
    					path.add(vertex);
    				}
    			Collections.reverse(path);
    			return path;
    		}
}

		class Vertex implements Comparable<Vertex> {
		    public final String name;
		    public Edge[] adjacencies;
		    public double minimumDistance = Double.POSITIVE_INFINITY;
		    public Vertex previousVertex;
		    public Vertex(String nameOfVertex) { 
		    	name = nameOfVertex; 
		    }
		    public String toString() { 
		    	return name; 
		    }
		    public int compareTo(Vertex vertexToBeCompared) {
		        return Double.compare(minimumDistance, vertexToBeCompared.minimumDistance);
		    }
		}
		
		class Edge {
		    public final Vertex target;
		    public final double weight;
		    public Edge(Vertex vertex, double vertexWeight) { 
		    	target = vertex; 
		    	weight = vertexWeight; 
		    }
		}
