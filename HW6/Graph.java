import java.util.Arrays;
import java.util.Iterator;
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
        numVertices = 0;
        maxNum = max;
    }

    /** Constructing undirected, unweighted graphs */

    /**
     * Adds a node to the graph and checks for duplicates
     * @param name name to reference the added node by
     * @return true if successful and false otherwise
     */
    public boolean addNode(String name) {
        if (numVertices >= maxNum) { // resize
            int newMax = (int)(maxNum * 1.25);
            Vertex[] temp = new Vertex[newMax];
            for (int i = 0; i < maxNum; i++) {
                temp[i] = vertices[i];
            }
            vertices = temp;
            maxNum = newMax;
        }
        int empty = -1;
        for (int i = 0; i < maxNum; i++) { // check for duplicates & empty index
            if (name.equals(vertices[i].name))
                return false;
            if (vertices[i] == null) {
                empty = i;
            }
        }
        vertices[empty] = new Vertex(name);
        numVertices++;
        return true;
    }

    /**
     * Adds a list of nodes to the graph and checks for duplicates
     * @param names names to reference the added nodes by
     * @return true if successful and false otherwise (false if some of the nodes couldn't be added; adds others if possible)
     */
    public boolean addNodes(String[] names) {
        boolean successful = false;
        for (int i = 0; i < names.length; i++) {
            if (i == 0 && addNode(names[i]))
                successful = true;
            else if (addNode(names[i]) && successful == true)
                successful = true;
        }
        return successful; // return false when one of the names couldn't be added
    }

    /**
     * Adds an edge from node from to node to
     * @param from one end of the added edge
     * @param to the other end of the added edge
     * @return true if successful and false otherwise
     */
    public boolean addEdge(String from, String to) {
        int fromIndex = -1;
        int toIndex = -1;
        if (from.equals(to))
            return false;
        for (int i = 0; i < maxNum; i++) {
            if (from.equals(vertices[i].name))
                fromIndex = i;
            if (to.equals(vertices[i].name))
                toIndex = i;
        }
        if (fromIndex == -1 || toIndex == -1) // either from or to not found
            return false;
        vertices[fromIndex].edges.addLast(new Edge(toIndex));
        vertices[toIndex].edges.addLast(new Edge(fromIndex)); // add both ways since undirected
        return true;
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
            if (i == 0 && addEdge(from, tolist[i]))
                successful = true;
            else if (addEdge(from, tolist[i]) && successful == true)
                successful = true;
        }
        return successful; // return false when one of the edges couldn't be added; rest that was successful still added
    }

    /**
     * Removes a node from the graph along with all connected edges
     * @param name the name of the node to be removed
     * @return true if successful and false otherwise
     */
    public boolean removeNode(String name) {
        // find the node
        int nameIndex = -1; // index of vertices[] where the input vertex is
        // look for the node containing input name
        for (int i = 0; i < maxNum; i++) {
            if (name.equals(vertices[i].name)) {
                nameIndex = i;
                i = maxNum;
            }
        }
        if (nameIndex == -1)
            return false; // input name not found
        // remove all connected edges first to make the vertex isolated
        LinkedList<Edge> neighbors = vertices[nameIndex].edges;
        Iterator<Edge> it = neighbors.iterator();
        while (it.hasNext()) {
            Vertex neighbor = vertices[it.next().endNode]; // end node index of the next edge
            // go to that endNode and delete the edge pointing to input node
            Iterator<Edge> it2 = neighbor.edges.iterator();
            int i = 0;
            while (it2.hasNext()) {
                if (it2.next().endNode == nameIndex) {
                    neighbor.edges.remove(i);
                    break;
                }
                i++;
            }
            neighbors.removeFirst();
        }
        // remove vertex
        vertices[nameIndex] = null;
        return true;
    }

    /**
     * Removes each node in nodelist and their edges from the graph
     * @param nodelist a list of the names of the nodes to be removed
     * @return true if successful and false otherwise (false if some of the list couldn't be removed; removes others if possible)
     */
    public boolean removeNodes(String[] nodelist) {
        boolean successful = false;
        for (int i = 0; i < nodelist.length; i++) {
            if (i == 0 && removeNode(nodelist[i]))
                successful = true;
            else if (removeNode(nodelist[i]) && successful == true)
                successful = true;
        }
        return successful; // return false when one of the edges couldn't be removed; rest that was successful still removed
    }

    /**
     * Prints the graph in an adjacency list format. The nodes and their neighbors and their neighbors should be listed in alphabetical order.
     */
    public void printGraph() {
        for (int i = 0; i < maxNum; i++) {
            String[] neighbors = new String[numVertices];
            Iterator<Edge> it = vertices[i].edges.iterator();
            while (it.hasNext())
                neighbors[i] = vertices[it.next().endNode].name;
            Arrays.sort(neighbors);
            System.out.println(vertices[i].name + ": " + neighbors.toString());
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
        // find the from node
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < maxNum; i++) {
            if (vertices[i].name.equals(from))
                fromIndex = i;
            else if (vertices[i].name.equals(to))
                toIndex = i;
        }
        // if from and to nodes found, start depth-first search
        if (fromIndex != -1 && toIndex != -1) {
            String[] path = null;
            int index = 0;
            if (neighborOrder.toLowerCase().equals("alphabetical")) { // consider edges from front
                path = new String[numVertices];
                return recursiveDFS(fromIndex, index, path);
            }
            else if (neighborOrder.toLowerCase().equals("reverse")) { // consider edges from end
                path = new String[numVertices];
                return reverse(recursiveDFS(fromIndex, index, path));
            }
            return path;
        }
        return null;
    }

    private String[] recursiveDFS(int nodeIndex, int start, String[] path) {
        Vertex node = vertices[nodeIndex];
        if (node.encountered == true) {
            return null; // fix later
        }
        Iterator<Edge> it = vertices[nodeIndex].edges.iterator();
        while (it.hasNext()) { // iterate through all edges of from node
            String[] temp = null;
            Edge edge = it.next();
            if (vertices[edge.endNode].encountered == false)
                temp = recursiveDFS(edge.endNode, start, temp);
            if (temp != null) {
                for (int i = 0; i < temp.length; i++) {
                    start++;
                    path[start] = temp[i];
                }
            }
        }

        return null;
    }

    private String[] reverse(String[] array) {
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
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
}