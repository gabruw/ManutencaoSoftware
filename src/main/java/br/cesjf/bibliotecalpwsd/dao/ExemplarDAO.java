package br.cesjf.bibliotecalpwsd.dao;
import br.cesjf.bibliotecalpwsd.model.Exemplar;
import java.io.Serializable;

public class ExemplarDAO extends GenericDAO<Exemplar, Long> implements Serializable {

    public static ExemplarDAO subjectDAO;

    public ExemplarDAO() {
        super(Exemplar.class);
    }

    public static ExemplarDAO getInstance() {
        if (subjectDAO == null) {
            subjectDAO = new ExemplarDAO();
        }
        return subjectDAO;
    }

}