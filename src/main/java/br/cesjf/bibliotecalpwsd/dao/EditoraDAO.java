package br.cesjf.bibliotecalpwsd.dao;
import br.cesjf.bibliotecalpwsd.model.Editora;
import java.io.Serializable;

public class EditoraDAO extends GenericDAO<Editora, Long> implements Serializable {

    public static EditoraDAO subjectDAO;

    public EditoraDAO() {
        super(Editora.class);
    }

    public static EditoraDAO getInstance() {
        if (subjectDAO == null) {
            subjectDAO = new EditoraDAO();
        }
        return subjectDAO;
    }

}