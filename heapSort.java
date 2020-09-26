public class HeapSort {
	
	public static void ColocarElemento(int T[], int i, int longitud) {
		
		int dere, izq, mayor = i, aux; 
		boolean sigue = true;
		izq = 2 * i;
		dere = 2 * i+1;
		
		while ((i <= longitud) && sigue) {
			
			if (izq <= longitud  && T[izq-1] > T[mayor-1])
				mayor = izq;
			else
				mayor = i;
			if (dere <= longitud  && T[dere-1] > T[mayor-1])
				mayor = dere;
			
			if (mayor == i)sigue = false;
			else {
				aux = T[mayor-1];
				T[mayor-1] = T[i-1];
				T[i-1] = aux;
				i = mayor;
				
				
				izq = 2 * i;
				dere = 2 * i +1;
				}
			}
		}
	
	public static void Construir_Monticulo(int T[]) {
		int mitad, i, longitud = T.length;
		mitad= longitud / 2;
		
		for (i = mitad; i >= 1; i--)
			ColocarElemento(T, i, longitud);
	}

	public static void heap(int T[]) {
		int aux, i;
		int longitud = T.length;
		Construir_Monticulo(T);


		for(i= 0; i < longitud; i++) //printing heap
			System.out.println(T[i]);

		System.out.println("\n");
		
		for (i = longitud - 1 ; i >= 0; i--) {
			aux = T[i]; 
			T[i] = T[0]; 
			T[0] = aux;
			
			ColocarElemento(T, 1, i);
		}
	}
	
			
		
}
