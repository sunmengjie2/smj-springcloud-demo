package com.sunmj.springcloud.demo.dao;


import com.sunmj.springcloud.demo.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

//    Iterable<User> findAll(Sort sort);
//
//    List<User> findAll(Example<User> example);

    //自定义方法，是以findBy开头
    List<User> findByUsername(String username);

    //模糊查询
    List<User> findByUsernameLike(String username);

    //模糊查询+排序
    List<User> findByUsernameLikeOrderByIdDesc(String username);

    //分页
    Page<User> findByUsername(String username, Pageable pageable);

    //
    //Page<User> findByUsername(String username, Sort sort);


    //自定义删除方法
    @Transactional
    int deleteByUsername(String username);

    @Transactional
    int deleteByUsernameAndPhone(String username,String phone);

    @Transactional
    int deleteByPhoneIsNull();



    //注解方法
    @Query("select  u from User u where username =?1")
    List<User> getByUsername(String username);


    @Query("select u from  User u where u.username=?1 and u.password=?2")
    List<User> qureyByUsernameAndPassword(String username,String password);

    //原生sql
    @Query(value = "select * from t_user where username=?1 and password=?2",nativeQuery = true )
    List<User> selectByUsernameAndPassword(String username,String password);


    //通过query注解 修改表字段
    @Modifying
    @Transactional
    @Query("update User u set u.password=:pwd where id=:id")
    int updatePasswordById(@Param("pwd") String pwd,@Param("id") Integer id);



}
