package service;

import dao.IDao;
import model.Odontologo;

import java.util.List;

public class OdontologoService {
    private final IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }
    public Odontologo saveOdontologo(Odontologo odontologo) {
        return odontologoIDao.save(odontologo);
    }
    public List<Odontologo> listOdontologos(){
        return odontologoIDao.listAll();
    }
}
