import java.util.Objects;

public class Main {

    public static String[] diccionari = {"id", "rol", "esta_viu", "enamorat", "esta_protegit"};

    /*

       Llegenda:

            ID's -->
                Tenim 8 id's desde 0 fins al 7, que son els jugadors que hi ha.

            ROL -->
                1 --> Aledeano (2)
                2 --> Lobo (2)
                3 --> Cupído (1)
                4 --> Vidente (1)
                5 --> Protector (1)
                6 --> Cazador (1)

            ESTA_VIU -->
                El número 1 vol dir que el jugador està viu, i el 0 vol dir que està mort.

            ENAMORAT -->
                El número 1 vol dir que el jugador està enamorat, i el 0 vol dir que no hi esta.

            PROTEGIT -->
                El número 1 vol dir que el jugador està protegit, i el 0 vol dir que no hi esta.

     */
    public static int[][] jugadors = {
            { 0 , 1 , 1 , 0 , 0 },
            { 1 , 1 , 1 , 0 , 0 },
            { 2 , 2 , 1 , 0 , 0 },
            { 3 , 2 , 1 , 0 , 0 },
            { 4 , 3 , 1 , 0 , 0 },
            { 5 , 4 , 1 , 0 , 0 },
            { 6 , 5 , 1 , 0 , 0 },
            { 7 , 6 , 1 , 0 , 0 },
    };
    public static int[] jugadorExpulsat = new int[1];
    public static int buscarId(String buscar){
        boolean trobat = false;
        int i = -1;
        do{
            i++;
            if(Objects.equals(buscar, diccionari[i])){
                trobat = true;
            }
        } while (!trobat);

        return i;
    }
    public static void main(String[] args) {

    }
}