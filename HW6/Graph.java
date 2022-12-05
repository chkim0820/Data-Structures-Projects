import java.util.LinkedList;

/**
 * Graph class for CSDS233 HW 6
 * @author Chaehyeon Kim cxk445
 */
public class Graph {

    private Vertex[] vertices;
    private int numVertices;
    private int maxNum;

    public Graph(int max) {
        vertices = new Vertex[max];
        maxNum = max;
        numVertices = 0;
    }

    /** Constructing undirected, unweighted graphs */

    /**
     * Adds a node ot the graph and checks for duplicates
     * @param name name to reference the added node by
     * @return true if successful and false otherwise
     */
    public boolean addNode(String name) {
        return false;
    }

    /**
     * Adds a list of nodes to the graph and checks for duplicates
     * @param names names to reference the added nodes by
     * @return true if successful and false otherwise
     */
    public boolean addNodes(String[] names) {
        return false;
    }

    /**
     * Adds an edge from node from to node to
     * @param from one end of the added edge
     * @param to the other end of the added edge
     * @return true if successful and false otherwise
     */
    public boolean addEdge(String from, String to) {
        return false;
    }

    /**
     * Adds an undirected edge from node from to each node in tolist
     * @param from one end of the added edge
     * @param tolist the other ends of the added edge
     * @return true if successful and false otherwise
     */
    public boolean addEdges(String from, String[] tolist) {
        return false;
    }

    /**
     * Removes a node from the graph along with all connected edges
     * @param name the name of the node to be removed
     * @return true if successful and false otherwise
     */
    public boolean removeNode(String name) {
        return false;
    }

    /**
     * Removes each node in nodelist and their edges from the graph
     * @param nodelist a list of the names of the nodes to be removed
     * @return true if successful and false otherwise
     */
    public boolean removeNodes(String[] nodelist) {
        return false;
    }

    /**
     * Prints the graph in an adjacency list format. The nodes and their neighbors and their neighbors should be listed in alphabetical order.
     */
    public void printGraph() {

    }

    /** Finding paths on undirected, unweighted graphs */

    /**
     * Returns the path or a list of node names of depth-first search between nodes from and to.
     * @param from The starting node of depth-first search
     * @param to The end node of depth-first search
     * @param neighborOrder The order in which neighbors are considered; can be alphabetical or reverse
     * @return The result (the path or a list of node names, of depth-first search between nodes from and to). Return an empty array if no path exists.
     */
    public String[] DFS(String from, String to, String neighborOrder) {
         return null;
    }

    /**
     * Returns the path or a list of node names of breadth-first search between nodes from and to.
     * @param from The starting node of breadth-first search
     * @param to The end node of breadth-first search
     * @param neighborOrder The order in which neighbors are considered; can be alphabetical or reverse
     * @return the path or a list of node names of breadth-first search between nodes from and to
     */
    public String[] BFS(String from, String to, String neighborOrder) {
        return null;
    }

    /**
     * Uses Dijkstra's algorithm to find the shortest path from node from to node to
     * @param from the starting node when finding the shortest path
     * @param to the ending node when finding the shortest path
     * @return the shortest path from node from to node to; only return one in case of multiple equivalent results
     */
    public String[] shortestPath(String from, String to) {
        return null;
    }

    /**
     * Returns the second shortest path between nodes from and to
     * @param from the starting node when finding the second shortest path
     * @param to the ending node when finding the second shortest path
     * @return the second shortest path between nodes from and too; only return one in case of multiple equivalent results
     */
    public String[] secondShortestPath(String from, String to) {
        return null;
    }

    /** 
     * Vertex class; a private, nested class of Graph class
     */
    private class Vertex {
        private String name;
        private LinkedList<Edge> edges;
        private Vertex parent;
        private boolean encountered;
        private int cost;
    }

    /**
     * Edge class; a private, nested class of Graph class
     */
    private class Edge {
        private int endNode;
        private int cost;
    }
}