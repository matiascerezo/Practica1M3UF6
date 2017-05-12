package controlador;

import java.util.ArrayList;
import java.util.List;
import model.Usuari;

/**
 *
 * @author Matias
 */
public class GestioUsuaris {

    public void afegirUsuari(Usuari u) {    

    }

    public void afegirLlistaUsuaris(List<Usuari> llistaUsuaris) {
    }

    public boolean esborrarUsuari(Usuari u) {
        return true;
    }

    public boolean modificarUsuari(Usuari u) {
        return true;
    }

    public Usuari cercarPerNif(Usuari u) {
        Usuari uTrobat = new Usuari();
        return uTrobat;
    }

    public List<Usuari> cercarTotsUsuaris() {
        ArrayList<Usuari> llistaUsuaris = new ArrayList<>();
        return llistaUsuaris;
    }
}
