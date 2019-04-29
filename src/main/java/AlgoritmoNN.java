public class AlgoritmoNN {
    private City[] listCity;
    private int[][] matrixDistance;
    private int[] path;
    private int listPos[];

    public AlgoritmoNN(City[] listCity, int[][] matrixDistance) {
        this.listCity = listCity;
        this.matrixDistance = matrixDistance;
        this.listPos = new int[listCity.length];
        doIt();
    }

    public int[] getListPos() {
        return listPos;
    }

    private void doIt() {
        int firstSelected =0; //GIU.staticRandom.nextInt(listCity.length);
        path = new int[listCity.length+1];
        int position = 0, contatorePath = 0, selected = firstSelected;
        Boolean[] isNotVisited = new Boolean[listCity.length];
      // Boolean[] isNotVisited = new Boolean[listCity.size()];
        populateVectorVisited(isNotVisited);
       // path.put(contatorePath,listCity.get(firstSelected));
        path[contatorePath] = listCity[firstSelected].getId();


        /** POSIZIONE **/
        listPos[firstSelected] = contatorePath;


        isNotVisited[firstSelected] = false;
        contatorePath++;
        int minimum = Integer.MAX_VALUE;
        while (contatorePath != listCity.length) {
            City c = listCity[selected];
            //City c = listCity.get(selected);
            if (verifica(c.getCandidateList(), isNotVisited)) {
                int d;
                for (int i = 0; i < c.getCandidateList().length; i++) {
                    int selezionata = ((c.getCandidateList())[i]);
                    d = matrixDistance[selected][selezionata];
                    if ((d < minimum) && (d != 0) && (isNotVisited[selezionata])) {
                        minimum = d;
                        position = selezionata;
                    }
                }
                isNotVisited[position] = false;
                selected = position;
                path[contatorePath] = listCity[selected].getId();

                /** POSIZIONE **/
                listPos[selected] = contatorePath;


               // path.put(contatorePath, listCity.get(selected));
                contatorePath++;
                minimum = Integer.MAX_VALUE;
            } else {
                int d;
                for (int i = 0; i < listCity.length; i++) {
                    d = matrixDistance[selected][i];
                    if ((d < minimum) && (d != 0) && (isNotVisited[i])) {
                        minimum = d;
                        position = i;
                    }
                }
                isNotVisited[position] = false;
                selected = position;
          //      path.put(contatorePath, listCity.get(selected));
                path[contatorePath] = listCity[selected].getId();

                /** POSIZIONE **/

                listPos[selected] = contatorePath;


                contatorePath++;
                minimum = Integer.MAX_VALUE;
            }
            path[contatorePath] = listCity[firstSelected].getId();
        }
    }

    public int[]  getPath() {
        return path;
    }

    private boolean verifica(int[] candidateList, Boolean[] isNotVisited) {
        for (int i = 0; i < candidateList.length; i++) {
            if (isNotVisited[candidateList[i]] == true)
                return true;
        }
        return false;
    }

    private void populateVectorVisited(Boolean[] isNotVisited) {
        for (int i = 0; i < isNotVisited.length; i++) {
            isNotVisited[i] = true;
        }
    }
}
