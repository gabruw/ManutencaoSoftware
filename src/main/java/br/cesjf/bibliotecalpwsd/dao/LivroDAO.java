package br.cesjf.bibliotecalpwsd.dao;
import br.cesjf.bibliotecalpwsd.model.Livro;
import java.io.Serializable;
import java.util.List;

public class LivroDAO extends GenericDAO<Livro, Long> implements Serializable {

    public static LivroDAO subjectDAO;

    public LivroDAO() {
        super(Livro.class);
    }

    public static LivroDAO getInstance() {
        if (subjectDAO == null) {
            subjectDAO = new LivroDAO();
        }
        return subjectDAO;
    }

    public List<Livro> buscarNome(String titulo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}