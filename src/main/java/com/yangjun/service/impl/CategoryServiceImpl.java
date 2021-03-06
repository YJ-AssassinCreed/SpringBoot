package com.yangjun.service.impl;

import com.yangjun.common.ServerResponse;
import com.yangjun.dao.CategoryMapper;
import com.yangjun.pojo.Category;
import com.yangjun.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author yangjun
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService{
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ServerResponse addCategory(String categoryName,Integer parentId){
        if(parentId!=null && StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        //这个分类是可用的
        category.setStatus(true);
        int rowCount = categoryMapper.insert(category);
        if(rowCount>0){
            return  ServerResponse.createBySuccessMessage("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }
    @Override
    public ServerResponse updateCategoryName(Integer categoryId,String categoryName){
        if(categoryId!=null && StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount>0){
            return  ServerResponse.createBySuccessMessage("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");
    }
    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId){
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParaentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)){
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * 递归查询本节点的id以及孩子节点的id
     * @param categoryId
     * @return
     */
    @Override
    public ServerResponse selectCategoryAndChildrenById(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null ){
            for(Category categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }
    //递归算法，算出子节点
    private Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //查找子节点，递归算法要有一个退出条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParaentId(categoryId);
        if(categoryList!=null){
            for(Category categoryItem : categoryList){
                findChildCategory(categorySet,categoryItem.getId());
            }
        }
        return categorySet;
    }
}
