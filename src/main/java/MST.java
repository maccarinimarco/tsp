import java.util.ArrayList;
import java.util.Random;

public class MST {

    private City[] listCity;
    private int[][] matrixDistance;

    private ArrayList<Integer> node = new ArrayList<>();
    private ArrayList<Node> graph = new ArrayList<>();
    private Supporto[][] matriceCandidate;

    private boolean[] visited;


    public ArrayList<Node> getGraph() {
        return graph;
    }

    public MST(City[] listCity, int[][] matrixDistance, Supporto[][] matriceCandidate) {
        this.listCity = listCity;
        this.matrixDistance = matrixDistance;
        this.matriceCandidate = matriceCandidate;
        this.visited = new boolean[listCity.length];
        for (int i = 0; i < this.visited.length; i++)
            this.visited[i] = false;

        doIt();
    }

    private void doIt() {
        int id = Partenza.partenza;

        graph.add(new Node(id));
        node.add(id);
        visited[id] = true;
        int giri = 0;
        while (giri != listCity.length - 1) {
            giri++;
            int nextToVisit = 0;
            int minimo = Integer.MAX_VALUE;
            int collegamento = 0;
            for (int i = 0; i < node.size(); i++) {
                Supporto d = getOpp(matriceCandidate[node.get(i)]);
                if (minimo > d.getDistanza()) {
                    nextToVisit = d.getId();
                    minimo = d.getDistanza();
                    collegamento = i;
                }
            }
            node.add(nextToVisit);
            visited[nextToVisit] = true;
            Node n = new Node(nextToVisit);
            n.getIdLinked().add(graph.get(collegamento));
            graph.get(collegamento).getIdLinked().add(n);
            graph.add(n);
        }
    }

    private Supporto getOpp(Supporto[] supportos) {
        for (int i = 0; i < supportos.length; i++) {
            if (!visited[supportos[i].getId()])
                return supportos[i];
        }
        System.out.println("nessuno");
        return null;
    }
}
