package cafeManagement.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cafeManagement.dao.GenericDAO;
import cafeManagement.service.GenericService;

/**
 * A generic service interface which provides general services to doas
 * This use the GenericDAO class to deal with all db operations 
 * 
 * 
 * @author a_mgr
 *
 * @param <E>
 * @param <K>
 */
@Service
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {

	private GenericDAO<E, K> genericDao;

	public GenericServiceImpl(GenericDAO<E, K> genericDao) {
		this.genericDao = genericDao;
	}

	public GenericServiceImpl() {
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdate(E entity) {
		genericDao.saveOrUpdate(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<E> getAll() {
		return genericDao.getAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public E get(K id) {
		return genericDao.find(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(E entity) {
		genericDao.add(entity);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(E entity) {
		genericDao.remove(entity);
	}	
}

