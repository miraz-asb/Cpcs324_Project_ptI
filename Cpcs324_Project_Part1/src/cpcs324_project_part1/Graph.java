package cpcs324_project_part1;

import java.io.PrintWriter;
import java.util.*;

public class Graph {
    private int verticesNo;//number of vertices of the graph
    private int edgeNo;//number of edges of the graph.
    private boolean isDigraph = false;//boolean that is set to true if the graph is directed graph
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();//list of vertices of a graph.
    private LinkedList<Edge>[] adjacencylist;//an array of linked lists
    
    /**
     * 
     * @param verticesNo
     * @param edgeNo 
     */
    public Graph(int verticesNo, int edgeNo) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        adjacencylist = new LinkedList[verticesNo];
        
        //initialize adjacency lists for all the vertices
        for (int i = 0; i < verticesNo; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    /**
     * empty constructor
     */
    public Graph() {
    }
    
    
    /**
     * creating a graph object with the specified parameters and randomly initializes the vertices’ labels, creating
     * edges that connects the created vertices randomly and assigning them random weights.
     * @param verticesNo
     * @param edgeNo
     * @return 
     */
    public Graph makeGraph(int verticesNo, int edgeNo) {
        //variables
        //new graph object
        Graph g = new Graph(verticesNo, edgeNo);        
        int random, w=0, leftEdge = 0;//w weight
        Vertex v, u;// v source , u destination

        //loop to randomly connect two randomly verices with randomly edges
        for (int i = 0; i < verticesNo ; i++) {

            //random vertices
            random = (int) (Math.random() * verticesNo );
            v = new Vertex(random);
            random = (int) (Math.random() * verticesNo );
            u = new Vertex(random);
       
            //check connectivity by isConnected function and if its connected choose different vertices by looping again
            if (isConnected(v, u,g.getAdjacencylist()) == true || v.getLable() == u.getLable()) {
                i--;
                continue;
            } //if not connectend, connect it by addEdge function shown below
            else {
                //random waight 
                w = (int) (Math.random() * 20 + 1);
                // add edge between v and u 
                g.addEdge(v, u, w);
                //increment number of edge
                g.edgeNo++;
                
                // if undirected graph add edge between u and v also 
                // if and only if v and u are not same
                if (g.isDigraph == false && v.getLable()!= u.getLable()) {
                    g.addEdge(u, v, w);
                    g.edgeNo++;
                }
            }
            //calculate remaining number of edge     
            leftEdge = edgeNo - g.edgeNo;            
        }

        //loop again on leftover edges
        for (int i = 0; i < leftEdge; i++) {
            //random vertices
            random = (int) (Math.random() * verticesNo );
            v = new Vertex(random);
            random = (int) (Math.random() * verticesNo );
            u = new Vertex(random);
            
            //check connectivity by isConnected function and if its connected choose different vertices by looping again
            if (isConnected(v, u,g.getAdjacencylist()) == true || v.getLable() == u.getLable()) {
                i--;
            } //if not connectend, connect it by addEdge function shown below
            else {
                //random waight 
                w = (int) (Math.random() * 20 + 1);
                // add edge between v and u 
                g.addEdge(v, u, w);
                //increment number of edge
                g.edgeNo++;
                
                // if undirected graph add edge between u and v also 
                // if and only if v and u are not same
                if (g.isDigraph == false && v.getLable()!= u.getLable()) {
                    g.addEdge(u, v, w);
                    g.edgeNo++;
                }
            }            
        }

        return g;
    }
    
    /**
     * creates an edge object and passes v, u and w as parameters which are source vertex, destination vertex and weight
     * @param v
     * @param u
     * @param w 
     */
    public void addEdge(Vertex v, Vertex u, int w) {
        Edge edge = new Edge(v, u, w);
        adjacencylist[v.getLable()].addFirst(edge);         
    }
    
    /**
     * check if there is an edge between the two vertices
     * @param v
     * @param u
     * @param temp
     * @return 
     */
    public boolean isConnected(Vertex v, Vertex u,LinkedList<Edge>[] temp) {
        LinkedList<Edge> templ;      
        for (LinkedList<Edge> temp1 : temp) {
            templ = temp1;// take temp of each linkedList in the array 
            for (int j = 0; j< templ.size();j++) {
                //check if v and u have egd
                // no need to check if source of edge = to u and destination = to v since we did it in makeGraph function
                if ((templ.get(j).getSource().getLable()== v.getLable() && templ.get(j).getDestination().getLable()==u.getLable())){
                    return true;
                }
            }
        }
        return false;
        
    }
    
    /**
     * print Graph
     * @param output
     */
    public void printGraph(PrintWriter output) {
        for (int i = 0; i < verticesNo ; i++) {
            LinkedList<Edge> list = adjacencylist[i];
            for (int j = 0; j < list.size(); j++) {
                Edge edge = list.get(j);
                output.println("Edge from " + edge.getSource().getLable() + " to " + edge.getDestination().getLable() + " has weight " + edge.getWeight());
            }
        }
    }
    /**
     * number of setter and getter for abstraction 
     */
    
    public int getVerticesNo() {
        return verticesNo;
    }
    
    public void setVerticesNo(int verticesNo) {
        this.verticesNo = verticesNo;
    }
    
    public int getEdgeNo() {
        return edgeNo;
    }
    
    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }
    
    public boolean isIsDigraph() {
        return isDigraph;
    }
    
    public void setIsDigraph(boolean isDigraph) {
        this.isDigraph = isDigraph;
    }
    
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }
    
    public void addVertices(Vertex vertex) {
        vertices.add(vertex);
    }
    
    public LinkedList<Edge>[] getAdjacencylist() {
        return adjacencylist;
    }
    
    public void setAdjacencylist(LinkedList<Edge>[] adjacencylist) {
        this.adjacencylist = adjacencylist;
    }  
    
}

