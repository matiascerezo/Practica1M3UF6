package controlador;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Usuari;
import persistencia.ConfigConnexio;
import persistencia.PersistUsuari;
import vista.VistaText;

/**
 *
 * @author Matias
 */
public class GestioUsuaris {

    PersistUsuari pu;
    VistaText vt = new VistaText();
    boolean sortir = true;

    public GestioUsuaris(Connection con) {
        this.pu = new PersistUsuari(con);
    }

    /**
     * Métode que mostra el menú inicial y executa una acció o una altre.
     *
     * @return
     */
    public boolean executarAccioMenu() {
        int opcio;
        Usuari u;
        do {
            vt.mostrarMenuPrincipal();
            opcio = vt.recollirOpcio();
            switch (opcio) {
                case 1:
                    u = vt.establirValorsUsuari(vt.agafarValorsUsuari());
                    afegirUsuari(u);
                    break;
                case 2:
                    afegirLlistaUsuaris(vt.llistaUsuarisPerAfegir());
                    break;
                case 3:
                    esborrarUsuari(vt.agafarNifUsuari());
                    break;
                case 4:
                    modificarUsuari(vt.establirValorsUsuari(vt.agafarValorsUsuari()));
                    break;
                case 5:
                    vt.mostrarUsuari(cercarPerNif(vt.agafarNifUsuari()));
                    break;
                case 6:
                    vt.mostrarLlistaUsuaris(cercarTotsUsuaris());
                    break;
                case 7:
                    setSortir(false);
                    break;
            }
        } while (opcio > 7 || opcio < 1);
        return sortir;
    }

    /**
     * Boolean que s'utilitza al main.
     *
     * @return
     */
    public boolean getSortir() {
        return sortir;
    }

    public void setSortir(boolean sortir) {
        this.sortir = sortir;
    }

    /**
     * Métode crida un altre métode que afegeix un usuari.
     *
     * @param u
     * @return
     */
    public boolean afegirUsuari(Usuari u) {
        return pu.afegir(u);
    }

    /**
     * Métode crida un altre métode que afegeix una llista d'usuaris.
     *
     * @param llistaUsuaris
     * @return
     */
    public boolean afegirLlistaUsuaris(List<Usuari> llistaUsuaris) {
        return pu.afegirArray(llistaUsuaris);
    }

    /**
     * Métode crida un altre métode que esborra un usuari per mitjà del seu nif.
     *
     * @param nif
     * @return
     */
    public boolean esborrarUsuari(String nif) {
        return pu.esborrarUsuari(nif);
    }

    /**
     * Métode crida un altre métode que modifica un usuari.
     *
     * @param u
     * @return
     */
    public boolean modificarUsuari(Usuari u) {
        return pu.modificarUsuari(u);
    }

    /**
     * Métode crida un altre métode que cerca un usuari per un nif.
     *
     * @param nif
     * @return
     */
    public Usuari cercarPerNif(String nif) {
        return pu.cercarPerNif(nif);
    }

    /**
     * Métode crida un altre métode que cerca tots el usuaris.
     *
     * @return
     */
    public List<Usuari> cercarTotsUsuaris() {
        return pu.cercarTotsUsuaris();
    }
}
