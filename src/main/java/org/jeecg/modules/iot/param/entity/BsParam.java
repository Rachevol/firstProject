package org.jeecg.modules.iot.param.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 系统参数设置
 * @Author: jeecg-boot
 * @Date:   2024-09-18
 * @Version: V1.0
 */
@Data
@TableName("bs_param")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bs_param对象", description="系统参数设置")
public class BsParam implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
    private String id;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
    private String paramName;
	/**参数类型（1-图片 2-文本）*/
	@Excel(name = "参数类型（1-图片 2-文本）", width = 15)
    @ApiModelProperty(value = "参数类型（1-图片 2-文本）")
    private Integer paramType;
	/**参数编码*/
	@Excel(name = "参数编码", width = 15)
    @ApiModelProperty(value = "参数编码")
    private String paramCode;
	/**参数值*/
	@Excel(name = "参数值", width = 15)
    @ApiModelProperty(value = "参数值")
    private String paramValue;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String content;
	/**状态 1-正常 0-禁用*/
	@Excel(name = "状态 1-正常 0-禁用", width = 15)
    @ApiModelProperty(value = "状态 1-正常 0-禁用")
    private Integer status;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
