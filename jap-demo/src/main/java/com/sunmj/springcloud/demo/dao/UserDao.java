package com.sunmj.springcloud.demo.dao;

import com.sunmj.springcloud.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * 持久层注解
 * Repository
 */
@Repository
public class UserDao {

    @Autowired
    EntityManager entityManager;

    /**
     * 添加，修改
     * @param userEntity
     * @return
     */
    @Transactional
    public User save(User userEntity){
        User user = entityManager.merge(userEntity);
        return user;
    }

    /**
     * 查询
     * @param id
     * @return
     */
    public User getById(Integer id){
        User userEntity = entityManager.find(User.class, id);

        return userEntity;
    }

    /**
     * 根据Id删除
     * 删除前先查询
     * @param id
     */
    @Transactional
    public void delById(Integer id){
        User user = getById(id);
        entityManager.remove(user);
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<User> findAll(){
        Query query = entityManager.createQuery("from UserEntity u");
        return query.getResultList();
    }

    /**
     * 根据username查询
     * @param username
     * @return
     */
    public List<User> findByUsername(String username){

        Query query = entityManager.createQuery("from UserEntity u where u.username like :username");

        String likeStr ="%".concat(username).concat("%");
        query.setParameter("username",likeStr);
        return query.getResultList();
    }
}
