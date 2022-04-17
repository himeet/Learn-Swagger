package com.glj.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Api
@ApiModel("用户实体类")
public class User {

    @ApiModelProperty("用户名")
    public String userName;

    @ApiModelProperty("密码")
    public String password;

}
