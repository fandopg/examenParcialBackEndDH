package dao.impl;

import db.H2Connection;
import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoH2 implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoH2.class);

    @Override
    public Odontologo save(Odontologo odontologo) {
        Connection connection = null;
        String insert = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";
        Odontologo odontologo1 = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.execute();

            ResultSet key = ps.getGeneratedKeys();
            odontologo1 = new Odontologo(odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
            while (key.next()){
                odontologo1.setId(key.getInt("id"));
            }

            connection.commit();
            LOGGER.info("Se guardo el odontologo: " + odontologo1);


        } catch (Exception exception){
            LOGGER.error(exception.getMessage());
            if(connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Ocurrio un error!");
                    LOGGER.error(exception.getMessage());
                    //exception.printStackTrace();
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                    exception.printStackTrace();
                }
            }

        } finally {
            try{
                connection.close();
            } catch (Exception exception){
                LOGGER.error("Error al cerrar la conexion. "+ exception.getMessage());
            }
        }

        return odontologo1;
    }

    @Override
    public List<Odontologo> listAll() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        String select = "SELECT * FROM ODONTOLOGOS";

        try{
            connection = H2Connection.getConnection();

            PreparedStatement ps = connection.prepareStatement(select);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                odontologos.add(crearObjetoOdontologo(rs));
            }
            LOGGER.info("Lista de los odontologos: " + odontologos);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        }
        finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Error al intentar cerrar la base de datos: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologos;
    }

    private Odontologo crearObjetoOdontologo(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String matricula = rs.getString("matricula");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");

        return new Odontologo(id, matricula, nombre, apellido);
    }
}
