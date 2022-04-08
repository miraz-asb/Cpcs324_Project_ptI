package cpcs324_project_part1;

import java.io.PrintWriter;
import java.util.*;

public class MHPrimAlg extends MSTAlgorithm {

    // output MST
    private LinkedList<Edge> MSTResultList = new LinkedList<>();
    private ResultSet[] resultSet;

    /**
     * preform prim by heap to given Graph
     *
     * @param g
     * @param output
     */
    public void primMH(Graph g,PrintWriter output) {
        int vertices = g.getVerticesNo();
        double StartTime = System.currentTimeMillis();
        boolean[] inHeap = new boolean[vertices];
        resultSet = new ResultSet[vertices];
        //keys[] used to store the key to know whether min hea update is required
        int[] key = new int[vertices];
        //create heapNode for all the vertices
        HeapNode[] heapNodes = new HeapNode[vertices];
        for (int i = 0; i < vertices; i++) {
            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].key = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
            resultSet[i].parent = -1;
            inHeap[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        //decrease the key for the first index
        heapNodes[0].key = 0;

        //add all the vertices to the MinHeap
        MinHeap minHeap = new MinHeap(vertices);
        //add all the vertices to priority queue
        for (int i = 0; i < vertices; i++) {
            minHeap.insert(heapNodes[i]);
        }
        
        LinkedList<Edge>[] adjacencylist = g.getAdjacencylist();
        //while minHeap is not empty
         do{
            //extract the min
            HeapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedNode.vertex;
            inHeap[extractedVertex] = false;

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = adjacencylist[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is present in heap
                if (inHeap[edge.getDestination().getLable()]) {
                    int destination = edge.getDestination().getLable();
                    int newKey = edge.getWeight();
                    //check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        decreaseKey(minHeap, newKey, destination);
                        //update the parent node for destination
                        resultSet[destination].parent = extractedVertex;
                        resultSet[destination].weight = newKey;
                        key[destination] = newKey;
                        MSTResultList.add(edge);
                    }
                }
            }
        }while (!minHeap.isEmpty());
        double FinishTime = System.currentTimeMillis();
        double TotalTime = FinishTime - StartTime;
        output.println("Total runtime of Prim's Min-Heap Algorithm : " + TotalTime + " ms.");
        displayResultingMST(output);

    }

    /**
     *
     * @param minHeap
     * @param newKey
     * @param vertex
     */
    public void decreaseKey(MinHeap minHeap, int newKey, int vertex) {

        //get the index which key's needs a decrease;
        int index = minHeap.indexes[vertex];

        //get the node and update its value
        HeapNode node = minHeap.mH[index];
        node.key = newKey;
        minHeap.bubbleUp(index);
    }

    @Override
    public void displayResultingMST(PrintWriter output) {
        int total_min_weight = 0;
        for (int i = 0; i < resultSet.length; i++) { // Loop to print edge information
            if(resultSet[i].parent != -1){
            total_min_weight += resultSet[i].weight; 
                output.println("Edge from " + i + " to " + resultSet[i].parent
                        + " has weight " + resultSet[i].weight);
        }
        }
        output.println("Total minimum key: " + total_min_weight); //To change body of generated methods, choose Tools | Templates.

    }

/////////////////////////////////////////////////////////MinHeap class/////////////////////////////////////////////////////////////////////////////////////////

    static class MinHeap {

        int capacity;
        int currentSize;
        HeapNode[] mH;
        int[] indexes; //will be used to decrease the key

        /**
         * constructor
         *
         * @param capacity
         */
        public MinHeap(int capacity) {
            this.capacity = capacity;
            mH = new HeapNode[capacity + 1];
            indexes = new int[capacity];
            mH[0] = new HeapNode();
            mH[0].key = Integer.MIN_VALUE;
            mH[0].vertex = -1;
            currentSize = 0;
        }

        /**
         * display MinHeap if needed
         */
        public void display() {
            for (int i = 0; i <= currentSize; i++) {
                System.out.println(" " + mH[i].vertex + " key " + mH[i].key);
            }
            System.out.println("________________________");
        }

        /**
         * insert HeapNode
         *
         * @param x
         */
        public void insert(HeapNode x) {
            currentSize++;
            int idx = currentSize;
            mH[idx] = x;
            indexes[x.vertex] = idx;
            bubbleUp(idx);
        }

        /**
         *
         * @param pos
         */
        public void bubbleUp(int pos) {
            int parentIdx = pos / 2;
            int currentIdx = pos;
            while (currentIdx > 0 && mH[parentIdx].key > mH[currentIdx].key) {
                HeapNode currentNode = mH[currentIdx];
                HeapNode parentNode = mH[parentIdx];

                //swap the positions
                indexes[currentNode.vertex] = parentIdx;
                indexes[parentNode.vertex] = currentIdx;
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx / 2;
            }
        }

        /**
         *
         * @return
         */
        public HeapNode extractMin() {
            HeapNode min = mH[1];
            HeapNode lastNode = mH[currentSize];
            // update the indexes[] and move the last node to the top
            indexes[lastNode.vertex] = 1;
            mH[1] = lastNode;
            mH[currentSize] = null;
            sinkDown(1);
            currentSize--;
            return min;
        }

        /**
         *
         * @param k
         */
        public void sinkDown(int k) {
            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k + 1;
            if (leftChildIdx < heapSize() && mH[smallest].key > mH[leftChildIdx].key) {
                smallest = leftChildIdx;
            }
            if (rightChildIdx < heapSize() && mH[smallest].key > mH[rightChildIdx].key) {
                smallest = rightChildIdx;
            }
            if (smallest != k) {

                HeapNode smallestNode = mH[smallest];
                HeapNode kNode = mH[k];

                //swap the positions
                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        /**
         *
         * @param a
         * @param b
         */
        public void swap(int a, int b) {
            HeapNode temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }

        /**
         *
         * @return
         */
        public boolean isEmpty() {
            return currentSize == 0;
        }

        /**
         *
         * @return
         */
        public int heapSize() {
            return currentSize;
        }

    }
}

