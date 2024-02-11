package com.ls.lsliving.commodity.dao;

import com.ls.lsliving.commodity.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类表
 * 
 * @author liushuo
 * @email liushuo@2951@gmail.com
 * @date 2024-02-11 15:21:29
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
