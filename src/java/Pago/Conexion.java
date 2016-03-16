/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pago;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wladimir
 */
public class Conexion {

    public String Conexion(String Nombre, String Cuenta, int Valor) throws ClassNotFoundException, SQLException {

        String resp = "";
        try {
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=dbPago;user=Wlacho;password=wladimir";
            con = DriverManager.getConnection(connectionURL);

            Statement st = con.createStatement();
            String Consulta = "select * from tblCuentas where Nombre like '" + Nombre + "' and Cuenta like '" + Cuenta + "'";
            ResultSet resulta = st.executeQuery(Consulta);
            if (resulta.next()) {
                int val = resulta.getInt("Valor");
                String cue = resulta.getString("Cuenta");
                if (Valor <= val) {
                    int res = val - Valor;
                    String modificar = "UPDATE tblCuentas SET Valor = " + res + " WHERE Cuenta = '" + cue+"'";
                    st.executeUpdate(modificar);
                    resp = "Transaccion exitosa";
                }else{
                    resp = "Fondos Insuficientes";
                }
            }else{
                resp = "Cuenta no encontrada";
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }

}
