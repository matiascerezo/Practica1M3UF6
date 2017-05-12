package persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matias
 */
public class ConfigConnexio {

    private Connection con;
    
    
    //public boolean 

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConfigConnexio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}