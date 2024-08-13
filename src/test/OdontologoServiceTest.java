package test;

import dao.impl.OdontologoH2;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OdontologoServiceTest {
    static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoH2());
    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./examenParcial;INIT=RUNSCRIPT FROM " +
                    "'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
    @Test
    @DisplayName("Testear que un odontologo fue guardado")
    void caso1(){
        //Dado
        Odontologo odontologo = new Odontologo("Matricula1","Luis", "Garces");
        //cuando
        Odontologo odontologoDesdeDb = odontologoService.saveOdontologo(odontologo);
        // entonces
        assertNotNull(odontologoDesdeDb.getId());
    }


    @Test
    @DisplayName("Listar todos los odontologos")
    void caso2(){
        //Dado
        List<Odontologo> odontologos;
        // cuando
        odontologos = odontologoService.listOdontologos();
        // entonces
        assertFalse(odontologos.isEmpty());
    }
}
