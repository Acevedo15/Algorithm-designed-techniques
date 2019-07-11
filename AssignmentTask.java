public class Principal {

    public static int[] mejorConfComputadores(int[] tiempos, int T) {

        int[] configuracion = new int[tiempos.length];
        int[] mejorConfiguracion = new int[tiempos.length];

        Booleano exito1 = new Booleano();

        seleccionOptima(configuracion,exito1,0, tiempos, T, mejorConfiguracion);

        return mejorConfiguracion;
    }

    public static void copiarArray(int[] m1, int[] m2){

        for(int i = 0; i<m1.length;i++)
            m1[i] = m2[i];
    }
    public static boolean esMejor(int[] m1, int[] m2){

        int maximo1 = m1[0];
        int maximo2 = m2[0];

        for(int i=1; i< m1.length;i++){

            if(m1[i] > maximo1)
                maximo1 = m1[i];
            if(m2[i] > maximo2)
                maximo2 = m2[i];
        }

        return maximo1<maximo2;

    }
    public static void seleccionOptima(int[] configuracion, Booleano exito, int tarea, int[] tiempos, int T, int[] mejorSolucion) {

        if (esSolucion(tiempos, T, configuracion)) {
            if (!exito.getValor()) { //para la primera solucion encontrada
                exito.setValor(true);
                copiarArray(mejorSolucion, configuracion);
            }else if (exito.getValor()) { //las siguientes soluciones
                if (esMejor(configuracion, mejorSolucion)) {
                    copiarArray(mejorSolucion, configuracion);
                }
            }

        } else {

            int super_ordenador_candidato = 0;

            while (tarea >= 0 && tarea < tiempos.length && super_ordenador_candidato >= 0 && super_ordenador_candidato < tiempos.length) {

                if (aceptable(super_ordenador_candidato, configuracion, tarea, tiempos, T)) {
                    configuracion[tarea] = super_ordenador_candidato;
                    seleccionOptima(configuracion, exito, tarea + 1, tiempos, T, mejorSolucion);
                }

                configuracion[tarea]  = 0;
                super_ordenador_candidato++;

            }
        }
    }



    public static int[] confComputadores(int[] tiempos, int T) {

        int[] configuracion = new int[tiempos.length];

        Booleano exito = new Booleano();

        vueltaAtras(configuracion, exito, 0,tiempos, T);

        return configuracion;
    }


    public static void vueltaAtras(int[] configuracion, Booleano exito, int tarea, int[] tiempos, int T) {

        if (esSolucion(tiempos, T, configuracion))
            exito.setValor(true);

        else {

            int super_ordenador_candidato = 0;

            while (!exito.getValor() && tarea < tiempos.length && tarea >= 0 && super_ordenador_candidato < tiempos.length) {

                if (aceptable(super_ordenador_candidato, configuracion, tarea, tiempos, T)) {

                    configuracion[tarea] = super_ordenador_candidato;
                    vueltaAtras(configuracion, exito, tarea + 1, tiempos, T);

                }

                super_ordenador_candidato++;
            }
        }
    }

    public static boolean aceptable(int candidato, int[] configuracion, int tarea, int[] tiempos, int T) {

        int i;
        int sumatiempos = 0;
        boolean esta = false;
        int maximo1 = configuracion[0];


        if(tarea == 0 && candidato == 0 ) //controlamos la primera entrada a la funcion ya que es un caso que no se contempla en el bucle for de abajo
            esta = true;

        for(int j=0; j<tarea; j++){

            if(configuracion[j] == candidato)
                esta = true;
            if(configuracion[j] > maximo1)
                maximo1 = configuracion[j];
        }

        if(esta || candidato == maximo1 + 1 ){ //condicion necesaria para ser aceptable --> ser un numero consecutivo al maximo supercomputador  o un número igual a los números que ya hay
            for (i = 0; i < tarea; i++) {
                if (configuracion[i] == candidato)
                    sumatiempos += tiempos[i];
            }
            if (sumatiempos + tiempos[tarea] <= T)
                return true;
            else return false;

        }
        else return false;
    }


    public static boolean esSolucion ( int[] tiempos, int T, int[] configuracion){

            int sumatiempos = 0;
            int i = 0;
            boolean resul = true;

            while(i<tiempos.length && resul) {

                for (int j = 0; j < tiempos.length; j++) {
                    if (configuracion[j] == i)
                        sumatiempos += tiempos[j];
                }
                if (sumatiempos > T)
                    resul = false;// si encontramos un supercomputador cuya suma de tareas sea mayor a T --> no es solucion y ya no buscamos mas
                sumatiempos = 0;
                i++;
            }
            return resul;

        }

}
