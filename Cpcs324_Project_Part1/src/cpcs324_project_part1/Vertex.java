
package cpcs324_project_part1;

import java.util.LinkedList;

public class Vertex {
    int lable;
    boolean isVisited = false;
    LinkedList<Edge>[] adjacencylist; //to represents the adjacency list of a vertex
    
    public Vertex(int lable) {
        this.lable=lable;
    }

    public int getLable() {
        return lable;
    }

    public void setLable(int lable) {
        this.lable = lable;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public LinkedList<Edge>[] getAdjacencylist() {
        return adjacencylist;
    }

    public void setAdjacencylist(LinkedList<Edge>[] adjacencylist) {
        this.adjacencylist = adjacencylist;
    }
    
    
    
    
   
}
