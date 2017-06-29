package vn.myclass.core.test;

import org.testng.annotations.Test;
import vn.myclass.core.dao.RoleDao;
import vn.myclass.core.daoimpl.RoleDaoImpl;
import vn.myclass.core.persistence.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trunght on 29/06/2017.
 */
public class RoleTest {
    @Test
    public  void CheckFindAll(){
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> list = roleDao.findAll();
    }

    @Test
    public void checkUpdateRole(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleid(2);
        roleEntity.setName("User_1");
        roleDao.update(roleEntity);
    }

    @Test
    public void checkSaveRole(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleid(3);
        roleEntity.setName("User_3");
        roleDao.save(roleEntity);
    }

    @Test
    public void checkFindById(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = roleDao.findbyID(1);
    }

    @Test
    public void checkFindByProperty(){
        RoleDao roleDao = new RoleDaoImpl();
        String property = "name";
        Object value = "Admin";
        String sortExpresstion = null;
        String sortDirection = null;
        Object[] object = roleDao.findbyProperty(property,value,sortExpresstion,sortDirection);
    }

    @Test
    public void checkDelete()
    {
       List<Integer> listId = new ArrayList<Integer>();
       listId.add(2);
       listId.add(3);
        RoleDao roleDao = new RoleDaoImpl();
        Integer coumt =  roleDao.delete(listId);
    }

}
