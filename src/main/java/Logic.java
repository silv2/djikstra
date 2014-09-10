import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Silvia on 10. 9. 2014.
 */
public class Logic {
    private Queue<Vertex> vertexQueue = new LinkedList<Vertex>() ;
    private List<Vertex> vertexList = new ArrayList<Vertex>();

    public void init(int source, int target, int size){
        boolean isTarget=false;

        for (int i=1; i<=size; i++){
            if (target==i) isTarget=true;
            Vertex vertex;
            if (source==i) {
                vertex = new Vertex(i,0,isTarget,false,0);
            }else{
                vertex = new Vertex(i,10000,isTarget,false,0);
            }
            vertexQueue.add(vertex);
            vertexList.add(vertex);

        }
        List<Graph> graphList = new ArrayList<Graph>();
        Graph graph = new Graph(vertexList.get(0),vertexList.get(1),5);
        Graph graph2 = new Graph(vertexList.get(0),vertexList.get(2),1);
        Graph graph3 = new Graph(vertexList.get(2),vertexList.get(3),1);
        Graph graph4 = new Graph(vertexList.get(1),vertexList.get(3),5);
        graphList.add(graph);
        graphList.add(graph2);
        graphList.add(graph3);
        graphList.add(graph4);
        djikstra(graphList);

    }

    public void djikstra(List<Graph> graphList){
        int minDistance = 0;
        while (vertexQueue.isEmpty()==false){
            Vertex minVertex = finMin();
            minDistance = minVertex.getDistance();
            int nameActual = minVertex.getName();
            vertexQueue.remove(minVertex);

            for (Graph graph : graphList){
                Vertex neighbor = null;
                boolean found = false;
                if ((graph.getFirstVertex().getName() == nameActual && vertexQueue.contains(graph.getSecondVertex()) )){
                    neighbor = graph.getSecondVertex();
                    found = true;
                }
                if ((graph.getSecondVertex().getName() == nameActual) && vertexQueue.contains(graph.getFirstVertex())){
                    neighbor = graph.getFirstVertex();
                    found = true;
                }
                if(found ){
                    int newDistance = 0;
                    newDistance = minDistance + graph.getDistance();
                    if (newDistance<neighbor.getDistance()) {
                        neighbor.setDistance(newDistance);
                        neighbor.setPrevious(nameActual);
                    }

                }
            }

        }



    }

    public Vertex finMin(){
        int min=10000;
        Vertex minVertex = null;
        for (Vertex v : vertexQueue){
            int distance = v.getDistance();
            if (distance<min) minVertex = v;
        }
        return minVertex;
    }
}
