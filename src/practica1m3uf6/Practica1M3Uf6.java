package practica1m3uf6;

import controlador.GestioUsuaris;
import persistencia.ConfigConnexio;

/**
 *
 * @author Matias
 */
public class Practica1M3Uf6 {

    public static void main(String[] args) {
        
        ConfigConnexio con = new ConfigConnexio();
        
        //Iniciem la connexió
        con.iniciarConnexio();
        
        GestioUsuaris gu = new GestioUsuaris(con.getConnection());
        
        do {
            //Mostrem menú d'opcions.  
            gu.executarAccioMenu();
        } while (gu.getSortir());

        //Tanquem conexió.
        con.closeConnection();
    }
}
