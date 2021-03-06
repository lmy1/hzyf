package com.cd.zjyf.management.bean.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by li.mingyang on 2018/4/17.
 */

public class IsshDto {
    @ApiModelProperty("是否审核(0未审核1审核通过2审核未通过)")
    private String issh;
    @ApiModelProperty("未通过原因")
    private String wtgyy;

    public String getIssh() {
        return issh;
    }

    public void setIssh(String issh) {
        this.issh = issh;
    }

    public String getWtgyy() {
        return wtgyy;
    }

    public void setWtgyy(String wtgyy) {
        this.wtgyy = wtgyy;
    }
}
