public class MaxSubRectangle {
    

    public static int maxSubArrayMedio(int[] C, int i0, int k, int iN) {

        int maximo = Integer.MIN_VALUE;
        int suma = 0;

        for (int i = k; i >= i0; i--) {
            suma += C[i];

            if (suma > maximo) {
                maximo = suma;

            }
        }
        suma = maximo;
        for (int j = k + 1; j <= iN; j++) {

            suma += C[j];

            if (suma > maximo) {
                maximo = suma;
            }
        }
        return maximo;
    }

    public static int maxSubArray(int[] B, int i0, int iN) {

        if (i0 == iN)
            return B[i0];
        else {

            int k = (i0 + iN) / 2;
            int m1 = maxSubArray(B, i0, k);
            int m2 = maxSubArray(B, k + 1, iN);
            int m3 = maxSubArrayMedio(B, i0, k, iN);
            return Math.max(m1, Math.max(m2, m3));
        }


    }
    public static int maxSuma2(int[][] m1, int[][] m2) { //complejidad O(N^3 *Log N)

        int[][] beneficio = new int[m1.length][m1[0].length];
        int[] ArrayAux;

        int sumaActual;
        int maxSuma = Integer.MIN_VALUE;



        for(int i=0 ; i<m1.length; i++){
            for(int j=0; j <m1[0].length; j++){
                beneficio[i][j] = m1[i][j] - m2[i][j];
            }
        }

        for(int i = 0; i < m1[0].length; i++){

            ArrayAux = new int[m1.length];

            for(int j  = i ; j < m1[0].length; j++){

                for(int k = 0; k < m1.length; k++){
                    ArrayAux[k] += beneficio[k][j];
                }

                sumaActual = maxSubArray(ArrayAux, 0, ArrayAux.length-1);

                if(sumaActual > maxSuma)
                    maxSuma = sumaActual;
            }

        }

        return maxSuma;
    }

}
