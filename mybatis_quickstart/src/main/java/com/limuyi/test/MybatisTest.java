package com.limuyi.test;

import com.limuyi.domain.User;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    /*
    快速入门
    * */
    @Test
    public void mybatisQuickStart() throws IOException {
        // 1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");  //Resources是org.apache.ibatis.io中的
        // 2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.执行sql
        List<User> users = sqlSession.selectList("userMapper.findAll");
        // 5.打印结果
        for (User user:users) {
            System.out.println(user);
        }
        // 6.释放资源
        sqlSession.close();
    }

    @Test
    public void testSave() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("jack");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京海淀");
        sqlSession.insert("userMapper.saveUser",user);
        //手动提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    //更新
    @Test
    public void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("luck");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("天津西青");
        sqlSession.update("userMapper.updateUser",user);
        //手动提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    //删除
    @Test
    public void testDelete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("userMapper.deleteUser",3);
        //手动提交事务
        sqlSession.commit();
        sqlSession.close();
    }

}
