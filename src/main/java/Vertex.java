/**
 * Created by Silvia on 10. 9. 2014.
 */
public class Vertex {
    private boolean visited;
    private int name;
    private int distance;
    private boolean target;
    private int previous;

    public Vertex(int name, int distance, boolean target, boolean visited, int previous) {
        this.name = name;
        this.distance = distance;
        this.target = target;
        this.visited = visited;
        this.previous = previous;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }
}
