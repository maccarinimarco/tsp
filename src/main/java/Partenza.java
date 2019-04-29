import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class Partenza {

    public static double startTime;


    public static Random staticRandom;


    public static String[] files = {
            "ch130.tsp",
            "d198.tsp",
            "eil76.tsp",
            "kroa100.tsp",
            "lin318.tsp",
            "pr439.tsp",
            "pcb442.tsp",
            "fl1577.tsp",
            "u1060.tsp",
            "rat783.tsp"
    };

    public static long seeds[] = {
            1555530061305L,
            1555530061856L,
            1555530068725L,
            1555530069097L,
            1555530405240L,
            1555570025143L,
            1555732365813L,
            1555639125431L,
            1555586021874L,
            1555609379770L

    };

    public static Lettore lettore;
    public static double alpha = 0.95;

    static int[] pathNN = null;
    static int[] path2OPT = null;
    static int[] listPos = null;
    static int[] pathSA = null;
    static int dimension = 0;
    static int[][] matrixDistance = null;
    static City[] listCity;

    static int best;
    static Supporto[][] matrixCandidate;

    static ArrayList<Node> mstG;

    public static int partenza = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("hello");
    }


    public  void doIt(int n) throws IOException {
        int nFile = n;
        partenza = 19;

        startTime = System.currentTimeMillis();
        long seed = seeds[nFile];
        staticRandom = new Random(seed);

        /** LETTURA DATI **/
        lettore = new Lettore(files[nFile]);
        dimension = lettore.getDimensione();
        listCity = lettore.getResult();
        best = lettore.getBest();
        matrixDistance = lettore.getMatrixDistances();
        matrixCandidate = lettore.getMatriceCandidateList();

        /** MINIMUM SPANNING TREE **/
        MST mst = new MST(listCity, matrixDistance, matrixCandidate);
        mstG = mst.getGraph();
        aggiungiRamiMST();

        /** NEAREST NEIGHBOUR **/
        AlgoritmoNN nn = new AlgoritmoNN(listCity, matrixDistance);
        pathNN = nn.getPath();
        listPos = nn.getListPos();

        /** 2OPT **/
        Algoritmo2OPT opt = new Algoritmo2OPT(listCity, matrixDistance, pathNN);
        path2OPT = opt.getPath();

        /**SA**/
        AlgoritmoSA sa = new AlgoritmoSA(listCity, path2OPT, matrixDistance);
        pathSA = sa.getBest();


        System.out.println(files[nFile] + "Distance sa " + GetD.getDistance(matrixDistance, pathSA) + "  " + ((((double) GetD.getDistance(matrixDistance, pathSA) - (double) best) / (double) best) * 100) + "%   time " + (System.currentTimeMillis() - startTime) / 1000 + " seed: " + seed);

        scriviFile(nFile);

    }


    private  void scriviFile(int nFile) {

        PrintWriter printWriter = null;
        try {
            //            br = new BufferedReader(new FileReader(file));
            printWriter = new PrintWriter(new File(files[nFile] + ".tour"));

            ClassLoader classLoader = getClass().getClassLoader();
            File file_in = new File(classLoader.getResource((files[nFile])).getFile());







            BufferedReader bufferedReader = new BufferedReader(new FileReader(file_in));


            for (int i = 0; i < 4; i++) {
                printWriter.println(bufferedReader.readLine());
            }
            printWriter.println("TOUR_SECTION");
            for (int i = 0; i < path2OPT.length - 1; i++) {
                printWriter.println(listCity[pathSA[i]].getId() + 1);
            }
            printWriter.println("-1");
            printWriter.println("EOF");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.close();
    }

    private static void aggiungiRamiMST() {
        for (int i = 0; i < mstG.size(); i++) {
            int id = mstG.get(i).getId();
            listCity[id].verificaAggiungiMST(mstG.get(i).getIdLinked());
        }
    }


/**
 private static void aggiungiRamiMST() {
 for (int i = 0; i < listCity.length; i++) {
 int id = listCity[i].getId();
 listCity[id].verificaAggiungiMST();
 }
 }
 **/
}
