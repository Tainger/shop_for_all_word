package com.stackoverflow.dream.service;

import com.stackoverflow.dream.common.ServerResponse;
import com.stackoverflow.dream.dao.CategoryMapper;
import com.stackoverflow.dream.dao.ProductMapper;
import com.stackoverflow.dream.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rocky
 * @ClassName ProductServiceImpl
 * @Description
 * @Create by rocky
 * @Date 2020/10/17 上午9:12
 */

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public ServerResponse<List<Category>> getAllSubCategory(int categoryId) {
        List<Category> categoryList = categoryMapper.getAllSubCategory(categoryId);
        return ServerResponse.createBySuccess(categoryList);
    }

    @Override
    public ServerResponse<String> addCategory(Category category) {
        String name = category.getName();
        int res = categoryMapper.checkCategoryName(name);
        if(res >= 1){
            return ServerResponse.createByErrorMessage("种类已经存在");
        }
        categoryMapper.insert(category);
        return ServerResponse.createBySuccessMessage("插入成功");
    }



    @Override
    public ServerResponse<Category> selectByCategoryId(int categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(null == category){
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(category);
    }

    @Override
    public ServerResponse<String> updateCategory(Category category) {

        int res = categoryMapper.updateByPrimaryKeySelective(category);
        if( res >= 1){
            return ServerResponse.createBySuccess("更新成功");
        }
        return ServerResponse.createByError();
    }
}
