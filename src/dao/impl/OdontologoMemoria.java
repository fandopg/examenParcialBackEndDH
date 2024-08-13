package dao.impl;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoMemoria  implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoMemoria.class);
    private List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public Odontologo save(Odontologo odontologo) {
        odontologo.setId(odontologos.size()+1);
        odontologos.add(odontologo);
        LOGGER.info("Odontologo guardado en memoria: " + odontologo);
        return odontologo;
    }
    @Override
    public List<Odontologo> listAll() {
        LOGGER.info("Listado de los odontologos: " + odontologos);
        return odontologos;
    }
}
