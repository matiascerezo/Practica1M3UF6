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
     * @return
     */
    public int mostrarMenuPrincipal() {
        int opcio;
        boolean correcte;
        do {
            System.out.print("\n1. Afegir \n2. Afegir llista\n3. Esborrar\n4. Modificar"
                    + "\n5. Cercar per NIF\n6. Cercar tots\n7. Sortir\nOpció: ");
            opcio = lector.nextInt();
            if (opcio > 7 || opcio < 1) {
                correcte = false;
                System.err.println("\nOpció no vàlida.");
            } else {
                correcte = true;
            }
        } while (!correcte);
        return opcio;
    }

    /**
     * Métode que recull el nif per realitzar cerques, modificacions i
     * eliminacions.
     *
     * @return
     */
    public String recollirNif() {
        System.out.println("Introdueix el Nif que vols:");
        return lector.next();
    }

    /**
     * Método que muestra JOptionPane y recoge los valores del usuario.
     *
     * @return
     */
    public List<String> agafarValorsUsuari() {
        List<String> valors = new ArrayList<>(3);
        valors.add(JOptionPane.showInputDialog("Introdueix el Nif:"));
        valors.add(JOptionPane.showInputDialog("Introdueix el nom:"));
        valors.add(JOptionPane.showInputDialog("Introdueix el cognom:"));
        return valors;
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
        List<String> valorsUsuari;
        try {
            if (numUsuaris.matches("^[2-100]")) {
                for (int i = 0; i < Integer.parseInt(numUsuaris); i++) {
                    valorsUsuari = agafarValorsUsuari();
                    Usuari usuari = new Usuari(valorsUsuari.get(0),
                            valorsUsuari.get(1), valorsUsuari.get(2));
                    llistaUsuaris.add(usuari);
                }
            }
        } catch (PatternSyntaxException pse) {
            System.err.println("Solo se permiten números entre 2-100.");
        }
        return llistaUsuaris;
    }

    /**
     * Métode que obté per paràmetre un usuari y el mostra per pantalla.
     *
     * @param usuari
     */
    public void mostrarUsuari(Usuari usuari) {
        System.out.println(usuari.toString());
    }

    /**
     * Mostra per pantalla els usuaris de la llista que obté per paràmetre.
     *
     * @param llistaUsuaris
     */
    public void mostrarLlistaUsuaris(List<Usuari> llistaUsuaris) {
        for (Usuari usuari : llistaUsuaris) {
            System.out.println(usuari.toString());
        }
    }

}
