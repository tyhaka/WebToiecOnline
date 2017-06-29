package vn.myclass.core.data.daoimpl;

import org.hibernate.*;
import vn.myclass.core.common.constant.CoreConstant;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trunght on 29/06/2017.
 */
public class AbtractDao <ID extends Serializable,T> implements GenericDao<ID, T> {
    private Class<T> persistenceClass;

    public AbtractDao() {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getpPersistenceClassName(){
        return persistenceClass.getSimpleName();
    }


    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try{

            //HQL
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getpPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        T result;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        }catch(HibernateException e){
            transaction.rollback();
            throw  e;
        }finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        }catch(HibernateException e){
            transaction.rollback();
            throw  e;
        }finally {
            session.close();
        }
    }

    public T findbyID(ID id) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result= (T) session.get(persistenceClass,id);
            if(result == null){
               throw new ObjectNotFoundException("Not Found " + id, null);
            }
        }catch(HibernateException e){
            transaction.rollback();
            throw  e;
        }finally {
            session.close();
        }
        return result;
    }

    public Object[] findbyProperty(String property, Object value, String sortExpresstion, String sortDirection) {
        List<T> list = new ArrayList<T>();
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object totalItem = 0;
        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(this.getpPersistenceClassName());
            if(property != null && value != null) {
                sql1.append(" where").append(property).append("= :value");
            }
            if(sortExpresstion != null & sortDirection != null){
                sql1.append(" order by").append(sortExpresstion);
                sql1.append(" " + (sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("value", value);
            list = query1.list();
            //
            StringBuilder sql2 = new StringBuilder("Select count(*) from ");
            sql2.append(getpPersistenceClassName());
            sql2.append(" where ").append(property).append("= :value");
            Query query2 = session.createQuery(sql2.toString());
            query2.setParameter("value",value);
            totalItem = query2.list().get(0);
            transaction.commit();
        }catch(HibernateException e){
            transaction.rollback();
            throw  e;
        }finally {
            session.close();
        }
        return new Object[]{totalItem,list};
    }

    public Integer delete(List<ID> ids) {
        Integer count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
        for(ID item: ids) {
            T t = (T) session.get(persistenceClass, item);
            session.delete(t);
            count++;
        }
        transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }
        return  count;
    }
}
