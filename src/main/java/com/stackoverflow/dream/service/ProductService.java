package com.stackoverflow.dream.service;

import com.stackoverflow.dream.common.ServerResponse;
import com.stackoverflow.dream.pojo.Category;

import java.util.List;

/**
 * @author rocky
 * @ClassName ProductService
 * @Description
 * @Create by rocky
 * @Date 2020/10/17 上午9:11
 */
public interface ProductService {

    /**
     * 获取所有的子分类
     * @param categoryId
     * @return
     */
    ServerResponse<List<Category>> getAllSubCategory(int categoryId);

    /**
     * 增加分类
     * @param category
     * @return
     */
    ServerResponse<String> addCategory(Category category);


    /**
     *
     * @param categoryId
     * @return
     */
    ServerResponse<Category> selectByCategoryId(int categoryId);


    /**
     *
     * @param category
     * @return
     */
    ServerResponse<String> updateCategory(Category category);
}
