import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static String[] diccionari = {"id", "rol", "esta_viu", "enamorat"};

    /*

       Llegenda:

            ID's -->
                Tenim 5 id's desde 0 fins al 4, que son els jugadors que hi ha.

            ROL -->
                1 --> Aledeano (2)
                2 --> Lobo (1)
                3 --> Cupído (1)
                4 --> Junter (1)

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

    /**
     * Aquest métode el que fa és retornar la posició del diccionari sobre el que volem buscar.
     * @param buscar Aquí posem el que volem buscar, ex: esta_viu seria = 2.
     * @return retorna un int amb la posició del element que volem buscar.
     */
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

    /**
     * Aquest array jugadorExpulsat, conté els jugadors que ja no participan en
     * la partida, ordenats per ordre d'expulsió / mort.
     */
    public static int[] jugadorExpulsat = new int[1];

    /**
     * Aqueste int el que fa es retornar l'ID del usuari.
     */
    public static int idUsuari = random.nextInt(jugadors.length);
    public static int idDerrerJugadorMort = -1;
    public static int contadorPartida = 0;
    public static boolean continuaPartida = true;

    /**
     * Aquest métode converteix els ID's dels usuaris en un String amb el nom del seu rol
     * @return retorna el nóm del rol de l'usuari.
     */
    public static String rolUsuari(int idRolADescubrir){
        return switch (idRolADescubrir) {
            case 0, 1 -> "Aledano";
            case 2 -> "Lobo";
            case 3 -> "Cupido";
            case 4 -> "Junter";
            default -> "";
        };
    }

    /**
     * Aquest int és un contador de les nits.
     */
    public static int nits = 0;

    /**
     * Aquest métode retorna els noms dels poders dels rols ( convertits abans en el métode rolUsuari()
     * en funció de cada rol.
     * @return retorna un String amb el nom del poder.
     */
    public static String poderRols(int idRolADescubrir){
        return switch (idRolADescubrir) {
            case 0, 1 -> "";
            case 2 -> "Matar";
            case 3 -> "Enamorar";
            case 4 -> "Vengança";
            default -> "";
        };
    }

    /**
     * @return Métode el Desenvolupament
     */

    public static void nit(){

        int rolJugadorPrincipal = jugadors[idUsuari][1];
        int idJugadorPrincipal = idUsuari;
        int nitPartida = nits;
        boolean ha_enamorat = false;

        if ( nitPartida == 0 ){
            // Hi ha cupido
            switch (poderRols(idJugadorPrincipal)){
                case "Matar":
                    llop();
                    break;
                case "Enamorar":
                    cupido();
                    ha_enamorat = true;
                    break;
                case "":
                case "Venganza":
                default:
                    break;
            }
        } else{
            switch (poderRols(idJugadorPrincipal)){
                case "Matar":
                    llop();
                    break;
                case "Enamorar":
                case "":
                case "Venganza":
                default:
                    break;
            }
        }

    }


    public static void dia(){
        System.out.println("\n\n\n\n\n\n\n\n\nLa nit ha acabat, i això ha sigut el que ha pasat.");
        System.out.println("Ha mort el jugador amb l'ID " + idDerrerJugadorMort + " i el seu rol era " + rolUsuari(jugadors[idDerrerJugadorMort][1]));
        if(jugadors[idDerrerJugadorMort][3] == 1){
            System.out.println("A part de aquesta mort, aquest jugador estaba enamorat d'un altre jugador, aixi que també hi ha una altre mort...");
            for(int i = 0 ; i < jugadors.length ; i++ ){
                if(jugadors[i][3] == 1 && idDerrerJugadorMort != i){
                    System.out.println("L'altre jugador mort és el jugador amb l'ID " + i + " i el seu rol era " + jugadors[i][1]);
                }
            }
        }
        //junter();
        System.out.println("Ara mateix queden els jugadors amb aquests rols encara:");
        for( int i = 0 ;  i < jugadors.length ; i++ ){
            if ( jugadors[i][2]  == 1 ){
                System.out.println(rolUsuari(jugadors[i][0]));
            }
        }
    }

    /**
     * Aquest es el métode del Llop
     */

    public static void llop(){
        int idJugadorMort;
        boolean jugadorMort = false;

        System.out.println("Ets el jugador " + idUsuari + "\nCom ets ell llop, has de triar a un dels jugadors per matar aquesta nit els jugadors vius són: ");
        for(int i=0; i<jugadors.length; i++){
            if(jugadors[i][2] == 1 && jugadors[i][0] != idUsuari){
                System.out.println(jugadors[i][0]);
            }
        }
        do {
            if(scanner.hasNextInt()){
                idJugadorMort = scanner.nextInt();
                for (int i = 0; i < jugadors.length; i++) {
                    if(jugadors[i][0] == idJugadorMort && jugadors[i][2] == 1 && idJugadorMort != idUsuari){
                        System.out.println("Has triat matar al jugador " + idJugadorMort);
                        jugadors[idJugadorMort][2] = 0;
                        idDerrerJugadorMort = idJugadorMort;
                        jugadorMort = true;
                        break;
                    }
                    else {
                        System.out.println("No pots matar aquest jugador amb aquest ID, intenteu de nou :)");
                        scanner.nextLine();
                    }
                }
            } else {
                System.out.println("Ha de ser un número enter, intenteu de nou :)");
                scanner.nextLine();
            }
        } while (!jugadorMort);
    }


    /**
     * Aquest es el mètode del Cupido
     */

    public static void cupido(){
        int enamorat1 = 0;
        int enamorat2 = 0;

        System.out.println("Hola Cupido, has de triar els dos jugadors enamorats!");
        System.out.println("Hauràs de triar entre les IDs de tots els jugadors, la teva també.");

        do {
            System.out.println("Tria un número del 0 al 4 per seleccionar la ID del primer enamorat: ");

            if (scanner.hasNextInt()) {
                enamorat1 = scanner.nextInt();
            }

            if (enamorat1 > 4 || enamorat1 < 0) {
                System.out.println("Error, ID fora de rang.");
            }
        } while (enamorat1 > 4 || enamorat1 < 0);

        do {
            System.out.println("Tria un altre número (diferent) del 0 al 4 per la ID del segon enamorat: ");

            if (scanner.hasNextInt()) {
                enamorat2 = scanner.nextInt();
            }

            if (enamorat2 == enamorat1) {
                System.out.println("Un jugador no es pot enamorar d'ell mateix!");
            }

        } while (enamorat2 == enamorat1 || (enamorat2 > 4 || enamorat2 < 0));

        // Canviar el valor "enamorat" de 0 a 1 del array dels jugadors escollits
        jugadors [enamorat1][3] = 1;
        jugadors [enamorat2][3] = 1;

        for (int i = 0; i < jugadors.length; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(jugadors[i][j] + " ");
            }
            System.out.println(" ");
        }

    }

    public static void junter() {
        int idMartir = -1;
        boolean martir = false;

        System.out.println("T'han matat, tria la ID d'un jugador viu per efectuar la teva venjança.");
        System.out.println("Aquestes són les IDs dels jugadors vius, escull sàviament: ");

        for (int i = 0; i < jugadors.length; i++) {
            if (jugadors[i][2] == 1 && jugadors[i][0] != idUsuari) {
                System.out.println(jugadors[i][0]);
            }
        }

        do {
            if (scanner.hasNextInt()) {
                idMartir = scanner.nextInt();
                scanner.nextLine(); // Netegem la línia del scanner per evitar un bucle infinit
                boolean errorInt = true; // Control d'error per ID invàlida

                if (idMartir == idUsuari) {
                    System.out.println("Error, no pots triar la teva pròpia ID. Torna-hi: ");

                } else if (idMartir < 0 || idMartir > 4) {
                    System.out.println("Error, ID fora de rang. Torna-hi: ");

                } else {
                    for (int i = 0; i < jugadors.length; i++) {

                        if (jugadors[i][0] == idMartir && jugadors[i][2] == 1) {
                            System.out.println("Has triat matar al jugador " + idMartir);
                            jugadors[i][2] = 0;
                            martir = true;
                            errorInt = false;
                            break;
                        }
                    }
                    if (errorInt) {
                        System.out.println("Error, no pots matar aquest jugador. Torna-hi: ");
                    }
                }
            } else {
                System.out.println("Error, la ID és un número enter. Torna-hi: ");
                scanner.nextLine(); // Netegem la línia del scanner per evitar un bucle infinit
            }

        } while (!martir);

        // Mostrar l'estat actual dels jugadors després de la mort
        System.out.println("Estat dels jugadors:");

        for (int i = 0; i < jugadors.length; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(jugadors[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void partida(){
        do {

            if (contadorPartida % 2 == 0) {
                nit();
            } else {
                dia();
            }
            contadorPartida++;

        }while (continuaPartida);
    }


    public static void main(String[] args) {
        partida();
    }

}