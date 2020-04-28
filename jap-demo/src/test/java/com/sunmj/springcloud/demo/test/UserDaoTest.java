package com.sunmj.springcloud.demo.test;

import com.sunmj.springcloud.demo.dao.UserDao;
import com.sunmj.springcloud.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void save(){
        User userEntity = new User();
        userEntity.setUsername("lisi");
        userEntity.setPassword("456456");
        userEntity.setCreateTime(new Date());
        userEntity.setPhone("15562233652");
        User save = userDao.save(userEntity);
        System.out.println(save);

        //修改
//        save.setUsername("admin");
//        userDao.save(save);
    }

    //查询
    @Test
    public void fingAll(){
        List<User> list = userDao.findAll();

        System.out.println(list);
    }

    //根据id查询
    @Test
    public void fingById(){
        User user = userDao.getById(2);

        System.out.println(user);
    }

    //根据名称查询
    @Test
    public void fingByusername(){
        List<User> list = userDao.findByUsername("li");
            System.err.println(list);

    }

    /**
     * 删除
     */
    @Test
    public void delete(){
        userDao.delById(1);
    }
}
