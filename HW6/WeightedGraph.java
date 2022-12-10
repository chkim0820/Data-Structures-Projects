import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.lang.model.util.ElementScanner6;

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

    /**
     * Searches for a vertex with the input string as its name
     * @param vertexName the name of the vertex searched
     * @return the index of the vertex with the input name
     */
    private int search(String vertexName) {
        for (int i = 0; i < maxNum; i++) {
            if (vertexName.equals(vertices[i].name))
                return i;
        }
        return -1;
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
        int fromIndex = search(from);
        int toIndex = search(to);
        if (fromIndex != -1 && toIndex != -1) { // from and to vertices found & edge can be added
            vertices[fromIndex].edges.addLast(new Edge(toIndex, weight));
            return true;
        }     
        return false; // one or both of from and to vertices could not be found; edge not added
    }

    /**
     * Adds an edge from node from to each node in tolist with the corresponding weights in weightlist
     * @param from node at the beginning of the added edges
     * @param tolist nodes at the end of the added edges
     * @param weightlist list of weights for the added edges in order
     * @return true if successful and false otherwise
     */
    public boolean addWeightedEdges(String from, String[] tolist, int[] weightlist) {
        int fromIndex = search(from); // index of from vertex
        boolean successful = false; // whether all successfully added or not
        if (fromIndex != -1) {
            for (int i = 0; i < tolist.length; i++) {
                if (addWeightedEdge(from, tolist[i], weightlist[i])) {
                    if (i == 0)
                        successful = true;
                }
                else 
                    successful = false;
            }
        }
        return successful; // true if all in tolist added successfully; false otherwise (even when some successfully added & some didn't)
    }

    /**
     * Prints the graph in an adjacency list format. The nodes and their neighbors and their neighbors should be listed in alphabetical order.
     */
    public void printWeightedGraph() {
        int numFound = 0;
        // loop through all vertices
        for (int i = 0; i < maxNum && numFound <= numVertices; i++) {
            if (vertices[i] != null) {
                numFound++;
                // loop through the whole list of edges
                Iterator<Edge> it = vertices[i].edges.iterator();
                ArrayList<String> arr = new ArrayList<>();
                while (it.hasNext()) {
                    arr.add(vertices[it.next().endNode].name); // adding the name of the node at the end of the edge
                }
                arr.sort(String.CASE_INSENSITIVE_ORDER); // sort arr alphabetically
                String[] arrString = (String[]) arr.toArray(); // convert ArrayList arr to String[]
                System.out.println('"' + vertices[i].name +'"' + " -> ");
                for (int j = 0; j < arrString.length; j++) { // print out all neighbors
                    if (j < arrString.length - 1)
                        System.out.print('"' + arrString[j] + '"' + " -> ");
                    else
                        System.out.print('"' + arrString[j]);
                }
            }
        }
    }

    /** Shortest paths on directed, weighted graphs */

    /**
     * Uses Dijkstra's algorithm to find the shortest path from node from to node to.
     * @param from the start node when finding the shortest path
     * @param to the end node when finding the shortest path
     * @return The shortest path from node from to node to. If there are multiple paths of equivalent length, only return one of them. If the path does not exist, return an empty array.
     */
    public String[] shortestPath(String from, String to) {
        return null;
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
