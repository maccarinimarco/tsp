import java.util.ArrayList;

public class Node {
    private int id;
    private ArrayList<Node> idLinked = new ArrayList<>();


    public Node(int id) {
        this.id = id;

    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public ArrayList<Node> getIdLinked() {
        return idLinked;
    }

    public void setIdLinked(ArrayList<Node> idLinked) {
        this.idLinked = idLinked;
    }

    @Override
    public String toString() {
        String appoggio = "Id: " + this.getId() + " ->  ";
        for (Node n : this.getIdLinked()) {
            appoggio += n.id + "  ";
        }

        return appoggio;
    }
}
