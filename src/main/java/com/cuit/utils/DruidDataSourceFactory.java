package com.cuit.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

// 要使用Druid作为连接池，我们必须要继承UnpooledDataSourceFactory
public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
    // 这个方法就是为了在我们要执行getDataSource()方法时，进行数据源初始化
    @Override
    public DataSource getDataSource() {
        try {
            ((DruidDataSource)this.dataSource).init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.dataSource;
    }

    public DruidDataSourceFactory() {
        this.dataSource = new DruidDataSource();
    }
}