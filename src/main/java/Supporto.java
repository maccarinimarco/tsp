public class Supporto implements Comparable {
    private int id;
    private int distanza;


    public Supporto(int id, int distanza) {
        this.id = id;
        this.distanza = distanza;
    }

    public int getId() {
        return id;
    }

    public int getDistanza() {
        return distanza;
    }

    public int compareTo(Object o) {
        return (this.distanza - ((Supporto) o).getDistanza());
    }

    @Override
    public String toString() {
        return
                "  " + id
                ;
    }
}
