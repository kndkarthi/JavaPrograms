package Prims;

// Terry Schmidt, November 2014
 
public class Prims {
    private boolean unsettled[];
    private boolean settled[];
    private int numVertices;
    private int adjMatrix[][];
    private int key[];
    public static final int INFINITE = 999;
    private int parent[];
    
    public static void main(String[] args) {
        int adjacencyMatrix[][];
        int amountOfVertices;
        
        StdIn.fromFile("MST adjacency matrix.txt"); //open file
        amountOfVertices = StdIn.readInt(); //read first integer
        System.out.println("Number of vertices: " + amountOfVertices);
        System.out.println("");
        
        adjacencyMatrix = new int[amountOfVertices + 1][amountOfVertices + 1];  // create matrix with correct size
        
        //populate the matrix from the opened file
        for (int i = 1; i <= amountOfVertices; i++) {
            for (int j = 1; j <= amountOfVertices; j++) {
                adjacencyMatrix[i][j] = StdIn.readInt();
                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                    continue;
                }
                if (adjacencyMatrix[i][j] == 0) {
                    adjacencyMatrix[i][j] = INFINITE;
                }
            }
        }
            Prims prims = new Prims(amountOfVertices); //create prims object that seeds unsettled, settled, adjMatrix, key and parent with the correct values
            prims.primsAlg(adjacencyMatrix);
            prims.printWeights();
    }
 
    public Prims(int amountOfVerticesInMatrix) {
        this.numVertices = amountOfVerticesInMatrix;
        unsettled = new boolean[amountOfVerticesInMatrix + 1];
        settled = new boolean[amountOfVerticesInMatrix + 1];
        adjMatrix = new int[amountOfVerticesInMatrix + 1][amountOfVerticesInMatrix + 1];
        key = new int[amountOfVerticesInMatrix + 1];
        parent = new int[amountOfVerticesInMatrix + 1];
    }
    
    public void primsAlg(int adjacencyMatrix[][]) {
        int evaluationVertex;
        for (int source = 1; source <= numVertices; source++)  {
            for (int destination = 1; destination <= numVertices; destination++) {
                this.adjMatrix[source][destination] = adjacencyMatrix[source][destination];
            }
        }
 
        for (int index = 1; index <= numVertices; index++) {
            key[index] = INFINITE;
        }
        
        key[1] = 0;
        unsettled[1] = true;
        parent[1] = 1;
 
        while (findUnsettledCount(unsettled) != 0 == true) {
            evaluationVertex = getMinKeyVertexInUnsettledArray(unsettled);
            unsettled[evaluationVertex] = false;
            settled[evaluationVertex] = true;
            evaluateNearbyVerticesOf(evaluationVertex);
        }
    } 
 
    public int findUnsettledCount(boolean unsettled[]) {
        int count = 0;
        for (int index = 0; index < unsettled.length; index++) {
            if (unsettled[index] == true) {
                count++;
            }
        }
        return count;
    }
 
    private int getMinKeyVertexInUnsettledArray(boolean[] unsettledArray) {
        int min = Integer.MAX_VALUE;
        int node = 0;
        for (int vertex = 1; vertex <= numVertices; vertex++) {
            if (unsettled[vertex] == true && key[vertex] < min) {
                node = vertex;
                min = key[vertex];
            }
        }
        return node;
    }
 
    public void evaluateNearbyVerticesOf(int vertexToEvaluateNeighborsOf) {
        for (int destinationVertex = 1; destinationVertex <= numVertices; destinationVertex++) {
            if (settled[destinationVertex] == false) {
                if (adjMatrix[vertexToEvaluateNeighborsOf][destinationVertex] != INFINITE) {
                    if (adjMatrix[vertexToEvaluateNeighborsOf][destinationVertex] < key[destinationVertex]) {
                        key[destinationVertex] = adjMatrix[vertexToEvaluateNeighborsOf][destinationVertex];
                        parent[destinationVertex] = vertexToEvaluateNeighborsOf;
                    }
                    unsettled[destinationVertex] = true;
                }
            }
        }
    }
 
    public void printWeights() {
        System.out.println("Source     Destination        Weight");
        int totalWeight = 0;
        	for (int vertex = 2; vertex <= numVertices; vertex++)  {
        		System.out.println(parent[vertex] + "\t:\t" + vertex +"\t:\t"+ adjMatrix[parent[vertex]][vertex]);
        		totalWeight += adjMatrix[parent[vertex]][vertex];
        	}
        System.out.println("");
        System.out.println("Total weight: " + totalWeight);
    }
}
