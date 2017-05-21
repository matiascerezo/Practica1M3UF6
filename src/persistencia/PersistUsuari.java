package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Usuari;

/**
 *
 * @author Matias
 */
public class PersistUsuari {

    Connection con;

    //Consultes.
    String insertInto = "INSERT INTO USUARIOS VALUES (?, ?, ?)";
    String deleteFrom = "DELETE FROM USUARIOS WHERE NIF = ?";
    String update = "UPDATE USUARIOS SET NOM = ?, COGNOM = ? WHERE NIF = ?";
    String selectAll = "SELECT * FROM USUARIOS";
    String selectNIF = "SELECT * FROM USUARIOS WHERE NIF = ?";

    public PersistUsuari(Connection con) {
        this.con = con;
    }

    /**
     * Metode que afegeix un usuari.
     *
     * @param u
     * @return
     */
    public boolean afegir(Usuari u) {
        return realitzarAccio(u, insertInto);
    }

    /**
     * Métode que elimina un usuari.
     *
     * @param nif
     * @return
     */
    public boolean esborrarUsuari(String nif) {
        boolean correcte = false;
        try {
            if (nif != null) {
                PreparedStatement ps = con.prepareStatement(deleteFrom);
                ps.setString(1, nif);
                ps.execute();
                System.out.println("Usuari \"" + nif + "\" eliminat.");
                correcte = true;
            }
        } catch (Exception e) {
            System.out.println("Error al afegir/modificar/eliminar l'usuari.");
            correcte = false;
        }
        return correcte;
    }

    /**
     * Métode que modifica un usuari.
     *
     * @param u
     * @return
     */
    public boolean modificarUsuari(Usuari u) {
        return realitzarAccio(u, update);
    }

    /**
     * Métode que inserta un array d'usuaris.
     *
     * @param listUsuaris
     * @return
     */
    public boolean afegirArray(List<Usuari> listUsuaris) {
        boolean correcte = false;
        try {
            if (!listUsuaris.isEmpty()) {
                PreparedStatement ps = con.prepareStatement(insertInto);
                for (Usuari u : listUsuaris) {
                    if (u.getNif() != null && u.getNom() != null && u.getCognom() != null) {
                        ps.setString(1, u.getNif());
                        ps.setString(2, u.getNom());
                        ps.setString(3, u.getCognom());
                        ps.execute();
                        System.out.println("Usuari amb nom: " + u.getCognom() + " afegit.");
                        correcte = true;
                    }
                }
            }
        } catch (Exception e) {
            correcte = false;
        }
        return correcte;
    }

    /**
     * Métode que cerca un usuari per el seu nif.
     *
     * @param nif
     * @return
     */
    public Usuari cercarPerNif(String nif) {
        Usuari uTrobat = null;
        try {
            if (nif != null) {
                uTrobat = new Usuari();
                PreparedStatement ps = con.prepareStatement(selectNIF);
                ps.setString(1, nif);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    uTrobat.setNif(rs.getString(1));
                    uTrobat.setNom(rs.getString(2));
                    uTrobat.setCognom(rs.getString(3));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return uTrobat;
    }

    /**
     * Métode que cerca tots el usuaris de la BD.
     *
     * @return
     */
    public List<Usuari> cercarTotsUsuaris() {
        ArrayList<Usuari> llistaUsuaris = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(selectAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                llistaUsuaris.add(new Usuari(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return llistaUsuaris;
    }

    /**
     * Métode que per parametre obté un usuari i una acció (Actualitzar o
     * insertar) i segons l'acció fa una cosa o altra.
     *
     * @param u
     * @param accio
     * @return
     */
    public boolean realitzarAccio(Usuari u, String accio) {
        boolean correcte = false;
        try {
            if (u.getNif() != null && u.getNom() != null && u.getCognom() != null) {
                if (accio.equals(update)) {
                    PreparedStatement ps = con.prepareStatement(update);
                    ps.setString(1, u.getNom());
                    ps.setString(2, u.getCognom());
                    ps.setString(3, u.getNif());
                    ps.execute();
                    System.out.println("Modificat correctament.");
                    correcte = true;
                } else {
                    PreparedStatement ps = con.prepareStatement(insertInto);
                    ps.setString(1, u.getNif());
                    ps.setString(2, u.getNom());
                    ps.setString(3, u.getCognom());
                    ps.execute();
                    System.out.println("Afegit correctament.");
                    correcte = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al afegir/modificar/eliminar l'usuari.");
            correcte = false;
        }
        return correcte;
    }
}
