import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class City {
    private int id;
    private double lat;
    private double lon;
    private int[] candidateList;


    public int getId() {
        return id;
    }

    public City(int id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public void setCandidateList(int[] candidateList) {
        this.candidateList = candidateList;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }


    public int getDistance(City c) {
        double ac = this.lat - c.getLat();
        double cb = this.lon - c.getLon();
        return (int) Math.round((Math.sqrt(Math.pow(ac, 2) + Math.pow(cb, 2))));
    }

    @Override
    public String toString() {
         return this.candidateList.length + "   City:" +
                " id=  " + id +
                ", lat=  " + lat +
                ", lon=  " + lon + " Candidate=  " + ritornaCandidate();
    }

    public String ritornaCandidate() {
        String s = "";
        for (int i = 0; i < candidateList.length; i++) {
            s += candidateList[i] + "  ";
        }
        return s;
    }

    public int[] getCandidateList() {
        return candidateList;
    }

    public void verificaAggiungiMST(ArrayList<Node> mst) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < Lettore.dimCandidate; i++)
            s.add(Partenza.matrixCandidate[this.id][i].getId());
        for (int i = 0; i < mst.size(); i++) {
            s.add(mst.get(i).getId());
        }
        this.candidateList = new int[s.size()];
        int conta = 0;
        for (int e : s) {
            this.candidateList[conta] = e;
            conta++;
        }
    }


/**
    public void verificaAggiungiMST() {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < Lettore.dimCandidate; i++)
            s.add(Partenza.matrixCandidate[this.id][i].getId());
        this.candidateList = new int[s.size()];
        int conta = 0;
        for (int e : s) {
            this.candidateList[conta] = e;
            conta++;
        }
    }

 **/
}
