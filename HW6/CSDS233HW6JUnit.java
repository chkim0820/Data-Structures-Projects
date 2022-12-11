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

    }
}
