
package cpcs324_project_part1;

import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 *
 */
public abstract class MSTAlgorithm {
    
 private LinkedList<Edge> MSTResultList;
    
    /**
     * it is an abstract function that should be implemented by the subclasses’ polymorphic functions. to display ResultingMST
     * @param output
     */
    abstract public void displayResultingMST(PrintWriter output);
    
    ////////////////////////////////////////////////////////HeapNode class////////////////////////////////////////////////////////////////////////////////////////////    
    static class HeapNode {

        int vertex;
        int key;
    }

    //////////////////////////////////////////////////////ResultSet class//////////////////////////////////////////////////////////////////////////////////////////
    static class ResultSet {

        int parent = -1;
        int weight = 0;
    }
}
