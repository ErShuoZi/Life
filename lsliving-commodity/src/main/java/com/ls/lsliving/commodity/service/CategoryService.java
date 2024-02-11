package com.ls.lsliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.common.utils.PageUtils;
import com.ls.lsliving.commodity.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 *
 * @author liushuo
 * @email liushuo@2951@gmail.com
 * @date 2024-02-11 15:21:29
 */
public interface CategoryService extends IService<CategoryEntity> {
    //返回所有分类及其子分类(带有层级关系-> tree)
    List<CategoryEntity> listTree();
    PageUtils queryPage(Map<String, Object> params);
}

