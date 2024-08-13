package test;

import dao.impl.OdontologoMemoria;
import model.Odontologo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoMemoriaTest {
    OdontologoService odontologoService = new OdontologoService(new OdontologoMemoria());

    @Test
    @DisplayName("Testear que un odontologo fue guardado")
    void caso1(){
        //Dado
        Odontologo odontologo = new Odontologo("ABC123", "Fernando", "Padilla");
        //cuando
        Odontologo odontologoDesdeDb = odontologoService.saveOdontologo(odontologo);
        // entonces
        assertNotNull(odontologoDesdeDb.getId());
    }


    @Test
    @DisplayName("Testear qlista de odontologos")
    void caso2(){
        //Dado
        Odontologo odontologo = new Odontologo("ABC123", "Fernando", "Padilla");
        //cuando
        odontologoService.saveOdontologo(odontologo);
        List<Odontologo> odontoloLista = odontologoService.listOdontologos();
        // entonces
        assertNotNull(odontoloLista);
    }

}