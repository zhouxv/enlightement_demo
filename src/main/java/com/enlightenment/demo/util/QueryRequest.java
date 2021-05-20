package com.enlightenment.demo.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "QueryRequest", description = "分页")
public class QueryRequest {

    @ApiModelProperty(value = "页面数据量")
    private int pageSize = 10;

    @ApiModelProperty(value = "当前页码")
    private int pageNum = 1;

    @ApiModelProperty(value = "总页数")
    private int pageTotal;

    @ApiModelProperty(value = "排序字段", hidden = true)
    private String field;

    @ApiModelProperty(value = "排序规则，asc升序，desc降序", hidden = true)
    private String order;
}
