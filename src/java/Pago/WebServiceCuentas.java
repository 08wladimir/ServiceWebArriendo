/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pago;

import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Wladimir
 */
@WebService(serviceName = "WebServiceCuentas")
public class WebServiceCuentas {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "Pagos")
    public String Pagos(@WebParam(name = "nombre") String Nombre, @WebParam(name = "cuenta") String Cuenta, @WebParam(name = "valor") int Valor) throws ClassNotFoundException, SQLException {
        
        Conexion conexion = new Conexion();
        String Mensaje = conexion.Conexion(Nombre, Cuenta, Valor);
        
        return Mensaje;
    }
}
