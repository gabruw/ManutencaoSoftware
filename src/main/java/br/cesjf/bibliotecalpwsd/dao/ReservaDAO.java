package br.cesjf.bibliotecalpwsd.dao;
import br.cesjf.bibliotecalpwsd.model.Reserva;
import java.io.Serializable;

public class ReservaDAO extends GenericDAO<Reserva, Long> implements Serializable {

    public static ReservaDAO subjectDAO;

    public ReservaDAO() {
        super(Reserva.class);
    }

    public static ReservaDAO getInstance() {
        if (subjectDAO == null) {
            subjectDAO = new ReservaDAO();
        }
        return subjectDAO;
    }

}