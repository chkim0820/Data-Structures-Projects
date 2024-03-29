import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javafx.scene.layout.Priority;

/**
 * WeightedGraph class that works for weighted and directed graphs; for CSDS233 HW 6
 * @author Chaehyeon Kim cxk445
 */
public class WeightedGraph {

    /** storing the vertices of the graph */
    private ArrayList<Vertex> vertices;

    /**
     * Constructor for this WeightedGraph class
     */
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

    /**
     * Returns an array containing names of all vertices
     * @return an array containing names of all vertices
     */
    public String[] getVertices() {
        ArrayList<String> nameList = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++)
            nameList.add(vertices.get(i).name);
        return nameList.toArray(new String[0]);
    }

    /**
     * Returns an array containing names of all neighbors of the vertex of the input index
     * @param nodeIndex the index of the node to look for edges for
     * @return an array containing names of all neighbors of the vertex of the input index
     */
    public String[] getEdges(int nodeIndex) {
        ArrayList<String> nameList = new ArrayList<>();
        LinkedList<Edge> edgeList = vertices.get(nodeIndex).edges;
        Iterator<Edge> it = edgeList.iterator();
        while (it.hasNext()) {
            Edge edge = it.next();
            nameList.add(edge.endNodeName + ", " + edge.cost + ""); // add the name of the neighbors
        }
        return nameList.toArray(new String[0]);
    }

    /**
     * Adds a node to the graph and checks for duplicates
     * @param name name to reference the added node by
     * @return true if successful and false otherwise
     */
    public boolean addNode(String name) {
        if (search(name) == -1) { // if a node of the same name is not there already
            vertices.add(new Vertex(name));
            return true;
        }
        return false;
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
            // loop through the whole list of edges
            Iterator<Edge> it = vertices.get(i).edges.iterator();
            ArrayList<String> arr = new ArrayList<>();
            while (it.hasNext()) {
                Edge edge = it.next();
                arr.add(edge.endNodeName + " (" + edge.cost + ")"); // adding the name of the node at the end of the edge
            }
            arr.sort(String.CASE_INSENSITIVE_ORDER); // sort arr alphabetically
            if (i != 0)
                System.out.print("\n");
            System.out.print(vertices.get(i).name + " to: ");
            for (int j = 0; j < arr.size(); j++) { // print out all neighbors
                if (j + 1 < arr.size())
                    System.out.print(arr.get(j) + ", ");
                else
                    System.out.print(arr.get(j));
            }
            if (i == vertices.size() - 1)
                System.out.print("\n");
        }
    }

    /** Shortest paths on directed, weighted graphs */

    /**
     * Uses Dijkstra's algorithm to find the shortest path from node from to node to.
     * @param from the start node when finding the shortest path
     * @param to the end node when finding the shortest path
     * @return The shortest path from node from to node to. If there are multiple paths of equivalent length, only return one of them. If the path does not exist, return an empty array.
     */
    public String[] shortestPath(String from, String to) { // revise using priority queue
        int fromIndex = search(from);
        int toIndex = search(to);
        if (fromIndex != -1 && toIndex != -1) {
            // Initialize the heap and the path linked list
            PriorityQueue<Vertex> heap = new PriorityQueue<>();
            //LinkedList<String> path = new LinkedList<>();
            // Add from vertex's neighbors to the heap
            Iterator<Edge> it = vertices.get(fromIndex).edges.iterator();
            //path.add(from);
            vertices.get(fromIndex).encountered = true;
            while (it.hasNext()) { // add all neighbors of the from vertex
                Edge edge = it.next();
                Vertex nextVertex = vertices.get(edge.endNode);
                nextVertex.cost = edge.cost;
                heap.add(nextVertex);
                nextVertex.encountered = true;
                nextVertex.parent = vertices.get(fromIndex);
            }
            // Iterate through the whole graph while heap is not null and heap's front does not equal to vertex
            Vertex removed = heap.poll(); // just peeking; not deleting yet
            boolean found = false;
            if (fromIndex == toIndex) // if from & to identical, path "found"
                found = true;
            while (removed != null && !found) { // while the heap is not empty and to not found
                //path.add(removed.name); // deleted the head
                Iterator<Edge> itRemoved = removed.edges.iterator();
                while (itRemoved.hasNext()) { // add all neighbors of the from vertex
                    Edge edge = itRemoved.next();
                    Vertex next = vertices.get(edge.endNode);
                    if (next.encountered == false) {
                        heap.add(next);
                        next.cost = removed.cost + edge.cost;
                        next.encountered = true;
                        next.parent = removed;
                    }
                }
                if (removed != null && removed.equals(vertices.get(toIndex)))
                    found = true;
                removed = heap.poll();
            }
            if (found)
                return pathBuilder(vertices.get(fromIndex), vertices.get(toIndex));
        }
        return null;
    }

    /**
     * A helper method that builds a String array of vertex names along the path if one found
     * @param toVertex the end node of the path
     * @return a String array of vertices along the path
     */
    private String[] pathBuilder(Vertex fromVertex, Vertex toVertex) {
        LinkedList<String> path = new LinkedList<>(); // path b/w node from to node to
        Vertex trav = toVertex;
        while (trav != null) {
            path.addFirst(trav.name);
            trav = trav.parent;
        }
        // reset the values of the encountered field for all nodes
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).encountered = false;
        }
        return path.toArray(new String[0]);
    }

    /**
     * Helper method returning the smaller of the two inputs
     * @param opt1 input 1
     * @param opt2 input 2
     * @return the smaller of the two inputs
     */
    private int minCost(int opt1, int opt2) {
        if (opt1 < opt2)
            return opt1;
        else
            return opt2;
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

    /**
     * A private nested Vertex class
     */
    private class Vertex implements Comparable<Vertex> {

        /** name of the vertex */
        private String name;
        /** edges of the vertex */
        private LinkedList<Edge> edges;
        /** stores whether this vertex has been encountered or not */
        private boolean encountered;
        /** stores the cost of the vertex */
        private int cost;
        /** the parent vertex of this vertex */
        private Vertex parent;

        /**
         * Constructor for Vertex class
         * @param name name of the vertex
         */
        public Vertex(String name) {
            this.name = name;
            edges = new LinkedList<Edge>();
            encountered = false;
            cost = 0;
            parent = null;
        }

        /**
         * A compareTo() method overriding the default compareTo() method for Vertex
         * @param vertex vertex to be compared to this vertex
         * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
         */
        @Override
        public int compareTo(Vertex vertex) {
            if (this.cost < vertex.cost)
                return -1;
            else if (this.cost > vertex.cost)
                return 1;
            else 
                return 0;
        }
    }

    /**
     * A private nested Edge class
     */
    private class Edge {

        /** the index of the endNode */
        private int endNode;
        /** the name of the endNode */
        private String endNodeName;
        /** the cost of this edge */
        private int cost;

        /**
         * Constructor for Edge class
         * @param endNode the index of the endNode
         * @param cost the cost of this edge
         */
        public Edge(int endNode, int cost) {
            this.endNode = endNode;
            endNodeName = vertices.get(endNode).name;
            this.cost = cost;
        }
    }

    /**
     * Main method of this WeightedGraph class; test the printWeightedGraph() and other methods.
     * @param args String arguments; not used in this main method.
     */
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();

        // Print out an empty graph
        System.out.println("When using printWeightedGraph() on an empty graph, it doesn't print anything. The result:");
        graph.printWeightedGraph();

        // Print out a graph with nodes and no edges
        System.out.println("\nWhen using printWeightedGraph() on a graph with nodes and no edges, it looks like this. The result:");
        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");
        graph.printWeightedGraph();

        // Print out a graph with nodes and edges
        System.out.println("\nWhen using printWeightedGraph() on a graph with nodes and edges, it looks like this. The result:");
        graph.addWeightedEdge("apple", "banana", 2);
        graph.addWeightedEdge("apple", "cider", 4);
        graph.addWeightedEdge("banana", "cider", 5);
        graph.addWeightedEdge("cider", "banana", 6);
        graph.addWeightedEdge("cider", "apple", 1);
        graph.printWeightedGraph();

        // More complex graph example; with on-campus locations
        // Setting up the graph
        System.out.println("\nMore complex graph example:");
        WeightedGraph graph2 = new WeightedGraph();
        graph2.addNode("Village");
        graph2.addNode("The Den");
        graph2.addNode("Plum Market");
        graph2.addNode("CoffeeHouse");
        graph2.addNode("Smith");
        graph2.addNode("Taft");
        graph2.addNode("Taplin");
        graph2.addNode("CIM");
        graph2.addNode("Cutter");
        
        String[] villageNeighbors = {"The Den", "Plum Market", "CIM"};
        int[] villageCosts = {9, 10, 30};
        graph2.addWeightedEdges("Village", villageNeighbors, villageCosts);

        String[] denNeighbors = {"Village", "Taft", "CoffeeHouse", "Plum", "CIM"};
        int[] denCosts = {9, 4, 5, 5, 30};
        graph2.addWeightedEdges("The Den", denNeighbors, denCosts);

        String[] plumNeighbors = {"Village", "The Den", "CoffeeHouse", "CIM"};
        int[] plumCosts = {10, 5, 8, 30};
        graph2.addWeightedEdges("Plum Market", plumNeighbors, plumCosts);

        String[] coffeeNeighbors = {"The Den", "Plum Market", "Smith"};
        int[] coffeeCosts = {5, 8, 3};
        graph2.addWeightedEdges("CoffeeHouse", coffeeNeighbors, coffeeCosts);

        String[] smithNeighbors = {"CoffeeHouse", "Taft", "Cutter"};
        int[] smithCosts = {3, 9, 3};
        graph2.addWeightedEdges("Smith", smithNeighbors, smithCosts);

        String[] taftNeighbors = {"The Den", "Smith", "Taplin", "CIM"};
        int[] taftCosts = {4, 9, 2, 12};
        graph2.addWeightedEdges("Taft", taftNeighbors, taftCosts);

        String[] taplinNeighbors = {"Taft", "CIM"};
        int[] taplinCosts = {2, 14};
        graph2.addWeightedEdges("Taplin", taplinNeighbors, taplinCosts);

        String[] cimNeighbors = {"Taft", "Taplin", "Cutter", "Plum Market", "The Den", "Village"};
        int[] cimCosts = {12, 14, 9, 30, 30, 30};
        graph2.addWeightedEdges("CIM", cimNeighbors, cimCosts);

        String[] cutterNeighbors = {"Smith", "CIM"};
        int[] cutterCosts = {9, 10, 30};
        graph2.addWeightedEdges("Cutter", cutterNeighbors, cutterCosts);
        
        graph2.printWeightedGraph();
        System.out.print("Notice how the neighbors are listed in alphabetical order.");
    }
}
