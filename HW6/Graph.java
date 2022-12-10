import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Graph class for CSDS233 HW 6
 * @author Chaehyeon Kim cxk445
 */
public class Graph {

    private ArrayList<Vertex> vertices;
    //private Vertex[] vertices;
    //private int numVertices;
    //private int maxNum;

    public Graph(int max) {
        vertices = new ArrayList<>();
        //numVertices = 0;
        //maxNum = max;
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
        // find the from node
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < maxNum; i++) { // add a separate node finder helper method
            if (vertices[i].name.equals(from))
                fromIndex = i;
            if (vertices[i].name.equals(to))
                toIndex = i;
        } // edge case: when from and to equal or diff. number of nodes in the graph
        // if from and to nodes found, start depth-first search
        if (fromIndex != -1 && toIndex != -1) {
            String[] path = new String[numVertices];;
            int index = 1;
            if (neighborOrder.toLowerCase().equals("alphabetical")) { // consider edges from front
                vertices[fromIndex].encountered = true;
                path[0] = vertices[fromIndex].name;
                recursiveDFS(fromIndex, toIndex, index, path);
                return path;
            }
            else if (neighborOrder.toLowerCase().equals("reverse")) { // consider edges from end
                vertices[fromIndex].encountered = true;
                path[0] = vertices[fromIndex].name;
                recursiveDFS(fromIndex, toIndex, index, path);
                return reverse(path); //sort()?
            }
        }
        return null;
    }

    private void recursiveDFS(int fromIndex, int toIndex, int index, String[] path) {
        //vertices[fromIndex].encountered = true;
        Iterator<Edge> it = vertices[fromIndex].edges.iterator();
        while (it.hasNext()) { // iterate through all edges of from node
            Edge edge = it.next();
            if (vertices[edge.endNode].encountered == false) {
                path[index] = vertices[edge.endNode].name;
                index++;
                vertices[edge.endNode].encountered = true;
                if (edge.endNode == toIndex)
                    break;
                recursiveDFS(edge.endNode, toIndex, index, path);
            }
        }
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
        if (fromIndex != -1 && toIndex != -1) {
            String[] temp = new String[numVertices];
            if (neighborOrder.toLowerCase().equals("alphabetical")) {
                int numPath = BFSHelper(fromIndex, toIndex, temp);
                String[] path = new String[numPath];
                for (int i = 0; i < numPath; i++) {
                    path[i] = temp[i];
                }
                return path;
            }
            else if (neighborOrder.toLowerCase().equals("reverse")) {
                int numPath = BFSHelper(fromIndex, toIndex, temp);
                String[] path = new String[numPath];
                for (int i = 0; i < numPath; i++) {
                    path[i] = temp[i];
                }
                return reverse(path);
            }
        }
        return null;
    }

    private int BFSHelper(int fromIndex, int toIndex, String[] path) {
        int i = 0;
        GraphQueue queue = new GraphQueue();
        // add the first vertex
        queue.add(vertices[fromIndex]);
        Vertex removed = queue.remove();
        while (i < numVertices && !removed.equals(vertices[toIndex])) { // or '!='?
            // add all edges to start
            Iterator<Edge> it = removed.edges.iterator();
            while (it.hasNext()) {
                Vertex next = vertices[it.next().endNode];
                if (next.encountered == false) {
                    queue.add(next);
                    next.encountered = true;
                }
            }
            removed = queue.remove();
            path[i] = removed.name;
            i++;
        }
        return i;
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
        if (fromIndex != -1 && toIndex != -1) {
            boolean alrEncountered = false;
            GraphQueue queue = new GraphQueue();
            queue.add(vertices[fromIndex]);
            int i = 1;
            // from vertex initializations
            Vertex removed = queue.remove();
            removed.encountered = true;
            removed.parent = null;
            removed.cost = 0;
            while (removed.equals(vertices[toIndex]) && alrEncountered == true) { // path found
                Iterator<Edge> it = removed.edges.iterator();
                while (it.hasNext()) {
                    Vertex neighbor = vertices[it.next().endNode];
                    queue.add(neighbor);
                    neighbor.encountered = true;
                    neighbor.parent = removed;
                    neighbor.cost = i;
                }
                removed = queue.remove();
                i++;
                if (removed.equals(vertices[toIndex]))
                    alrEncountered = true;
            } // removed == destination when loop exited
            String[] path = new String[i + 1];
            path[i] = removed.name;
            for (int j = i - 1; j >= 0; j--) {
                path[j] = removed.parent.name;
                removed = removed.parent;
            }
            return path;
        }
        return null;
    }

    /** BFS improved?
     * 
     * int fromIndex = search(from);
        int toIndex = search(to);
        String[] path = new String[numVertices];
        GraphQueue queue = new GraphQueue();
        queue.add(vertices[fromIndex]);
        Vertex removed = queue.remove();
        int i = 1;
        while (removed.equals(vertices[toIndex])) {
            Iterator<Edge> it = removed.edges.iterator();
            while (it.hasNext()) {
                Vertex neighbor = vertices[it.next().endNode];
                queue.add(neighbor);
                neighbor.encountered = true;
                neighbor.parent = removed;
                neighbor.cost = i;
            }
            removed = queue.remove();
            i++;
        }
     */

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