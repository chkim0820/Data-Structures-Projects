import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Graph class for CSDS233 HW 6
 * @author Chaehyeon Kim cxk445
 */
public class Graph {

    private ArrayList<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList<>();
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
        while (it.hasNext())
            nameList.add(it.next().endNodeName); // add the name of the neighbors
        return nameList.toArray(new String[0]);
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

    /** Constructing undirected, unweighted graphs */

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

    /**
     * Adds a list of nodes to the graph and checks for duplicates
     * @param names names to reference the added nodes by
     * @return true if successful and false otherwise (false if some of the nodes couldn't be added; adds others if possible)
     */
    public boolean addNodes(String[] names) {
        boolean successful = false;
        for (int i = 0; i < names.length; i++) {
            if (addNode(names[i])) {
                if (i == 0)
                    successful = true;
            }
            else
                successful = false;
        }
        return successful; // return false when one or more of the names couldn't be added
    }

    /**
     * Adds an edge from node from to node to
     * @param from one end of the added edge
     * @param to the other end of the added edge
     * @return true if successful and false otherwise
     */
    public boolean addEdge(String from, String to) {
        int fromIndex = search(from);
        int toIndex = search(to);
        if (fromIndex != -1 && toIndex != -1) {
            vertices.get(fromIndex).edges.addLast(new Edge(toIndex));
            if (fromIndex != toIndex)
                vertices.get(toIndex).edges.addLast(new Edge(fromIndex)); // add both ways since undirected
            return true;
        }
        return false;
    }

    /**
     * Adds an undirected edge from node from to each node in tolist
     * @param from one end of the added edge
     * @param tolist the other ends of the added edge
     * @return true if successful and false otherwise (false if some of the edges couldn't be added; adds others if possible)
     */
    public boolean addEdges(String from, String[] tolist) {
        boolean successful = false;
        for (int i = 0; i < tolist.length; i++) {
            if (addEdge(from, tolist[i])) {
                if (i == 0)
                    successful = true;
            }
            else
                successful = false;
        }
        return successful; // return false when one or more of the edges couldn't be added
    }

    /**
     * Removes a node from the graph along with all connected edges
     * @param name the name of the node to be removed
     * @return true if successful and false otherwise
     */
    public boolean removeNode(String name) {
        // find the node
        int nameIndex = search(name);
        if (nameIndex != -1) { // if vertex with 'name' found/exists in the graph
            // remove all connected edges first to make the vertex isolated
            LinkedList<Edge> neighbors = vertices.get(nameIndex).edges;
            Iterator<Edge> it = neighbors.iterator();
            while (it.hasNext()) { // go to the endnodes and delete from there
                Vertex neighbor = vertices.get(it.next().endNode);
                Iterator<Edge> nit = neighbor.edges.iterator();
                int nindex = 0;
                while (nit.hasNext()) {
                    Edge edge = nit.next();
                    if (edge.endNode == nameIndex)
                        neighbor.edges.remove(nindex);
                    nindex++;
                }
            }
            // reassign all endNode values
            reassignEndNode();
            // remove vertex
            vertices.remove(nameIndex);
            return true;
        }
        return false;
    }

    /**
     * Reassign endNode indeces for all edges after vertices shifted after deletion
     */
    private void reassignEndNode() {
        for (int i = 0; i < vertices.size(); i++) {
            LinkedList<Edge> edgeList = vertices.get(i).edges;
            Iterator<Edge> it = edgeList.iterator();
            while (it.hasNext()) {
                Edge edge = it.next();
                edge.endNode = search(edge.endNodeName);
            }
        }
    }

    /**
     * Removes each node in nodelist and their edges from the graph
     * @param nodelist a list of the names of the nodes to be removed
     * @return true if successful and false otherwise (false if some of the list couldn't be removed; removes others if possible)
     */
    public boolean removeNodes(String[] nodelist) {
        boolean successful = false;
        for (int i = 0; i < nodelist.length; i++) {
            if (removeNode(nodelist[i])) {
                if (i == 0)
                    successful = true;
            }
            else
                successful = false;
        }
        return successful; // return false when one or more of the edges couldn't be added
    }

    /**
     * Prints the graph in an adjacency list format. The nodes and their neighbors and their neighbors should be listed in alphabetical order.
     * Each rows are each vertices of the graph.
     */
    public void printGraph() {
        // loop through all vertices
        for (int i = 0; i < vertices.size(); i++) {
            // loop through the whole list of edges
            Iterator<Edge> it = vertices.get(i).edges.iterator();
            ArrayList<String> arr = new ArrayList<>();
            while (it.hasNext())
                arr.add(it.next().endNodeName); // adding the name of the node at the end of the edge
            arr.sort(String.CASE_INSENSITIVE_ORDER); // sort arr alphabetically
            if (i != 0)
                System.out.print("\n");
            System.out.print('"' + vertices.get(i).name +'"' + " -> ");
            for (int j = 0; j < arr.size(); j++) { // print out all neighbors
                if (j + 1 < arr.size())
                    System.out.print('"' + arr.get(j) + '"' + " -> ");
                else
                    System.out.print('"' + arr.get(j) + '"');
            }
            if (i == vertices.size() - 1)
                System.out.print("\n");
        }
    }

    /** Finding paths on undirected, unweighted graphs */

    /**
     * A helper method that builds a String array of vertex names along the path if one found
     * @param toVertex the end node of the path
     * @return a String array of vertices along the path
     */
    private String[] pathBuilder(Vertex fromVertex, Vertex toVertex) {
        LinkedList<String> path = new LinkedList<>(); // path b/w node from to node to
        Vertex trav = toVertex;
        boolean encountered = false;
        while (trav != null) {
            if (encountered && trav.equals(toVertex))
                trav = null;
            encountered = true;
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
     * Returns the path or a list of node names of depth-first search between nodes from and to.
     * @param from The starting node of depth-first search
     * @param to The end node of depth-first search
     * @param neighborOrder The order in which neighbors are considered; can be alphabetical or reverse
     * @return The result (the path or a list of node names, of depth-first search between nodes from and to). Return an empty array if no path exists.
     */
    public String[] DFS(String from, String to, String neighborOrder) {
        int fromIndex = search(from);
        int toIndex = search(to);
        if (fromIndex != -1 && toIndex != -1) { // both from and to exists
            boolean found = false;
            if (neighborOrder.toLowerCase().equals("alphabetical"))
                found = recursiveDFS(fromIndex, toIndex, 1);
            else if (neighborOrder.toLowerCase().equals("reverse"))
                found = recursiveDFS(fromIndex, toIndex, -1);
            else 
                return null; // neighborOrder doesn't specify alphabetical or reverse
            if (found) {
                return pathBuilder(vertices.get(fromIndex), vertices.get(toIndex));
            }
        }
        return null;
    }

    /**
     * A private recursive helper method for DFS()
     * @param fromIndex the index of the vertex at the start of the search
     * @param toIndex the index of the vertex at the end of the search
     * @param neighborOrder 1 if alphabetical or -1 if reverse
     * @return the vertex at the end of the search
     */
    private boolean recursiveDFS(int fromIndex, int toIndex, int neighborOrder) {
        Vertex from = vertices.get(fromIndex);
        from.encountered = true; // mark from node as encountered
        if (fromIndex == toIndex) { // base case
            return true;
        }
        else if (from.edges.size() > 0) { // if there are edges for from
            ArrayList<String> neighbors = new ArrayList<>();
            Iterator<Edge> it = from.edges.iterator();
            while (it.hasNext()) // iterate through the list of all edges for the current vertex
                neighbors.add(it.next().endNodeName); // add i-th edge's name
            neighbors.sort(String.CASE_INSENSITIVE_ORDER); // sort the neighbors alphabetically
            if (neighborOrder < 0)
                neighbors = reverse(neighbors); // reverse alphabetical order if reverse order requested
            // call recursive methods
            for (int j = 0; j < neighbors.size(); j++) {
                int newFromIndex = search(neighbors.get(j));
                Vertex newFrom = vertices.get(newFromIndex);
                if (newFrom.encountered == false) { // if not encountered yet
                    newFrom.parent = from;
                    return recursiveDFS(newFromIndex, toIndex, neighborOrder);
                }
            }
        }
        // if from & to and edge not found, 
        return false;
    }

    /**
     * A helper method that reverses the input arrayList
     * @param arrayList an ArrayList object to be reversed
     * @return reversed arrayList
     */
    private ArrayList<String> reverse(ArrayList<String> arrayList) {
        ArrayList<String> revArr = new ArrayList<>();
        for (int i = arrayList.size() - 1; i >= 0; i--) // add elements of arrayList from back to front to the temp array
            revArr.add(arrayList.get(i));
        return revArr;
    }

    /**
     * Returns the path or a list of node names of breadth-first search between nodes from and to.
     * @param from The starting node of breadth-first search
     * @param to The end node of breadth-first search
     * @param neighborOrder The order in which neighbors are considered; can be alphabetical or reverse
     * @return the path or a list of node names of breadth-first search between nodes from and to
     */
    public String[] BFS(String from, String to, String neighborOrder) {
        int fromIndex = search(from);
        int toIndex = search(to);
        if (fromIndex != -1 && toIndex != -1) { // if both from and to vertices exist
            boolean found;
            if (neighborOrder.toLowerCase().equals("alphabetical"))
                found = BFSHelper(fromIndex, toIndex, 1);
            else if (neighborOrder.toLowerCase().equals("reverse"))
                found = BFSHelper(fromIndex, toIndex, -1);
            else 
                return null;
            if (found) { // if a path b/w from and to found
                return pathBuilder(vertices.get(fromIndex), vertices.get(toIndex));
            }
        }
        return null;
    }

    /**
     * Helper method for BFS
     * @param fromIndex the starting node of the search
     * @param toIndex the end node of the search
     * @param neighborOrder 1 if alphabetical and -1 if reverse
     * @return the vertex at the end of the search
     */
    private boolean BFSHelper(int fromIndex, int toIndex, int neighborOrder) {
        GraphQueue queue = new GraphQueue();
        // add the first vertex
        queue.add(vertices.get(fromIndex));
        Vertex removed = queue.peek();
        removed.encountered = true;
        while (removed != null && !removed.equals(vertices.get(toIndex))) { // or !=
            removed = queue.remove();
            // add all edges to start
            ArrayList<String> currentEdges = new ArrayList<>();
            Iterator<Edge> it = removed.edges.iterator();
            while (it.hasNext()) // add all edges to currentEdges list to sort alphabetically or reversely
                currentEdges.add(vertices.get(it.next().endNode).name);
            currentEdges.sort(String.CASE_INSENSITIVE_ORDER); // sort alphabetically
            if (neighborOrder < 0) // reverse alphabetical sort
                currentEdges = reverse(currentEdges);
            for (int j = 0; j < currentEdges.size(); j++) {
                Vertex next = vertices.get(search(currentEdges.get(j)));
                if (next.encountered == false) {
                    queue.add(next);
                    next.encountered = true;
                    next.parent = removed;
                }
            }
            removed = queue.peek();
        }
        if (removed != null) // if path found
            return true;
        else // if path not found
            return false;
    }

    /**
     * Uses Dijkstra's algorithm to find the shortest path from node from to node to
     * @param from the starting node when finding the shortest path
     * @param to the ending node when finding the shortest path
     * @return the shortest path from node from to node to; only return one in case of multiple equivalent results
     */
    public String[] shortestPath(String from, String to) {
        return BFS(from, to, "alphabetical");
    }

    /**
     * Returns the second shortest path between nodes from and to
     * @param from the starting node when finding the second shortest path
     * @param to the ending node when finding the second shortest path
     * @return the second shortest path between nodes from and to; only return one in case of multiple equivalent results
     */
    public String[] secondShortestPath(String from, String to) {
        int fromIndex = search(from);
        int toIndex = search(to);
        if (fromIndex != -1 && toIndex != -1) { // if both from and to vertices exist
            if (SSPHelper(fromIndex, toIndex)) {
                return pathBuilder(vertices.get(fromIndex), vertices.get(toIndex));
            }
        }
        return null;
    }

    /**
     * Helper method for secondShortestPath() that finds the path
     * @param fromIndex the starting vertex of the search
     * @param toIndex the end vertex of the search
     * @return the vertex at the second shortest path
     */
    private boolean SSPHelper(int fromIndex, int toIndex) {
        GraphQueue queue = new GraphQueue();
        boolean alrEncountered = false;
        // add the first vertex
        queue.add(vertices.get(fromIndex));
        Vertex removed = queue.peek();
        removed.encountered = true;
        // loop through the graph
        while (removed != null && !removed.equals(vertices.get(toIndex)) && !alrEncountered) { // or !=
            removed = queue.remove();
            // add all neighbors of removed
            ArrayList<String> currentEdges = new ArrayList<>();
            Iterator<Edge> it = removed.edges.iterator();
            while (it.hasNext()) // add all edges to currentEdges list to sort alphabetically or reversely
                currentEdges.add(vertices.get(it.next().endNode).name);
            currentEdges.sort(String.CASE_INSENSITIVE_ORDER); // sort alphabetically
            for (int j = 0; j < currentEdges.size(); j++) {
                int nextIndex = search(currentEdges.get(j));
                Vertex next = vertices.get(nextIndex);
                if (next.encountered == true && nextIndex == toIndex) {
                    alrEncountered = true;
                    next.parent = removed;
                    return true;
                }
                else if (next.encountered == false) {
                    queue.add(next);
                    next.encountered = true;
                    next.parent = removed;
                }
            }
            removed = queue.peek();
        }
        if (removed != null) // if path found
            return true;
        else // if path not found
            return false;
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

        public Vertex(String name) {
            this.name = name;
            edges = new LinkedList<Edge>();
            parent = null;
            encountered = false;
            cost = 0;
        }
    }

    /**
     * Edge class; a private, nested class of Graph class
     */
    private class Edge {
        private int endNode;
        private String endNodeName;
        private int cost;

        public Edge(int endNode) {
            this.endNode = endNode;
            endNodeName = vertices.get(endNode).name;
            cost = 0;
        }
    }

    /**
     * GraphQueue class; a private, nested class of Graph class
     */
    private class GraphQueue{
        private LinkedList<Vertex> list;

        public GraphQueue() {
            list = new LinkedList<Vertex>();
        }

        public void add(Vertex vertex) {
            list.addLast(vertex);
        }

        public Vertex remove() {
            return list.removeFirst();
        }

        public Vertex peek() {
            return list.peek();
        }
    }

    /**
     * Main method of this Graph class; test the printGraph() and other methods.
     * @param args String arguments; not used in this main method.
     */
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Print out an empty graph
        System.out.println("When using printGraph() on an empty graph, it doesn't print anything. The result:");
        graph.printGraph();

        // Print out a graph with nodes and no edges
        System.out.println("\nWhen using printGraph() on a graph with nodes and no edges, it looks like this. The result:");
        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");
        graph.printGraph();

        // Print out a graph with nodes and edges
        System.out.println("\nWhen using printGraph() on a graph with nodes and edges, it looks like this. As you can see, it prints the graph in an adjacency list format. The result:");
        graph.addEdge("apple", "banana");
        graph.addEdge("apple", "cider");
        graph.addEdge("banana", "cider");
        graph.printGraph();

        // More complex graph example
        System.out.println("\nMore complex graph example:");
        Graph graph2 = new Graph();
        graph2.addNode("A");
        graph2.addNode("b");
        graph2.addNode("C");
        graph2.addNode("d");
        graph2.addNode("E");
        graph2.addEdge("A", "b");
        graph2.addEdge("A", "d");
        graph2.addEdge("A", "E");
        graph2.addEdge("b", "C");
        graph2.addEdge("b", "d");
        graph2.addEdge("C", "E");
        graph2.addEdge("C", "E");
        graph2.printGraph();
        System.out.print("Notice how the neighbors are listed in alphabetical order.");
    }
}