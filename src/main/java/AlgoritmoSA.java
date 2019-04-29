import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritmoSA {
    private static final double MINUTES = 2.8;
    private static final double MULTY = 60000;

    private City[] listCity;
    private int[] arrayPath;
    private int[][] matrix;

    private int[] best;



    public AlgoritmoSA(City[] listCity, int[] arrayPath, int[][] matrix) throws IOException {
        this.listCity = listCity;
        this.arrayPath = arrayPath;
        this.matrix = matrix;
        doIt();
    }


    public int[] getBest() {
        return best;
    }

    private void doIt() {
        double temp = 100, alpha = Partenza.alpha;
        int[] current = arrayPath;
        best = current;
        int convergenza = 0;
        while (((System.currentTimeMillis() - Partenza.startTime) < (MINUTES * MULTY)) && (GetD.getDistance(matrix, best) != Partenza.best)) {
            for (int i = 0; i < 100; i++) {
                Algoritmo2OPT opt = new Algoritmo2OPT(listCity, matrix, swapConPosizione(swapConPosizione(current, 0, ((int) ((double) current.length * (0.5)))), ((int) ((double) current.length * (0.5))), ((int) ((double) current.length * (1)))));
                int[] next = opt.getPath();
                int distNext = GetD.getDistance(matrix, next);
                int distCurrent = GetD.getDistance(matrix, current);
                if (distNext < distCurrent) {
                    current = next;
                    if (distNext < GetD.getDistance(matrix, best)) {
                        best = next;
                    }
                } else {
                    if (randomaVerifica(distNext, distCurrent, temp)) {
                        current = next;
                    }
                }
            }
            temp *= alpha;
        }
    }

    private static boolean randomaVerifica(int next, int current, double temp) {
        double r = Partenza.staticRandom.nextDouble();
        double difference = -((double) next - (double) current);
        double d = Math.pow(Math.E, (difference / temp));
        return (r < d);
    }

    public void write(String str)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/marco/IdeaProjects/ProgettoMaccarini/src/main/resources/res.txt", true));
        writer.append(str);
        writer.close();
    }

    public static int[] swapConPosizione(int[] current, int inizio, int fine) {
        int[] appoggio = new int[current.length];
        int a = 0, b = 1, c = 2, d = 3;
        int posizioni[] = new int[4];

        for (int i = 0; i < posizioni.length; i++) {
            posizioni[i] = Partenza.staticRandom.nextInt((fine - inizio - 1)) + inizio;
        }

        Arrays.sort(posizioni);
        int contatoreVettore = 0;
        appoggio[contatoreVettore] = current[posizioni[a]];
        contatoreVettore++;
        for (int i = posizioni[c] + 1; i <= posizioni[d]; i++) {
            appoggio[contatoreVettore] = current[i];
            contatoreVettore++;
        }
        for (int i = posizioni[b] + 1; i <= posizioni[c]; i++) {
            appoggio[contatoreVettore] = current[i];
            contatoreVettore++;
        }
        for (int i = posizioni[a] + 1; i <= posizioni[b]; i++) {
            appoggio[contatoreVettore] = current[i];
            contatoreVettore++;
        }
        for (int i = posizioni[d] + 1; i < current.length - 1; i++) {
            appoggio[contatoreVettore] = current[i];
            contatoreVettore++;
        }
        for (int i = 0; i < posizioni[a]; i++) {
            appoggio[contatoreVettore] = current[i];
            contatoreVettore++;
        }
        appoggio[contatoreVettore++] = current[posizioni[a]];
        return appoggio;
    }
}