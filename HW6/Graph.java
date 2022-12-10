import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Graph class for CSDS233 HW 6
 * @author Chaehyeon Kim cxk445
 */
public class Graph {

    private ArrayList<Vertex> vertices;

    public Graph(int max) {
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
                int nindex = 0;
                Iterator<Edge> nit = neighbor.edges.iterator();
                while (nit.hasNext()) {
                    if (nit.next().endNode == nameIndex)
                        break;
                    nindex++;
                }
                neighbor.edges.remove(nindex);
            }
            // remove vertex
            vertices.remove(nameIndex);
            return true;
        }
        return false;
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
     */
    public void printGraph() {
        // loop through all vertices
        for (int i = 0; i < vertices.size(); i++) {
            // loop through the whole list of edges
            Iterator<Edge> it = vertices.get(i).edges.iterator();
            ArrayList<String> arr = new ArrayList<>();
            while (it.hasNext())
                arr.add(vertices.get(it.next().endNode).name); // adding the name of the node at the end of the edge
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

    /** Finding paths on undirected, unweighted graphs */

    /**
     * Returns the path or a list of node names of depth-first search between nodes from and to.
     * @param from The starting node of depth-first search
     * @param to The end node of depth-first search
     * @param neighborOrder The order in which neighbors are considered; can be alphabetical or reverse
     * @return The result (the path or a list of node names, of depth-first search between nodes from and to). Return an empty array if no path exists.
     */
    public String[] DFS(String from, String to, String neighborOrder) {
        //ArrayList<Vertex> encountered = new ArrayList<>(); // keeps track of which nodes are encountered
        int fromIndex = search(from);
        int toIndex = search(to);
        if (fromIndex != -1 && toIndex != -1) { // both from and to exists
            Vertex toVertex = null;
            if (neighborOrder.toLowerCase().equals("alphabetical"))
                toVertex = recursiveDFS(fromIndex, toIndex, 1);
            else if (neighborOrder.toLowerCase().equals("reverse"))
                toVertex = recursiveDFS(fromIndex, toIndex, -1);
            // build a String array of nodes in the path
            LinkedList<String> path = new LinkedList<>();
            while (toVertex != null) {
                path.addFirst(toVertex.name);
                toVertex = toVertex.parent;
            }
            // reset the values of the encountered field for all nodes
            for (int i = 0; i < vertices.size(); i++) {
                vertices.get(i).encountered = false;
            }
            return (String[])path.toArray();
        }
        return null;
    }

    private Vertex recursiveDFS(int fromIndex, int toIndex, int neighborOrder) { // =1 if alphabetical -1 if reverse
        Vertex from = vertices.get(fromIndex);
        from.encountered = true; // mark from node as encountered
        if (fromIndex == toIndex) { // base case
            return from;
        }
        if (from.edges.size() != 0) {
            ArrayList<String> neighbors = new ArrayList<>();
            for (int i = 0; i < from.edges.size(); i++) { // traverse through the list of all edges
                neighbors.add(vertices.get(from.edges.get(i).endNode).name); // add i-th edge's name
            }
            neighbors.sort(String.CASE_INSENSITIVE_ORDER); // sort the neighbors alphabetically
            if (neighborOrder < 0)
                neighbors = reverse(neighbors); // reverse alphabetical order if reverse order requested
            // call recursive methods
            Vertex toVertex = null;
            for (int j = 0; j < neighbors.size(); j++) {
                int newFromIndex = search(neighbors.get(j));
                Vertex newFrom = vertices.get(newFromIndex);
                if (newFrom.encountered == false) { // if not encountered yet
                    newFrom.parent = from;
                    toVertex = recursiveDFS(newFromIndex, toIndex, neighborOrder);
                }
            }
        }
        // if from & to and edge not found, 
        return null;
    }

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
            LinkedList<String> path = new LinkedList<>(); // path b/w node from to node to
            Vertex toVertex;
            if (neighborOrder.toLowerCase().equals("alphabetical"))
                toVertex = BFSHelper(fromIndex, toIndex, 1);
            else if (neighborOrder.toLowerCase().equals("reverse"))
                toVertex = BFSHelper(fromIndex, toIndex, -1);
            else 
                return null;
            while (toVertex != null) {
                path.addFirst(toVertex.name);
                toVertex = toVertex.parent;
            }
            // reset the values of the encountered field for all nodes
            for (int i = 0; i < vertices.size(); i++) {
                vertices.get(i).encountered = false;
            }
            return (String[])path.toArray();
        }
        return null;
    }

    private Vertex BFSHelper(int fromIndex, int toIndex, int neighborOrder) {
        GraphQueue queue = new GraphQueue();
        // add the first vertex
        queue.add(vertices.get(fromIndex));
        Vertex removed = queue.remove();
        for (int i = 0; i < vertices.size() && !removed.equals(vertices.get(toIndex)); i++) { // or !=
            // add all edges to start
            ArrayList<String> currentEdges = new ArrayList<>();
            Iterator<Edge> it = removed.edges.iterator();
            while (it.hasNext()) { // add all edges to currentEdges list to sort alphabetically or reversely
                //Vertex nextNode = vertices.get(it.next().endNode);
                currentEdges.add(vertices.get(it.next().endNode).name);
            }
            currentEdges.sort(String.CASE_INSENSITIVE_ORDER); // sort alphabetically
            if (neighborOrder < 0) // reverse alphabetical sort
                currentEdges = reverse(currentEdges);
            for (int j = 0; j < currentEdges.size(); j++) {
                Vertex next = vertices.get(search(currentEdges.get(j)));
                if (next.encountered == false) {
                    queue.add(next);
                    next.encountered = true;
                    next.parent = removed;
                    next.cost = i + 1;
                }
            }
            removed = queue.remove();
        }
        return removed;
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
            LinkedList<String> path = new LinkedList<>(); // path b/w node from to node to
            Vertex toVertex = SSPHelper(fromIndex, toIndex);
            while (toVertex != null) {
                path.addFirst(toVertex.name);
                toVertex = toVertex.parent;
            }
            // reset the values of the encountered field for all nodes
            for (int i = 0; i < vertices.size(); i++) {
                vertices.get(i).encountered = false;
            }
            return (String[])path.toArray();
        }
        return null;
    }

    private Vertex SSPHelper(int fromIndex, int toIndex) {
        GraphQueue queue = new GraphQueue();
        boolean alrEncountered = false;
        // add the first vertex
        queue.add(vertices.get(fromIndex));
        Vertex removed = queue.remove();
        // loop through the graph
        for (int i = 0; i < vertices.size() && !removed.equals(vertices.get(toIndex)) && alrEncountered == false; i++) { // or !=
            // add all neighbors of removed
            ArrayList<String> currentEdges = new ArrayList<>();
            Iterator<Edge> it = removed.edges.iterator();
            while (it.hasNext()) { // add all edges to currentEdges list to sort alphabetically or reversely
                currentEdges.add(vertices.get(it.next().endNode).name);
            }
            currentEdges.sort(String.CASE_INSENSITIVE_ORDER); // sort alphabetically
            for (int j = 0; j < currentEdges.size(); j++) {
                int nextIndex = search(currentEdges.get(j));
                Vertex next = vertices.get(nextIndex);
                if (next.encountered == true && nextIndex == toIndex) {
                    alrEncountered = true;
                    next.parent = removed;
                    next.cost = next.parent.cost + 1;
                }
                else if (next.encountered == false) {
                    queue.add(next);
                    next.encountered = true;
                    next.parent = removed;
                    next.cost = next.parent.cost + 1;
                }
            }
            removed = queue.remove();
        }
        return vertices.get(toIndex);
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
            edges = null;
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
        private int cost;

        public Edge(int endNode) {
            this.endNode = endNode;
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
    }
}