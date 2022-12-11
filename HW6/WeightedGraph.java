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
        LinkedList<Vertex> finalized = new LinkedList<>(); // PriorityQueue containing all finalized vertices
        int fromIndex = search(from);
        int toIndex = search(to);
        if (fromIndex != -1 && toIndex != -1) {
            ///PriorityQueue<Vertex> heap = new PriorityQueue<>();
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
            return path.toArray(new String[0]);
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
        private String endNodeName;
        private int cost;

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
