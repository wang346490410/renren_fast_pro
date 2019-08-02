package io.renren.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.AvoidRepeatableCommit;
import io.renren.common.annotation.SysLog;
import io.renren.common.dto.XlsxView;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.GoodsEntity;
import io.renren.modules.sys.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


/**
 * 商品管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-03 10:56:57
 */
@RestController
@RequestMapping("generator/goods")
@Api("商品信息接口")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:goods:list")
    @ApiOperation("商品列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{goodsId}")
    @RequiresPermissions("generator:goods:info")
    public R info(@PathVariable("goodsId") Long goodsId){
			GoodsEntity goods = goodsService.selectById(goodsId);

        return R.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @AvoidRepeatableCommit
    @RequestMapping("/save")
    @RequiresPermissions("generator:goods:save")
    public R save(@RequestBody GoodsEntity goods){
			goodsService.insert(goods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:goods:update")
    public R update(@RequestBody GoodsEntity goods){
			goodsService.updateById(goods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:goods:delete")
    public R delete(@RequestBody Long[] goodsIds){
			goodsService.deleteBatchIds(Arrays.asList(goodsIds));
        return R.ok();
    }

    /**
     * 导出
     */
    @RequestMapping("/export")
    @RequiresPermissions("generator:goods:delete")
    @SysLog("导出数据")
    public ModelAndView export(){
        Map<String, Object> map = null;
        List<GoodsEntity> dataList = goodsService.selectList(new EntityWrapper<GoodsEntity>()
        );
        List<Map<String,Object>> mapList = new ArrayList<>();
        map = new HashMap<>();
        map.put("index","name");
        map.put("type",SXSSFCell.CELL_TYPE_STRING);
        map.put("name","商品名");
        mapList.add(map);

        map = new HashMap<>();
        map.put("index","intro");
        map.put("type", SXSSFCell.CELL_TYPE_STRING);
        map.put("name","介绍");
        mapList.add(map);

        map = new HashMap<>();
        map.put("index","price");
        map.put("type",SXSSFCell.CELL_TYPE_STRING);
        map.put("name","价格");
        mapList.add(map);

        map = new HashMap<>();
        map.put("index","num");
        map.put("type",SXSSFCell.CELL_TYPE_STRING);
        map.put("name","数量");
        mapList.add(map);

        return new ModelAndView(new XlsxView("商品信息数据导出",mapList,dataList));
    }

}
