public class Algoritmo2OPT {
    private City[] listCity;
    private int[][] matrixDistance;
    private int pathNN[];
    private int path[];


    public Algoritmo2OPT(City[] listCity, int[][] matrixDistance, int[] pathNN) {
        this.listCity = listCity;
        this.matrixDistance = matrixDistance;
        this.pathNN = pathNN;
        this.path = this.pathNN;
        perf();
    }

    public int[] getPath() {
        return path;
    }

    private void perf() {
        int change, mini = 0, minj = 0, minChange;
        do {
            minChange = Integer.MAX_VALUE;
            for (int i = 0; i < path.length - 1; i++) {
                int porcoCLIST[] = listCity[path[i]].getCandidateList();
                int lunghezza=porcoCLIST.length;
                for (int indiceCandidate = 0; indiceCandidate < lunghezza; indiceCandidate++) {
                    int j= Partenza.listPos[porcoCLIST[indiceCandidate]] ;
                        change = getChange(i, j);
                        if (minChange > change) {
                            minChange = change;
                            mini = i;
                            minj = j;
                        }
                }
            }
            if (minChange < 0) {
                if (mini > minj) {
                    path = swapCity2(mini, minj);
                }else {
                    path = swapCity(mini, minj);
                }
            }
        }
        while (minChange < 0);
    }

    private int getChange(int i, int j) {
        return matrixDistance[path[i]][path[j]] + matrixDistance[path[i + 1]][path[j + 1]] - matrixDistance[path[i]][path[i + 1]] - matrixDistance[path[j]][path[j + 1]];
    }

    public int[] swapCity(int i, int j) {
        int[] appoggio = new int[path.length];
        for (int v = 0; v <= i; v++) {
            appoggio[v] = path[v];
            Partenza.listPos[path[v]]=  v;
        }
        int conta = 0;
        for (int v = i + 1; v <= j; v++) {
            appoggio[v] = path[j - conta];
            Partenza.listPos[path[(j - conta)]] = v;
            conta++;
        }
        for (int v = j + 1; v < path.length - 1; v++) {
            appoggio[v] = path[v];
            Partenza.listPos[path[v]] = v;
        }
        appoggio[appoggio.length - 1] = appoggio[0];
        return appoggio;
    }

    public int[] swapCity2(int i, int j) {
        int[] appoggio = new int[path.length];
        int conta = 0;
        for (int v = j; v >= 0; v--) {
            appoggio[conta] = path[v];
            Partenza.listPos[path[v]]= conta;

            conta++;
        }
        for (int v = path.length - 2; v >= (i + 1); v--) {
            appoggio[conta] = path[v];
            Partenza.listPos[path[v]]= conta;
            conta++;
        }
        for (int v = (j + 1); v <= i; v++) {
            appoggio[conta] = path[v];
            Partenza.listPos[path[v]]= conta;
            conta++;
        }
        appoggio[conta] = appoggio[0];
        return appoggio;
    }
}
