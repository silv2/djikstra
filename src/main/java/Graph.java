/**
 * Created by Silvia on 10. 9. 2014.
 */
public class Graph {
    private Vertex firstVertex;
    private Vertex secondVertex;
    private int distance;

    public Graph(Vertex firstVertex, Vertex secondVertex, int distance) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.distance = distance;
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(Vertex firstVertex) {
        this.firstVertex = firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public void setSecondVertex(Vertex secondVertex) {
        this.secondVertex = secondVertex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
