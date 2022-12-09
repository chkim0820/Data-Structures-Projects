import java.util.LinkedList;

/**
 * WeightedGraph class that works for weighted and directed graphs; for CSDS233 HW 6
 * @author Chaehyeon Kim cxk445
 */
public class WeightedGraph {

    private int maxNum;
    private int numVertices;
    private Vertex[] vertices;

    public WeightedGraph(int max) {
        maxNum = max;
        numVertices = max;
        vertices = new Vertex[max];
    }

    /** Constructing weighted, directed graphs */

    /**
     * Adds an weighted edge from node from to node to
     * @param from node at the beginning of the added edge
     * @param to node at the end of the added edge
     * @param weight weight of the added edge (int)
     * @return true if successful and false otherwise
     */
    public boolean addWeightedEdge(String from, String to, int weight) {

        

        return false;
    }

    /**
     * Adds an edge from node from to each node in tolist with the corresponding weights in weightlist
     * @param from node at the beginning of the added edges
     * @param tolist nodes at the end of the added edges
     * @param weightlist list of weights for the added edges in order
     * @return true if successful and false otherwise
     */
    public boolean addWeightedEdges(String from, String[] tolist, int[] weightlist) {
        return false;
    }

    /**
     * Prints the graph in an adjacency list format. The nodes and their neighbors and their neighbors should be listed in alphabetical order.
     */
    public void printWeightedGraph() {

    }

    /** Shortest paths on directed, weighted graphs */

    /**
     * Uses Dijkstra's algorithm to find the shortest path from node from to node to.
     * @param from the start node when finding the shortest path
     * @param to the end node when finding the shortest path
     * @return The shortest path from node from to node to. If there are multiple paths of equivalent length, only return one of them. If the path does not exist, return an empty array.
     */
    public String[] shortestPath(String from, String to) {
        
    }

    /**
     * Uses Dijkstra's algorithm to find the second shortest path from node from to node to
     * @param from the start node when finding the second shortest path
     * @param to the end node when finding the second shortest path
     * @return Returns the second shortest path between nodes from and to. Only return one path in the case of multiple equivalent results. Return an empty array if no second shortest path exists.
     */
    public String[] secondShortestPath(String from, String to) {
        return null;
    }

    private class Vertex {
        private String name;
        private LinkedList<Edge> edges;
        private boolean encountered;
        private int cost;
        private Vertex parent;

        public Vertex(String name) {
            this.name = name;
            edges = new LinkedList<Edge>();
            encountered = false;
            cost = 0;
            parent = null;
        }
    }

    private class Edge {
        private int endNode;
        private int cost;

        public Edge(int endNode, int cost) {
            this.endNode = endNode;
            this.cost = cost;
        }
    }
}
