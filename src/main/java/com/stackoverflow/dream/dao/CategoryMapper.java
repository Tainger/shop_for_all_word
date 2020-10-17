package com.stackoverflow.dream.dao;

import com.stackoverflow.dream.pojo.Category;

import java.util.List;

/**
 * @author rocky
 */
public interface CategoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);


    /**
     * 校验种类类别是否存在
     * @param categoryName
     * @return
     */
    int checkCategoryName(String categoryName);


    /**
     * 获取所有子分类
     * @param categoryId
     * @return
     */
    List<Category> getAllSubCategory(Integer categoryId);


}