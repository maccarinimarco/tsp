public class GetD {
    public static  int getDistance(int[][] matrix, int[] path){
        int d=0;
        for(int i=0; i< path.length-1 ; i++){
            d+= matrix[path[i]][path[i+1]];
        }
        return d;
    }
}
