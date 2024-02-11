package com.ls.lsliving.commodity.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.common.utils.PageUtils;
import com.ls.common.utils.Query;

import com.ls.lsliving.commodity.dao.CategoryDao;
import com.ls.lsliving.commodity.entity.CategoryEntity;
import com.ls.lsliving.commodity.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    //返回tree
    //使用java 8 的流式计算 + 递归操作
    @Override
    public List<CategoryEntity> listTree() {
        //1.查处所有数据
        //继承了 CategoryServiceImpl extends ServiceImpl ServiceImpl实现了IService
        //public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> M 继承BaseMapper ，自动装配了M
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        //2.转为tree
        //2.1 过滤，返回一级
        //2.2 map映射，给每个分类设置对应的子分类
        //2.3 排序
        //2.4 收集
        //2.5 返回
        //filter -> map -> sorted
        //filter不会影响原始数据，map会
        List<CategoryEntity> categoryTrees = categoryEntities.stream().filter(categoryEntity -> {
            return categoryEntity.getParentId() == 0;
        }).map(category -> {
            category.setChildrenCategories(getChildrenCategories(category,categoryEntities));
            return category;
        }).sorted((category1,category2) -> {
            return category1.getSort() == null ? 0 : category1.getSort() - category2.getSort() == 0 ? 0 : category2.getSort();
        }).collect(Collectors.toList());
        return categoryTrees;
    }

    //递归查询所有分类的子分类
    //把
    private List<CategoryEntity> getChildrenCategories(CategoryEntity root,List<CategoryEntity>all) {
        List<CategoryEntity> collect = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentId() == root.getId();
        }).map(categoryEntity -> {
            //找到子分类
            categoryEntity.setChildrenCategories(getChildrenCategories(categoryEntity, all));
            return categoryEntity;
        }).sorted((category1, category2) -> {
            return category1.getSort() == null ? 0 : category1.getSort() - category2.getSort() == 0 ? 0 : category2.getSort();
        }).collect(Collectors.toList());
        return collect;
    }



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

}