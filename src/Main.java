// Oscar Cruañas i Oriol Fontcuberta

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
                5 --> Aledeano (2)

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
            { 5 , 1 , 1 , 0 }
    };

    /**
     * Aquest array jugadorExpulsat, conté els jugadors que ja no participan en
     * la partida, ordenats per ordre d'expulsió / mort.
     */
    public static int idJugadorExpulsat = -1;

    /**
     * Aqueste int el que fa es retornar l'ID del usuari.
     */
    public static int idUsuari = random.nextInt(jugadors.length);
    public static int idDerrerJugadorMort = -1;
    public static int contadorPartida = 1;
    public static boolean continuaPartida = true;
    public static int nits = 0;
    public static boolean enamorats = false;
    public static boolean junterExecutat = false;
    public static boolean fiPartida = false;



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
     * Aquest métode converteix els ID's dels usuaris en un String amb el nom del seu rol
     * @return retorna el nóm del rol de l'usuari.
     */
    public static String rolUsuari(int idRolADescubrir){
        return switch (idRolADescubrir) {
            case 0, 1, 5 -> "Aledano";
            case 2 -> "Lobo";
            case 3 -> "Cupido";
            case 4 -> "Junter";
            default -> "";
        };
    }

    /**
     * Aquest métode retorna els noms dels poders dels rols ( convertits abans en el métode rolUsuari()
     * en funció de cada rol.
     * @return retorna un String amb el nom del poder.
     */
    public static String poderRols(int idRolADescubrir){
        return switch (idRolADescubrir) {
            case 0, 1, 5 -> "";
            case 2 -> "Matar";
            case 3 -> "Enamorar";
            case 4 -> "Vengança";
            default -> "";
        };
    }

    /**
     * Aquest es el métode del Llop
     */

    public static void llop(){
        int idJugadorMort;
        boolean jugadorMort = false;
        boolean jugadorEncontrado = false;

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
                        jugadors[i][2] = 0; // Corregir esta línea para usar 'i' en lugar de 'idJugadorMort'
                        idDerrerJugadorMort = idJugadorMort;
                        jugadorMort = true;
                        jugadorEncontrado = true; // Indica que hemos encontrado y procesado al jugador
                        break;
                    }
                }

                if (!jugadorEncontrado) { // Verificar después del bucle for
                    System.out.println("No pots matar aquest jugador amb aquest ID");
                }
            } else {
                System.out.println("No pots matar aquest jugador amb aquest ID, ha de ser un número enter, intenteu de nou :)");
                scanner.nextLine();
            }
        } while (!jugadorMort);
        if (jugadors[idDerrerJugadorMort][3] == 1){
            for(int i=0; i<jugadors.length; i++){
                if(jugadors[i][3] == 1 && jugadors[i][0] != idDerrerJugadorMort){
                    jugadors[i][2] = 0;
                }
            }
        }
        resultats();
    }

    public static void llopRandom(){
        int randomMort = 0;
        boolean estaViu = false;
        do{
            randomMort = random.nextInt(jugadors.length);
            if ( jugadors[randomMort][2] == 1 && jugadors[randomMort][1] != 2 ) {
                estaViu = true;
            }
        } while (!estaViu);
        resultats();
        jugadors[randomMort][2] = 0;
        idDerrerJugadorMort = randomMort;
    }

    /**
     * Aquest es el mètode del Cupido
     */

    public static void cupido(){
        if (!enamorats) {
            int enamorat1 = 0;
            int enamorat2 = 0;

            System.out.println("\uD83D\uDC98 Hola Cupido, has de triar els dos jugadors enamorats! \uD83D\uDC98 \n");
            System.out.println("Hauràs de triar entre les IDs de tots els jugadors, si vol la teva, també.");

            do {
                System.out.println("Tria un número del 0 al 5 per seleccionar la ID del primer enamorat: ");

                if (scanner.hasNextInt()) {
                    enamorat1 = scanner.nextInt();
                }

                if (enamorat1 > 5 || enamorat1 < 0) {
                    System.out.println("Error, ID fora de rang.");
                }
            } while (enamorat1 > 5 || enamorat1 < 0);

            do {
                System.out.println("Tria un altre número (diferent) del 0 al 5 per la ID del segon enamorat: ");

                if (scanner.hasNextInt()) {
                    enamorat2 = scanner.nextInt();
                }

                if (enamorat2 == enamorat1) {
                    System.out.println("Un jugador no es pot enamorar d'ell mateix!");
                }

            } while (enamorat2 == enamorat1 || (enamorat2 > 5 || enamorat2 < 0));

            // Canviar el valor "enamorat" de 0 a 1 del array dels jugadors escollits
            jugadors[enamorat1][3] = 1;
            jugadors[enamorat2][3] = 1;
            enamorats = true;

        }

    }

    public static void cupidoRandom(){
        if (!enamorats){
            int randomCupido1 = 0;
            int randomCupido2 = 0;

            randomCupido1 = random.nextInt(jugadors.length);

            boolean estaEnamorat = false;
            do{
                randomCupido2 = random.nextInt(jugadors.length);
                if ( jugadors[randomCupido1][0] != jugadors[randomCupido2][0]) {
                    estaEnamorat = true;
                    enamorats = true;

                }
            } while (!estaEnamorat);


            jugadors[randomCupido1][3] = 1;
            jugadors[randomCupido2][3] = 1;

        }
    }

    public static void junter() {
        if(!junterExecutat) {

            int idMartir = -1;
            boolean martir = false;

            System.out.println("\uD83D\uDD2B T'han matat, tria la ID d'un jugador viu per efectuar la teva venjança. \uD83D\uDD2B\n");
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

                    } else if (idMartir < 0 || idMartir > 5) {
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
        }
    }

    public static void junterRandom() {

        if(!junterExecutat) {

            int randomMartir = 0;
            boolean estaViu = false;

            do {
                randomMartir = random.nextInt(jugadors.length);
                if (jugadors[randomMartir][2] == 1 && randomMartir != 4) {
                    estaViu = true;
                    System.out.println("Com m'he mort i era el Junter decideixo matar al jugador amb l'ID " + randomMartir);
                }
            } while (!estaViu);

            jugadors[randomMartir][2] = 0;
        }

    }

    public static void primerDia() {
        pauses();
        System.out.println("El teu rol és " + rolUsuari(idUsuari) + " i el teu ID és el " + idUsuari);
        System.out.println("\uD83C\uDF04 Espero que hagis passat un bon primer dia a la Vila de Fuenteovejuna. \uD83C\uDF04\n");
        System.out.println("Ara ja és hora d'anar a dormir, però vigila, les nits són una mica mogudes en aquest poble...");
        System.out.println("        \uD83D\uDC80\uD83D\uDC80\uD83D\uDC80");
        System.out.println();
        pauses();

    }

    public static void dia(){
        System.out.println("        \uD83C\uDF19\uD83C\uDF19\uD83C\uDF19\n");
        System.out.println("La nit ha acabat, i això ha sigut el que ha pasat.");
        System.out.println("Ha mort el jugador amb l'ID " + idDerrerJugadorMort + " i el seu rol era " + rolUsuari(jugadors[idDerrerJugadorMort][1]));
        if(jugadors[idDerrerJugadorMort][3] == 1){
            System.out.println("A part de aquesta mort, aquest jugador estaba enamorat d'un altre jugador, aixi que també hi ha una altre mort...");
            for(int i = 0 ; i < jugadors.length ; i++ ){
                if(jugadors[i][3] == 1 && idDerrerJugadorMort != i){
                    jugadors[i][2] = 0;
                    System.out.println("L'altre jugador mort és el jugador amb l'ID " + i + " i el seu rol era " + rolUsuari(jugadors[i][1]));
                    if(jugadors[idDerrerJugadorMort][1] == 4  ){
                        if(idDerrerJugadorMort == idUsuari) {
                            junter();
                        } else {
                            junterRandom();
                        }
                    }
                    resultats();
                }
            }
        }
        if(jugadors[idDerrerJugadorMort][1] == 4  ){
            if(idDerrerJugadorMort == idUsuari) {
                junter();
            } else {
                junterRandom();
            }
        }
        resultats();
        pauses();
        System.out.println("Ara mateix queden els jugadors amb aquests rols encara:");
        for( int i = 0 ;  i < jugadors.length ; i++ ){
            if ( jugadors[i][2] == 1 ){
                System.out.println(rolUsuari(jugadors[i][0]));
            }
        }
        pauses();
        System.out.println("Ara és el moment de fer les votacions, aixi que ens has de dir qui creus que es el possible llop i a qui vols expulsar de la partida.");
        System.out.println("Ara mateix queden aquests jugadors vius:");
        for( int i = 0 ;  i < jugadors.length ; i++ ){
            if ( jugadors[i][2]  == 1 && jugadors[i][0] != idUsuari ){
                System.out.println(jugadors[i][0]);
            }
        }
        System.out.println("Recorda! L'ID del teu jugador és " + idUsuari);
        boolean jugadorExpulsat = false;
        boolean jugadorTrobat = false;
        do {
            if(scanner.hasNextInt()){
                idJugadorExpulsat = scanner.nextInt();
                for (int i = 0; i < jugadors.length; i++) {
                    if(jugadors[i][0] == idJugadorExpulsat && jugadors[i][2] == 1 && idJugadorExpulsat != idUsuari){
                        System.out.println("Hau triat expulsar al jugador " + idJugadorExpulsat);
                        if(jugadors[idJugadorExpulsat][1] == 4  ){
                            if(idJugadorExpulsat == idUsuari) {
                                junter();
                            } else {
                                junterRandom();
                            }
                        }
                        resultats();
                        if(jugadors[idJugadorExpulsat][3] == 1){
                            for(int x=0; x<jugadors.length; x++){
                                if(jugadors[x][3] == 1 && x != idJugadorExpulsat){
                                    jugadors[x][2] = 0;
                                    System.out.println("Aquest jugador estaba enamorat d'un altre jugador...");
                                    System.out.println("Aquest jugador és el jugador amb l'ID " + x);
                                }
                            }
                        }
                        resultats();
                        jugadors[i][2] = 0; // Corregir esta línea para usar 'i' en lugar de 'idJugadorMort'
                        jugadorExpulsat = true;
                        jugadorTrobat = true; // Indica que hemos encontrado y procesado al jugador
                        break;
                    }
                }

                if (!jugadorTrobat) { // Verificar después del bucle for
                    System.out.println("No pots expulsar al jugador amb aquest ID");
                }
            } else {
                System.out.println("No pots expulsar aquest jugador amb aquest ID, ha de ser un número enter, intenteu de nou :)");
                scanner.nextLine();
            }
        } while (!jugadorExpulsat);
        resultats();
    }

    public static void nit(){

        int rolJugadorPrincipal = jugadors[idUsuari][1];
        int idJugadorPrincipal = idUsuari;
        int nitPartida = nits;
        boolean ha_enamorat = false;

        if ( nitPartida == 0 ){
            // Hi ha cupido
            switch (poderRols(idJugadorPrincipal)){
                case "Enamorar":
                    cupido();
                    llopRandom();
                    break;
                case "Matar":
                    cupidoRandom();
                    llop();
                    break;
                case "":
                case "Venganza":
                default:
                    llopRandom();
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
                    llopRandom();
                    break;
            }
        }

    }

    public static void pauses() {

        String enterKey = "enter";

        System.out.println("\n\uD83D\uDCDC Prem ENTER per continuar. \uD83D\uDCDC");
        enterKey = scanner.nextLine();

        if (enterKey == "") {
            System.out.println(" ");
        }


    }

    public static void resultats() {

        String guanyadors = "";
        int sumVius = 0;
        boolean fiPartida = false;

        for (int i = 0; i < jugadors.length; i++) {
            // Calcular jugadors NO llop vius / morts:
            if (jugadors[i][1] != 2 && jugadors[i][2] == 1) {
                sumVius++;
            }
        }

        for (int i = 0; i < jugadors.length; i++) {
            if (jugadors[i][1] == 2 && jugadors[i][2] == 0) {
                guanyadors = "VILATANS";
                fiPartida = true;

            } else if (jugadors[i][1] == 2 && sumVius <= 1) {
                guanyadors = "LLOP";
                fiPartida = true;

            }
        }

        if (fiPartida) {
            System.out.println("S'ha acabat la partida!");
            System.out.println("La victòria és per...\n");
            System.out.println("    \u2728\u2728\u2728 " + guanyadors + " \u2728\u2728\u2728 \n");
            System.exit(0);
        }
    }

    public static void partida(){
        while(!fiPartida){
            do {
                if (contadorPartida % 2 == 0 && contadorPartida == 2) {
                    nit();
                }
                else if (contadorPartida % 2 == 0) {
                    nit();
                }else if (contadorPartida % 2 != 0 && contadorPartida != 1) {
                    dia();
                } else if (contadorPartida == 1) {
                    primerDia();
                }
                contadorPartida++;

            }while (continuaPartida);
        }
    }

    public static void instruccions(){
        System.out.println("Objectiu del joc:\n");
        System.out.println("Si ets llop, hauràs de matar als demés jugadors durant la nit. Un cada nit. Si els altres jugadors et voten, has perdut.");
        System.out.println("Si no ets llop, hauràs d'expulsar al llop abans de que us mati a tots.\n");
        System.out.println("Rols:\n");
        System.out.println("Vilatà/Aldeano: No tens cap poder especial, hauràs de confiar en el teu instint per votar al llop.");
        System.out.println("Llop: Cada nit, escolliràs una víctima a través de la seva ID.");
        System.out.println("Cupido: Al principi de la partida, escolliràs a 2 jugadors (tu pots ser un d'ells) per enamorar-los. Si un es mor, l'altre també.");
        System.out.println("Caçador: Si et maten, podràs triar un jugador al que matar.\n");
    }

    public static void menu() {

        int opcioMenu = 0;

        System.out.println("\uD83D\uDC3A El Llop \uD83D\uDC3A\n");
        System.out.println("Escull una opció:\n[1] Jugar partida\n[2] Instruccions\n[3] Sortir");
        opcioMenu = scanner.nextInt();

        switch (opcioMenu) {
            case 1:
                partida();
                resultats();
                break;
            case 2:
                instruccions();
                menu();
                break;
            case 3:
                break;
            default:
                System.out.println("Opció no vàlida.");
                menu();
        }

    }
    public static void main(String[] args) {
        menu();
    }

}