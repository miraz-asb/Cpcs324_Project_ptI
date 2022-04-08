package cpcs324_project_part1;

import java.io.PrintWriter;
import java.util.*;

public class PQPrimAlg extends MSTAlgorithm {

    private LinkedList<Edge> MSTResultList = new LinkedList<>();

    /**
     * Apply prim by PriorityQueue to given Graph
     *
     * @param g
     * @param output
     */
    public void primsPQ(Graph g, PrintWriter output) {
        double StartTime = System.currentTimeMillis();
        // Whether a vertex is in PriorityQueue or not
        Boolean[] mst = new Boolean[g.getVerticesNo()];
        HeapNode[] List = new HeapNode[g.getVerticesNo()];

        // Stores the parents of a vertex and there weight
        int parent[] = new int[g.getVerticesNo()];

        for (int i = 0; i < g.getVerticesNo(); i++) {
            List[i] = new HeapNode();
        }

        for (int i = 0; i < g.getVerticesNo(); i++) {
            mst[i] = false;
            List[i].key = Integer.MAX_VALUE;// Initialize key values to infinity
            List[i].vertex = i;
            parent[i] = -1; // -1 is NIL
        }

        // Include the source vertex in mstset
        mst[0] = true;

        // Set key of the first element in the queue as 0 
        List[0].key = 0;

        PriorityQueue<HeapNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.key));

        for (int i = 0; i < g.getVerticesNo(); i++) {
            queue.add(List[i]);
        }

        // Loops until the queue is not empty
        while (!queue.isEmpty()) {

            // Extracts a node with min key value
            HeapNode node0 = queue.poll();// فيها اقل قيمة في البايروريتي كيو

            // Include that node into mstset
            mst[node0.vertex] = true;//boolean دخلنا او لا 

            // For all adjacent vertex of the extracted vertex V
            for (Edge iterator : g.getAdjacencylist()[node0.vertex]) { 
                //same as the upper for(int i =0 ; i<g.getAdjacencylist()[node0.vertex].size();i++)
                // If V is in queue
                if (mst[iterator.getDestination().getLable()] == false) {

                    // If the key value of the adjacent vertex is
                    // more than the extracted key
                    // update the key value of adjacent vertex
                    // to update first remove and add the updated vertex
                    if (List[iterator.getDestination().getLable()].key > iterator.getWeight()) {

                        queue.remove(List[iterator.getDestination().getLable()]);// remove parent from queue
                        List[iterator.getDestination().getLable()].key = iterator.getWeight(); // change its key with its weight (because its weight is smaller)

                        queue.add(List[iterator.getDestination().getLable()]); //add the parent again in the queue
                        parent[iterator.getDestination().getLable()] = node0.vertex; // update the value of parent
                    }
                }
            }
        }

        for (int i = 0; i < g.getVerticesNo(); i++) { // loop to add all edges that are making MST
            LinkedList<Edge> t = g.getAdjacencylist()[i];
            for (int j = 0; j < t.size(); j++) {
                if (t.get(j).getSource().getLable() == i && t.get(j).getDestination().getLable() == parent[i]) {
                    MSTResultList.add(t.get(j));
                }
            }
        }
        //finish time of the algorithm
        double FinishTime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        output.println("Total runtime of Prim's Priority Queue Algorithm: " + (FinishTime - StartTime) + " ms.");
        displayResultingMST(output);
    }

    @Override
    public void displayResultingMST(PrintWriter output) {
        int total_min_weight = 0;
        for (int i = 0; i < MSTResultList.size(); i++) {
            output.println("Edge from " + MSTResultList.get(i).getSource().getLable() + " to " + MSTResultList.get(i).getDestination().getLable() + " has weight " + MSTResultList.get(i).getWeight());

            total_min_weight += MSTResultList.get(i).getWeight();
        }
        output.println("Total minimum key: " + total_min_weight);
    }

}



