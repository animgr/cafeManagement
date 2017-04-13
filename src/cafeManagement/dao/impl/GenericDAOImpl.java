package cafeManagement.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import cafeManagement.dao.GenericDAO;

/**
 * The generic implementation dao which use hibernate features for
 * implementations
 * 
 * By defining this class as abstract, we prevent Spring from creating instance
 * of this class If not defined as abstract, getClass().getGenericSuperClass()
 * would return Object. There would be exception because Object class does not
 * hava constructor with parameters.
 * 
 * @author a_mgr
 *
 * @param <E>
 *            the dao interface
 * @param <K>
 *            the key type for the dao
 */
@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDAOImpl<E, K extends Serializable> implements GenericDAO<E, K> {

	@PersistenceContext
	private EntityManager em;

	protected Class<? extends E> daoType;

	@SuppressWarnings("rawtypes")
	public GenericDAOImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public void add(E entity) {
		em.persist(entity);
	}

	@Override
	public void saveOrUpdate(E entity) {
		em.merge(entity);
	}

	@Override
	public void remove(E entity) {
		em.remove(entity);
	}

	@Override
	public E find(K key) {
		return em.find(daoType, key);
	}

	@Override
	public List<E> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<E> criteria = (CriteriaQuery<E>) builder.createQuery(daoType);
		criteria.select(criteria.from(daoType));
		return em.createQuery(criteria).getResultList();

	}
}
