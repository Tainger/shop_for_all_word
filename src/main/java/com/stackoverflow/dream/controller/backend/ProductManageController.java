package com.stackoverflow.dream.controller.backend;

import com.stackoverflow.dream.common.ServerResponse;
import com.stackoverflow.dream.component.HostHolder;
import com.stackoverflow.dream.pojo.Category;
import com.stackoverflow.dream.pojo.User;
import com.stackoverflow.dream.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author rocky
 * @ClassName ProductManageController
 * @Description
 * @Create by rocky
 * @Date 2020/10/17 上午9:04
 */

@RestController("manage/product")
public class ProductManageController {

    private static final Logger logger = LoggerFactory.getLogger(ProductManageController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private HostHolder hostHolder;

    @GetMapping("category/get/sub")
    public ServerResponse<List<Category>> getSubCategory(
            @RequestParam(defaultValue = "0" ,required = false, name = "categoryId") int categoryId){

        User user = hostHolder.getUser();
        System.out.println(user);
        return productService.getAllSubCategory(categoryId);
    }

    @PostMapping("category/add")
    public ServerResponse<String> addCategory(@RequestBody Category category) {
        return productService.addCategory(category);
    }

    @GetMapping("category/get")
    public ServerResponse<Category> getCategory(int categoryId) {
        if(null == hostHolder.getUser()){
            return ServerResponse.createBySuccessMessage("请登录！");
        }
        ServerResponse<Category> response = productService.selectByCategoryId(categoryId);
        if(response.isSuccess()){
            return response;
        }
        return ServerResponse.createBySuccessMessage("没有该分类！");
    }

    @PostMapping("category/update")
    public ServerResponse<String> updateCategory(@RequestBody Category category) {
        if(null == hostHolder.getUser()){
            return ServerResponse.createBySuccessMessage("请登录！");
        }
        return productService.updateCategory(category);
    }

}
