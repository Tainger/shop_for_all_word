package com.stackoverflow.dream.dao;

import com.stackoverflow.dream.pojo.Category;
import com.stackoverflow.dream.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author rocky
 */

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}