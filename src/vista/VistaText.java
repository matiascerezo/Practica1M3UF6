package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import model.Usuari;

public class VistaText {

    Scanner lector = new Scanner(System.in);

    /**
     * Método que muestra por pantalla un menú con opciones y devuelve la opción
     * introducida por el usuario.
     *
     */
    public void mostrarMenuPrincipal() {
        System.out.print("\n1. Afegir \n2. Afegir llista\n3. Esborrar\n4. Modificar"
                + "\n5. Cercar per NIF\n6. Cercar tots\n7. Sortir\nOpció: ");
    }

    /**
     * Métode que recull l'opció del menú.
     *
     * @return
     */
    public int recollirOpcio() {
        return lector.nextInt();
    }

    /**
     * Método que muestra JOptionPane y recoge los valores del usuario.
     *
     * @return
     */
    public List<String> agafarValorsUsuari() {
        List<String> valors = new ArrayList<>();
        String nif = JOptionPane.showInputDialog("Introdueix el Nif:");
        String nom = JOptionPane.showInputDialog("Introdueix el nom:");
        String cognom = JOptionPane.showInputDialog("Introdueix el cognom:");
        valors.add(nif);
        valors.add(nom);
        valors.add(cognom);
        return valors;
    }

    /**
     * Métode que crea un objecte Usuari amb els valors recollits.
     *
     * @param valorsUser
     * @return
     */
    public Usuari establirValorsUsuari(List<String> valorsUser) {
        Usuari u = new Usuari(valorsUser.get(0), valorsUser.get(1), valorsUser.get(2));
        return u;
    }

    /**
     * Torna un string que será el nif del usuari per cerques, eliminacions.
     *
     * @return
     */
    public String agafarNifUsuari() {
        return JOptionPane.showInputDialog("Introdueix el nif del usuari:");
    }

    /**
     * Mostra un missatge preguntant quants usuaris vol afegir y recull tots el
     * valors necessaris per als usuaris.
     *
     * @return
     */
    public List<Usuari> llistaUsuarisPerAfegir() {
        String numUsuaris = JOptionPane.showInputDialog("Introdueix el número d'usuaris que vols afegir:");
        List<Usuari> llistaUsuaris = new ArrayList<>();
        try {
            if (numUsuaris.matches("\\d+")) {
                for (int i = 0; i < Integer.parseInt(numUsuaris); i++) {
                    Usuari u = establirValorsUsuari(agafarValorsUsuari());
                    llistaUsuaris.add(u);
                }
            }
        } catch (PatternSyntaxException pse) {
            System.err.println("Solo se permiten números.");
        }
        return llistaUsuaris;
    }

    /**
     * Métode que obté per paràmetre un usuari y el mostra per pantalla.
     *
     * @param usuari
     */
    public void mostrarUsuari(Usuari usuari) {
        System.out.println("\n" + usuari.toString());
    }

    /**
     * Mostra per pantalla els usuaris de la llista que obté per paràmetre.
     *
     * @param llistaUsuaris
     */
    public void mostrarLlistaUsuaris(List<Usuari> llistaUsuaris) {
        for (Usuari usuari : llistaUsuaris) {
            System.out.println("\n" + usuari.toString());
        }
    }

}
