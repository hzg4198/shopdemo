package com.cuit.entity;

import lombok.Data;

import java.util.List;
@Data
public class PageBean {
    private int pageNumber;   //当前页 （浏览器传递过来)
    private int pageSize; //每页显示个数 （固定值，也可以从浏览器传递过来)
    private int totalRecord;//总记录数 数据库查询来的
    private int totalPage;//总分页数(算法 计算获得 :totalPage=(totalRecord+pageSize-1)/pageSize
    private int startIndex;//开始索引（算法：计算获取 startIndex=(pageNumber-1)*pageSize
    private List<Product> data;//分页数据(数据库查询)
}
