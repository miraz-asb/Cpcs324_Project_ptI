package cpcs324_project_part1;

import java.io.PrintWriter;
import java.util.*;

public class KruskalAlg extends MSTAlgorithm {

    //output MST
    private LinkedList<Edge> MSTResultList = new LinkedList<>();

    /**
     * constructor
     */
    public KruskalAlg() {
    }

    /**
     * apply KruskalAlgorithm to given Graph
     *
     * @param g
     * @param output
     */
    public void applyKruskal(Graph g,PrintWriter output) {
        // start time
        double StartTime = System.currentTimeMillis();
        // get Adjacencylist of g
        LinkedList<Edge>[] allEdges = g.getAdjacencylist();
        // new PriorityQueue that order edge according to weight
        PriorityQueue<Edge> priorityQueueVar = new PriorityQueue<>(g.getEdgeNo(), Comparator.comparingInt(o -> o.getWeight()));

        //add all the edges to priority queue
        //sort the edges depending on weights
        for (LinkedList<Edge> allEdge : allEdges) {
            for (int j = 0; j < allEdge.size(); j++) {
                priorityQueueVar.add(allEdge.get(j));
            }
        }

        //create a parent []
        int parent[] = new int[g.getVerticesNo()];

        //makeset
        makeSet(parent);

        //process vertices - 1 edges
        int index = 0;
        while (index < g.getVerticesNo() - 1 && !priorityQueueVar.isEmpty()) {
            // take edge from priorityQueue
            Edge edge = priorityQueueVar.remove();
            //check if adding this edge creates a cycle
            int x_set = find(parent, edge.getSource().getLable());

            int y_set = find(parent, edge.getDestination().getLable());

            if (x_set == y_set) {
                //ignore, will create cycle
            } else {
                //add it to our final result
                MSTResultList.add(edge);
                index++;
                union(parent, x_set, y_set);
            }
        }
        //finish time of the algorithm
        double FinishTime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        output.println("Total runtime of Kruskal's Algorithm: " + (FinishTime - StartTime) + " ms.");
        displayResultingMST(output);
    }

    /**
     * initialize parent and weight
     *
     * @param parent
     */
    public void makeSet(int [] parent){
    for (int i = 0; i <parent.length ; i++) {
        parent[i] = i;
    }
    }

    /**
     * find root of sent vertex
     *
     * @param parent
     * @param i
     * @return label of parent
     */
    public int find(int [] parent, int i){
    if(parent[i]!= i)
        return find(parent, parent[i]);
    return i;
}

    /**
     * union the parent with child and weight
     *
     * @param parent
     * @param x
     * @param y
     */
    public void union(int [] parent, int x, int y){
    int x_set_parent = find(parent, x);
    int y_set_parent = find(parent, y);
    parent[y_set_parent] = x_set_parent;
}    

    @Override
    public void displayResultingMST(PrintWriter output) {
        int total_min_weight = 0;
        for (int i = 0; i < MSTResultList.size(); i++) { // Loop to print edge information
            Edge edge = MSTResultList.get(i);
            output.println("Edge from " + edge.getSource().getLable() + " to " + edge.getDestination().getLable() + " has weight " + edge.getWeight());
            total_min_weight += edge.getWeight();
        }
        output.println("Total minimum key: " + total_min_weight); //To change body of generated methods, choose Tools | Templates.

    }

}
