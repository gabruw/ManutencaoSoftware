package br.cesjf.bibliotecalpwsd.dao;
import br.cesjf.bibliotecalpwsd.model.Emprestimo;
import java.io.Serializable;

public class EmprestimoDAO extends GenericDAO<Emprestimo, Long> implements Serializable {

    public static EmprestimoDAO subjectDAO;

    public EmprestimoDAO() {
        super(Emprestimo.class);
    }

    public static EmprestimoDAO getInstance() {
        if (subjectDAO == null) {
            subjectDAO = new EmprestimoDAO();
        }
        return subjectDAO;
    }

}