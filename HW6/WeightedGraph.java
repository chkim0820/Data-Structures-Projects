import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * WeightedGraph class that works for weighted and directed graphs; for CSDS233 HW 6
 * @author Chaehyeon Kim cxk445
 */
public class WeightedGraph {

    private ArrayList<Vertex> vertices;

    public WeightedGraph() {
        vertices = new ArrayList<>();
    }

    /**
     * Searches for a vertex with the input string as its name
     * @param vertexName the name of the vertex searched
     * @return the index of the vertex with the input name
     */
    private int search(String vertexName) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertexName.equals(vertices.get(i).name))
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
        if (fromIndex != -1 && toIndex != -1 && weight >= 0) { // from and to vertices found & weight non-negative
            vertices.get(fromIndex).edges.addLast(new Edge(toIndex, weight));
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
        // loop through all vertices
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i) != null) {
                // loop through the whole list of edges
                Iterator<Edge> it = vertices.get(i).edges.iterator();
                ArrayList<String> arr = new ArrayList<>();
                while (it.hasNext()) {
                    arr.add(vertices.get(it.next().endNode).name); // adding the name of the node at the end of the edge
                }
                arr.sort(String.CASE_INSENSITIVE_ORDER); // sort arr alphabetically
                String[] arrString = (String[]) arr.toArray(); // convert ArrayList arr to String[]
                System.out.println('"' + vertices.get(i).name +'"' + " -> ");
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
        PriorityQueue<Vertex> finalized = new PriorityQueue<>(); // PriorityQueue containing all finalized vertices
        int fromIndex = search(from);
        int toIndex = search(to);
        if (fromIndex != -1 && toIndex != -1) {
            finalized.add(vertices.get(fromIndex));
            vertices.get(fromIndex).finalized = true;
            Iterator<Edge> it = vertices.get(fromIndex).edges.iterator();
            // Initializing parents and costs of neighbors
            while (it.hasNext()) {
                Vertex neighbor = vertices.get(it.next().endNode);
                neighbor.parent = vertices.get(fromIndex);
                neighbor.cost = it.next().cost;
                neighbor.encountered = true;
            }
            for (int i = 0; i < vertices.size(); i++) { // rest of nodes' costs also updated
                if (vertices.get(i).encountered == false)
                    vertices.get(i).cost = (int)Double.POSITIVE_INFINITY;
            }
            // traversing using Dijkstra's algorithm
            while (finalized.size() != vertices.size()) {
                int minIndex = 0;
                int minCost;
                if (fromIndex == 0)
                    minCost = vertices.get(1).cost;
                else
                    minCost = vertices.get(0).cost;
                for (int j = 0; j < vertices.size(); j++) { // find smallest cost & index
                    if (!vertices.get(j).finalized) {
                        if (minCost >= vertices.get(j).cost) {
                            minCost = vertices.get(j).cost;
                            minIndex = j;
                        }
                    }
                }
                // add the vertex with the smallest cost to finalized
                finalized.add(vertices.get(minIndex));
                vertices.get(minIndex).finalized = true;
                // update costs of all non-finalized neighbors of the newly-finalized vertex
                Iterator<Edge> it2 = vertices.get(minIndex).edges.iterator();
                while (it2.hasNext()) {
                    Edge edge = it2.next();
                    Vertex next = vertices.get(edge.endNode);
                    if (next.finalized == false) {
                        int original = next.cost;
                        next.cost = minCost(next.cost, next.parent.cost + edge.cost);
                        if (next.cost != original) { // if cost changed, change the parent node
                            next.parent = vertices.get(minIndex);
                        }
                    }
                }
            }
            // build an array list with the shortest path
            ArrayList<String> path = new ArrayList<>();
            Vertex trav = vertices.get(toIndex);
            while (trav != null) {
                path.add(trav.name);
                trav = trav.parent;
            }
            // reset encountered
            for (int i = 0; i < vertices.size(); i++) {
                vertices.get(i).encountered = false;
            }
            return (String[])path.toArray();
        }
        return null;
    }

    private int minCost(int opt1, int opt2) {
        return -1;
    }

    /**
     * Uses Dijkstra's algorithm to find the second shortest path from node from to node to
     * @param from the start node when finding the second shortest path
     * @param to the end node when finding the second shortest path
     * @return Returns the second shortest path between nodes from and to. Only return one path in the case of multiple equivalent results. Return an empty array if no second shortest path exists.
     */
    public String[] secondShortestPath(String from, String to) {
        /** Only reading done */
        return null;
    }

    private class Vertex {
        private String name;
        private LinkedList<Edge> edges;
        private boolean encountered;
        private int cost;
        private Vertex parent;
        private boolean finalized;

        public Vertex(String name) {
            this.name = name;
            edges = new LinkedList<Edge>();
            encountered = false;
            cost = 0;
            parent = null;
            finalized = false;
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
