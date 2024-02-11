package com.ls.lsliving.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ls.lsliving.commodity.entity.CategoryEntity;
import com.ls.lsliving.commodity.service.CategoryService;
import com.ls.common.utils.PageUtils;
import com.ls.common.utils.R;



/**
 * 商品分类表
 *
 * @author liushuo
 * @email liushuo@2951@gmail.com
 * @date 2024-02-11 15:21:29
 */
@RestController
@RequestMapping("commodity/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/treeList")
//    @RequiresPermissions("commodity:category:list")
    public R treeList(@RequestParam Map<String, Object> params){
        List<CategoryEntity> categoryEntities = categoryService.listTree();
        return R.ok().put("treeList", categoryEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:category:info")
    public R info(@PathVariable("id") Long id){
		CategoryEntity category = categoryService.getById(id);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:category:delete")
    public R delete(@RequestBody Long[] ids){
		categoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
