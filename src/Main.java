import java.util.Objects;
import java.util.Random;

public class Main {

    static Random random = new Random();

    public static String[] diccionari = {"id", "rol", "esta_viu", "enamorat"};

    /*

       Llegenda:

            ID's -->
                Tenim 5 id's desde 0 fins al 4, que son els jugadors que hi ha.

            ROL -->
                1 --> Aledeano (2)
                2 --> Lobo (1)
                3 --> Cupído (1)
                4 --> Cazador (1)

            ESTA_VIU -->
                El número 1 vol dir que el jugador està viu, i el 0 vol dir que està mort.

            ENAMORAT -->
                El número 1 vol dir que el jugador està enamorat, i el 0 vol dir que no hi esta.

     */
    public static int[][] jugadors = {
            { 0 , 1 , 1 , 0 },
            { 1 , 1 , 1 , 0 },
            { 2 , 2 , 1 , 0 },
            { 3 , 3 , 1 , 0 },
            { 4 , 4 , 1 , 0 },
    };
    public static int[] jugadorExpulsat = new int[1];
    public static int idUsuari = random.nextInt(jugadors.length);

    public static String rolUsuari(int idUsuari){
        return switch (idUsuari) {
            case 0, 1 -> "Aledano";
            case 2 -> "Lobo";
            case 3 -> "Cupido";
            case 4 -> "Cazador";
            default -> "";
        };
    }

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