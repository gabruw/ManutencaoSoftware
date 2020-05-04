/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;
import br.cesjf.bibliotecalpwsd.util.PersistenceUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;

/**
 *
 * @author bibim
 */


public abstract class GenericDAO<T, I extends Serializable> {

    protected EntityManager entityManager;

    private Class<T> persistedClass;

    public static GenericDAO genericDAO;

    protected GenericDAO() {
    }

    public GenericDAO(Class<T> persistedClass) {
        this();
        this.persistedClass = persistedClass;
        entityManager = PersistenceUtil.getEntityManager();
    }

    public T persistir(@Valid T entity) {
        try {
            EntityTransaction t = entityManager.getTransaction();
            t.begin();
            entityManager.merge(entity);
            entityManager.flush();
            t.commit();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.INFO, persistedClass.getSimpleName() + " salvo(a) com sucesso!");
            return entity;
        } catch (Exception e) {
            if (e.getMessage().contains("ConstraintViolationException")) {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.WARNING, "Não foi possível salvar o(a) " + persistedClass.getSimpleName() + ", por não ser é único! " + e.getMessage());
            } else {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.WARNING, "Não foi possível salvar o(a) " + persistedClass.getSimpleName() + "! " + e.getMessage());
            }
            return null;
        }
    } 

    public void remover(I id) {
        try {
            T entity = buscar(id);
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            T mergedEntity = entityManager.merge(entity);
            entityManager.remove(mergedEntity);
            entityManager.flush();
            tx.commit();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.INFO, persistedClass.getSimpleName() + " removido(a) com sucesso!");
        } catch (Exception e) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.WARNING, "Não foi possível remover o(a) " + persistedClass.getSimpleName() + ", por estar sendo utilizado! " + e.getMessage());
        }
    }

    public List<T> buscarTodas() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(persistedClass);
            query.from(persistedClass);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.WARNING, "Não foram encontrados(as) " + persistedClass.getSimpleName() + "s! " + e.getMessage());
            return null;
        }
    }

    public T buscar(I id) {
        try {
            return entityManager.find(persistedClass, id);
        } catch (Exception e) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.WARNING, "Não foram encontrados(as) " + persistedClass.getSimpleName() + "s! " + e.getMessage());
            return null;
        }
    }
    
    

    public List<T> buscarNome(String namedQuery, List<List> parameters) {
        Query query = entityManager.createNamedQuery(namedQuery, persistedClass);
        for (List<String> a : parameters) {
            query.setParameter(a.get(0), a.get(1));
        }
        return query.getResultList();
    } 
}