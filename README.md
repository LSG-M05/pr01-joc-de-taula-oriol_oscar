<h1 align="center">:wolf: Joc de taula: El Llop :wolf:</h1>

<!-- Noms -->
<div align="center">
  <h3>Developers: Oriol Fontcuberta, Oscar Cruañas</h3>
</div>

<hr>

<!-- Temàtica del programa -->
<h2>Temàtica</h2>
<div>
  
  :star2: Aquest programa és una versió del tradicional joc del Llop per jugar en terminal, versió **singleplayer** contra la màquina!
  <br />
  
  Cada partida compta amb un total de **6 jugadors**! Entre els que de forma aleatòria, es reparteixen els rols de *vilatà, llop, caçador i cupido*.
  Repartició de rols:
  <br />

  <table>
  <tr>
    <th>:woman_farmer: 3 Vilatans :woman_farmer:</th>
    <th>:wolf: 1 Llop :wolf:</th>
    <th>:gun: 1 Caçador :gun:</th>
    <th>:cupid: 1 Cupido :cupid:</th>
  </tr>
  <tr>
    <td>Sense poders especials, hauran d'utilitzar l'enginy per derrotar al llop.</td>
    <td>Si t'ha tocat llop, cada nit hauràs de triar una víctima, però vigila no t'expulsin del poble!</td>
    <td>Si se't cruspeix un llop, o et fan fora els teus amics vilatans, encara et quedarà una bala. Utilitzala amb seny.</td>
    <td>Comptes amb la fletxa per enamorar dos personatges. Si mor un, l'altre el seguirà!</td>
  </tr>
</table>

<h3>:1st_place_medal: Com es guanya?</h3>

  **Objectiu del llop:** Eliminar tots els jugadors abans de ser descobert.
  <br />
  **Objectiu dels demés rols:** Col·laborar per expulsar al llop del poble.

</div>

<hr />

<!-- Funcionalitats -->

<h2>Funcionalitats</h2>
<div>

✔️ **Menú** on l'usuari podrà triar si jugar una partida, veure els rols que ofereix el joc amb la seva informació o sortir.<br>
✔️ **Enamorar**, en el cas de que el jugador sigui cupido podrà escollir dos jugadors (bots) a partir de la seva ID per enamorar-los.<br>
✔️ **Matar**, en cas de ser llop o caçador, el jugador escollirà un jugador a través de la seva ID per matar-lo.<br>
✔️ **Votar**, cada matí (en el joc), el jugador donarà el seu vot a través de la ID per a qui creu que ha de ser expulsat.<br>
✔️ **Autonomia** del programa per funcionar i automatizar les accions dels rols que restin després d'adjucar-li un a l'usuari.<br>

<h3>:card_index_dividers: Llistat de mètodes</h3>
<br>

    public static int buscarId(String buscar)
    public static String rolUsuari()
    public static String poderRols()
    public static void nit()
    public static void llop()
    public static void cupido()
    public static void junter()
    public static void junterRandom()
    public static void llopRandom()
    public static void cupidoRandom()
    public static void pauses()
    public static void resultats()

  
</div>

<hr>

<div align="center">
    <img src="https://media.giphy.com/media/5VfNPyYqdiMCHWwPmo/giphy.gif" alt="GIF" width="200" height="200" style="margin-left: 20px;">
</div>
