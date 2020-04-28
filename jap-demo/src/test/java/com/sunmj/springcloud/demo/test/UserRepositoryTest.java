package com.sunmj.springcloud.demo.test;

import com.sunmj.springcloud.demo.dao.UserRepository;
import com.sunmj.springcloud.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void save(){
        //新增
        User user = new User();
        user.setUsername("小五");
        user.setPassword("666666");
        user.setCreateTime(new Date());
        user.setPhone("15935560205");
        User save = userRepository.save(user);

        //批量保存
        List<User> arrayList = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("小六");
        user1.setPassword("123");
        user1.setPhone("15935560205");
        user1.setCreateTime(new Date());

        User user2 = new User();
        user2.setUsername("xiao3");
        user2.setPassword("123");
        user2.setPhone("15935560205");
        user2.setCreateTime(new Date());

        arrayList.add(user1);
        arrayList.add(user2);
        Iterable<User> users = userRepository.saveAll(arrayList);
        System.out.println(users);

    }

    @Test
    public void update(){
        //修改

        User user = userRepository.findById(3).get();
        user.setUsername("小四");
        userRepository.save(user);
        System.out.println(user);
    }

    @Test
    public void delete(){
        //判断是否存在
        boolean b = userRepository.existsById(5);
        if(b){
            //删除
            userRepository.deleteById(5);
        }

        //批删  需要先查询对象
        User user = userRepository.findById(7).get();
        List<User> arrayList = new ArrayList<>();
        arrayList.add(user);

        userRepository.deleteAll(arrayList);
    }

    @Test
    public void query(){
        //查询多条记录
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(6);
        Iterable<User> userList = userRepository.findAllById(arrayList);
        System.out.println(userList);
    }

    //排序
    @Test
    public void sort(){

        //Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        Sort sort = Sort.by(Sort.Direction.ASC, "username");
//        Iterable<User> all=userRepository.findAll(sort);
//        System.out.println(all);

        //多字段排序
        Sort.Order usernameOrder = Sort.Order.desc("username");
        Sort.Order idOrder = Sort.Order.desc("id");
        Sort sort1 = Sort.by(usernameOrder,idOrder);
        Iterable<User> all1 = userRepository.findAll(sort1);

        Iterator<User> it = all1.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void pageAndSorting(){

        //分页
        //  PageRequest构建分页对象      page开始的页数 从0开始（0代表第一页）
        Pageable pageable = PageRequest.of(0,2);
        Page<User> page = userRepository.findAll(pageable);
        System.out.println("总页数"+page.getTotalPages());
        System.out.println("总条数"+page.getTotalElements());
        System.out.println("分页数据"+page.getContent());

        //排序和分页
        Pageable pageable1 = PageRequest.of(0, 2, Sort.Direction.DESC,"username","id");
        Sort.Order usernameOrder = Sort.Order.desc("username");
        Sort.Order idOrder = Sort.Order.desc("id");
        Sort sort1 = Sort.by(usernameOrder, idOrder);
        PageRequest pageable2 = PageRequest.of(0, 2, sort1);
        Page<User> all = userRepository.findAll(pageable2);
        System.out.println(all);

    }

    @Test
    public void jpaRepoistroy(){
        User user= new User();
        user.setUsername("小四");
        //user.setPhone("");
        //Example是一个借口 它用静态方法来Example 按照Example指定条件来查询
        Example<User> example = Example.of(user);
        List<User> all = userRepository.findAll(example);
        //在example查询的基础上 还可以进行分页和排序
//        List<UserEntity> all = userRepository.findAll(example,pageable);
        System.out.println(all);
    }


    //自定义查询
    @Test
    public void selfQuery(){
        List<User> list = userRepository.findByUsername("小六");
        System.out.println(list);

        List<User> list1 = userRepository.findByUsernameLike("%".concat("小").concat("%"));
        for(User user :list1){
            System.out.println(user);
        }

        List<User> list3 = userRepository.findByUsernameLikeOrderByIdDesc("%".concat("小").concat("%"));
        for(User user :list3){
            System.err.println(user);

        }
        System.out.println("---------------------");
        Pageable pageable=PageRequest.of(0,2);
        Page<User> pageList = userRepository.findByUsername("小五", pageable);
        System.err.println(pageList);
    }

    //自定义删除
    @Test
    public void testSelfDelete(){
        int  i= userRepository.deleteByUsername("小四");
        System.out.println(i);

        int i1 = userRepository.deleteByPhoneIsNull();
        System.out.println(i1);
    }

    //注解方式
    @Test
    public void queryForSql(){
        List<User> list = userRepository.getByUsername("小五");
        System.out.println(list);

        List<User> list1 = userRepository.qureyByUsernameAndPassword("小六","123");
        System.out.println(list1);

        List<User> list2 = userRepository.selectByUsernameAndPassword("小六","123");
        System.err.println(list2);
    }

    @Test
    public void updateByQery(){
        int i = userRepository.updatePasswordById("6666", 6);
        System.out.println(i);
    }










}
