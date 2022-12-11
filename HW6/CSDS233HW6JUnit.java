import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A class containing all test methods for CSDS233 HW 6 methods
 * @author Chaehyeon Kim cxk445
 */
public class CSDS233HW6JUnit {
    
    /** Testing methods inside the Graph class*/


    /**
     * Test addNode() method in Graph class
     */
    @Test
    public void testAddNode() {
        Graph graph = new Graph();

        // Testing adding to an empty graph
        assertEquals(true, graph.addNode("apple"));
        String[] temp = {"apple"};
        assertArrayEquals(temp, graph.getVertices());

        // Testing adding a duplicate
        assertEquals(false, graph.addNode("apple"));
        assertArrayEquals(temp, graph.getVertices());

        // Testing adding to a non-empty graph
        assertEquals(true, graph.addNode("banana"));
        String[] temp2 = {"apple", "banana"};
        assertArrayEquals(temp2, graph.getVertices());
    }

    /**
     * Test addNodes() method in Graph class
     */
    @Test
    public void testAddNodes() {
        Graph graph = new Graph();

        // Testing adding one node
        String[] temp = {"apple"};
        assertEquals(true, graph.addNodes(temp));
        assertArrayEquals(temp, graph.getVertices());
        //Testing adding one duplicate
        assertEquals(false, graph.addNodes(temp));
        assertArrayEquals(temp, graph.getVertices());

        // Testing adding multiple nodes
        String[] temp2 = {"banana", "cider"};
        String[] actual1 = {"apple", "banana", "cider"};
        assertEquals(true, graph.addNodes(temp2));
        assertArrayEquals(actual1, graph.getVertices());
        // Testing adding multiple duplicates
        assertEquals(false, graph.addNodes(temp2));
        assertArrayEquals(actual1, graph.getVertices());
        // Testing adding one duplicate, one new node
        String[] temp3 = {"banana", "dill"};
        String[] actual2 = {"apple", "banana", "cider", "dill"};
        assertEquals(false, graph.addNodes(temp3));
        assertArrayEquals(actual2, graph.getVertices());
    }

    /**
     * Test addEdge() method in Graph class
     */
    @Test
    public void testAddEdge() {
        Graph graph = new Graph();

        // add vertices to the graph
        graph.addNode("apple");
        graph.addNode("banana");

        // Testing adding an edge to a non-existing node
        assertEquals(false, graph.addEdge("banana", "cider")); // one present
        assertEquals(false, graph.addEdge("cider", "dill")); // both not present

        // Testing adding an edge between two existing nodes
        assertEquals(true, graph.addEdge("apple", "banana"));
        String[] fromTemp = {"banana"};
        String[] toTemp = {"apple"};
        assertArrayEquals(fromTemp, graph.getEdges(0));
        assertArrayEquals(toTemp, graph.getEdges(1));

        // Testing adding another edge (parallel edge) between two existing nodes
        assertEquals(true, graph.addEdge("apple", "banana"));
        String[] fromTemp2 = {"banana", "banana"};
        String[] toTemp2 = {"apple", "apple"};
        assertArrayEquals(fromTemp2, graph.getEdges(0));
        assertArrayEquals(toTemp2, graph.getEdges(1));

        // Testing adding a loop edge
        assertEquals(true, graph.addEdge("apple", "apple"));
        String[] temp = {"banana", "banana", "apple"};
        assertArrayEquals(temp, graph.getEdges(0));
    }

    /**
     * Test addEdges() method in Graph class
     */
    @Test
    public void testAddEdges() {
        Graph graph = new Graph();

        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");

        // Testing adding one edge
        String[] temp = {"banana"};
        assertEquals(true, graph.addEdges("apple", temp));
        assertArrayEquals(temp, graph.getEdges(0));

        // Testing adding multiple edges
        String[] temp2 = {"apple", "cider"};
        String[] actual2 = {"apple", "apple", "cider"};
        assertEquals(true, graph.addEdges("banana", temp2));
        assertArrayEquals(actual2, graph.getEdges(1));
    }

    /**
     * Test removeNode() method in Graph class
     */
    @Test
    public void testRemoveNode() {
        Graph graph = new Graph();

        // Set up the graph
        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");
        graph.addNode("dill");
        graph.addNode("egg");
        graph.addEdge("apple", "banana");
        graph.addEdge("apple", "cider");
        graph.addEdge("banana", "cider");
        graph.addEdge("apple", "dill");

        // Testing removing a non-existent node
        assertEquals(false, graph.removeNode("flan"));

        // Testing removing an isolated vertex
        assertEquals(true, graph.removeNode("egg"));
        String[] temp = {"apple", "banana", "cider", "dill"};
        assertArrayEquals(temp, graph.getVertices());

        // Testing removing a vertex with 1 edge
        assertEquals(true, graph.removeNode("dill"));
        String[] temp2 = {"apple", "banana", "cider"};
        String[] edge2 = {"banana", "cider"};
        assertArrayEquals(temp2, graph.getVertices());
        assertArrayEquals(edge2, graph.getEdges(0)); // check that edge from the neighbor (apple) was removed
        
        // Testing removing a vertex with multiple edges
        assertEquals(true, graph.removeNode("cider"));
        String[] temp3 = {"apple", "banana"};
        String[] edge3 = {"banana"};
        String[] edge4 = {"apple"};
        assertArrayEquals(temp3, graph.getVertices());
        assertArrayEquals(edge3, graph.getEdges(0));
        assertArrayEquals(edge4, graph.getEdges(1));
    }

    /**
     * Test removeNodes() method in Graph class
     */
    @Test
    public void testRemoveNodes() {
        Graph graph = new Graph();

        // Set up the graph
        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");
        graph.addNode("dill");
        graph.addNode("egg");
        graph.addEdge("apple", "banana");
        graph.addEdge("apple", "cider");
        graph.addEdge("banana", "cider");
        graph.addEdge("apple", "dill");

        // Testing removing a non-existent node
        String[] nodelist = {"flan"};
        assertEquals(false, graph.removeNodes(nodelist));

        // Testing removing a non-existent node and an existent node
        String[] nodelist2 = {"egg", "flan"};
        String[] temp2 = {"apple", "banana", "cider", "dill"};
        assertEquals(false, graph.removeNodes(nodelist2));
        assertArrayEquals(temp2, graph.getVertices()); // check if it deleted what it could

        // Testing removing multiple nodes
        String[] nodelist3 = {"cider", "dill"};
        String[] temp3 = {"apple", "banana"};
        assertEquals(true, graph.removeNodes(nodelist3));
        assertArrayEquals(temp3, graph.getVertices());
    }

    /** printGraph() method of Graph class was tested in a main method of Graph class*/

    /**
     * Test DFS() in Graph class 
     */
    @Test
    public void testDFS() {
        // Setting up the graph
        Graph graph = new Graph();
        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");
        graph.addNode("dill");
        graph.addNode("egg");
        graph.addNode("fig");
        graph.addEdge("apple", "banana");
        graph.addEdge("apple", "cider");
        graph.addEdge("banana", "dill");
        graph.addEdge("cider", "dill");

        // Testing a path with a non-existent node
        assertArrayEquals(null, graph.DFS("apple", "garlic", "alphabetical"));

        // Testing the same from and to nodes
        String[] temp = {"apple"};
        assertArrayEquals(temp, graph.DFS("apple", "apple", "alphabetical"));

        // Testing a path at depth = 1 or 4 depending on which order
        String[] temp2 = {"apple", "banana", "dill", "cider"};
        assertArrayEquals(temp2, graph.DFS("apple", "cider", "alphabetical"));
        // reverse
        String[] reverse2 = {"apple", "cider"};
        assertArrayEquals(reverse2, graph.DFS("apple", "cider", "reverse"));

        // Testing a path at depth = 2
        String[] temp3 = {"apple", "banana", "dill"};
        assertArrayEquals(temp3, graph.DFS("apple", "dill", "alphabetical"));
        // reverse
        String[] reverse3 = {"apple", "cider", "dill"};
        assertArrayEquals(reverse3, graph.DFS("apple", "dill", "reverse"));
   
        // Testing a path between two existing nodes, but one is isolated
        assertArrayEquals(null, graph.BFS("apple", "egg", "alphabetical"));

        // Testing a path between two existing nodes, but both are isolated
        assertArrayEquals(null, graph.BFS("egg", "fig", "alphabetical"));
    }

    /**
     * Test method for BFS() in Graph class
     */
    @Test
    public void testBFS() {
        // Setting up the graph
        Graph graph = new Graph();
        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");
        graph.addNode("dill");
        graph.addNode("egg");
        graph.addNode("fig");
        graph.addEdge("apple", "banana");
        graph.addEdge("apple", "cider");
        graph.addEdge("banana", "dill");
        graph.addEdge("cider", "dill");

        // Testing a path with a non-existent node
        assertArrayEquals(null, graph.BFS("apple", "fig", "alphabetical"));

        // Testing the same from and to nodes
        String[] temp = {"apple"};
        assertArrayEquals(temp, graph.BFS("apple", "apple", "alphabetical"));

        // Testing a path at depth = 1
        String[] temp2 = {"apple", "cider"};
        assertArrayEquals(temp2, graph.BFS("apple", "cider", "alphabetical"));
        // reverse
        String[] reverse2 = {"apple", "cider"};
        assertArrayEquals(reverse2, graph.BFS("apple", "cider", "reverse"));

        // Testing a path at depth = 2
        String[] temp3 = {"apple", "banana", "dill"};
        assertArrayEquals(temp3, graph.BFS("apple", "dill", "alphabetical"));
        // reverse
        String[] reverse3 = {"apple", "cider", "dill"};
        assertArrayEquals(reverse3, graph.BFS("apple", "dill", "reverse"));

        // Testing a path between two existing nodes, but one is isolated
        assertArrayEquals(null, graph.BFS("apple", "egg", "alphabetical"));

        // Testing a path between two existing nodes, but both are isolated
        assertArrayEquals(null, graph.BFS("egg", "fig", "alphabetical"));
    }

    /**
     * Test method for shortestPath() in Graph class
     */
    @Test
    public void testShortestPath() {
        // Setting up the graph
        Graph graph = new Graph();
        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");
        graph.addNode("dill");
        graph.addEdge("apple", "banana");
        graph.addEdge("apple", "cider");
        graph.addEdge("banana", "cider");
        graph.addEdge("cider", "dill");

        // Testing a path with a non-existent node
        assertArrayEquals(null, graph.shortestPath("apple", "fig"));

        // Testing the same from and to nodes
        String[] temp = {"apple"};
        assertArrayEquals(temp, graph.shortestPath("apple", "apple"));

        // Testing a path at depth = 1
        String[] temp2 = {"apple", "banana"};
        assertArrayEquals(temp2, graph.shortestPath("apple", "banana"));
        String[] temp3 = {"apple", "cider"};
        assertArrayEquals(temp3, graph.shortestPath("apple", "cider"));

        // Testing a path at depth = 2
        String[] temp4 = {"apple", "cider", "dill"};
        assertArrayEquals(temp4, graph.shortestPath("apple", "dill"));
    }

    /**
     * Test method for secondShortestPath() in Graph class
     */
    @Test
    public void testSecondShortestPath() {
        // Setting up the graph
        Graph graph = new Graph();
        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");
        graph.addNode("dill");
        graph.addNode("egg");
        graph.addNode("fig");
        graph.addEdge("apple", "banana");
        graph.addEdge("apple", "cider");
        graph.addEdge("banana", "cider");
        graph.addEdge("cider", "dill");

        // Testing a path with a non-existent node
        assertArrayEquals(null, graph.secondShortestPath("apple", "grapefruit"));

        // Testing the same from and to nodes
        String[] temp = {"apple", "banana", "apple"};
        assertArrayEquals(temp, graph.secondShortestPath("apple", "apple"));

        // Testing a path at depth = 1
        String[] temp2 = {"apple", "cider", "banana"};
        assertArrayEquals(temp2, graph.secondShortestPath("apple", "banana"));
        String[] temp3 = {"apple", "banana", "cider"};
        assertArrayEquals(temp3, graph.secondShortestPath("apple", "cider"));

        // Testing a path at depth = 2
        String[] temp4 = {"apple", "banana", "cider", "dill"};
        assertArrayEquals(temp4, graph.secondShortestPath("apple", "dill"));

        // Testing a path between two existing nodes, but one is isolated
        assertArrayEquals(null, graph.secondShortestPath("apple", "egg"));

        // Testing a path between two existing nodes, but both are isolated
        assertArrayEquals(null, graph.secondShortestPath("egg", "fig"));
    }


    /** Test methods for the methods in WeightedGraph class */
    
    /**
     * Test method for addWeightedEdge() in WeightedGraph class
     */
    @Test
    public void testAddWeightedEdge() {
        WeightedGraph graph = new WeightedGraph();

        // add vertices to the graph
        graph.addNode("apple");
        graph.addNode("banana");

        // Testing adding an edge to a non-existing node
        assertEquals(false, graph.addWeightedEdge("banana", "cider", 3)); // one present
        assertEquals(false, graph.addWeightedEdge("cider", "dill", 2)); // both not present

        // Testing adding an edge between two existing nodes
        assertEquals(true, graph.addWeightedEdge("apple", "banana", 3));
        String[] fromTemp = {"banana, 3"};
        String[] toTemp = {};
        assertArrayEquals(fromTemp, graph.getEdges(0));
        assertArrayEquals(toTemp, graph.getEdges(1));

        // Testing adding another edge (parallel edge) between two existing nodes
        assertEquals(true, graph.addWeightedEdge("apple", "banana", 2));
        String[] fromTemp2 = {"banana, 3", "banana, 2"};
        assertArrayEquals(fromTemp2, graph.getEdges(0));
        assertArrayEquals(toTemp, graph.getEdges(1));

        // Testing adding a loop edge
        assertEquals(true, graph.addWeightedEdge("apple", "apple", 1));
        String[] temp = {"banana, 3", "banana, 2", "apple, 1"};
        assertArrayEquals(temp, graph.getEdges(0));
    }

    /**
     * Test method for addWeightedEdges() in WeightedGraph class
     */
    @Test
    public void testAddWeightedEdges() {
        WeightedGraph graph = new WeightedGraph();

        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");

        // Testing adding one edge
        String[] temp = {"banana"};
        String[] expected = {"banana, 1"};
        int[] weight = {1};
        assertEquals(true, graph.addWeightedEdges("apple", temp, weight));
        assertArrayEquals(expected, graph.getEdges(0));

        // Testing adding multiple edges
        String[] temp2 = {"apple", "cider"};
        int[] weight2 = {2, 3};
        String[] expected2 = {"apple, 2", "cider, 3"};
        assertEquals(true, graph.addWeightedEdges("banana", temp2, weight2));
        assertArrayEquals(expected2, graph.getEdges(1));
    }

    /** printWeightedGraph() tested in the main method of WeightedGraph class */

    /**
     * Test method for shortestPath() in WeightedGraph class
     */
    @Test
    public void testWeightedShortestPath() {
        // Setting up the graph
        WeightedGraph graph = new WeightedGraph();
        graph.addNode("apple");
        graph.addNode("banana");
        graph.addNode("cider");
        graph.addNode("dill");
        graph.addWeightedEdge("apple", "banana", 4);
        graph.addWeightedEdge("apple", "cider", 8);
        graph.addWeightedEdge("banana", "cider", 19);
        graph.addWeightedEdge("cider", "dill", 1);

        // Testing a path with a non-existent node
        assertArrayEquals(null, graph.shortestPath("apple", "fig"));

        // Testing the same from and to nodes
        String[] temp = {"apple"};
        assertArrayEquals(temp, graph.shortestPath("apple", "apple"));

        // Testing a path at depth = 1
        String[] temp2 = {"apple", "banana"};
        assertArrayEquals(temp2, graph.shortestPath("apple", "banana"));
        String[] temp3 = {"apple", "cider"};
        assertArrayEquals(temp3, graph.shortestPath("apple", "cider"));

        // Testing a path at depth = 2
        String[] temp4 = {"apple", "banana", "dill"};
        assertArrayEquals(temp4, graph.shortestPath("apple", "dill"));
    }

    /**
     * Test method for secondShortestPath() in WeightedGraph class
     */
    @Test
    public void testWeightedSecondShortestPath() {
        
    }
}
