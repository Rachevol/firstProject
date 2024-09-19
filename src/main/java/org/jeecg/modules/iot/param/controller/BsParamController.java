package org.jeecg.modules.iot.param.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.iot.param.entity.BsParam;
import org.jeecg.modules.iot.param.service.IBsParamService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 系统参数设置
 * @Author: jeecg-boot
 * @Date:   2024-09-18
 * @Version: V1.0
 */
@Api(tags="系统参数设置")
@RestController
@RequestMapping("/param/bsParam")
@Slf4j
public class BsParamController extends JeecgController<BsParam, IBsParamService> {
	@Autowired
	private IBsParamService bsParamService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bsParam
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "系统参数设置-分页列表查询")
	@ApiOperation(value="系统参数设置-分页列表查询", notes="系统参数设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BsParam bsParam,
								   @RequestParam(name = "pageNo", defaultValue = "1") Integer
										   pageNo,
								   @RequestParam(name = "pageSize", defaultValue = "10")
								   Integer pageSize,
								   @RequestParam(name = "paramNameKey", required = false)
								   String paramNameKey,
								   @RequestParam(name = "paramCodeKey", required = false)
								   String paramCodeKey,
								   HttpServletRequest req) {
		QueryWrapper<BsParam> queryWrapper =
				QueryGenerator.initQueryWrapper(bsParam, req.getParameterMap());
		// 参数名称模糊查询
		if (StringUtils.isNotBlank(bsParam.getParamName())) {
			queryWrapper.like("param_name", bsParam.getParamName());
		}
		// 参数编码模糊查询
		if (StringUtils.isNotBlank(bsParam.getParamCode())) {
			queryWrapper.like("param_code", bsParam.getParamCode());
		}
		Page<BsParam> page = new Page<BsParam>(pageNo, pageSize);
		IPage<BsParam> pageList = bsParamService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 /**
	 *   添加
	 *
	 * @param bsParam
	 * @return
	 */
	@AutoLog(value = "系统参数设置-添加")
	@ApiOperation(value="系统参数设置-添加", notes="系统参数设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BsParam bsParam) {
		bsParamService.save(bsParam);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param bsParam
	 * @return
	 */
	@AutoLog(value = "系统参数设置-编辑")
	@ApiOperation(value="系统参数设置-编辑", notes="系统参数设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BsParam bsParam) {
		bsParamService.updateById(bsParam);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统参数设置-通过id删除")
	@ApiOperation(value="系统参数设置-通过id删除", notes="系统参数设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bsParamService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "系统参数设置-批量删除")
	@ApiOperation(value="系统参数设置-批量删除", notes="系统参数设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bsParamService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统参数设置-通过id查询")
	@ApiOperation(value="系统参数设置-通过id查询", notes="系统参数设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BsParam bsParam = bsParamService.getById(id);
		if(bsParam==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(bsParam);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bsParam
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BsParam bsParam) {
        return super.exportXls(request, bsParam, BsParam.class, "系统参数设置");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BsParam.class);
    }

}
