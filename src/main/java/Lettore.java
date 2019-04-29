import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Lettore {
    public static int dimCandidate ;
    public static  int dimensione;
    private int best;
    //private TreeMap<Integer,City> result = new TreeMap<>();
    private City[] result;
    private int matrixDistances[][];
    private String nomeFile;
    private Supporto matriceCandidateList[][];

    public int[][] getMatrixDistances() {
        return matrixDistances;
    }

    public int getDimensione() {
        return dimensione;
    }

    public int getBest() {
        return best;
    }

    public City[] getResult() {
        return result;
    }

    public Lettore(String n) {
        nomeFile = n;
        leggi();
    }


    public void leggi() {
        BufferedReader br = null;
        try {
            String currentLine;
            boolean readingNodes = false;



            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(nomeFile).getFile());
            br = new BufferedReader(new FileReader(file));
            int cont = 0;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.contains("EOF")) {
                    readingNodes = false;
                    if (result.length != dimensione) {
                        System.out.println("Error loading cities");
                        System.exit(-1);
                    }
                }
                if (readingNodes) {
                    String[] tokens = currentLine.split("\\s+");
                    int counter = 0;
                    if (tokens[0].equals(""))
                        counter++;
                    double x = Double.parseDouble(tokens[1 + counter]);
                    double y = Double.parseDouble(tokens[2 + counter]);
                    result[cont] = (new City(cont, x, y));
                    cont++;
                }
                if (currentLine.contains("DIMENSION")) {
                    String[] tokens = currentLine.split("\\s*:\\s*");
                    dimensione = Integer.parseInt(tokens[1].trim());
                 //      dimCandidate = (int)(Math.sqrt(dimensione)+0.5)*2;
                //   dimCandidate = 13;

                    dimCandidate = (int)((double)dimensione*(0.15));

          //          System.out.print(dimCandidate+"  ");
                    result = new City[dimensione];
                    matrixDistances = new int[dimensione][dimensione];
                    matriceCandidateList = new Supporto[dimensione][dimensione-1];
                }
                if (currentLine.contains("BEST_KNOWN")) {
                    String[] tokens = currentLine.split("\\s*:\\s*");
                    best = Integer.parseInt(tokens[1].trim());
                }
                if (currentLine.contains("NODE_COORD_SECTION")) {
                    readingNodes = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                calcolaMatrice();
                calcolaCandidateList();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void calcolaMatrice() {
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                matrixDistances[i][j] = result[i].getDistance(result[j]);
            }
        }
    }

    public Supporto[][] getMatriceCandidateList() {
        return matriceCandidateList;
    }

    private void calcolaCandidateList() {
        for (int i = 0; i < dimensione; i++) {
            RigaCandidate r = new RigaCandidate(matrixDistances[i]);
         //   metti(i,r.getOrdered());
            matriceCandidateList[i] = r.getOrdered().clone();
        }
    }

    private void metti(int il, Supporto[] ordered) {
        for(int i=0; i<ordered.length; i++){
            Supporto r = ordered[i];
            matriceCandidateList[il][i] = r;
        }
    }

}
