import java.util.Arrays;

public class RigaCandidate {
    private Supporto[] cl;


    public RigaCandidate(int[] matrixDistance) {
        cl = new Supporto[matrixDistance.length];
        for(int i = 0; i< matrixDistance.length; i++){
            cl[i] = new Supporto(i,matrixDistance[i]);
        }
        Arrays.sort(cl);
    }



    public Supporto[] getOrdered() {
        Supporto[] cli = new  Supporto[Lettore.dimensione-1];
        for(int i=0; i<cli.length; i++){
            cli[i] = this.cl[i+1];
        }
        return cli;
    }
}
