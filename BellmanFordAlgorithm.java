package finalCSC421;

//Terry Schmidt, November 2014
//CSC421 Final program

public class BellmanFordAlgorithm {
    private static int distances[];
    private static int numOfVertices;
    public static final int MAX_VALUE = 99999;
    private static int[][] adjMatrixFromFile;
    
    public static void main(String[] args) {
        int amountOfVertices = 0;
        int srcVertex;
        
        StdIn.fromFile("SP adjacency matrix.txt"); //open file
        amountOfVertices = StdIn.readInt(); //read first integer
        System.out.println("Number of vertices: " + amountOfVertices);
        srcVertex = StdIn.readInt();
        System.out.println("Source Vertex: " + srcVertex);
        System.out.println("");
 
 
        adjMatrixFromFile = new int[amountOfVertices + 1][amountOfVertices + 1];  //set correct size
        
        //populate the matrix from the opened file
        for (int i = 1; i <= amountOfVertices; i++) {
            for (int j = 1; j <= amountOfVertices; j++) {
                adjMatrixFromFile[i][j] = StdIn.readInt();
                if (i == j) {
                    adjMatrixFromFile[i][j] = 0;
                    continue;
                }
                if (adjMatrixFromFile[i][j] == 0) {
                    adjMatrixFromFile[i][j] = MAX_VALUE;
                }
            }
        }
        
        numOfVertices = amountOfVertices;
        distances = new int[numOfVertices+ 1];
        
        bellmanFordAlgorithm(adjMatrixFromFile, srcVertex + 1);  //call the function of the actual algorithm
        
        //print
        for (int vertex = 1; vertex <= amountOfVertices; vertex++) {
            System.out.println("The shortest path from " + srcVertex + " to " + (vertex - 1) + " costs " + distances[vertex] + ".");
        }
    }
 
    public static boolean bellmanFordAlgorithm(int matrix[][], int source) {
        
       for (int node = 1; node <= numOfVertices; node++) {
            distances[node] = MAX_VALUE;
        }
 
        distances[source] = 0;  //init single source
        Relax();  // relax all
 
        for (int srcNode = 1; srcNode <= numOfVertices; srcNode++) {
            for (int destNode = 1; destNode <= numOfVertices; destNode++) {
                if (matrix[srcNode][destNode] != MAX_VALUE) {
                    if (distances[destNode] > distances[srcNode] + matrix[srcNode][destNode]) {
                        return false;  // there is a negative cycle
                    }
                }
            }
        }
        return true;  // there is not a negative cycle
    }
    
    //this relax function relaxes all distances
    public static void Relax() {
        for (int node = 1; node <= numOfVertices - 1; node++) {
            for (int srcNode = 1; srcNode <= numOfVertices; srcNode++) {
                for (int destNode = 1; destNode <= numOfVertices; destNode++) {
                    if (adjMatrixFromFile[srcNode][destNode] != MAX_VALUE) {
                        if (distances[destNode] > distances[srcNode] + adjMatrixFromFile[srcNode][destNode]) {
                            distances[destNode] = distances[srcNode] + adjMatrixFromFile[srcNode][destNode];
                        }
                    }
                }
            }
        }
    }
}
