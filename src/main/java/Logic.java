import java.io.*;
import java.util.*;

/**
 * Created by Silvia on 10. 9. 2014.
 */
public class Logic {
    private Queue<Vertex> vertexQueue = new LinkedList<Vertex>() ;
    private List<Vertex> vertexList = new ArrayList<Vertex>();
    private List<Integer> minWay = new ArrayList<Integer>();

    public void init(int source, int size) throws IOException, WrongFormatException {
        for (int i=0; i<size; i++){
            Vertex vertex;
            if (source==i) {
                vertex = new Vertex(i,0,false,false,-1);
            }else{
                vertex = new Vertex(i,10000,false,false,-1);
            }
            vertexQueue.add(vertex);
            vertexList.add(vertex);

        }

        BufferedReader br = null;
        String sCurrentLine;
        char[] numbers;
        List<Graph> graphList = new ArrayList<Graph>();
        String filePath = new File("").getAbsolutePath();
        try {

            br = new BufferedReader(new FileReader(filePath + "/src/main/resources/matrix.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean badFormat = false;
        int target = 0;
        while ((sCurrentLine = br.readLine()) != null) {
            numbers = sCurrentLine.toCharArray();
                if (numbers.length==1) target = Character.getNumericValue(numbers[0]);

                else {
                    try {
                        if (Character.getNumericValue((numbers[0])) == -1 || Character.getNumericValue((numbers[2])) == -1 ||
                                Character.getNumericValue((numbers[4])) == -1) {
                            badFormat = true;
                            throw new WrongFormatException("Wrong format input");
                        } else {
                            Graph graph = new Graph(vertexList.get(Character.getNumericValue((numbers[0]))),
                                    vertexList.get(Character.getNumericValue((numbers[2]))), Character.getNumericValue((numbers[4])));
                            graphList.add(graph);
                        }

                    } catch (WrongFormatException ex) {
                        ex.printStackTrace();
                    }
                }
        }

       vertexList.get(target).setTarget(true);
        if (badFormat == false)
             djikstra(graphList);


    }

    public void djikstra(List<Graph> graphList){
        int minDistance = 0;

        while (vertexQueue.isEmpty()==false ){
            Vertex  minVertex = finMin();
            minDistance = minVertex.getDistance();
            int nameActual = minVertex.getName();
            vertexQueue.remove(minVertex);
            if (minVertex.isTarget()) {
                minWay.add(minVertex.getName());
               while (minVertex.getPrevious() != -1){
                   minWay.add(minVertex.getPrevious());
                   minVertex = vertexList.get(minVertex.getPrevious());
               }
            }
            else {
                for (Graph graph : graphList) {
                    Vertex neighbor = null;
                    boolean found = false;
                    if ((graph.getFirstVertex().getName() == nameActual && vertexQueue.contains(graph.getSecondVertex()))) {
                        neighbor = graph.getSecondVertex();
                        found = true;
                    }
                    if ((graph.getSecondVertex().getName() == nameActual) && vertexQueue.contains(graph.getFirstVertex())) {
                        neighbor = graph.getFirstVertex();
                        found = true;
                    }
                    if (found) {
                        int newDistance = 0;
                        newDistance = minDistance + graph.getDistance();
                        if (newDistance < neighbor.getDistance()) {
                            neighbor.setDistance(newDistance);
                            neighbor.setPrevious(nameActual);
                        }

                    }
                }
            }

        }

    if (minWay.isEmpty()) System.out.println("I couldn´t find way.");
        else {
        Collections.reverse(minWay);
        System.out.print("Best way is: ");
        for (Integer i : minWay) {
            System.out.print(i+" ");
        }
    }


    }

    public Vertex finMin(){
        int min=10000;
        Vertex minVertex = null;
        for (Vertex v : vertexQueue){
            int distance = v.getDistance();
            if (distance<min) {
                minVertex = v;
                min = distance;
            }
        }
        return minVertex;
    }
}
