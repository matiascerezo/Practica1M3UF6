package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matias
 */
public class ConfigConnexio {

    private Connection con;

    /**
     * Métode que inicia la connexió normal.
     */
    public void iniciarConnexio() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@ieslaferreria.xtec.cat:8081:INSLAFERRERI", "MATIAS", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Métode que inicia la connexio mitjantçant un properties d'on agafa el
     * valors que necesita amb les seves keys.
     */
    public void iniciarConnexioProperties() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("prop.properties"));
            Class.forName(prop.getProperty("className"));
            DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usuari"), prop.getProperty("contrasenya"));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException ex) {
            Logger.getLogger(ConfigConnexio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Métode que torna la connexió.
     * @return connexió
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * Métode que tanca la connexió.
     */
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConfigConnexio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
