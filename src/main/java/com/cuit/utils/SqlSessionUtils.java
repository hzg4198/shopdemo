package com.cuit.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtils {
    private static SqlSessionFactory sessionFactory;
    private static SqlSession session; // 单例的SqlSession实例

    static {
        try {
            InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sessionFactory = sqlSessionFactoryBuilder.build(stream);

            // 初始化单例的SqlSession实例
            session = sessionFactory.openSession();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession getSession() {
        return session; // 返回单例的SqlSession实例
    }

    public static void close() {
        if (session != null) {
            session.close();
        }
    }
}
