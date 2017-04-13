package cafeManagement.dao;

import java.util.List;

/**
 * The basic GenericDao interface with CRUD and common finding methods.
 * 
 * @author a_mgr
 */
public interface GenericDAO<E,K> {
    public void add(E entity) ;
    public void saveOrUpdate(E entity) ;
    public void remove(E entity);
    public E find(K key);
    public List<E> getAll() ;
}