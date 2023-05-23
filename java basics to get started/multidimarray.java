public class multidimarray {
    public static void main(String[] args ){
            int [][] flats = new int [2][3];
            for(int i=0;i<flats.length;i++)
            {
               for(int j=0;j <flats[i].length;j++)
               {
                flats[i][j] = 100*i +j;
                System.out.println(flats[i][j]);
               }
            }
    }
}
