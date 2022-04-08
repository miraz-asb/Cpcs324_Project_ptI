package cpcs324_project_part1;

public class Edge implements Comparable<Edge> {

    private Vertex source;
    private Vertex destination;
    private Vertex parent = new Vertex(-1);
    private int weight = 1;

    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.parent = source;
    }

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    public Edge() {
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edgeToCompare) {
        return this.weight - edgeToCompare.getWeight();
    }


}
