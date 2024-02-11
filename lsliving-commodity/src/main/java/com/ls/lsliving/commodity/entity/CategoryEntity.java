package com.ls.lsliving.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;


import lombok.Data;

/**
 * 商品分类表
 * 
 * @author liushuo
 * @email liushuo@2951@gmail.com
 * @date 2024-02-11 15:21:29
 */
@Data
@TableName("commodity_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父分类 id
	 */
	private Long parentId;
	/**
	 * 层级
	 */
	private Integer catLevel;
	/**
	 * 0 不显示，1 显示]
	 */
	private Integer isShow;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 统计单位
	 */
	private String proUnit;
	/**
	 * 商品数量
	 */
	private Integer proCount;


	//增加一个属性 childrenCategories
	//该字段不对应表中的字段，需要加上@TableField注解
	@TableField(exist = false)
	private List<CategoryEntity> childrenCategories;

}
