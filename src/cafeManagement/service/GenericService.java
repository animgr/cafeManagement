package cafeManagement.service;

import java.util.List;

/**
 * A generic service interface which provides general services to daos
 * 
 * @see http://www.codesenior.com/en/tutorial/Spring-Generic-DAO-and-Generic-Service-Implementation
 * 
 * @author a_mgr
 *
 * @param <E>
 * @param <K>
 */
public interface GenericService<E, K> {
    public void saveOrUpdate(E entity);
    public List<E> getAll();
    public E get(K id);
    public void add(E entity);
    public void remove(E entity);
}
