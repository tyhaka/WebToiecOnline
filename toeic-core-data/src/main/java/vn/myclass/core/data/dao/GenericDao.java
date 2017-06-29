package vn.myclass.core.data.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by trunght on 29/06/2017.
 */
public interface GenericDao <ID extends Serializable, T> {
    List<T> findAll();
    T update(T entity);
    void save (T entity);
    T findbyID (ID var1);
    Object[] findbyProperty(String property, Object value,String sortExpresstion,  String sortDirection);
    Integer delete(List<ID> ids);
}
